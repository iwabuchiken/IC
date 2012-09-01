package ic.items;

public class CL {

	String name;
	int genre_id;
	long db_id;
	
	public CL(String name, int genre_id) {
		
		this.name = name;
		this.genre_id = genre_id;
		
	}

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
	
	
	
}//public class CL
