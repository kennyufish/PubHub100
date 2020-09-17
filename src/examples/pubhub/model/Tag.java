package examples.pubhub.model;

public class Tag {
	
	private String isbn13;
	private String tag_name;
	
	//constructor
	public Tag(String isbn, String tag) {
		this.isbn13 = isbn;
		this.tag_name = tag;
	}
	
	public Tag() {
		this.isbn13 = null;
		this.tag_name = null;
		
	}
	
	public String getIsbn13() {
		return isbn13;
	}
	
	public void setIsbn13(String isbn) {
		this.isbn13 = isbn;
	}
	
	public String getTag() {
		return tag_name;
	}
	
	public void setTag(String tag) {
		this.tag_name = tag;
	}
	
}
