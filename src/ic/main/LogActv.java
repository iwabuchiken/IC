package ic.main;

import ic.utils.CONS;
import ic.utils.Methods_ic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LogActv  extends ListActivity {

	
	
	private Vibrator vib;

	@Override
	protected void
	onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.thumb_activity);
        
        this.setTitle(this.getClass().getName());
        
        vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        
	}//onCreate(Bundle savedInstanceState)
	
	@Override
	protected void
	onStart() {
		
		super.onStart();
		
		List<String> fileList = Methods_ic.get_LogFileList(this);
//		List<String> fileList = new ArrayList<String>();
		
//		fileList.add("abc");
//		fileList.add("def");
//		fileList.add("ghi");
		
		ArrayAdapter<String> adapter = null;
		
		if (fileList == null) {
			
			fileList = new ArrayList<String>();
			
			fileList.add("No data");
			
			adapter = new ArrayAdapter<String>(
					this,
//				R.layout.dlg_db_admin,
					android.R.layout.simple_list_item_1,
					fileList
					);
			
		} else {//if (fileList == null)
			
			adapter = new ArrayAdapter<String>(
					this,
//				R.layout.dlg_db_admin,
					android.R.layout.simple_list_item_1,
					fileList
					);
			
		}//if (fileList == null)

		/*----------------------------
		 * 4. Set adapter
			----------------------------*/
		ListView lv = this.getListView();
		
		lv.setAdapter(adapter);
		
	}//onStart()

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void
	onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		vib.vibrate(CONS.Admin.vib_Long);
		
		ListView lv = this.getListView();
		
		String itemName = (String) lv.getItemAtPosition(position);

		// File exists?
		File f = new File(
					Methods_ic.get_Dirpath_Log(),
					CONS.DBAdmin.AdminLog.fname_Log_Full);
		
		if (!f.exists()) {
			
			// Log
			String log_msg = "Log file doesn't exist";

			Log.d("["
					+ "LogActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);
			
			// debug
			String toa_msg = "Log file doesn't exist";
			Toast.makeText(this, toa_msg, Toast.LENGTH_LONG).show();
			
			return;
			
		}//if (!f.exists())
		
		
		// If yes, then start ShowLogActv
		Intent i = new Intent();
		
		i.setClass(this, ShowLogActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		i.putExtra(
				CONS.Admin.IntentKeys.LogFilePath.toString(),
				f.getAbsolutePath());
		
		this.startActivity(i);
		
	}//onListItemClick(ListView l, View v, int position, long id)

}//public class LogActv  extends ListActivity
