package tasks;

import ic.items.Furi;
import ic.items.Word;
import ic.utils.CONS;
import ic.utils.DBUtils;
import ic.utils.Methods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sl.libs.json.YahooFurigana;
import sl.libs.xml.XmlHandler;

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Task_GetYomi extends AsyncTask<String, Integer, Integer> {

	static Activity actv;
	
	Dialog dlg;
	
	public Task_GetYomi(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public Task_GetYomi(Activity actv, Dialog dlg) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		this.dlg = dlg;
		
	}

	@Override
	protected Integer doInBackground(String... arg0) {
		
		// Log
		Log.d("[" + "Task_GetYomi.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Task_GetYomi => Starts");
		
		List<Word> wordList = _doInBackground__GetWordList();
		
		/***************************************
		 * If no more entries to process, quit the task
		 ***************************************/
		if (wordList.size() < 1) {
			
			return CONS.RetVal.GETYOMI_NO_ENTRY;
			
		}//if (wordList.size() < 1)
		
		/*********************************
		 * Get combo from API
		 *********************************/
		wordList = _doInBackground__GetCombo(wordList);
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "wordList.size()=" + wordList.size());

		return null;
		
	}//protected Integer doInBackground(String... arg0) {

	
	private List<Word> _doInBackground__GetWordList() {
		
		DBUtils dbu = new DBUtils(actv, CONS.DBAdmin.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		/*----------------------------
		 * 0. Table exists?
			----------------------------*/
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tableName=" + CONS.DBAdmin.tname_CheckLists);
		
		boolean res = dbu.tableExists(rdb, CONS.DBAdmin.tname_CheckLists);
		
		if (res == false) {
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]",
					"getAllData() => Table doesn't exist: "
							+ CONS.DBAdmin.tname_CheckLists);
			
			rdb.close();
			
			return null;
			
		}//if (res == false)
		
		/*----------------------------
		 * 2. Get data
		 * 		2.1. Get cursor
		 * 		2.2. Add to list
			----------------------------*/
		//
		String sql = "SELECT * FROM " + CONS.DBAdmin.tname_CheckLists;
		
		Cursor c = null;
		
		try {
			
			c = rdb.rawQuery(sql, null);
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getCount()=" + c.getCount());
			
		} catch (Exception e) {
			// Log
			Log.e("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return null;
		}
	
		/*********************************
		 * Get names
		 *********************************/
//		List<String> itemNames = new ArrayList<String>();
//		
//		List<Long> itemIds = new ArrayList<Long>();
		
		List<Word> wordList = new ArrayList<Word>();
		
		c.moveToFirst();
		
		int numOfSamples = 10;
		
		/***************************************
		 * Counter: Count 1 each time when a new entry 
		 * 				is made into wordList
		 ***************************************/
		int counter = 0;
//		int numOfSamples = c.getCount();
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "numOfSamples=" + numOfSamples);
		
//		for (int i = 0; i < 10; i++) {
//		for (int i = 0; i < numOfSamples; i++) {
		for (int i = 0; i < c.getCount(); i++) {
			
			String name = c.getString(
								Methods.getArrayIndex(
									CONS.DBAdmin.cols_check_lists_FULL,
									"name"));
			
			String yomi = c.getString(
								Methods.getArrayIndex(
									CONS.DBAdmin.cols_check_lists_FULL,
									"yomi"));
		
			long itemId = c.getLong(0);

			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "name=" + name + "/" + "yomi=" + yomi);
			
			if (name != null 
					&& (yomi == null || yomi.equals("null") || yomi.equals(""))) {
				
				wordList.add(new Word(itemId, name, yomi));
				
				counter += 1;

			} else {//if (name != null)
				
				// Log
				Log.d("Task_GetYomi.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"!(name != null && (yomi == null || yomi.equals(\"null\")))");
				
			}//if (name != null)
			
			/***************************************
			 * Check
			 ***************************************/
			if (counter > numOfSamples) {
				
				break;
				
			}//if (counter == numOfSamples)
			
			/*********************************
			 * Next row in the cursor
			 *********************************/
			c.moveToNext();
			
		}//for (int i = 0; i < 10; i++)
		
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "wordList.size()=" + wordList.size());
		
		rdb.close();
		
		/***************************************
		 * Return
		 ***************************************/
		return wordList;
		
	}//private List<Word> _doInBackground__GetWordList()

	private static List<Word> _doInBackground__GetCombo(
			List<Word> wordList) {
		
		YahooFurigana yf = YahooFurigana.getInstance();
		
		int isNull = 0;
		int isNotNull = 0;
		
		int count = 0;
		int numOfSamples = 5;
//		int numOfSamples = 10;
		
//		for (int i = 0; i < itemNames.size(); i++) {
		for (int i = 0; i < wordList.size(); i++) {
//		for (int i = 0; (i < wordList.size()) && count < numOfSamples; i++) {
			
			Word word = wordList.get(i);
			
			String keyWord = word.getName();
				
			
			// Log
			Log.d("["
					+ "Task_GetYomi.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "keyword=" + keyWord);
			
			
			String furi = yf.getFurigana(keyWord, true);
	
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"keyword=" + keyWord
					+ "/"
					+ "furi=" + furi);
	
			word.setCombo(furi);
				
		}//for (int i = 0; i < itemNames.size(); i++)
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Get furigana => Done");
		
		/***************************************
		 * Return
		 ***************************************/
		return wordList;

	}//private static List<Word> doInBackground_B18_v_6_0__2_getFurigana

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer res) {
		// TODO Auto-generated method stub
		super.onPostExecute(res);
		
		// debug
		Toast.makeText(actv, "Task_GetYomi => Done", Toast.LENGTH_LONG).show();
		
	}//protected void onPostExecute(Integer res)

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

}//public class Task_GetYomi extends AsyncTask<String, Integer, Integer>
