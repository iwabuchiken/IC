package ic.main;

import ic.utils.CONS;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

public class SettingsActv extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setTitle(this.getClass().getName());
		
		setContentView(R.layout.pref_main);
		
		getPreferenceManager()
			.setSharedPreferencesName(CONS.Prefs.prefName);
		
		addPreferencesFromResource(R.xml.preferences);
		
		//debug
		do_debugs();
		
	}

	private void do_debugs() {
		// TODO Auto-generated method stub
		_debug_D_30_v_1_0();
//		_debug_D_30_v_1_0a();
		
	}

	private void _debug_D_30_v_1_0a() {
		// TODO Auto-generated method stub
		Resources res = getResources();
		
		final TypedArray selectedValues = res
		        .obtainTypedArray(R.array.updateFrequencyValues);
		
		int val = selectedValues.getInt(2, -1);
		
		// Log
		String log_msg = "val=" + String.valueOf(val)
						+ "/"
						+ "product(x3)="
						+ String.valueOf(val * 3);

		Log.d("[" + "SettingsActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		selectedValues.recycle();
		
	}//private void _debug_D_30_v_1_0a()

	private void _debug_D_30_v_1_0() {
		// TODO Auto-generated method stub
		SharedPreferences sharedPrefs = 
					PreferenceManager.getDefaultSharedPreferences(this);
		
		SharedPreferences prefs = this
				.getSharedPreferences(
						CONS.Prefs.prefName,
						Context.MODE_PRIVATE);
		
		String res = sharedPrefs.getString(
						this.getString(R.string.pref_key_sort_type),
						"NOUPDATE");
		
		String res2 = prefs.getString(
				this.getString(R.string.pref_key_sort_type),
				"NOUPDATE");
		
		// Log
		String log_msg = "sort_type => " + res;

		Log.d("[" + "SettingsActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		// Log
		String log_msg2 = "sort_type(res2) => " + res2;
		
		Log.d("[" + "SettingsActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg2);
		
	}//private void _debug_D_30_v_1_0()

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		// Log
		String log_msg = "Back pressed...";

		Log.d("[" + "ShowLogActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		this.finish();
		
		this.overridePendingTransition(0, 0);
		
	}//public void onBackPressed()

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	
	
}
