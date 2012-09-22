package ic.utils;

public class Experiments {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		String test = "123";
		String test = "123a";
		
		
		System.out.println(test + ": " + is_numeric(test));

	}

	public static boolean is_numeric(String num_string) {
		
		return num_string.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+");
//		return num_string.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+ ");
//		return num_string.matches("(-|\\+)?[0-9]+(\\.[0-9]+)? ");
//		return num_string.matches("[0-9]+(\\.[0-9]+)? ");

	}//public static boolean is_numeric(String num_string)

}
