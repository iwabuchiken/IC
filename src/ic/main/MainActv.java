package ic.main;

import ic.utils.Methods;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActv extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/********************************
		 * 
		 ********************************/
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        this.setTitle(this.getClass().getName());
        
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

    
}//public class MainActv extends Activity
