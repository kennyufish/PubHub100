package examples.pubhub.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.model.Tag;

public class TagDAOImpl implements TagDAO {
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	/*------------------------------------------------------------------------------------------------*/
	@Override
	public List<Tag> getAllTags(String isbn13){
		List<Tag> tags = new ArrayList<>();
		
		try {
			//debug purpose
			System.out.println("Trying to establish conneciton");
			
			connection = DAOUtilities.getConnection();
			String sql = " SELECT * FROM Book_tags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			// Obtain isbn13 from book object;
			stmt.setString(1, isbn13);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				//debug purpose
				System.out.println("Tags detected");
				
				Tag tag = new Tag();
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTag(rs.getString("tag_name"));
				
				//add the tag to the list
				tags.add(tag);
			}
			
			rs.close();
			
			//debug purpose
			System.out.println("Done Connection");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		return tags;
	}
	
	
	
	
	/*------------------------------------------------------------------------------------------------*/
	@Override
	public List<Book> getBooksByTag(String tag){
		List<Book> books = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();
			
			//String sql = "SELECT * FROM Books WHERE isbn_13 = (SELECT isbn_13 FROM Book_tags WHERE tag_name=?)";
			String sql = "SELECT * FROM (books LEFT JOIN book_tags ON book_tags.isbn_13 = books.isbn_13) WHERE UPPER(book_tags.tag_name) LIKE UPPER (?)";
			stmt = connection.prepareStatement(sql);
			// Obtain isbn13 from book object;
			stmt.setString(1, tag);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				
				books.add(book);
			}
			
			rs.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		return books;
	}
	
	
	/*------------------------------------------------------------------------------------------------*/
	@Override	
	//A method to add a tag to a book, given the tag name and a reference to a book (either a Book reference variable or just an ISBN-13)
	public boolean addTag(String tag, String isbn13) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO Book_tags VALUES (?, ?)"; 
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn13);
			stmt.setString(2, tag);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/
	@Override
	//A method to remove a tag from a book, given the tag name and a reference to a book (either a Book reference variable or just an ISBN-13)
	public boolean deleteTag(String tag, String isbn13) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE isbn_13=? AND tag_name=? ";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn13);
			stmt.setString(2, tag);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	
	
	/*------------------------------------------------------------------------------------------------*/

	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
	
	
}
