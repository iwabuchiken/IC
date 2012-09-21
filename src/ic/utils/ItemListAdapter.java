package ic.utils;

import ic.items.Item;
import ic.main.R;


import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemListAdapter extends ArrayAdapter<Item> {

	/*--------------------------------------------------------
	 * Class fields
		--------------------------------------------------------*/
	// Context
	Context con;

	// Inflater
	LayoutInflater inflater;

	//
	/*--------------------------------------------------------
	 * Constructor
		--------------------------------------------------------*/
	//
	public ItemListAdapter(Context con, int resourceId, List<Item> iList) {
		// Super
		super(con, resourceId, iList);

		// Context
		this.con = con;

		// Inflater
		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		

	}//public TIListAdapter(Context con, int resourceId, List<TI> items)


	/*--------------------------------------------------------
	 * Methods
		--------------------------------------------------------*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Get view
		 * 
		 * 3. Get item
		 * 3-2. Set value to views
		 * 
		 * 4. Set bg color
			----------------------------*/
    	/*----------------------------
		 * 1. Set up
			----------------------------*/
    	View v = null;

    	if (convertView != null) {
			v = convertView;
		} else {//if (convertView != null)

//			v = inflater.inflate(R.layout.list_row_main, null);
			v = inflater.inflate(R.layout.list_row_item_list, null);
			
		}//if (convertView != null)

    	/*----------------------------
		 * 2. Get view
			----------------------------*/
		TextView tv_text =
				(TextView) v.findViewById(R.id.list_row_item_list_tv_text);
		
		TextView tv_status =
				(TextView) v.findViewById(R.id.list_row_item_list_tv_status);
		
		TextView tv_serial_num = 
				(TextView) v.findViewById(R.id.list_row_item_list_tv_serial_num);

		/*----------------------------
		 * 3. Get item
			----------------------------*/
		Item item = (Item) getItem(position);
		
		/*********************************
		 * 3-2. Set value to views
		 *********************************/
		if (item != null) {
			
			tv_text.setText(item.getText());

//			tv_status.setText(String.valueOf(item.getCreated_at()));
			tv_status.setText(String.valueOf(item.getStatus()));
			
			tv_serial_num.setText(String.valueOf(item.getSerial_num()));
			
		} else {//if (item != null)
			
			// Log
			Log.d("ItemListAdapter.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "item => null");
			
		}//if (item != null)
		
		
		/*----------------------------
		 * 4. Set bg color
			----------------------------*/
		
//    	return null;
		return v;
    }//public View getView(int position, View convertView, ViewGroup parent)



}//public class ItemListAdapter extends ArrayAdapter<TI>
