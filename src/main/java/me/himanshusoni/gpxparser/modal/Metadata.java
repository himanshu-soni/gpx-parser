package me.himanshusoni.gpxparser.modal;

import java.util.Date;
import java.util.HashSet;

/**
 * Created by Himanshu on 7/5/2015.
 */
public class Metadata extends Extension {
	private String name;
	private String desc;
	private Person author;
	private Copyright copyright;
	private HashSet<Link> links;
	private Date time;
	private String keywords;
	private Bounds bounds;

	public Metadata() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public Copyright getCopyright() {
		return copyright;
	}

	public void setCopyright(Copyright copyright) {
		this.copyright = copyright;
	}

	public HashSet<Link> getLinks() {
		return links;
	}

	public void setLinks(HashSet<Link> links) {
		this.links = links;
	}

	public void addLink(Link link) {
		if (links == null) {
			links = new HashSet<>();
		}
		links.add(link);
	}

	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}
}
