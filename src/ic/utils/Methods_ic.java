package ic.utils;

import ic.items.CL;
import ic.main.MainActv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
					
					return (int)
							(cl1.getCreated_at() - cl2.getCreated_at());
					
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

}//public class Methods_ic
