package ic.utils;

import ic.items.CL;
import ic.items.Item;
import ic.main.CheckActv;
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

	/*******************************
	 * Class fields
		----------------------------********************/
	// Context
	Context con;

	// Inflater
	LayoutInflater inflater;

	// Views
	TextView tv_list_name;
	TextView tv_created_at;
	TextView tv_genre;	

	// Colors
	int[] colors = {R.color.green4, R.color.blue1, R.color.gold2};
	
	//
	/*******************************
	 * Constructor
		********************/
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


	/*******************************
	 * Methods
		----------------------------********************/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	/*******************************
		 * Steps
		 * 1. Set up
		 * 2. Get view
		 * 
		 * 3. Get item
		 * 3-1. All clear?
		 * 3-1-1. If all clear, set background black
		 * 
		 * 3-2. Set value to views
		 * 
		 * 4. Set bg color
			********************/
//    	// Log
//		Log.d("MainListAdapter.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Starts => getView()");
    	/*******************************
		 * 1. Set up
			********************/
    	View v = null;

    	if (convertView != null) {
			v = convertView;
		} else {//if (convertView != null)

			v = inflater.inflate(R.layout.list_row_main, null);
			
		}//if (convertView != null)

    	/*******************************
		 * 2. Get view
			********************/
//		TextView tv_list_name = (TextView) v.findViewById(R.id.list_row_main_tv_list_name);
    	tv_list_name = (TextView) v.findViewById(R.id.list_row_main_tv_list_name);
    	
		tv_created_at = (TextView) v.findViewById(R.id.list_row_main_tv_created_at);
		
		tv_genre = (TextView) v.findViewById(R.id.list_row_main_tv_genre);

		/*******************************
		 * 3. Get item
			********************/
		CL clList = (CL) getItem(position);
		
		/*********************************
		 * 3-1. All clear?
		 *********************************/
		CheckActv.iList = Methods.get_item_list_from_check_list(
								(Activity) con,
								clList.getDb_id());

		boolean all_checked = true;
		
		for (Item item : CheckActv.iList) {
			
			if (item.getStatus() == 0) {
				
				all_checked = false;
				
				break;
				
			}//if (item.getStatus() == condition)
			
		}//for (Item item : CheckActv.iList)
		
		/*********************************
		 * 3-1-1. If all clear, set background black
		 *********************************/
//		// Log
//		Log.d("MainListAdapter.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "all_checked=" + all_checked);
		
		if (all_checked == true) {
			
			tv_list_name.setBackgroundColor(Color.BLUE);
			tv_list_name.setTextColor(Color.WHITE);
			
		} else {//if (all_clear == true)

			tv_list_name.setBackgroundColor(Color.WHITE);
			tv_list_name.setTextColor(Color.BLACK);
			
		}//if (all_clear == true)
		
//		// Log
//		if (CheckActv.iList != null) {
//
//			Log.d("MainListAdapter.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "CheckActv.iList.size()=" + CheckActv.iList.size());
//
//		}//if (CheckActv.iList == condition)

//		CheckActv.clList = Methods.get_clList_from_db_id((Activity) con, item.getDb_id());
//		
//		boolean all_clear = true;
//		
//		for (elemType item : CheckActv.clList) {
//			
//		}//for (elemType item : CheckActv.clList)
		
		/*********************************
		 * 3-2. Set value to views
		 *********************************/
		tv_list_name.setText(clList.getName());
		
//		tv_created_at.setText(String.valueOf(item.getCreated_at()));
		tv_created_at.setText(Methods.convert_millSec_to_DateLabel(clList.getCreated_at()));
		
		tv_genre.setText(
							Methods.get_genre_name_from_genre_id((Activity) con,
							clList.getGenre_id()));
		
		/*******************************
		 * 4. Set bg color
			********************/
//		set_bg_color(item);
//		String genre_name = Methods.get_genre_name_from_genre_id(
//								(Activity) con,
//								item.getGenre_id());
//		
//		if (genre_name.equals("Daily")) {
//			
////			tv_genre.setBackgroundResource(colors[0]);
//			tv_genre.setBackgroundColor(Color.GREEN);
//			tv_genre.setTextColor(Color.WHITE);
//			
//			
//		} else if (genre_name.equals("JOB")) {
//		} else if (genre_name.equals("Meals")) {
//		} else if (genre_name.equals("Works")) {
//			
//		} else {//if (genre_name.equals("Daily"))
//			
//		}//if (genre_name.equals("Daily"))
		
		
//    	return null;
		return v;
    }//public View getView(int position, View convertView, ViewGroup parent)


	private void set_bg_color(CL clList) {
		// Log
		Log.d("MainListAdapter.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Genre id=" + clList.getGenre_id());
		
		// TODO Auto-generated method stub
		String genre_name = Methods.get_genre_name_from_genre_id(
				(Activity) con,
				clList.getGenre_id());

		// Log
		Log.d("MainListAdapter.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "genre_name=" + genre_name);
		
		if (genre_name.equals("Daily")) {
		
			// Log
			Log.d("MainListAdapter.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "genre_name.equals(\"Daily\")");
			
			tv_genre.setBackgroundResource(colors[0]);
			tv_genre.setTextColor(Color.WHITE);
		
		
//		} else if (genre_name.equals("JOB")) {
//		} else if (genre_name.equals("Meals")) {
//		} else if (genre_name.equals("Works")) {
//		
		} else {//if (genre_name.equals("Daily"))
		
		}//if (genre_name.equals("Daily"))

	}



}//public class TIListAdapter extends ArrayAdapter<TI>
