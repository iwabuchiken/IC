package ic.utils;

import ic.items.CL;
import ic.main.MainActv;

import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;

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

}//public class Methods_ic
