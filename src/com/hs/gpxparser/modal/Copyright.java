package com.hs.gpxparser.modal;

/**
 * Created by Himanshu on 7/5/2015.
 */
public class Copyright {
	private String year;
	private String license;
	private String author;

	public Copyright(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
