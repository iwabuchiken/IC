package ic.items;

public class Items {

	String text;
	int serial_num;
	long list_id;
	long db_id;
	
	public Items(String text, int serial_num, long list_id) {

		this.text = text;
		this.serial_num = serial_num;
		this.list_id = list_id;

	}//public Items(String text, int serial_num, long list_id)

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(int serial_num) {
		this.serial_num = serial_num;
	}

	public long getList_id() {
		return list_id;
	}

	public void setList_id(long list_id) {
		this.list_id = list_id;
	}

	public long getDb_id() {
		return db_id;
	}

	public void setDb_id(long db_id) {
		this.db_id = db_id;
	}
	
	
	
}//public class Items
