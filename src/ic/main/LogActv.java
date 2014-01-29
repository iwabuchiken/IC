package ic.main;

import ic.utils.Methods_ic;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LogActv  extends ListActivity {

	@Override
	protected void
	onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.thumb_activity);
        
        this.setTitle(this.getClass().getName());
        
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

}
