package me.wtao.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.InputDevice;
import android.view.InputEvent;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class InputDeviceParser {
	protected static final Logcat sLogcat = new Logcat();
	static {
		sLogcat.setOn();
	}
	protected InputDevice mDevice;
	
	public InputDeviceParser(int devId) {
		mDevice = InputDevice.getDevice(devId);
	}
	
	public InputDeviceParser(InputEvent event) {
		mDevice = InputDevice.getDevice(event.getDeviceId());
	}
	
	public static ArrayList<Integer> getDeviceId(String regex) {
		ArrayList<Integer> validIds = new ArrayList<Integer>();
		int ids[] = InputDevice.getDeviceIds();
		InputDevice device = null;
		String name = null;
		
		Pattern pattern = Pattern.compile(regex);
		for(int id : ids) {
			device = InputDevice.getDevice(id);
			name = device.getName();
			Matcher matcher = pattern.matcher(name);
			if(matcher.matches()) {
				validIds.add(id);
			}
		}
		
		return (validIds.isEmpty() ? null : validIds);
	}
	
	public static Integer getDeviceId(String... segments) {
		int ids[] = InputDevice.getDeviceIds();
		InputDevice device = null;
		String name = null;
		
		for(int id : ids) {
			device = InputDevice.getDevice(id);
			name = device.getName();
			
			for(String seg : segments) {
				if(name.contains(seg)) {
					return id;
				}
			}
		}
		
		return null;
	}
	
	public String getName() {
		return mDevice.getName();
	}
	
}
