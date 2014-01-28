package ic.utils;

import ic.items.CL;
import ic.main.MainActv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

public class Methods_ic {

	public static boolean
	sort_CheckList_ItemName(Activity actv) {
		// TODO Auto-generated method stub
		Collections.sort(MainActv.CLList, new Comparator<CL>(){

			public int compare(CL cl1, CL cl2) {
				// TODO Auto-generated method stub
				
				
				return (int) (cl1.getName().compareToIgnoreCase(cl2.getName()));
			}
			
		});//Collections.sort()

		return true;
		
	}//sort_CheckList_ItemName(Activity actv)
	
	public static boolean
	sort_CheckList(Activity actv, CONS.Admin.SortTypes type) {
		// TODO Auto-generated method stub
		
		CONS.Admin.sortType = type;
		
		Collections.sort(MainActv.CLList, new Comparator<CL>(){
			
			public int compare(CL cl1, CL cl2) {
				// TODO Auto-generated method stub
				
				switch(CONS.Admin.sortType) {
				
				case SortBy_Yomi:
					
					if (cl1.getYomi() == null) {
						
						// Log
						Log.d("["
								+ "Methods_ic.java : "
								+ +Thread.currentThread().getStackTrace()[2]
										.getLineNumber()
								+ " : "
								+ Thread.currentThread().getStackTrace()[2]
										.getMethodName() + "]",
						"cl1.getYomi => null");
						
						return 1;
						
					} else	if (cl2.getYomi() == null) {
						
						// Log
						Log.d("["
								+ "Methods_ic.java : "
								+ +Thread.currentThread().getStackTrace()[2]
										.getLineNumber()
										+ " : "
										+ Thread.currentThread().getStackTrace()[2]
												.getMethodName() + "]",
								"cl2.getYomi => null");
							
						return 1;
						
					}
					
//					} else {
//						
//						// Log
//						Log.d("["
//								+ "Methods_ic.java : "
//								+ +Thread.currentThread().getStackTrace()[2]
//										.getLineNumber()
//								+ " : "
//								+ Thread.currentThread().getStackTrace()[2]
//										.getMethodName() + "]",
//						"cl1=" + cl1.getYomi()
//						+ "/"
//						+ "cl2=" + cl2.getYomi());
//						
//					}
					
					return (int)
						(cl1.getYomi().compareToIgnoreCase(cl2.getYomi()));
					
				case SortBy_CreatedAt:
					
					// Log
					String msg = "cl1 created_at="
								+ String.valueOf(cl1.getCreated_at())
								+ "/"
								+ "cl2 created_at="
								+ String.valueOf(cl2.getCreated_at())
								+ "("
								+ String.valueOf(cl1.getCreated_at() - cl2.getCreated_at())
								+ ")";
					
					
					Log.d("["
							+ "Methods_ic.java : "
							+ +Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ " : "
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]", msg);
					
					long diff = (cl1.getCreated_at() - cl2.getCreated_at());
					
					if (diff > 0) {
						
						return 1;
						
					} else if (diff < 0){//if (diff > 0)
						
						return -1;
						
					} else {//if (diff > 0)
						
						return 0;
						
					}//if (diff > 0)
					
					
//					return (int)
////							(cl2.getCreated_at() - cl1.getCreated_at());
//							(cl1.getCreated_at() - cl2.getCreated_at());
					
				default:
					
					return (int)
							(cl1.getYomi().compareToIgnoreCase(cl2.getYomi()));
					
				}//switch(CONS.Admin.sortType) {
				
//				return (int) (cl1.getName().compareToIgnoreCase(cl2.getName()));
				
			}//public int compare(CL cl1, CL cl2)
			
		});//Collections.sort()
		
		return true;
		
	}//sort_CheckList_ItemName(Activity actv)

	public static List<CL>
	build_CL(Activity actv, Cursor c) {
		// TODO Auto-generated method stub
		List<CL> CLList = new ArrayList<CL>();

		for (int i = 0; i < c.getCount(); i++) {
	
			CL cl = new CL.Builder()
						.setDb_id(c.getLong(0))
						.setCreated_at(c.getLong(1))
						.setModified_at(c.getLong(2))
						.setName(c.getString(3))
						.setGenre_id(c.getInt(4))
						.setYomi(c.getString(5))
						.build();
						
			CLList.add(cl);
			
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)

		return CLList;
		
	}//build_CL(Activity actv, Cursor c)

