package edu.sjsu.cmpe.library.repository;

import java.util.List;

import javax.ws.rs.core.Response;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
//import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.domain.Review;

/**
 * Book repository interface.
 */
public interface BookRepositoryInterface {
	/**
	 * This will Save a new book in repository with @param newBook and @return a
	 * newly created book instance with ISBN
	 */
	Book saveBook(Book newBook);

	/**
	 * This will Retrieve an existing book by ISBN with @param a valid isbn and @return
	 * a book instance
	 */
	Book getBookByISBN(Long isbn);

	// TODO: add other operations here!

	/*
	 * Delete a book with @param a valid isbn
	 */
	Response deleteexistingBook(Long isbn);

	/*
	 * Update a book with @param a status
	 */
	Book updateexistingBook(Long isbn, String status);

	/*
	 * Create review
	 */

	// Review createReview(int isbn, Review rating);

	Response createReview(Long isbn, Review review);

	Review getReviewByID(Long isbn, int id);

	List<Review> getReviewsByisbn(Long isbn);

	Author getAuthorByID(Long isbn, int id);

	List<Author> getAuthorsByisbn(Long isbn);

}
