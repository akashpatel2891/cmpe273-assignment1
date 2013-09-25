package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Review;

@JsonPropertyOrder(alphabetic = true)
public class ReviewsDto extends LinksDto {

	private List<Review> reviews = new ArrayList<Review>();

	/**
	 * @param review
	 */
	public ReviewsDto(List<Review> reviews) {
		super();
		this.reviews = reviews;
	}

	/**
	 * @return the review
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * @param review
	 *            the review to set
	 */
	public void setReview(List<Review> reviews) {
		this.reviews = reviews;
	}
}
