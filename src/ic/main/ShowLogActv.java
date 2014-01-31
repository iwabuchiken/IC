package ic.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ic.utils.CONS;
import ic.utils.Methods_ic;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ShowLogActv  extends ListActivity {

	@Override
	protected void
	onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.thumb_activity);
        
        this.setTitle(this.getClass().getName());
        
	}//onCreate(Bundle savedInstanceState)

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		
		//REF override http://foobarpig.com/android-dev/how-to-disable-animation-on-startactivity-finish-and-backpressed.html
		// Log
		String log_msg = "Back pressed...";

		Log.d("[" + "ShowLogActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		this.finish();
		
		this.overridePendingTransition(0, 0);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
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
		
		// Get Intent value
		Intent i = this.getIntent();
		
		String fpath_Log = i.getStringExtra(
					CONS.Admin.IntentKeys.LogFilePath.toString());

		List<String> logLines = Methods_ic.read_LogFile(this, fpath_Log);
		
		// Log
		String log_msg = "logLines => " + String.valueOf(logLines.size());

		Log.d("[" + "ShowLogActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		// Build: Array adapter
		ArrayAdapter adapter = new ArrayAdapter<String>(
						this,
						android.R.layout.simple_list_item_1,
						logLines
						);
		
		// Set: adapter to the list
		this.setListAdapter(adapter);
		
	}
	

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	
}
