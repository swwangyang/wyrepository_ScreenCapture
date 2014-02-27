package me.wtao.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.InputEvent;
import android.view.MotionEvent;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class TouchDeviceParser extends InputDeviceParser {

	private static TouchDeviceParser sParser = null;

	public static final TouchDeviceParser getTouchDeviceParser(InputEvent event) {
		if (sParser == null) {
			synchronized (TouchDeviceParser.class) {
				if (sParser == null) {
					sParser = new TouchDeviceParser(event);
				}
			}
		}
		return sParser;
	}

	public static final TouchDeviceParser getTouchDeviceParser() {
		if (sParser == null) {
			synchronized (TouchDeviceParser.class) {
				if (sParser == null) {
					sParser = new TouchDeviceParser(
							InputDeviceParser.getDeviceId("touch", "screen"));
				}
			}
		}
		return sParser;
	}
	
	public final int getTouchDeviceId() {		
		return mDevice.getId();
	}

	public float getWidth() {
		return mDevice.getMotionRange(MotionEvent.AXIS_X).getRange() + 1;
	}

	public float getHeight() {
		return mDevice.getMotionRange(MotionEvent.AXIS_Y).getRange() + 1;
	}

	public float getResolutionX() {
		try {
			//change by wy 2/27/2014
			//return mDevice.getMotionRange(MotionEvent.AXIS_X).getResolution();
			return 0;
		} catch (NoSuchMethodError e) {
			sLogcat.e("failed to get resolution: ", e);
			return 0;
		}
	}

	public float getResolutionY() {
		try {
			//change by wy 2/27/2014
			//return mDevice.getMotionRange(MotionEvent.AXIS_X).getResolution();
			return 0;
		} catch (NoSuchMethodError e) {
			sLogcat.e("failed to get resolution: ", e);
			return 0;
		}
	}

	private TouchDeviceParser(int devId) {
		super(devId);
	}

	private TouchDeviceParser(InputEvent event) {
		super(event);
	}

}
