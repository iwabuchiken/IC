package tasks;

import ic.utils.CONS;
import ic.utils.Methods;
import ic.utils.Methods_ic;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

//public class TaskFTP extends AsyncTask<String, Integer, String> {
public class TaskFTP extends AsyncTask<String, Integer, Integer> {

	Activity actv;
	
	String ftpTag;//=> Use this field for ftp_upload_db_file

	Vibrator vib;

	long	t_Start;
	long	t_End;
	
	
	public TaskFTP(Activity actv) {
		
		this.actv = actv;
		
	}
	
	@Override
//	protected String doInBackground(String... ftpTags) {
	protected Integer
	doInBackground(String... ftpTags) {

		ftpTag = ftpTags[0];
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
		Methods_ic.uploadDbFile(actv);
		
		return 0;
		
	}//doInBackground(String... ftpTags)

	@Override
//	protected void onPostExecute(String result) {
	protected void onPostExecute(Integer res) {
		
		super.onPostExecute(res);
		
		vib.vibrate(CONS.Admin.vib_Long);
		
		this.t_End = Methods.getMillSeconds_now();
		
		String elapsedTime =
				Methods.get_ElapsedTime(this.t_Start, this.t_End);
		
		// debug
		String msg = "Result(FTP) => " + String.valueOf(res)
					+ "(" + elapsedTime + ")";
		
		Toast.makeText(actv, msg,
				Toast.LENGTH_SHORT).show();
		
		// Log
		Log.d("["
				+ "TaskFTP.java : "
				+ Thread.currentThread().getStackTrace()[2]
						.getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2]
						.getMethodName() + "]",
				msg);
			
	}//protected void onPostExecute(String result)
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		this.t_Start = Methods.getMillSeconds_now();

	}
	
}
