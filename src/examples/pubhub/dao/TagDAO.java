package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Tag;
import examples.pubhub.model.Book;

public interface TagDAO {
	
	//A method to retrieve all tags that have been added to a given book
	public List<Tag> getAllTags(String isbn13);	
	//A method to retrieve all books that have a given tag. Hint: This will require either a SQL JOIN statement or a nested query.
	public List<Book> getBooksByTag(String tag);
	
	//A method to add a tag to a book, given the tag name and a reference to a book (either a Book reference variable or just an ISBN-13)
	public boolean addTag(String tag, String isbn13);
	//A method to remove a tag from a book, given the tag name and a reference to a book (either a Book reference variable or just an ISBN-13)
	public boolean deleteTag(String tag, String isbn13);
	
}