	/*********************************
	 * @return -1	=> SocketException, IOException in connecting,
	 * 					logging in or disconnecting<br>
	 * 			-2	=> Login failed<br>
	 * 			Others	=> Reply code<br>
	 *********************************/
	public static int uploadDbFile(Activity actv) {
		/*********************************
		 * Setup: Pass and labels
		 *********************************/
		// FTP client
		FTPClient fp = new FTPClient();
		
		int reply_code;
		
		String server_name = CONS.FTPData.serverName;
//		String server_name = "ftp.benfranklin.chips.jp";
		
		String uname = CONS.FTPData.userName;

		String passwd = CONS.FTPData.passWord;
		
		String fpath = StringUtils.join(
				new String[]{
						CONS.DBAdmin.dirPath_db,
						CONS.DBAdmin.dbName
//						CONS.DB.dPath_db,
//						CONS.DB.fName_db
				}, File.separator);
		
		// Log
		Log.d("Methods_ic.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "fpath=" + fpath);
		
		String fpath_remote =
						"./"
						+ CONS.FTPData.dpath_Remote_Db
						+ "/"
						+ CONS.Admin.appName + "_"
						+ String.valueOf(
								Methods.get_TimeLabel(Methods.getMillSeconds_now()))
						+ CONS.DBAdmin.dbFileExt;
//						"./" + "ifm9." + String.valueOf(Methods.getMillSeconds_now())
//						+ ".db";

		// Log
		Log.d("Methods_ic.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "fpath_remote=" + fpath_remote);

		
		/*********************************
		 * Connect
		 *********************************/
		try {
			
			fp.connect(CONS.FTPData.serverName);
//			fp.connect(server_name);
			
			reply_code = fp.getReplyCode();
			
			// Log
			Log.d("Methods_ic.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fp.getReplyCode()=" + fp.getReplyCode());
			
		} catch (SocketException e) {
			
			// Log
			Log.e("Methods_ic.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return CONS.RetVal.FTP_Exception_Socket;
			
		} catch (IOException e) {

			// Log
			Log.e("Methods_ic.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return CONS.RetVal.EXCEPTION_IO;
		}
		
		
		/*********************************
		 * Log in
		 *********************************/
		boolean res;
		
		try {
			
			res = fp.login(CONS.FTPData.userName, CONS.FTPData.passWord);
//			res = fp.login(uname, passwd);
			
			if(res == false) {
				
				reply_code = fp.getReplyCode();
				
				// Log
				Log.e("Methods_ic.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Log in failed => " + reply_code);
				
				fp.disconnect();
				
				return CONS.RetVal.FTP_LOGIN_FAILED;
				
			} else {
				
				// Log
				Log.d("Methods_ic.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Log in => Succeeded");
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return CONS.RetVal.EXCEPTION_IO;
			
		}//try
		
		/*********************************
		 * FTP files
		 *********************************/
		// REF http://stackoverflow.com/questions/7740817/how-to-upload-an-image-to-ftp-using-ftpclient answered Oct 12 '11 at 13:52

		// �t�@�C�����M
		FileInputStream is;
		
		try {
			
			is = new FileInputStream(fpath);
//			is = new FileInputStream(fpath_audio);
			
			res = fp.setFileType(FTP.BINARY_FILE_TYPE);
			
//			fp.storeFile("./" + MainActv.fileName_db, is);// �T�[�o�[��
			res = fp.storeFile(fpath_remote, is);// �T�[�o�[��
			
//			fp.makeDirectory("./ABC");
			
			if (res == true) {
				
				// Log
				Log.d("Methods_ic.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "File => Stored");
				
			} else {//if (res == true)

				// Log
				Log.d("Methods_ic.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Store file => Failed");
				
				is.close();
				
				int i_res = uploadDbFile_Disconnect(fp);
				
				if (i_res == CONS.RetVal.OK) {
					
					return reply_code;
					
				} else {//if (i_res == CONS.RetVal.OK)
					
					return i_res;
					
				}//if (i_res == CONS.RetVal.OK)
				

			}//if (res == true)
			
			is.close();

		} catch (FileNotFoundException e) {

			// Log
			Log.e("Methods_ic.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			int i_res = uploadDbFile_Disconnect(fp);
			
			if (i_res == CONS.RetVal.OK) {
				
				return reply_code;
				
			} else {//if (i_res == CONS.RetVal.OK)
				
				return i_res;
				
			}//if (i_res == CONS.RetVal.OK)

			
		} catch (IOException e) {
			
			// Log
			Log.e("Methods_ic.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());

			int i_res = uploadDbFile_Disconnect(fp);
			
			if (i_res == CONS.RetVal.OK) {
				
				return reply_code;
				
			} else {//if (i_res == CONS.RetVal.OK)
				
				return i_res;
				
			}//if (i_res == CONS.RetVal.OK)

		}//try
						
		/*********************************
		 * Disconnect
		 *********************************/
		try {
			
			fp.disconnect();
			
			// Log
			Log.d("Methods_ic.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fp => Disconnected");

			return reply_code;
			
		} catch (IOException e) {
			
			// Log
			Log.e("Methods_ic.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return CONS.RetVal.EXCEPTION_IO;
			
		}
		
//		//debug
//		return CONS.RetVal.NOP;
		
	}//public static int uploadDbFile(Activity actv)

	public static int
	uploadDbFile_Disconnect(FTPClient fp) {

		try {
			
			fp.disconnect();
			
			// Log
			Log.d("Methods_ic.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fp => Disconnected");

			return CONS.RetVal.OK;
			
		} catch (IOException e) {
			
			// Log
			Log.e("Methods_ic.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return CONS.RetVal.EXCEPTION_IO;
			
		}

	}//uploadDbFile_Disconnect(FTPClient fp)
	
}//public class Methods_ic
