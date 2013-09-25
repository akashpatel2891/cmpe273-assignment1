package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
	private long isbn;
	private String title;
	private String language;
	private int number_of_pages;
	private String publication_date;
	private String status;
	private List<Author> authors = new ArrayList<Author>();
	//@SuppressWarnings("unused")
	private List<Review> reviews = new ArrayList<Review>();
	private Review review;

	// add more fields here

	/**
	 * @return the isbn
	 */

	public long getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn
	 *            the isbn to set
	 */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("publication-date")
	public String getPublication_date() {
		return publication_date;
	}

	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the number of pages
	 */

	@JsonProperty("num-pages")
	public int getNumber_of_pages() {
		return number_of_pages;
	}

	public void setNumber_of_pages(int number_of_pages) {
		this.number_of_pages = number_of_pages;
	}

	/**
	 * @return the publication date
	 */
	/*
	 * private SimpleDateFormat date = new SimpleDateFormat("MM/DD/YYYY");
	 * 
	 * @JsonProperty("publication-date")
	 * 
	 * public String getPublicationdate() { return
	 * date.format(publication_date); }
	 * 
	 * /**
	 * 
	 * @param publication date the publication date to set
	 * 
	 * 
	 * public void setPublicationdate(String publication_date) {
	 * this.publication_date = publication_date; }
	 */

	/**
	 * @return the status
	 */

	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */

	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * @return the author list
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	/*
	 * @param authors authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	/*
	 * @return the review list
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews reviews to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

}

// TODO Auto-generated method stub

