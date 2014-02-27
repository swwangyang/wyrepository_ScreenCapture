package me.wtao.view;

import java.lang.reflect.Method;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

public abstract class MiuiFloatingWinAdapter {

	private static final int MIN_SDK_VERSION = 16; // android 4.1 or later

	private static Boolean MI_PHONE = null;
	private static Boolean MIUI = null; // TODO: how to verify
	private static boolean MIUI_V5 = false;

	public static boolean isMIUI() {
		if (MI_PHONE == null && MIUI == null) {
			final String manufaturer = Build.MANUFACTURER.toLowerCase();
			MI_PHONE = manufaturer.contains("xiaomi");

			try {
				Method method = Class.forName("android.os.SystemProperties")
						.getDeclaredMethod("get", String.class);
				if (method != null) {
					String miuiVersion = (String) method.invoke(null,
							"ro.miui.ui.version.name");
					if (!TextUtils.isEmpty(miuiVersion)
							&& miuiVersion.toLowerCase().contains("v5")) {
						MIUI = MIUI_V5 = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (MI_PHONE == null) {
				MI_PHONE = false;
			}
			if (MIUI == null) {
				MIUI = false;
			}
		}

		return MI_PHONE || MIUI;
	}

	public static boolean hasRestrictted() {
		if (!isMIUI()) {
			return false;
		}
		if (MIUI_V5 || Build.VERSION.SDK_INT >= MIN_SDK_VERSION) {
			return true;
		} else {
			return false;
		}
	}

	public static void enableAll(Context context) {
		enableNotification(context);
		enableFloatingWindow(context);
	}

	public static void enableNotification(Context context) {
		try {
			final ContentResolver contentResolver = context
					.getContentResolver();
			final String pkgName = context.getPackageName();
			String query = Settings.System.getString(contentResolver,
					"status_bar_notification_filter_white_list");
			if (query == null || !query.contains(pkgName)) {
				StringBuilder sb = new StringBuilder();
				sb.append(pkgName);
				sb.append(" ");
				sb.append(query);
				Settings.System.putString(contentResolver,
						"status_bar_notification_filter_white_list",
						sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * need root
	 */
	public static void enableFloatingWindow(Context context) {
		try {
			PackageManager pkgMgr = context.getPackageManager();

			final String pkgName = context.getPackageName();
			pkgMgr.setApplicationEnabledSetting(pkgName, 134217728, 134217728);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
