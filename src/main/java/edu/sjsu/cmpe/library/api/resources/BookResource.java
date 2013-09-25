package edu.sjsu.cmpe.library.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
//import edu.sjsu.cmpe.library.repository.BookRepository;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	/** bookRepository instance */
	private final BookRepositoryInterface bookRepository;

	/**
	 * BookResource constructor
	 * 
	 * @param bookRepository
	 *            a BookRepository instance
	 */
	public BookResource(BookRepositoryInterface bookRepository) {
		this.bookRepository = bookRepository;
	}

	// view book api
	@GET
	@Path("/{isbn}")
	@Timed(name = "view-book")
	public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
		Book book = bookRepository.getBookByISBN(isbn.get());
		BookDto bookResponse;
		bookResponse = new BookDto(book);
	
		//add links using BookDto class's object
		bookResponse.addLink(new LinkDto("view-book", "/books/"
				+ book.getIsbn(), "GET"));
		bookResponse.addLink(new LinkDto("update-book", "/books/"
				+ book.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book", "/books/"
				+ book.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("create-review", "/books/"
				+ book.getIsbn() + "/reviews", "POST"));
		bookResponse.addLink(new LinkDto("view-all-reviews", "/books/"
				+ book.getIsbn() + "/reviews", "GET"));
		
		//add more links
	
		return bookResponse;
	}

	// create a book api
	@POST
	@Timed(name = "create-book")
	public Response createBook(Book request) {
		// Store the new book in the BookRepository so that we can retrieve it.
		Book savedBook = bookRepository.saveBook(request);
		String location = "/books/" + savedBook.getIsbn();
		LinksDto LinkResponse; 
		LinkResponse = new LinksDto();
		
		///add links using linksDto class object
		LinkResponse.addLink(new LinkDto("view-book", location, "GET"));
		LinkResponse.addLink(new LinkDto("update-book", location, "POST"));
		LinkResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
		LinkResponse.addLink(new LinkDto("create-review",
				location + "/reviews", "POST"));
		
		// Add other links if needed

		return Response.status(201).entity(LinkResponse).build();
	}

	// Delete a book api
	@DELETE
	@Path("/{isbn}")
	@Timed(name = "delete-book")
	public Response deleteBookByIsbn(@PathParam("isbn") Long isbn) {
		Response response1;
		response1 = bookRepository.deleteexistingBook(isbn);
		// return response1;
		return response1;
	}

	// Update a book api
	@PUT
	@Timed(name = "update-book")
	@Path("/{isbn}")
	public Response updatedBook(@PathParam("isbn") LongParam isbn,
			@QueryParam("status") String status) {
		Book updatedBook = bookRepository
				.updateexistingBook(isbn.get(), status);
		String location = "/books/" + updatedBook.getIsbn();
		LinksDto LinkResponse;
		LinkResponse = new LinksDto();
		
		//add links using LinksDto class object
		LinkResponse.addLink(new LinkDto("view-book", location, "GET"));
		LinkResponse.addLink(new LinkDto("update-book", location, "PUT"));
		LinkResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
		LinkResponse.addLink(new LinkDto("create-review",
				location + "/reviews", "POST"));
		if (bookRepository.getBookByISBN(isbn.get()).getReviews() != null)
			LinkResponse.addLink(new LinkDto("view-all-reviews", location
					+ "/reviews", "GET"));

		// link generated
		return Response.status(201).entity(LinkResponse).build();

	}

	// create review api

	@POST
	@Timed(name = "create-review")
	@Path("/{isbn}/reviews")
	public Response createBookReview(@PathParam("isbn") Long isbn, Review review) {
		Response responseReview;
		responseReview = bookRepository.createReview(isbn, review);
		return responseReview;
	}

	
	//view-review api
	
	@POST
	@Timed(name = "view-review")
	@Path("/{isbn}/reviews/{id}")
	public ReviewDto viewBookReview(@PathParam("isbn") Long isbn,@PathParam("id") int id, Review review){
		review = bookRepository.getReviewByID(isbn, id);
		ReviewDto response;
		response = new ReviewDto(review);
		String location = "/books/" + isbn + "/reviews/" + id;
		response.addLink(new LinkDto("view-review", location, "GET"));
		return response;
	}

	
	//view all reviews api
	
	@GET
	@Timed(name = "view-all-reviews")
	@Path("/{isbn}/reviews")
	public ReviewsDto viewAllReviews(@PathParam("isbn") Long isbn,
			List<Review> reviews) {
		reviews = bookRepository.getReviewsByisbn(isbn);
		ReviewsDto Response;
		Response = new ReviewsDto(reviews);
		// String location = "/books/" + isbn + "/reviewws/" +id;
		// Response.addLink(new LinkDto("view-review", location, "GET"));
		return Response;
	}

	
	//view author api
	
	@POST
	@Timed(name = "view-author")
	@Path("/{isbn}/authors/{id}")
	public AuthorDto viewBookAuthor(@PathParam("isbn") Long isbn,
			@PathParam("id") int id, Author author) {
		author = bookRepository.getAuthorByID(isbn, id);
		AuthorDto response;
		response = new AuthorDto(author);
		String location = "/books/" + isbn + "/authors/" + id;
		response.addLink(new LinkDto("view-author", location, "GET"));
		return response;
	}

	
	// view all authors api
	
	@GET
	@Timed(name = "view-all-authors")
	@Path("/{isbn}/authors")
	public AuthorsDto viewAllAuthors(@PathParam("isbn") Long isbn,
			List<Author> authors) {
		authors = bookRepository.getAuthorsByisbn(isbn);
		AuthorsDto Response;
		Response = new AuthorsDto(authors);
		return Response;
	}

}