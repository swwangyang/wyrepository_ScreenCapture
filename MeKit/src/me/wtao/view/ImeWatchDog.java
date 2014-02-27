package me.wtao.view;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.WindowManager;

public class ImeWatchDog extends FloatingView {
	private static String ACTION = "windowSoftInputMode.adjectResize";
	
	private static ImeWatchDog sWatchDog = null;

	private boolean mObservable;
	private LocalBroadcastManager mLocalBroadcastManager;
	
	public static ImeWatchDog getImeWatchDog(Context context) {
		if(sWatchDog == null) {
			synchronized (ImeWatchDog.class) {
				if(sWatchDog == null) {
					sWatchDog = new ImeWatchDog(context);
					sWatchDog.attachedToWindow();
				}
			}
		}
		
		return sWatchDog;
	}

	private ImeWatchDog(Context context) {
		super(context);

		mLocalBroadcastManager = LocalBroadcastManager
				.getInstance(getContext());
		mObservable = false;
	}

	public void registerOnSoftInputChangedReceiver(
			OnSoftInputChangedReceiver receiver, IntentFilter filter) {
		if(!mObservable) {
			attachedToWindow();
			show();
		}
		
		if (filter == null) {
			filter = new IntentFilter();
		}
		filter.addAction(ACTION);
		mLocalBroadcastManager.registerReceiver(receiver, filter);
	}

	public void unregisterOnSoftInputChangedReceiver(
			OnSoftInputChangedReceiver receiver) {
		mLocalBroadcastManager.unregisterReceiver(receiver);
	}
	
	@Override
	public void attachedToWindow() {
		super.attachedToWindow();
		mObservable = true;
	}
	
	@Override
	public void dismiss() {
		mObservable = false;
		super.dismiss();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (mObservable && changed) {
//			final int screenHeight = ScreenMetrics.getMeasuredScrrenHeight();
//			final int height = getTop() + getBottom();
//			final int offset = (screenHeight == height) ? 0 : ScreenMetrics.getMeasuredStatusBarHeight();
//			
//			boardcastSoftInputChanged(b + offset); // FLAG_LAYOUT_INSET_DECOR
			boardcastSoftInputChanged(b);	// FLAG_LAYOUT_IN_SCREEN
		}

		super.onLayout(changed, l, t, r, b);
	}

	/**
	 * also set WindowManager.LayoutParams width as 1 px.
	 * 
	 * @see WindowManager.LayoutParams#FLAG_NOT_FOCUSABLE
	 * @see WindowManager.LayoutParams#FLAG_ALT_FOCUSABLE_IM
	 * @see WindowManager.LayoutParams#FLAG_NOT_TOUCHABLE
	 */
	protected void onInitializeWindowLayoutParams() {
		super.onInitializeWindowLayoutParams();
		
		// on top of screen
		mWindowParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
		
		// full screen
		mWindowParams.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		mWindowParams.flags &= ~WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
		
		// never focus or touch
		mWindowParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
				| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

		mWindowParams.width = 1; // px
		mWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT; // auto changed
	}

	private void boardcastSoftInputChanged(final int position) {
		Intent intent = new Intent();
		intent.setAction(ACTION);
		intent.putExtra(OnSoftInputChangedReceiver.IME_TOP_POSITION, position);
		mLocalBroadcastManager.sendBroadcast(intent);
	}

}
