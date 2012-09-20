package ic.main;

import ic.items.CL;
import ic.utils.Methods;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class CheckActv extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/********************************
		 * 1. Set up
		 * 2. Get intent values
		 * 
		 * 3. Get list object
		 * 
		 * 4. Set title
		 * 
		 * 5. Get item list
		 * 
		 ********************************/
		/*********************************
		 * 1. Set up
		 *********************************/
		super.onCreate(savedInstanceState);

		setContentView(R.layout.actv_check);

		this.setTitle(this.getClass().getName());
		
		/*********************************
		 * 2. Get intent values
		 *********************************/
		Intent i = this.getIntent();
		
		long list_id = i.getLongExtra(MainActv.intent_list_id, -1);
		
		if (list_id == -1) {
			
			// debug
			Toast.makeText(this, "No intent value", Toast.LENGTH_SHORT).show();
			
			return;
			
		}//if (list_id == -1)
		
		// debug
//		Toast.makeText(this, "list_id=" + list_id, Toast.LENGTH_SHORT).show();
		
		/*********************************
		 * 3. Get list object
		 *********************************/
		CL clList = Methods.get_clList_from_db_id(this, list_id);
		
		// Log
		Log.d("CheckActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "clList.getName(): " + clList.getName());
		
		/*********************************
		 * 4. Set title
		 *********************************/
		TextView tv_title = (TextView) findViewById(R.id.actv_check_tv);

		String title = clList.getName();
		
		if (title.equals("")) {
			
			tv_title.setText("No list name data");
			
		} else {
			
			tv_title.setText(title);
			
		}
		
		/*********************************
		 * 5. Get item list
		 *********************************/
		aaa
		
	}//public void onCreate(Bundle savedInstanceState)

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
	}
	
}//public class CheckActv extends ListActivity
