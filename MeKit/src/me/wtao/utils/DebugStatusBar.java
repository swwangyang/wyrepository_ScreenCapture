package me.wtao.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import me.wtao.view.FloatingView;

public class DebugStatusBar extends FloatingView {
	/**
	 * {@value #STATUS_PREFIX_ERROR}
	 */
	public static final String STATUS_PREFIX_ERROR = "ERROR";
	/**
	 * {@value #STATUS_PREFIX_WARN}
	 */
	public static final String STATUS_PREFIX_WARN = "WARN";
	/**
	 * {@value #STATUS_PREFIX_UPDATE}
	 */
	public static final String STATUS_PREFIX_UPDATE = "UPDATE";
	/**
	 * {@value #STATUS_PREFIX_DEBUG}
	 */
	public static final String STATUS_PREFIX_DEBUG = "DEBUG";
	/**
	 * {@value #STATUS_PREFIX_VERBOSE}
	 */
	public static final String STATUS_PREFIX_VERBOSE = "VERBOSE";	
	/**
	 * @see #STATUS_PREFIX_ERROR
	 */
	public static final String E = STATUS_PREFIX_ERROR;
	/**
	 * @see #STATUS_PREFIX_WARN
	 */
	public static final String W = STATUS_PREFIX_WARN;
	/**
	 * @see #STATUS_PREFIX_UPDATE
	 */
	public static final String I = STATUS_PREFIX_UPDATE;
	/**
	 * @see #STATUS_PREFIX_DEBUG
	 */
	public static final String D = STATUS_PREFIX_DEBUG;
	/**
	 * @see #STATUS_PREFIX_VERBOSE
	 */
	public static final String V = STATUS_PREFIX_VERBOSE;
	
	private static final int LENGTH_E = E.length();
	private static final int LENGTH_W = W.length();
	private static final int LENGTH_I = I.length();
	private static final int LENGTH_D = D.length();
	private static final int LENGTH_V = V.length();	
	
	private final TextView mContentView;
	private boolean mHightlight = true;
	
	public DebugStatusBar(Context context) {
		super(context);
		
		mContentView = new TextView(getContext());
		mContentView.setGravity(Gravity.CENTER_VERTICAL);
		mContentView.setTextSize(8.f);
		mContentView.setTextColor(Color.BLACK);
		
		addView(mContentView);
		
		int bk = Color.parseColor("#7fffffff"); // semi-transparent white color
		setBackgroundColor(bk);
	}
	
	public void setHightlightOn() {
		mHightlight = true;
	}

	public void setHightlightOff() {
		mHightlight = false;
	}
	
	public void updateDebugStatus(String... info) {
		StringBuilder sb = new StringBuilder();
		if (mHightlight) {
			for (String seg : info) {
				if (seg.startsWith(E)) {
					sb.append(seg.substring(seg.indexOf(E) + LENGTH_E));
				} else if (seg.startsWith(W)) {
					sb.append(seg.substring(seg.indexOf(W) + LENGTH_W));
				} else if (seg.startsWith(I)) {
					sb.append(seg.substring(seg.indexOf(I) + LENGTH_I));
				} else if (seg.startsWith(D)) {
					sb.append(seg.substring(seg.indexOf(D) + LENGTH_D));
				} else if (seg.startsWith(V)) {
					sb.append(seg.substring(seg.indexOf(V) + LENGTH_V));
				} else {
					sb.append(seg);
				}
			}
		} else {
			// highlight disable
			for (String seg : info) {
				sb.append(seg);
			}
			mContentView.setText(sb.toString());
		}
	}
	
	@Override
	protected void onInitializeWindowLayoutParams() {
		super.onInitializeWindowLayoutParams();
		
		mWindowParams.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		mWindowParams.flags &= ~WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
		
		if(!ScreenMetrics.hasMessured()) {
			ScreenMetrics metrics = new ScreenMetrics(getContext());
			metrics.requestMessure();
		}
		
		final boolean upperHalf = false;
		final int height = ScreenMetrics.getMeasuredStatusBarHeight() / 2;
		
		mWindowParams.x = 0;
		mWindowParams.y = (upperHalf ? 0 : (height + 1));
		mWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
		mWindowParams.height = height;
	}

}
