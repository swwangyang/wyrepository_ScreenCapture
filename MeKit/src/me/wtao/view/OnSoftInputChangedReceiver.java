package me.wtao.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

public abstract class OnSoftInputChangedReceiver extends BroadcastReceiver {
	public static final String IME_TOP_POSITION = "IME_TOP_POSITION";

	private ViewGroup mListener;
	private boolean mBlocked;

	public View getView() {
		return mListener;
	}

	public void setView(View v) {
		mListener = (ViewGroup) v;
	}

	public void blocked() {
		mBlocked = true;
	}

	public void unBlocked() {
		mBlocked = false;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		final int bottom = intent.getIntExtra(IME_TOP_POSITION, 0);

		if(onReLayout(bottom)) {
			return;
		}
		
		if (mListener != null && !mBlocked) {
			ViewGroup.LayoutParams rlp = (ViewGroup.LayoutParams) mListener
					.getLayoutParams();
			rlp.height = bottom - mListener.getTop();
			mListener.setLayoutParams(rlp);
		}
	}
	
	/**
	 * 
	 * @param imeTopPosition
	 * @return 
	 */
	public abstract boolean onReLayout(final int imeTopPosition);

}
