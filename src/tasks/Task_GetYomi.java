package tasks;

import ic.items.Furi;
import ic.items.Word;
import ic.utils.CONS;
import ic.utils.DBUtils;
import ic.utils.Methods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
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
		
		//debug
		if (wordList == null) {
			// Log
			Log.d("["
					+ "Task_GetYomi.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "wordList => null");
			
		} else {//if (wordList == null)
			
			// Log
			Log.d("["
					+ "Task_GetYomi.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "wordList => " + wordList.size());
			
			for (Word word : wordList) {
				// Log
				Log.d("["
						+ "Task_GetYomi.java : "
						+ +Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ " : "
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"word=" + word.getName());
			}
			
		}//if (wordList == null)
		
		/*********************************
		 * Null check
		 *********************************/
		if (wordList == null) {
			
			// Log
			Log.d("["
					+ "Task_GetYomi.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "wordList => null");
			
			return CONS.RetVal.GetWordList_Failed;
			
		}
		
		
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

		/***************************************
		 * Convert combo into yomi (i.e. all-hiragana)
		 ***************************************/
		_doInBackground___ConvertCombo2Yomi(wordList);
		
		/***************************************
		 * Update table
		 ***************************************/
		String res = _doInBackground__UpdateTable(wordList);
		
		/*********************************
		 * Return value
		 *	=> 1000 + number of updated records
		 *********************************/
		int retVal = CONS.RetVal.MAGINITUDE_ONE
						+ Integer.parseInt(res.split(File.separator)[1]);
		
		return retVal;
		
	}//protected Integer doInBackground(String... arg0) {

	/*********************************
	 * @return 1. null => (1) Table doesn't exist<br>
	 * 					(2) Raw query failed<br>
	 * 					(3) Query result => No records<br><br>
	 * 			2. List{@literal<Word>}
	 *********************************/
	//REF {@literal} http://stackoverflow.com/questions/647195/how-do-you-escape-curly-braces-in-javadoc-inline-tags-such-as-the-code-tag answered Oct 29 '13 at 12:18
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
					"Table doesn't exist: "
							+ CONS.DBAdmin.tname_CheckLists);
			
			rdb.close();
			
			return null;
			
		}//if (res == false)
		
		/*----------------------------
		 * 2. Get data
		 * 		2.1. Get cursor
		 * 		2.2. Add to list
			----------------------------*/
		String tname	= CONS.DBAdmin.tname_CheckLists;
		String fields[]	= CONS.DBAdmin.cols_check_lists_FULL;
		String where	= 
					CONS.DBAdmin.cols_check_lists_FULL[
					     Methods.getArrayIndex(
					    		 CONS.DBAdmin.cols_check_lists_FULL,
					    		 "yomi")]
					+ " is null";
