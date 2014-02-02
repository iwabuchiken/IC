
package adapters;

import ic.main.R;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LogAdapter extends ArrayAdapter<String> {

	Context con;
	
	// Inflater
	LayoutInflater inflater;

	public LogAdapter(Context con, int resourceId,
			List<String> list) {
		
		super(con, resourceId, list);
		
		// Context
		this.con = con;

		// Inflater
		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}//public MainListAdapter

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get view
    	View v = null;
    	
    	if (convertView != null) {

    		v = convertView;
    		
		} else {//if (convertView != null)

			v = inflater.inflate(R.layout.list_row_log_line, null);

		}//if (convertView != null)

    	// Log
		String log_msg2 = "Get views...";

		Log.d("[" + "LogAdapter.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg2);
		
		
    	TextView tv_Meta =
    			(TextView) v.findViewById(R.id.list_row_log_line_tv_meta);
    	
    	TextView tv_Content =
    			(TextView) v.findViewById(R.id.list_row_log_line_tv_content);

    	// Get: Text 
    	String fullText = getItem(position);

    	if (fullText == null) {
			
    		// Log
			String log_msg = "Get item => Null";

			Log.d("["
					+ "LogAdapter.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);
			
			return v;
    		
		}
    	
    	// Build texts
    	String fullText_a[] = fullText.split("\\]", 2);
    	
    	if (fullText_a.length < 2) {
			
    		// Log
			String log_msg = "Text length => < 2";

			Log.d("["
					+ "LogAdapter.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", log_msg);
			
			return v;
    		
		}
    	
    	//REF matcher http://stackoverflow.com/questions/9366742/android-regular-expression-return-matched-string answered Feb 20 '12 at 18:56
        String p1 = "\\[(.+)\\] (.+)";
        Pattern p = Pattern.compile(p1);
        Matcher m = p.matcher(fullText);
    	
        String meta = null;
        String content = null;
        
        if (!m.matches()) {
			
        	meta = fullText_a[0];
        	content = fullText_a[1];
        	
		} else {//if (!m.matches())
			
			meta = m.group(1);
			content = m.group(2);
			
		}//if (!m.matches())
		
    	
//    	String pattern = "\\[(.+)\\]";
//    	
//    	meta.replaceAll(pattern, "$1");
    	
		// Set text
    	tv_Meta.setText(meta);
    	tv_Content.setText(content);
    	
    	return v;
    	
	}//public View getView

	
}//public class MainListAdapter<T>
