
package examples.pubhub.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.model.*;
import examples.pubhub.dao.*;

public class TestTagDAO {
	
	public static void main(String[] args) {
		TagDAO dao = new TagDAOImpl();
		String isbn13= "1111111111111";
		
		//test for add method
		dao.addTag("Pink", isbn13);
		
		//test for delete method
		dao.addTag("apple", isbn13);
		dao.deleteTag("apple", isbn13);
		
		//test for get all tags method
		List<Tag> tagList = dao.getAllTags(isbn13);
		
		for (int i = 0; i < tagList.size();i++) {
			Tag tag = tagList.get(i);
			System.out.println(tag.getTag());
		}
		
		//test for get all books by tag method
		List<Book> booklist = dao.getBooksByTag("PINK");
		
		for (int i = 0; i < booklist.size();i++) {
			Book book = booklist.get(i);
			System.out.println(book.getTitle());
		}
	}
	
}