//		+ " = "
//		+ "?";
		
		String args[]	= new String[]{
				
//				"null"
//				null
				""
		};
		
		//
		String sql = "SELECT * FROM " + CONS.DBAdmin.tname_CheckLists;
		
		Cursor c = null;
		
		try {
			
//			c = rdb.rawQuery(sql, null);
			c = rdb.query(tname, fields, where, null, null, null, null);
//			c = rdb.query(tname, fields, where, args, null, null, null);
		
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
		 * Query => No records?
		 *********************************/
		if (c.getCount() < 1) {
			
			// Log
			Log.d("["
					+ "Task_GetYomi.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query => No records");
			
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
		
		int numOfSamples = CONS.DBAdmin.GetYomi_ChunkNum;
//		int numOfSamples = 5;
//		int numOfSamples = 10;
		
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

	/*********************************
	 * @return List{@literal<Word>}<br>
	 * 			No null returns
	 *********************************/
	private static List<Word>
	_doInBackground__GetCombo(List<Word> wordList) {
		
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

	}//_doInBackground__GetCombo(List<Word> wordList)

	private static List<Word>
	_doInBackground___ConvertCombo2Yomi
	(List<Word> wordList) {
		
		for (int i = 0; i < wordList.size(); i++) {

			Word word = wordList.get(i);
			
			String combo = word.getCombo();
			
			if (combo != null) {
				
				// Log
				Log.d("["
						+ "Task_GetYomi.java : "
						+ +Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ " : "
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"combo => " + combo);
				
//				String gana = Methods.convert_Kana2Gana(word.getFuri());
				String yomi = Methods.convert_Kana2Gana(combo);
				
				if (yomi != null) {
					
					// Log
					Log.d("["
							+ "Task_GetYomi.java : "
							+ +Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ " : "
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"yomi => " + yomi);
					
//					wordList.get(i).setGana(gana);
					word.setYomi(yomi);
					
				} else {//if (gana != null)
					
//					wordList.get(i).setGana(null);
					word.setYomi(null);
					
					// Log
					Log.d("Task_GetYomi.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"yomi == null"
							+ "(id=" + wordList.get(i).getId() + ")");
					
					continue;
					
				}//if (yomi != null)
				
				
			} else {//if (combo != null)
				
				// Log
				Log.d("Task_GetYomi.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "word.getFuri() == null");
				
				word.setYomi(null);
				
			}//if (combo != null)
			
		}//for (int i = 0; i < wordList.size(); i++)
		
		/***************************************
		 * Return
		 ***************************************/
		return wordList;
		
	}//_doInBackground___ConvertCombo2Yomi

	/*********************************
	 * @return "10/6/4"<br>
	 * 			=> Target/Success/Fail
	 *********************************/
	private static String
	_doInBackground__UpdateTable
	(List<Word> wordList) {
		/***************************************
		 * Setup
		 ***************************************/
		DBUtils dbu = new DBUtils(actv, CONS.DBAdmin.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		String sql = null;
		
		// Variables for debugging
		int numOfTargets = wordList.size();
		int numOfSuccess = 0;
		int numOfFail = 0;
		
		/***************************************
		 * Update
		 ***************************************/
		for (int i = 0; i < wordList.size(); i++) {
			
			Word word = wordList.get(i);
			
//			long dbId = wordList.get(i).getId();
			long dbId = word.getId();
			
			String colYomi = CONS.DBAdmin.cols_check_lists[
	                    Methods.getArrayIndex(
	                    		CONS.DBAdmin.cols_check_lists,
	                    		"yomi")
	        ];
			
			// Get "gana" value: "gana" value in a Word instance 
			//	corresponds to "yomi" yomi in db
//			String yomi = word.getName();
			String yomi = word.getYomi();
			
			int res = dbu.updateData_CheckList(
							actv,
							wdb,
							CONS.DBAdmin.tname_CheckLists,
							dbId,
							colYomi, yomi);

			if (res == CONS.RetVal.DB_UPDATE_SUCCESSFUL) {
				
				numOfSuccess += 1;
				
				// Log
				Log.d("Task_GetYomi.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Data updated: name=" + word.getName()
						+ "/"
						+ "yomi=" + yomi);
				
			} else {//if (res == CONS.DB_UPDATE_SUCCESSFUL)
				
				// Log
				Log.d("Task_GetYomi.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Update failed => id=" + dbId
						+ "/"
						+ "name=" + word.getName());
				
			}//if (res == CONS.DB_UPDATE_SUCCESSFUL)
			
			
			
		}//for (int i = 0; i < wordList.size(); i++)
		
		
		/***************************************
		 * Close db
		 ***************************************/
		wdb.close();
		
		/***************************************
		 * Result
		 ***************************************/
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"numOfTargets=" + numOfTargets
				+ "/"
				+ "numOfSuccess=" + numOfSuccess
				+ "/"
				+ "numOfFail=" + numOfFail);
		
		return StringUtils.join(
						new String[]{
								String.valueOf(numOfTargets),
								String.valueOf(numOfSuccess),
								String.valueOf(numOfFail)
						},
						File.separator);
		
	}//_doInBackground__UpdateTable

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer res) {
		// TODO Auto-generated method stub
		super.onPostExecute(res);
		
		String message;

		int iRes = res.intValue();
		
		switch(iRes) {
		
		case CONS.RetVal.GetWordList_Failed:
			
			message = "Get WordList => Failed";
			
			break;
			
		case CONS.RetVal.GETYOMI_NO_ENTRY:
			
			message = "Get WordList => No entry";
			
			break;
			
		default:
			
			message = "Unknown value => " + String.valueOf(iRes);
			
			break;
		}
		
		if (iRes >= CONS.RetVal.MAGINITUDE_ONE) {
			
			message = "Get Yomi => Done ("
					+ String.valueOf(iRes - CONS.RetVal.MAGINITUDE_ONE)
					+ ")";
			
		}
		
		
		// debug
		Toast.makeText(actv, message, Toast.LENGTH_LONG).show();
//		Toast.makeText(actv, "Task_GetYomi => Done", Toast.LENGTH_LONG).show();
		
		// Log
		Log.d("[" + "Task_GetYomi.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", message);
		
	}//protected void onPostExecute(Integer res)

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

}//public class Task_GetYomi extends AsyncTask<String, Integer, Integer>
