package me.wtao.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

public class ScreenMetrics {
	/**
	 * default screen orientation
	 * 
	 * @see Surface#ROTATION_0
	 */
	public static final int NO_ROTATION = Surface.ROTATION_0;
	/**
	 * rotated 90 degrees clockwise
	 * 
	 * @see Surface#ROTATION_90
	 */
	public static final int ROTATION_90 = Surface.ROTATION_90;
	/**
	 * up-down screen orientation
	 * 
	 * @see Surface#ROTATION_180
	 */
	public static final int ROTATION_180 = Surface.ROTATION_180;
	/**
	 * rotated 90 degrees counter-clockwise
	 * 
	 * @see Surface#ROTATION_270
	 */
	public static final int ROTATION_270 = Surface.ROTATION_270;
	
	// NEVER modify the static final String below unless you known what you have
	// done. see more on
	// <sdk-path>/platforms/android-<sdk_int>/data/res/values/dimens.xml
	/**
	 * Height of the status bar.
	 */
	private static final String STATUS_BAR_HEIGHT = "status_bar_height";
	/**
	 * Height of the bottom navigation / system bar.
	 */
	private static final String NAVIGATION_BAR_HEIGHT = "navigation_bar_height";
	/**
	 * Height of the bottom navigation bar in landscape; often the same as
	 * {@link #NAVIGATION_BAR_HEIGHT}.
	 */
	private static final String NAVIGATION_BAR_HEIGHT_LANDSCAPE = "navigation_bar_height_landscape";
	/**
	 * Height of the system bar (combined status & navigation); used by SystemUI
	 * internally, not respected by the window manager.
	 * 
	 * @see ScreenMetrics#NAVIGATION_BAR_HEIGHT
	 */
	private static final String SYSTEM_BAR_HEIGHT = STATUS_BAR_HEIGHT;
	/**
	 * Default height of an action bar. Caution not system status bar of screen
	 * top, and NEVER modify unless you know what you do.
	 */
	private static final String ACTION_BAR_DEFAULT_HEIGHT = "action_bar_default_height";
	// END

	/**
	 * double check Locking
	 * 
	 * @see #messure()
	 * @see #requestMessure()
	 * @see #accessCheck()
	 */
	private static Boolean sScreenMeasured = false;

	private static float sScreenDensity;
	private static int sScreenWidth;
	private static int sScrrenHeight;
	private static int sStatusBarHeight;
	private static int sNavigationBarHeight;
	private static int sAppActionBarDefaultHeight;

	private Context mContext;
	private boolean mPhysicalScreenMode;

	public ScreenMetrics(Context context) {
		mContext = context;
		mPhysicalScreenMode = false;
		messure();
	}

	public ScreenMetrics(Application application) {
		this((Context) application);
	}

	public ScreenMetrics(Activity activity) {
		this((Context) activity);
	}

	public ScreenMetrics(View v) {
		this(v.getContext());
	}

	public static float getScreenDensity() {
		accessCheck();
		return sScreenDensity;
	}
	
	public static int getResolutionX() {
		return (int) (TouchDeviceParser.getTouchDeviceParser().getWidth() + 0.5f);
	}

	public static int getResolutionY() {
		return (int) (TouchDeviceParser.getTouchDeviceParser().getHeight() + 0.5f);
	}

	public static int getMessuredScreenWidth() {
		accessCheck();
		return sScreenWidth;
	}

	public static int getMeasuredScrrenHeight() {
		accessCheck();
		return sScrrenHeight;
	}

	public static int getMeasuredStatusBarHeight() {
		accessCheck();
		return sStatusBarHeight;
	}

	public static int getMeasuredNavigationBarHeight() {
		accessCheck();
		return sNavigationBarHeight;
	}

	public static int getMeasuredCombinedBarHeight() {
		return getMeasuredNavigationBarHeight();
	}

	public static int getAppActionBarDefaultHeight() {
		accessCheck();
		return sAppActionBarDefaultHeight;
	}

	public synchronized void requestMessure() {
		sScreenMeasured = false;
		messure();
	}
	
	public static synchronized boolean hasMessured() {
		return sScreenMeasured;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public void messure() {
		if (!sScreenMeasured) {
			synchronized (this) {
				if (!sScreenMeasured) {
					WindowManager manager = (WindowManager) mContext
							.getSystemService(Context.WINDOW_SERVICE);

					Display display = manager.getDefaultDisplay();
					DisplayMetrics metrics = new DisplayMetrics();
					display.getMetrics(metrics);
					sScreenDensity = metrics.density;

					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
						// 3.0 or later
						display.getMetrics(metrics);
						sScreenWidth = metrics.widthPixels;
						sScrrenHeight = metrics.heightPixels;
					} else {
						sScreenWidth = display.getWidth();
						sScrrenHeight = display.getHeight();
					}

					sStatusBarHeight = getHeightPixelsById(STATUS_BAR_HEIGHT);
					sNavigationBarHeight = getHeightPixelsById(isLandspace() ? NAVIGATION_BAR_HEIGHT_LANDSCAPE
							: NAVIGATION_BAR_HEIGHT);

					if (mPhysicalScreenMode) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
							// 4.3 or later
							display.getRealMetrics(metrics);
							sScreenWidth = metrics.widthPixels;
							sScrrenHeight = metrics.heightPixels;
						} else if(isConfigShowNavigationBar()) {
							TouchDeviceParser parser = TouchDeviceParser
									.getTouchDeviceParser();
							final int width = (int) (parser.getWidth() + 0.5f);
							final int height = (int) (parser.getHeight() + 0.5f);
							if (sScreenWidth == width) {
								sScrrenHeight = height;
							} else {
								// landscape
								sScreenWidth = height;
								sScrrenHeight = width;
							}
						}
					}

					sAppActionBarDefaultHeight = getHeightPixelsById(ACTION_BAR_DEFAULT_HEIGHT);

					sScreenMeasured = true;
				}
			}
		}
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
	}

	/**
	 * Will measure the real physical screen, ignore its decorations, such as
	 * navigation bar.
	 */
	public void setPhysicalScreenMode() {
		mPhysicalScreenMode = true;
	}
	
	/**
	 * The returned value may be {@link #NO_ROTATION}, {@link #ROTATION_90},
	 * {@value #ROTATION_180} or {@value #ROTATION_270}.
	 * 
	 * @return the rotation of the screen from its default oritation.
	 */
	public int getOrientation() {
		WindowManager manager = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			return display.getRotation();
		} else {
			return display.getOrientation();
		}
	}

	public boolean isLandspace() {
		return sScreenWidth > sScrrenHeight;
	}

	private static void accessCheck() {
		synchronized (sScreenMeasured) {
			if (!sScreenMeasured) {
				throw new IllegalAccessError(
						"You MUST messure screen at least once, before using its property.");
			}
		}
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private boolean isConfigShowNavigationBar() {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			// 4.0 or below
			return false;
		} else if (ViewConfiguration.get(mContext).hasPermanentMenuKey()) {
			return false;
		}

		// TODO: we have no permission to access system framework package
		// config.xml, parse boolean value of config_showNavigationBar failed.
		// any other methods?

		return true;
	}

	private int getHeightPixelsById(String name) {
		final Resources res = mContext.getResources();
		int resId = res.getIdentifier(name, "dimen", "android");
		if (resId == 0) {
			return 0;
		} else {
			return res.getDimensionPixelSize(resId);
		}
	}

}
