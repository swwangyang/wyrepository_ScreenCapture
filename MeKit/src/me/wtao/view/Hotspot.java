package me.wtao.view;

import me.wtao.utils.ScreenMetrics;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Hotspot extends FloatingView {
	// been supported so far
	public static final int EDGE_TOP = 0x0001;
	public static final int EDGE_BOTTOM = 0x0002;
	public static final int EDGE_LEFT = 0x0004;
	public static final int EDGE_RIGHT = 0x0008;

	// TODO: more self-define position
	public static final int CORNER_TOP_LEFT = EDGE_TOP | EDGE_LEFT;
	public static final int CORNER_TOP_RIGHT = EDGE_TOP | EDGE_RIGHT;
	public static final int CORNER_BOTTOM_LEFT = EDGE_BOTTOM | EDGE_LEFT;
	public static final int CORNER_BOTTOM_RIGHT = EDGE_BOTTOM | EDGE_RIGHT;

	public static final int UPPER_HALF = 0x0100;
	public static final int LOWER_HALF = 0x0200;
	public static final int FULL_EDGE = UPPER_HALF | LOWER_HALF;

	public static interface OnHotspotListener {
		public int getHotspotGravity();

		public boolean dispatchTouchEvent(MotionEvent event);
	}

	public static interface OnInterceptTouchEventListener {
		public boolean shouldInterruptTouchEvent();
	}

	private final OnSoftInputChangedReceiver mOnSoftInputChangedReceiver;

	private boolean mPhysicalScreenMode;
	private int mOffset;
	private ScreenMetrics mScreenMetrics; // reserved
	
	private OnHotspotListener mOnHotspotListener;
	private OnInterceptTouchEventListener mOnInterceptTouchEventListener;

	public Hotspot(Context context) {
		this(context, null);
	}
	
	public Hotspot(Context context, OnSoftInputChangedReceiver receiver) {
		super(context);
		
		mPhysicalScreenMode = false;
		mOffset = 0;
		// MUST call before anywhere using display metrics, such as system
		// status bar height, etc.
		mScreenMetrics = new ScreenMetrics(context);
		
		if(receiver == null) {
			mOnSoftInputChangedReceiver = new OnSoftInputChangedReceiver() {
	
				@Override
				public boolean onReLayout(int imeTopPosition) {
					Hotspot.this.setBottom(imeTopPosition);
					Hotspot.this.requestLayout();
					return true;
				}
			};
		} else {
			mOnSoftInputChangedReceiver = receiver;
		}

		attachedToWindow();
	}

	public OnSoftInputChangedReceiver getOnSoftInputChangedReceiver() {
		return mOnSoftInputChangedReceiver;
	}

	public void setOnHotspotListener(OnHotspotListener listener) {
		mOnHotspotListener = listener;

		if (mOnHotspotListener != null) {
			final int mask = listener.getHotspotGravity();
			setGravity(mask);
		}
	}

	public void setOnInterceptTouchEventListener(
			OnInterceptTouchEventListener listener) {
		mOnInterceptTouchEventListener = listener;
	}

	public void setGravity(final int mask) {
		switch (mask) {
		case EDGE_TOP:
			mWindowParams.gravity = Gravity.TOP | Gravity.LEFT;
			break;

		case EDGE_BOTTOM:
			mWindowParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
			break;

		case EDGE_LEFT:
			mWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
			break;

		case EDGE_RIGHT:
			mWindowParams.gravity = Gravity.RIGHT | Gravity.TOP;
			break;
		}

		sWindowManager.updateViewLayout(this, mWindowParams);
	}
	
	/**
	 * Default mode and just measure the display region; if the system UI bar is
	 * present, it'll ignore its height. In Android 4.0 or later, the navigation
	 * bar is present when the devices don't have the traditional hardware keys,
	 * and always present in tablets.
	 * 
	 * @see #NAVIGATION_BAR_HEIGHT
	 * @see #NAVIGATION_BAR_HEIGHT_LANDSCAPE
	 * @see #SYSTEM_BAR_HEIGHT
	 */
	public void setDefaultDisplayMode() {
		mPhysicalScreenMode = false;
		mOffset = 0;
	}

	/**
	 * Will measure the real physical screen, ignore its decorations, such as
	 * navigation bar.
	 */
	public void setPhysicalScreenMode() {
		mPhysicalScreenMode = true;
		
		if((mWindowParams.gravity & Gravity.BOTTOM) == Gravity.BOTTOM){
			mScreenMetrics.requestMessure();
			mOffset = ScreenMetrics.getMeasuredScrrenHeight()
					- ScreenMetrics.getResolutionY();
		}
	}

	public void setHotspotWidth(final int width) {
		switch(width) {
		case WindowManager.LayoutParams.MATCH_PARENT:
			mWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
			break;
		case WindowManager.LayoutParams.WRAP_CONTENT:
			mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
			break;
		default:
			mWindowParams.width = ((width < 0) ? ScreenMetrics
					.getMeasuredStatusBarHeight() : width);
		}
		sWindowManager.updateViewLayout(this, mWindowParams);
	}

	public void setHotspotHeight(final int height) {
		final int screenHeight = ScreenMetrics.getMeasuredScrrenHeight();

		switch (height) {
		case WindowManager.LayoutParams.MATCH_PARENT:
			mWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
			break;
		case WindowManager.LayoutParams.WRAP_CONTENT:
			mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
			break;
		case UPPER_HALF:
			mWindowParams.y = 0;
			mWindowParams.height = screenHeight / 2;
			break;

		case LOWER_HALF:
			mWindowParams.y = screenHeight / 2;
			mWindowParams.height = screenHeight / 2;
			break;

		case FULL_EDGE:
			mWindowParams.y = 0;
			mWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
			break;

		default:
			mWindowParams.height = ((height < 0) ? screenHeight : height);
			mWindowParams.y += mOffset;
		}

		sWindowManager.updateViewLayout(this, mWindowParams);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean interrupted = false;
		if (mOnInterceptTouchEventListener != null) {
			interrupted = mOnInterceptTouchEventListener
					.shouldInterruptTouchEvent();
		}

		if (mOnHotspotListener != null && !interrupted) {
			return mOnHotspotListener.dispatchTouchEvent(ev);
		} else {
			return super.dispatchTouchEvent(ev);
		}
	}

	@Override
	public void show() {
		setVisibility(View.VISIBLE);
		mWindowParams.flags &= ~WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
		sWindowManager.updateViewLayout(this, mWindowParams);
	}

	@Override
	public void hide() {
		setVisibility(View.INVISIBLE);
		mWindowParams.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
		sWindowManager.updateViewLayout(this, mWindowParams);
	}

	/**
	 * always set to be touchable.
	 * 
	 * @see WindowManager.LayoutParams#FLAG_NOT_FOCUSABLE
	 */
	protected void onInitializeWindowLayoutParams() {
		super.onInitializeWindowLayoutParams();
		
		mWindowParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
		
		mWindowParams.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		mWindowParams.flags &= ~WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
		
		mWindowParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		mWindowParams.flags &= ~WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

		mWindowParams.width = ScreenMetrics.getMeasuredStatusBarHeight();
		mWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
	}
}
