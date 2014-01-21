package ic.items;

public class PS {

	private long dbId, created_at, modified_at, dueDate, amount;
	
	private String storeName, memo, items;

	public long getDbId() {
		return dbId;
	}

	public long getCreated_at() {
		return created_at;
	}

	public long getModified_at() {
		return modified_at;
	}

	public long getDueDate() {
		return dueDate;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getMemo() {
		return memo;
	}

	public String getItems() {
		return items;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	public void setModified_at(long modified_at) {
		this.modified_at = modified_at;
	}

	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
}//public class PS

