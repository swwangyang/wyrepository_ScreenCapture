package me.wtao.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public abstract class FloatingView extends RelativeLayout implements
		FloatingViewComp {
	protected static WindowManager sWindowManager = null;
	protected WindowManager.LayoutParams mWindowParams;

	private boolean mDidAdd = false;

	/**
	 * Simple constructor to use when creating a view from code.
	 * 
	 * @param context
	 *            The Context the view is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @see #FloatingView(Context, AttributeSet)
	 */
	public FloatingView(Context context) {
		this(context, null);
	}

	/**
	 * 
	 * @param context
	 *            The Context the view is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param attrs
	 *            The attributes of the XML tag that is inflating the view. May
	 *            be null.
	 * @see #FloatingView(Context, AttributeSet, int)
	 */
	public FloatingView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * @param context
	 *            The Context the view is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param attrs
	 *            The attributes of the XML tag that is inflating the view. May
	 *            be null.
	 * @param defStyle
	 *            The default style to apply to this view. If 0, no style will
	 *            be applied (beyond what is included in the theme). This may
	 *            either be an attribute resource, whose value will be retrieved
	 *            from the current theme, or an explicit style resource.
	 * @see android.view.View#View(Context, AttributeSet)
	 */
	public FloatingView(Context context, AttributeSet attrs, int defStyle) {
		super(context.getApplicationContext(), attrs, defStyle);

		if (sWindowManager == null) {
			synchronized (this) {
				if (sWindowManager == null) {
					sWindowManager = (WindowManager) getContext()
							.getSystemService(Context.WINDOW_SERVICE);
				}
			}
		}
	}

	/**
	 * SHOULD always invoke before any other operations for safety. it's
	 * recommenced to initialize {@link WindowManager.LayoutParams} in
	 * {@link #onInitializeWindowLayoutParams()}; if you need update the
	 * LayoutParams somewhere else, be careful not to modify the
	 * {@link WindowManager.LayoutParams#type} after invoking attachedToWindow.
	 */
	public void attachedToWindow() {
		if (!mDidAdd) {
			onInitializeWindowLayoutParams();

			setVisibility(View.GONE);
			sWindowManager.addView(this, mWindowParams);

			mDidAdd = true;
		}
	}
	
	public boolean hasAttachedToWindow() {
		return mDidAdd;
	}

	/**
	 * show the view
	 */
	public void show() {
		sWindowManager.updateViewLayout(this, mWindowParams);
		setVisibility(View.VISIBLE);
	}

	/**
	 * hide the view if needed, invoke {@link #show()} to show again
	 */
	public void hide() {
		setVisibility(View.GONE);
		sWindowManager.updateViewLayout(this, mWindowParams);
	}

	/**
	 * remove the view from {@link WindowManager}, and subclass can release
	 * associated references or resources here.
	 */
	public void dismiss() {
		if (mDidAdd) {
			setVisibility(GONE);
			sWindowManager.removeView(this);
			mDidAdd = false;
		}
	}

	protected View setContentView(int layoutId) {
		View contentView = LayoutInflater.from(getContext()).inflate(layoutId,
				null);

		// attach the view
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		addView(contentView, rlp);

		return contentView;
	}

	/**
	 * super.onInitializeWindowLayoutParams() MUST before initializing other
	 * window layout params, defualt window type is
	 * {@link WindowManager.LayoutParams#TYPE_SYSTEM_OVERLAY}.
	 * 
	 * @see PixelFormat#TRANSLUCENT
	 * @see WindowManager.LayoutParams#FLAG_LAYOUT_INSET_DECOR
	 * @see WindowManager.LayoutParams#FLAG_LAYOUT_NO_LIMITS
	 */
	protected void onInitializeWindowLayoutParams() {
		mWindowParams = new WindowManager.LayoutParams();

		mWindowParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
		mWindowParams.format = PixelFormat.TRANSLUCENT;
		mWindowParams.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
				| WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;

		mWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
		mWindowParams.x = 0;
		mWindowParams.y = 0;
		mWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
		mWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
	}
}
