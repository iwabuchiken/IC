package ic.main;

import android.app.ListActivity;
import android.os.Bundle;

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

}
