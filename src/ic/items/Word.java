package ic.items;

import ic.items.ShoppingItem.Builder;

public class Word {

	long id;
	String name;
	
	/***************************************
	 * The value returned from Yahoo Furigana<br>
	 * The katakana remains to be a katakana
	 ***************************************/
	String combo;
	
	/***************************************
	 * The all-hiragana string converted from katakana-including<br>
	 * 		string, through a method in Methods.java
	 ***************************************/
//	String hiragana;
	String yomi;
	
	private Word() {
		
	}
	
	public Word(long itemId, String name) {

		this.id = itemId;
		this.name = name;
		
	}

	public Word(long itemId, String name, String combo) {
		
		this.id = itemId;
		this.name = name;
		this.combo = combo;
		
	}

	public Word(Builder builder) {

		this.id		= builder.id;
		this.name	= builder.name;
		this.yomi	= builder.yomi;
		
	}//public BM(Builder builder)

	
	public static Word getInstance() {
		
		return new Word();
		
	}

	/*********************************
	 * Getter/Setter
	 *********************************/
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCombo() {
		return combo;
	}

	public String getYomi() {
		return yomi;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}
	
	/*********************************
	 * Builder
	 *********************************/
	public static class Builder {
		
		private long	id;
		private String	name;
		private String	yomi;

		public Word build() {
			
			return new Word(this);
			
		}

		public Builder setId(long id) {
			this.id = id;	return this;
		}

		public Builder setName(String name) {
			this.name = name;	return this;
		}

		public Builder setYomi(String yomi) {
			this.yomi = yomi;	return this;
		}

		
		
		
	}//public static class Builder	
	
}//public class Word
