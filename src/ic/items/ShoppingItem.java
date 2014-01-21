package ic.items;


public class ShoppingItem {

	//
	String	store, name, genre, yomi;
	int		price;
	int		id;
	
	String	created_at;
	String	updated_at;
	String	posted_at;

	public ShoppingItem(Builder builder) {

		store = builder.store;
		name = builder.name;
		genre = builder.genre;
		yomi = builder.yomi;
		price = builder.price;
		id = builder.id;
		
		this.created_at	= builder.created_at;
		this.updated_at	= builder.updated_at;
		this.posted_at	= builder.posted_at;
		
	}//public BM(Builder builder)

	public ShoppingItem(String store, String name, int price, String genre) {
		
		this.store = store;
		this.name = name;
		this.price = price;
		this.genre = genre;
		
	}//public ShoppingItem(String store, String name, int price, String genre)

	public ShoppingItem(String store, String name, int price, String genre, int id) {
		
		this.store = store;
		this.name = name;
		this.price = price;
		this.genre = genre;
		this.id = id;
		
	}//public ShoppingItem(String store, String name, int price, String genre)
	
	public ShoppingItem
		(String store, String name,
		int price, String genre, int id,
		String	created_at,
		String	updated_at,
		String	posted_at)
	{
		
		this.store = store;
		this.name = name;
		this.price = price;
		this.genre = genre;
		this.id = id;
		
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.posted_at = posted_at;
		
	}//public ShoppingItem(String store, String name, int price, String genre)

	public
	ShoppingItem
	(int id, String name, String yomi, String genre, String store, int price) {
		
		this.id = id;
		this.name = name;
		this.yomi = yomi;
		this.genre = genre;
		this.store = store;
		this.price = price;
		
		
		
	}//public ShoppingItem(String store, String name, int price, String genre)

	

	public
	ShoppingItem
	(int id, String store, String name, int price, String genre, String yomi) {

		this.id = id;
		this.name = name;
		this.yomi = yomi;

		this.genre = genre;
		this.store = store;
		this.price = price;

	}

	public ShoppingItem() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public String getCreated_at() {
		return created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getPosted_at() {
		return posted_at;
	}

	public void setPosted_at(String posted_at) {
		this.posted_at = posted_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getStore() {
		return store;
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	public int getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}

	public String getYomi() {
		return yomi;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static class Builder {
		
		private String store, name, genre, yomi;
		private int price;
		private int id;
		
		private String	created_at;
		private String	updated_at;
		private String	posted_at;
		
		public ShoppingItem build() {
			
			return new ShoppingItem(this);
			
		}
		
		public Builder setStore(String store) {
			this.store = store;	return this;
		}
		public Builder setCreated_at(String created_at) {
			this.created_at = created_at;	return this;
		}

		public Builder setUpdated_at(String updated_at) {
			this.updated_at = updated_at;	return this;
		}

		public Builder setPosted_at(String posted_at) {
			this.posted_at = posted_at;	return this;
		}

		public Builder setName(String name) {
			this.name = name;	return this;
		}
		public Builder setGenre(String genre) {
			this.genre = genre;	return this;
		}
		public Builder setYomi(String yomi) {
			this.yomi = yomi;	return this;
		}
		public Builder setPrice(int price) {
			this.price = price;	return this;
		}
		public Builder setId(int id) {
			this.id = id;	return this;
		}

		
		
	}//public static class Builder
}
