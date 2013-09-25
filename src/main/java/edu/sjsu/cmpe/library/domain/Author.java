package edu.sjsu.cmpe.library.domain;

public class Author {

	private static int id1;
	private int id;
	private String name;

	public Author() {
		this.id = ++id1;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	/*
	 * public void setId(long id) { this.id = id; }
	 */

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}