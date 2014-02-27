package me.wtao.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.os.*;
import android.view.*;
import android.widget.*;


public class ScreenshotDemo extends Activity {

	/*
	 * The ImageView used to display taken screenshots.
	 */
	private ImageView imgScreen;
	private IScreenCaptureService aslProvider = null;

	private ServiceConnection aslServiceConn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			aslProvider = IScreenCaptureService.Stub.asInterface(service);
		}
	};
	


    /** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        imgScreen = (ImageView)findViewById(R.id.imgScreen);
        Button btn = (Button)findViewById(R.id.btnTakeScreenshot); 
        btn.setOnClickListener(btnTakeScreenshot_onClick); 

        // connect to ASL service
        //Intent intent = new Intent(ScreenshotService.class.getName());
        Intent intent = new Intent();
        intent.setClass(this, ScreenCaptureService.class);
        //intent.addCategory(Intent.ACTION_DEFAULT);
        bindService (intent, aslServiceConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onDestroy() {
    	unbindService(aslServiceConn);
    	super.onDestroy();
    }
    
    /**
	 * 保存图片到sdcard中
	 * @param pBitmap
	 */
	private static boolean savePic(Bitmap pBitmap,String strName)
	{
	  FileOutputStream fos=null;
	  try {
		fos=new FileOutputStream(strName);
		if(null!=fos)
		{
			pBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
			fos.flush();
			fos.close();
			return true;
		}
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}catch (IOException e) {
		e.printStackTrace();
	}
	  return false;
	} 
	

    private View.OnClickListener btnTakeScreenshot_onClick = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			try {
				Bitmap screen = aslProvider.takeScreenCapture();
				if (screen != null)
				{
					if(savePic(screen,"sdcard/"+System.currentTimeMillis()+".png"))
					Toast.makeText(ScreenshotDemo.this, R.string.sc, Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(ScreenshotDemo.this, R.string.fa, Toast.LENGTH_SHORT).show();
				}
			catch(NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}
	};
}