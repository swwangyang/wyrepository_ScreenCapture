package me.wtao.io;

import java.io.File;

import me.wtao.utils.Logcat;
import android.os.Environment;

public abstract class ExternalStorage {
	private static final Logcat sLogcat = new Logcat();
	
	/* Checks if external storage is available for read and write */
	public static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/* Checks if external storage is available to at least read */
	public static boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	public static File getExternalStorageDirectory(String dir) {
	    // Get the directory for the user's public pictures directory.
		StringBuilder sb = new StringBuilder(Environment.getExternalStorageDirectory().getPath());
		sb.append(File.separatorChar);
		sb.append(dir);
		dir = sb.toString();
		
		File file = new File(dir);
		if ((file.exists() && file.isDirectory())
				|| file.mkdirs()) {
			return file;
		} else {
			sLogcat.e("Directory not created: ", dir);
			return null;
		}
	}
}
