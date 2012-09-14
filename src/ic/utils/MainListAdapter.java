package ic.utils;

import ic.items.CL;
import ic.main.R;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainListAdapter extends ArrayAdapter<CL> {

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
//	public MainListAdapter(Context con, int resourceId, List<String> list) {
//		// Super
//		super(con, resourceId, list);
//
//		// Context
//		this.con = con;
//
//		// Inflater
//		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		
//
//	}//public TIListAdapter(Context con, int resourceId, List<TI> items)

	public MainListAdapter(Context con, int resourceId, List<CL> clList) {
		// Super
		super(con, resourceId, clList);

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
		 * 3. Get item
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

			v = inflater.inflate(R.layout.list_row_main, null);
			
		}//if (convertView != null)

    	/*----------------------------
		 * 2. Get view
			----------------------------*/
		TextView tv = (TextView) v.findViewById(R.id.list_row_main_tv);

		/*----------------------------
		 * 3. Get item
			----------------------------*/
		CL item = (CL) getItem(position);
		
		tv.setText(item.getName());
		
		/*----------------------------
		 * 4. Set bg color
			----------------------------*/
		
//    	return null;
		return v;
    }//public View getView(int position, View convertView, ViewGroup parent)



}//public class TIListAdapter extends ArrayAdapter<TI>
