package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
//import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.core.*;

//import com.fasterxml.jackson.core.sym.Name;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Review;
//import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.LinkDto;

//import edu.sjsu.cmpe.library.dto.LinksDto;

public class BookRepository implements BookRepositoryInterface {
	/** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
	private final ConcurrentHashMap<Long, Book> bookInMemoryMap;

	/** Never access this key directly; instead use generateISBNKey() */
	private long isbnKey;
	private int reviewidkey;

	public BookRepository(ConcurrentHashMap<Long, Book> bookMap) {
		checkNotNull(bookMap, "bookMap must not be null for BookRepository");
		bookInMemoryMap = bookMap;
		isbnKey = 0;
	}

	/**
	 * This should be called if and only if you are adding new books to the
	 * repository.
	 * 
	 * @return a new incremental ISBN number
	 */
	private final Long generateISBNKey() {
		// increment existing isbnKey and return the new value
		return Long.valueOf(++isbnKey);
	}

	private final int generateReviewID() {
		return ++reviewidkey;
	}

	/*
	 * To link the authors with books
	 */

	public Author getAuthorLinks(Long isbn) {

		Author author = null;
		return author;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.sjsu.cmpe.library.repository.BookRepositoryInterface#deleteexistingBook
	 * (java.lang.Long)
	 */
	
	// delete book method
	
	public Response deleteexistingBook(Long isbn) {
		// delete a file using isbn in the remove function by hashmap
		bookInMemoryMap.remove(isbn);

		// LinksDto class object ResponseLink created
		LinksDto responseLink = new LinksDto();

		// Link added
		responseLink.addLink(new LinkDto("create-book", "/books", "POST"));

		// links generated
		return Response.status(201).entity(responseLink).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.sjsu.cmpe.library.repository.BookRepositoryInterface#updateexistingBook
	 * (java.lang.Long, java.lang.String)
	 */
	
	//update book mehod
	
	public Book updateexistingBook(Long isbn, String status) {
		// update a book
		Book updatedBook;
		updatedBook = getBookByISBN(isbn);

		// set a status for an update
		updatedBook.setStatus(status);
		return updatedBook;
	}
	
	//creae review method
	
	public Response createReview(Long isbn, Review review) {
		//review id generated and set
		int reviewid = generateReviewID();
		review.setId(reviewid);
		
		Book book;
		book = getBookByISBN(isbn);
		List<Review> review1;
		review1 = new ArrayList<Review>();
		review1 = book.getReviews();
		
		//adding a review in list
		
		review1.add(review);
		book.setReviews(review1);
		LinksDto responseReview;
		responseReview = new LinksDto();
		String Location = "/books/" + isbn + "/reviews/" + reviewid;
		responseReview.addLink(new LinkDto("view-review", Location, "GET"));
		return Response.status(201).entity(responseReview).build();
	}
	
	// getting the reiew by id
	
	public Review getReviewByID(Long isbn, int id) {
		return getBookByISBN(isbn).getReviews().get(id);
	}

	//getting list of reviews using isbn
	
	public List<Review> getReviewsByisbn(Long isbn) {
		return getBookByISBN(isbn).getReviews();
	}

	/// getting author of the book using id
	
	public Author getAuthorByID(Long isbn, int id) {
		return getBookByISBN(isbn).getAuthors().get(id);
	}

	// getting the list of authors using isbn
	
	public List<Author> getAuthorsByisbn(Long isbn) {
		return getBookByISBN(isbn).getAuthors();
	}

	/**
	 * This will auto-generate unique ISBN for new books.
	 */
	@Override
	public Book saveBook(Book newBook) {
		checkNotNull(newBook, "newBook instance must not be null");
		// Generate new ISBN
		Long isbn = generateISBNKey();
		newBook.setIsbn(isbn);

		// TODO: create and associate other fields such as author

		// Finally, save the new book into the map
		bookInMemoryMap.putIfAbsent(isbn, newBook);

		return newBook;
	}

	/**
	 * @see edu.sjsu.cmpe.library.repository.BookRepositoryInterface#getBookByISBN(java.lang.Long)
	 */

	@Override
	public Book getBookByISBN(Long isbn) {
		checkArgument(isbn > 0,
				"ISBN was %s but expected greater than zero value", isbn);
		return bookInMemoryMap.get(isbn);

	}

	// TODO Auto-generated method stub

}
