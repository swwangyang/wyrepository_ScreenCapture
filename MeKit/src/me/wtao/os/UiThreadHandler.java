package me.wtao.os;

import android.os.Handler;
import android.os.Looper;

public class UiThreadHandler {
	private final Thread mUiThread;
	private final Handler mHandler;

	public UiThreadHandler() {
		Looper main = Looper.getMainLooper();
		mUiThread = main.getThread();
		mHandler = new Handler(main);
	}

	/**
	 * Runs the specified action on the UI thread. If the current thread is the
	 * UI thread, then the action is executed immediately. If the current thread
	 * is not the UI thread, the action is posted to the event queue of the UI
	 * thread.
	 * 
	 * @param action
	 *            the action to run on the UI thread
	 */
	public final void runOnUiThread(Runnable action) {
		if (Thread.currentThread() != mUiThread) {
			mHandler.post(action);
		} else {
			action.run();
		}
	}
}
