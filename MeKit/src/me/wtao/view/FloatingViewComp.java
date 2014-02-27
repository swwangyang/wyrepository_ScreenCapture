package me.wtao.view;

import android.view.WindowManager;

public interface FloatingViewComp {
	/**
	 * SHOULD always invoke before any other operations for safety. it's
	 * recommenced to initialize {@link WindowManager.LayoutParams} in
	 * {@link #onInitializeWindowLayoutParams()}; if you need update the
	 * LayoutParams somewhere else, be careful not to modify the
	 * {@link WindowManager.LayoutParams#type} after invoking attachedToWindow.
	 */
	public void attachedToWindow();

	/**
	 * show the view
	 */
	public void show();

	/**
	 * hide the view if needed, invoke {@link #show()} to show again
	 */
	public void hide();

	/**
	 * remove the view from {@link WindowManager}, and subclass can release
	 * associated references or resources here.
	 */
	public void dismiss();
}
