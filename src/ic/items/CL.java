package ic.items;

public class CL {

	String name;
	String yomi;
	
	int genre_id;
	long db_id;
	
	long created_at;
	long modified_at;
	
	
	
	public CL(String name, int genre_id) {
		
		this.name = name;
		this.genre_id = genre_id;
		
	}//public CL(String name, int genre_id)

	public CL(String name, int genre_id, 
			long db_id, long created_at, long modified_at) {
		
		this.name = name;
		this.genre_id = genre_id;
		
		this.db_id = db_id;
		this.created_at = created_at;
		this.modified_at = modified_at;
		
	}//public CL(String name, int genre_id)

	public CL(Builder builder) {

		this.name		= builder.name;
		this.yomi		= builder.yomi;
		
		this.genre_id	= builder.genre_id;
		this.db_id		= builder.db_id;
		
		this.created_at	= builder.created_at;
		this.modified_at	= builder.modified_at;
		
	}//public BM(Builder builder)

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getGenre_id() {
		return genre_id;
	}


	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}


	public long getDb_id() {
		return db_id;
	}


	public void setDb_id(long db_id) {
		this.db_id = db_id;
	}


	public long getModified_at() {
		return modified_at;
	}


	public void setModified_at(long modified_at) {
		this.modified_at = modified_at;
	}


	public long getCreated_at() {
		return created_at;
	}
	
	
	
	public String getYomi() {
		return yomi;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}



	public static class Builder {
		
		private String name;
		private String yomi;
		
		private int genre_id;
		
		private long db_id;
		private long created_at;
		private long modified_at;
		
		public CL build() {
			
			return new CL(this);
			
		}

		public Builder setName(String name) {
			this.name = name;	return this;
		}

		public Builder setYomi(String yomi) {
			this.yomi = yomi;	return this;
		}

		public Builder setGenre_id(int genre_id) {
			this.genre_id = genre_id;	return this;
		}

		public Builder setDb_id(long db_id) {
			this.db_id = db_id;	return this;
		}

		public Builder setCreated_at(long created_at) {
			this.created_at = created_at;	return this;
		}

		public Builder setModified_at(long modified_at) {
			this.modified_at = modified_at;	return this;
		}

		
		
	}//public static class Builder

}//public class CL
