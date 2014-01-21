package ic.items;

public class Furi {

	long id;
	String name;
	
	/***************************************
	 * The value returned from Yahoo Furigana<br>
	 * The katakana remains to be a katakana
	 ***************************************/
	String furi;
	
	/***************************************
	 * The all-hiragana string converted from katakana-including<br>
	 * 		string, through a method in Methods.java
	 ***************************************/
	String gana;
	
	private Furi() {
		
	}
	
	public Furi(long itemId, String name) {

		this.id = itemId;
		this.name = name;
		
	}

	public Furi(long itemId, String name, String yomi) {
		
		this.id = itemId;
		this.name = name;
		this.gana = yomi;
		
	}

	public static Furi getInstance() {
		
		return new Furi();
		
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getFuri() {
		return furi;
	}
	public String getGana() {
		return gana;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFuri(String furi) {
		this.furi = furi;
	}
	public void setGana(String gana) {
		this.gana = gana;
	}
	
	
}
