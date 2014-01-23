package sl.libs.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class YahooFurigana {

	static JSONObject joRoot;
	
	String keyWord;

	private YahooFurigana() {
		
	}

	private YahooFurigana(String keyWord) {
		
		this.keyWord = keyWord;
		
	}
	
	public static YahooFurigana getInstanceWithKeyWord(String keyWord) {
	
		YahooFurigana.joRoot =
				YahooFurigana.getJsonObjectFromKeyWord(keyWord);
		
//		String kw = "洗濯網（中）";
		
		
		
//		String url = "http://benfranklin.chips.jp/Learn_php/01/01_get_furigana.php?kw="
////				+ kw;
//				+ keyWord;
//
//		// Log
//		Log.d("YahooFurigana.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "url=" + url);
//
//		/*********************************
//		 * Get: HttpResponse object
//		 *********************************/
//		HttpPost httpPost = new HttpPost(url);
//		
//		httpPost.setHeader("Content-type", "application/json");
//		
//		HttpUriRequest postRequest = httpPost;
//		
//		DefaultHttpClient dhc = new DefaultHttpClient();
//		
//		HttpResponse hr = null;
//		
//		try {
//			
//			hr = dhc.execute(postRequest);
//			
//		} catch (ClientProtocolException e) {
//			
//			// Log
//			Log.e("YahooFurigana.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			return null;
//			
//		} catch (IOException e) {
//			
//			// Log
//			Log.e("YahooFurigana.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			return null;
//			
//		}
//		
//
//		/*----------------------------
//		 * 6. Response null?
//			----------------------------*/
//		if (hr == null) {
//
//			// Log
//			Log.d("YahooFurigana.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "hr == null");
//			
//			return null;
//			
//		} else {//if (hr == null)
//			
//			// Log
//			Log.d("YahooFurigana.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "hr => Not null");
//			
//		}//if (hr == null)
//
//		/*********************************
//		 * Status code
//		 *********************************/
//		int status = hr.getStatusLine().getStatusCode();
//		
//		// Log
//		Log.d("YahooFurigana.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "status=" + status);
//		
//		/*********************************
//		 * Entity
//		 *********************************/
//		HttpEntity entity = hr.getEntity();
//		
//		// Log
//		Log.d("YahooFurigana.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "entity.toString()=" + entity.toString());
//		
//		/*********************************
//		 * JSONObject
//		 *********************************/
//		try {
//			
//			YahooFurigana.joRoot = new JSONObject(EntityUtils.toString(entity));
//			
//		} catch (ParseException e) {
//			
//			// Log
//			Log.e("YahooFurigana.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			return null;
//			
//		} catch (JSONException e) {
//
//			// Log
//			Log.e("YahooFurigana.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			return null;
//
//		} catch (IOException e) {
//
//			// Log
//			Log.e("YahooFurigana.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			return null;
//			
//		}//try
//		
//		// Log
//		Log.d("YahooFurigana.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "JSONObject => Created");
		
		return new YahooFurigana(keyWord);
		
	}//public static YahooFurigana getInstanceWithKeyWord(String keyWord)

	public static YahooFurigana getInstance() {
		
		return new YahooFurigana();
		
	}
	
	private static JSONObject getJsonObjectFromKeyWord(String keyWord) {
		String url = "http://benfranklin.chips.jp/Learn_php/01/01_get_furigana.php?kw="
//				+ kw;
				+ keyWord;

		// Log
		Log.d("YahooFurigana.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "url=" + url);

		/*********************************
		 * Get: HttpResponse object
		 *********************************/
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setHeader("Content-type", "application/json");
		
		HttpUriRequest postRequest = httpPost;
		
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpResponse hr = null;
		
		try {
			
			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Executing HttpClient...");
			
			hr = dhc.execute(postRequest);
			
			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Response obtained!");
			
		} catch (ClientProtocolException e) {
			
			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {
			
			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			
			
			return null;
			
		}
		

		/*----------------------------
		 * 6. Response null?
			----------------------------*/
		if (hr == null) {

			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr == null");
			
			return null;
			
		} else {//if (hr == null)
			
			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "hr => Not null");
			
		}//if (hr == null)

		/*********************************
		 * Status code
		 *********************************/
		int status = hr.getStatusLine().getStatusCode();
		
		// Log
		Log.d("YahooFurigana.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "status=" + status);
		
		/*********************************
		 * Entity
		 *********************************/
		HttpEntity entity = hr.getEntity();
		
		// Log
		Log.d("YahooFurigana.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "entity.toString()=" + entity.toString());
		
		/*********************************
		 * JSONObject
		 *********************************/
		try {
			
//			YahooFurigana.joRoot = new JSONObject(EntityUtils.toString(entity));

			
			return new JSONObject(EntityUtils.toString(entity));
			
		} catch (ParseException e) {
			
			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (JSONException e) {

			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;

		} catch (IOException e) {

			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
		
//		// Log
//		Log.d("YahooFurigana.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "JSONObject => Created");

//		return null;
	}//private static JSONObject getJsonObjectFromKeyWord(String keyWord)

	public static JSONObject getJoRoot() {
		return joRoot;
	}

	public static void setJoRoot(JSONObject joRoot) {
		YahooFurigana.joRoot = joRoot;
	}


	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/*********************************
	 * 20130220_163033
	 * @param	flag	Strictness of the conversion. If set <i>true</i>, when the furigana
	 * 					tag is missing in the returned JSON data, the surface tag value
	 * 					will be used in constructing the furigana string.
	 * @return	The constructed furigana string. If any error, returns <i>null</i>.
	 * @see		YahooFurigana: public String getFurigana(String keyWord, boolean flag)
	 *********************************/
	public String getFurigana_B18_v_5_0_e_1_t_1(boolean flag) {
		/*********************************
		 * joRoot => Initialized?
		 *********************************/
		if (joRoot == null) {
			
			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "joRoot => Null");
			
			return null;
			
		}//if (joRoot == null)
		
		/*********************************
		 * Get string
		 *********************************/
		/*********************************
		 * Parse JSONObject
		 *********************************/
		JSONObject jsonResult = null;
		
		String tagName = "Result";
		
		try {
			
			jsonResult = joRoot.getJSONObject(tagName);

			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "JSONObject => Created (Tag=" + tagName + ")");

		} catch (JSONException e) {
			
			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
		
		/*********************************
		 * Parse: WordList
		 *********************************/
//		JSONArray jsArray = null;
		
		JSONObject joWordList = null;

		tagName = "WordList";
//		String tagName = "abc";
//		String tagName = "Word";
		
		try {
			
//			jsArray = jsonResult.getJSONArray(tagName);
			joWordList = jsonResult.getJSONObject(tagName);
			
			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "JSONObject => Created (Tag=" + tagName + ")");

		} catch (JSONException e) {
			
			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
		
		/*********************************
		 * Words
		 *********************************/
		JSONArray jaWords = null;

		JSONObject joWord = null;
		
		tagName = "Word";
		
		try {
			
//			Object obj = joWordList.getJSONArray(tagName);
			jaWords = joWordList.getJSONArray(tagName);

//			// Log
//			Log.d("YahooFurigana.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]",
//					"obj=" + obj.getClass().getName());
			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "jaWords => Obtained");
			
		} catch (JSONException e) {
			
			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			try {
				
				JSONObject joWord_single = joWordList.getJSONObject(tagName);

//				String furi = YahooFurigana.getFuriganaFromJSONObjectWord(joWord);
				String furi = YahooFurigana.getFuriganaFromJSONObjectWord(joWord_single);
				
				// Log
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "furi=" + furi);
				
				return furi;
				

			} catch (JSONException e1) {
				
				// Log
				Log.e("YahooFurigana.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", e.toString());

				return null;
				
			}//try
			
		}//try

		/*********************************
		 * List<String> furiList
		 *********************************/
		List<String> furiList = new ArrayList<String>();
		
		/*********************************
		 * StringBuilder
		 *********************************/
		StringBuilder sb = new StringBuilder();
		
		/*********************************
		 * Iterate
		 *********************************/
		for (int i = 0; i < jaWords.length(); i++) {
			
			try {
				
//				JSONObject joWord = jaWords.getJSONObject(i);
				joWord = jaWords.getJSONObject(i);
				
				// Log
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "joWord => Created");

//						=> [528:getFurigana](6448): joWord => Created
						
				String tagSurface = "Surface";
				
//				JSONArray jaSurfaces = joWord.getJSONArray(tagSurface);
				
				/*********************************
				 * Get keys
				 *********************************/
				Iterator keys = joWord.keys();
				
				List<String> keyList = new ArrayList<String>();
				
				while (keys.hasNext()) {
					String key = (String) keys.next();
					
					
//					keyList.add((String) keys.next());
					keyList.add(key);
					
//					// Log
//					Log.d("YahooFurigana.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber()
//							+ ":"
//							+ Thread.currentThread().getStackTrace()[2]
//									.getMethodName() + "]", "key=" + key);
					
				}//while (keys.hasNext())
				
				// Log
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"keyList.size()=" + keyList.size());
				
				/*********************************
				 * Has "Furigana" key?
				 * 	=> If yes: Add the value to furiList
				 * 	=> If no: Add the value of 'Surface' key to furiList
				 *********************************/
				String targetKey = "Furigana";
				
				String surfaceKey = "Surface";
				
				if (keyList.contains(targetKey)) {
					
					// Log
					Log.d("YahooFurigana.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"Furigana=" + joWord.getString(targetKey));
					
					furiList.add(joWord.getString(targetKey));
					
				} else {//if (keyList.contains(""))
					
					// Log
					Log.d("YahooFurigana.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"No 'Furigana' key; 'Surface'=" + joWord.getString(surfaceKey));
					
					furiList.add(joWord.getString(surfaceKey));
					
				}//if (keyList.contains(""))
				
				/*********************************
				 * furiList => Convert to a string
				 *********************************/
				sb = new StringBuilder();
				
				for (int j = 0; j < furiList.size(); j++) {
					
					sb.append(furiList.get(j));
					
					// Log
					Log.d("YahooFurigana.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"item=" + furiList.get(j));
					
				}//for (int j = 0; j < furiList.size(); j++)
				
				// Log
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Furigana is=" + sb.toString());
				
				
				
//				String jaSurfaces = joWord.getJSONArray(tagSurface);
//				
//				// Log
//				Log.d("YahooFurigana.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"Number of surfaces=" + jaSurfaces.length());
				
			} catch (JSONException e) {

				// Log
				Log.e("YahooFurigana.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", e.toString());

			}
			
		}//for (int i = 0; i < jaWords.length(); i++)
		
		/*********************************
		 * Return
		 *********************************/
		return sb.toString();
		
		
//		return null;
		
	}//public String getFurigana_B18_v_5_0_e_1_t_1(boolean flag)

	/*********************************
	 * 20130220_163033
	 * @param	flag	Strictness of the conversion. If set <i>true</i>, when the furigana
	 * 					tag is missing in the returned JSON data, the surface tag value
	 * 					will be used in constructing the furigana string.
	 * @return	The constructed furigana string. If any error, returns <i>null</i>.
	 * @see		YahooFurigana: public String getFurigana(String keyWord, boolean flag)
	 *********************************/
	public String getFurigana(boolean flag) {
		/*********************************
		 * joRoot => Initialized?
		 *********************************/
		if (joRoot == null) {
			
			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "joRoot => Null");
			
			return null;
			
		}//if (joRoot == null)
		
		/*********************************
		 * Get string
		 *********************************/
		/*********************************
		 * Parse JSONObject
		 *********************************/
		JSONObject jsonResult = null;
		
		String tagName = "Result";
		
		try {
			
			jsonResult = joRoot.getJSONObject(tagName);

			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "JSONObject => Created (Tag=" + tagName + ")");

		} catch (JSONException e) {
			
			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
		
		/*********************************
		 * Parse: WordList
		 *********************************/
//		JSONArray jsArray = null;
		
		JSONObject joWordList = null;

		tagName = "WordList";
//		String tagName = "abc";
//		String tagName = "Word";
		
		try {
			
//			jsArray = jsonResult.getJSONArray(tagName);
			joWordList = jsonResult.getJSONObject(tagName);
			
			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "JSONObject => Created (Tag=" + tagName + ")");

		} catch (JSONException e) {
			
			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
		
		/*********************************
		 * Words
		 *********************************/
		JSONArray jaWords = null;

		JSONObject joWord = null;
		
		tagName = "Word";
		
		try {
			
//			Object obj = joWordList.getJSONArray(tagName);
			jaWords = joWordList.getJSONArray(tagName);

//			// Log
//			Log.d("YahooFurigana.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]",
//					"obj=" + obj.getClass().getName());
			// Log
			Log.d("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "jaWords => Obtained");
			
		} catch (JSONException e) {
			
			// Log
			Log.e("YahooFurigana.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			try {
				
				JSONObject joWord_single = joWordList.getJSONObject(tagName);

//				String furi = YahooFurigana.getFuriganaFromJSONObjectWord(joWord);
				String furi = YahooFurigana.getFuriganaFromJSONObjectWord(joWord_single);
				
				// Log
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "furi=" + furi);
				
				return furi;
				

			} catch (JSONException e1) {
				
				// Log
				Log.e("YahooFurigana.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", e.toString());

				return null;
				
			}//try
			
		}//try

		/*********************************
		 * List<String> furiList
		 *********************************/
		List<String> furiList = new ArrayList<String>();
		
		/*********************************
		 * StringBuilder
		 *********************************/
		StringBuilder sb = new StringBuilder();
		
		/*********************************
		 * Iterate
		 *********************************/
		for (int i = 0; i < jaWords.length(); i++) {
			
			try {
				
//				JSONObject joWord = jaWords.getJSONObject(i);
				joWord = jaWords.getJSONObject(i);
				
				// Log
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "joWord => Created");

//						=> [528:getFurigana](6448): joWord => Created
						
				String tagSurface = "Surface";
				
//				JSONArray jaSurfaces = joWord.getJSONArray(tagSurface);
				
				/*********************************
				 * Get keys
				 *********************************/
				Iterator keys = joWord.keys();
				
				List<String> keyList = new ArrayList<String>();
				
				while (keys.hasNext()) {
					String key = (String) keys.next();
					
					
//					keyList.add((String) keys.next());
					keyList.add(key);
					
//					// Log
//					Log.d("YahooFurigana.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber()
//							+ ":"
//							+ Thread.currentThread().getStackTrace()[2]
//									.getMethodName() + "]", "key=" + key);
					
				}//while (keys.hasNext())
				
				// Log
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"keyList.size()=" + keyList.size());
				
				/*********************************
				 * Has "Furigana" key?
				 * 	=> If yes: Add the value to furiList
				 * 	=> If no: Add the value of 'Surface' key to furiList
				 *********************************/
				String targetKey = "Furigana";
				
				String surfaceKey = "Surface";
				
				if (keyList.contains(targetKey)) {
					
					// Log
					Log.d("YahooFurigana.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"Furigana=" + joWord.getString(targetKey));
					
					furiList.add(joWord.getString(targetKey));
					
				} else {//if (keyList.contains(""))
					
					// Log
					Log.d("YahooFurigana.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"No 'Furigana' key; 'Surface'=" + joWord.getString(surfaceKey));
					
					furiList.add(joWord.getString(surfaceKey));
					
				}//if (keyList.contains(""))
				
				/*********************************
				 * furiList => Convert to a string
				 *********************************/
				sb = new StringBuilder();
				
				for (int j = 0; j < furiList.size(); j++) {
					
					sb.append(furiList.get(j));
					
					// Log
					Log.d("YahooFurigana.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
							"item=" + furiList.get(j));
					
				}//for (int j = 0; j < furiList.size(); j++)
				
				// Log
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Furigana is=" + sb.toString());
				
				
				
//				String jaSurfaces = joWord.getJSONArray(tagSurface);
//				
//				// Log
//				Log.d("YahooFurigana.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"Number of surfaces=" + jaSurfaces.length());
				
			} catch (JSONException e) {

				// Log
				Log.e("YahooFurigana.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", e.toString());

			}
			
		}//for (int i = 0; i < jaWords.length(); i++)
		
		/*********************************
		 * Return
		 *********************************/
		return sb.toString();
		
		
//		return null;
		
	}//public String getFurigana(boolean flag)

	private static String getFuriganaFromJSONObjectWord(JSONObject joWord) {
		
		String tagSurface = "Surface";
		
		/*********************************
		 * Get keys
		 *********************************/
		Iterator keys = joWord.keys();
		
		List<String> keyList = new ArrayList<String>();
		
		while (keys.hasNext()) {
			String key = (String) keys.next();
			
			
			keyList.add(key);
			
		}//while (keys.hasNext())
		
		// Log
		Log.d("YahooFurigana.java"
				+ "["
				+ Thread.currentThread().getStackTrace()[2]
						.getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2]
						.getMethodName() + "]",
				"keyList.size()=" + keyList.size());
		
		/*********************************
		 * Has "Furigana" key?
		 * 	=> If yes: Add the value to furiList
		 * 	=> If no: Add the value of 'Surface' key to furiList
		 *********************************/
		String targetKey = "Furigana";
		
		String surfaceKey = "Surface";
		
		/***************************************
		 * 1. If Word has "Furigana" key, then try to 
		 * 		extract the value
		 * 2. If exception occurrs, then try to extract
		 * 		the value of the key "Surface"
		 * 3. If 2 also fails, then return null
		 ***************************************/	
		if (keyList.contains(targetKey)) {
			
			// Log
			try {
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Furigana=" + joWord.getString(targetKey));
				
				return joWord.getString(targetKey);
				
			} catch (JSONException e) {
				
				/***************************************
				 * "Surface"
				 ***************************************/
				// Log
				Log.e("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", e.toString());
				
				try {
					
					return joWord.getString(surfaceKey);
					
				} catch (JSONException e1) {

					// Log
					Log.d("YahooFurigana.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]", e1.toString());
					
					return null;
					
				}//try
				
			}//try
			
		} else {//if (keyList.contains(""))
			
			// Log
			try {
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"No 'Furigana' key; 'Surface'=" + joWord.getString(surfaceKey));
				
				return joWord.getString(surfaceKey);
				
			} catch (JSONException e) {

				// Log
				Log.d("YahooFurigana.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", e.toString());
				
				return null;
				
			}
			
//			return joWord.getString(surfaceKey);
			
		}//if (keyList.contains(""))
		
	}//private static String getFuriganaFromJSONObjectWord(JSONObject joWord)

	/*********************************
	 * 作成日：20130220_161907
	 * The method internally uses getFurigana(boolean flag)<br>
	 * 
	 * @param	keyWord	The word for which the furigana string will be sought
	 * @param	flag	Strictness of the conversion. If true, when the furigana
	 * 					tag is missing in the returned JSON data, the surface tag value
	 * 					will be used in constructing the furigana string.
	 * @return	The constructed furigana string(katakana-including).<br>
	 * 				If any error, returns <i>null</i>.
	 * @see		YahooFurigana: public String getFurigana(boolean flag)
	 *********************************/
	public String getFurigana(String keyWord, boolean flag) {
		// TODO Auto-generated method stub
		
		YahooFurigana.joRoot = YahooFurigana.getJsonObjectFromKeyWord(keyWord);
		
//		this.getFurigana(true);
		
		// Log
		Log.d("[" + "YahooFurigana.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Calling => getFurigana(flag)");
		
		return this.getFurigana(flag);
//		return this.getFurigana_B18_v_5_0_e_1_t_1(flag);
		
	}//public String getFurigana(String keyWord)

	public String getFurigana_B18_v_5_0_e_1_t_1(String keyWord, boolean flag) {
		// TODO Auto-generated method stub
		
		YahooFurigana.joRoot = YahooFurigana.getJsonObjectFromKeyWord(keyWord);
		
//		this.getFurigana(true);
		
//		return this.getFurigana(flag);
		return this.getFurigana_B18_v_5_0_e_1_t_1(flag);
		
	}//public String getFurigana_B18_v_5_0_e_1_t_1(String keyWord, boolean flag)

}//public class YahooFurigana
