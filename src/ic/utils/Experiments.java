package ic.utils;

import ic.items.Item;

import java.io.File;import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//import org.apache.commons.lang.StringUtils;

public class Experiments {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 4
//		System.out.println("args[0]: " + args[0]);
		int old_num;
		int new_num;
		
		if (args.length > 1) {

			old_num = Integer.parseInt(args[0]);
			new_num = Integer.parseInt(args[1]);
			
		} else {//if (args.length > 1)
			
			old_num = 2;
			new_num = 0;
			
		}//if (args.length > 1)
		
		change_order_3(old_num, new_num);

//		// 3
////		System.out.println("args[0]: " + args[0]);
//		int old_num;
//		int new_num;
//		
//		if (args.length > 1) {
//
//			old_num = Integer.parseInt(args[0]);
//			new_num = Integer.parseInt(args[1]);
//			
//		} else {//if (args.length > 1)
//			
//			old_num = 2;
//			new_num = 0;
//			
//		}//if (args.length > 1)
//		
//		change_order_2(old_num, new_num);
		
//		// 2
//		change_order();
		
		// 1
////		String test = "123";
//		String test = "123a";
//		
//		
//		System.out.println(test + ": " + is_numeric(test));

	}

	public static boolean is_numeric(String num_string) {
		
		return num_string.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+");
//		return num_string.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+ ");
//		return num_string.matches("(-|\\+)?[0-9]+(\\.[0-9]+)? ");
//		return num_string.matches("[0-9]+(\\.[0-9]+)? ");

	}//public static boolean is_numeric(String num_string)

	public static void change_order_3(int old_num, int new_num) {
		
		String[] data = {"a-0", "b-1", "c-2", "d-3", "e-4", "f-5", "g-6"};
		
		// Show the original
		for (int i = 0; i < data.length; i++) {
			
			System.out.println(i + ": " + data[i]);
			
		}
		
		// Process each element: Numbers before target
//		int i;

		if (old_num < new_num) {
			
			System.out.println("["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]" + "old_num < new_num");
			
			change_order_3_old_smaller_than_new(old_num, new_num, data);
			
		} else if (old_num >= new_num) {//if (old_num >= new_num)

			System.out.println("["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]" + "old_num >= new_num");

			change_order_3_old_larger_than_new(old_num, new_num, data);
			
		} else {//if (old_num >= new_num)
			
		}//if (old_num >= new_num)
		
		// Sort
		sort_by_number(data);
		
		// Show again
		System.out.println();
		
		for (int j = 0; j < data.length; j++) {
			
			System.out.println(j + ": " + data[j]);
			
		}
		
		
	}//public static void change_order()

	private static void change_order_3_old_larger_than_new(int old_num,
			int new_num, String[] data) {
		
		int i;
		
		for (i = new_num; i < old_num; i++) {
		
		String[] temp = data[i].split("-");
		
		temp[1] = String.valueOf(Integer.parseInt(temp[1]) + 1);
		
//		data[i] = StringUtils.join(temp);
//		data[i] = join(temp, File.separator);
		data[i] = join(temp, "-");
		
	}
	
	// Process the target
	String[] temp = data[old_num].split("-");
	
	temp[1] = String.valueOf(new_num);
	
	data[old_num] = join(new String[]{temp[0], temp[1]}, "-");
		
	}

	private static void change_order_3_old_smaller_than_new(int old_num,
			int new_num, String[] data) {
		
		System.out.println("old smaller than new");
		
		int i;
		
		for (i = old_num + 1; i <= new_num; i++) {
			
			String[] temp = data[i].split("-");
			
//			temp[1] = String.valueOf(Integer.parseInt(temp[1]) + 1);
			temp[1] = String.valueOf(Integer.parseInt(temp[1]) - 1);
			
//			data[i] = StringUtils.join(temp);
//			data[i] = join(temp, File.separator);
			data[i] = join(temp, "-");
			
		}
		
		// Process the target
		String[] temp = data[old_num].split("-");
		
		temp[1] = String.valueOf(new_num);
		
		data[old_num] = join(new String[]{temp[0], temp[1]}, "-");
		
	}

	public static void change_order() {
		
		String[] data = {"a-0", "b-1", "c-2", "d-3", "e-4"};
		
		// Show the original
		for (int i = 0; i < data.length; i++) {
			
			System.out.println(i + ": " + data[i]);
			
		}
		
		// Process each element: Numbers before target
		int old_num = 2; int new_num = 0;
		
		int i;
		
//		for (int i = new_num; i < old_num - 1; i++) {
//		for (int i = new_num; i < old_num; i++) {
		for (i = new_num; i < old_num; i++) {
			
			String[] temp = data[i].split("-");
			
			temp[1] = String.valueOf(Integer.parseInt(temp[1]) + 1);
			
//			data[i] = StringUtils.join(temp);
//			data[i] = join(temp, File.separator);
			data[i] = join(temp, "-");
			
		}
		
		i += 1;
		
		// Process each element: Numbers after target
		for (; i < data.length; i++) {
			
			String[] temp = data[i].split("-");
			
			temp[1] = String.valueOf(Integer.parseInt(temp[1]) + 1);
			
//			data[i] = StringUtils.join(temp);
//			data[i] = join(temp, File.separator);
			data[i] = join(temp, "-");
			
		}
		
		// Process the target
		String[] temp = data[old_num].split("-");
		
		temp[1] = String.valueOf(new_num);
		
		data[old_num] = join(new String[]{temp[0], temp[1]}, "-");
		
		
		// Show again
		System.out.println();
		
		for (int j = 0; j < data.length; j++) {
			
			System.out.println(j + ": " + data[j]);
			
		}
		
		
	}//public static void change_order()

	public static void change_order_2(int old_num, int new_num) {
		
		String[] data = {"a-0", "b-1", "c-2", "d-3", "e-4"};
		
		// Show the original
		for (int i = 0; i < data.length; i++) {
			
			System.out.println(i + ": " + data[i]);
			
		}
		
		// Process each element: Numbers before target
//		int old_num = 2; int new_num = 0;
		
		int i;
		
		for (i = new_num; i < old_num; i++) {
			
			String[] temp = data[i].split("-");
			
			temp[1] = String.valueOf(Integer.parseInt(temp[1]) + 1);
			
//			data[i] = StringUtils.join(temp);
//			data[i] = join(temp, File.separator);
			data[i] = join(temp, "-");
			
		}
		
		// Process the target
		String[] temp = data[old_num].split("-");
		
		temp[1] = String.valueOf(new_num);
		
		data[old_num] = join(new String[]{temp[0], temp[1]}, "-");
		
		// Sort
		sort_by_number(data);
		
		// Show again
		System.out.println();
		
		for (int j = 0; j < data.length; j++) {
			
			System.out.println(j + ": " + data[j]);
			
		}
		
		
	}//public static void change_order()

//	private class StringUtils {
		
	public static String join(String[] data, String separator) {
		
		StringBuilder sb = new StringBuilder();
		
		int i;
		
		for (i = 0; i < data.length - 1; i++) {
			
			sb.append(data[i] + separator);
		}
		
		sb.append(data[i]);
		
		return sb.toString();
	}
//	}

	public static void sort_by_number(String[] data) {
		
		Comparator<String> comp = new Comparator<String>(){

			public int compare(String s1, String s2) {
				/*********************************
				 * 1. Get genre name
				 * 2. Null?
				 * 
				 * 3. Genre names => Not equal?
				 * 4. Genre names => Equal?
				 *********************************/
				int num_s1 = Integer.parseInt(s1.split("-")[1]);
				int num_s2 = Integer.parseInt(s2.split("-")[1]);
				
				return num_s1 - num_s2;
//				return -(i1_status - i2_status);
				
			}//public int compare(Item i1, Item i2)
			
		};//Comparator<CL> comp

		Arrays.sort(data, comp);
		
	}//public static void sort_by_number(String[] data)
}
