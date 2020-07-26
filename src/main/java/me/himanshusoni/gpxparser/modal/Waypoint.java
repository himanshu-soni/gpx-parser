package me.himanshusoni.gpxparser.modal;

import me.himanshusoni.gpxparser.type.Fix;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class Waypoint extends Extension {

	private double latitude;
	private double longitude;

	private double elevation;
	private Date time;
	private double magneticVariation;
	private double geoIdHeight;
	private String name;
	private String comment;
	private String description;
	private String src;
	private HashSet<Link> links;
	/**
	 * Symbol
	 */
	private String sym;
	private String type;
	private Fix fix;
	private int sat;
	/**
	 * Horizontal dilution of precision.
	 */
	private double hdop;
	/**
	 * Vertical dilution of precision.
	 */
	private double vdop;
	/**
	 * Position dilution of precision.
	 */
	private double pdop;

	private double ageOfGpsData;
	private int dGpsStationId;

	public Waypoint(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Returns the ageOfGPSData of this waypoint.
	 *
	 * @return a Double representing the ageOfGPSData of this waypoint.
	 */
	public double getAgeOfGPSData() {
		return this.ageOfGpsData;
	}

	/**
	 * Returns the comment of this waypoint.
	 *
	 * @return a String representing the comment of this waypoint.
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Returns the description of this waypoint.
	 *
	 * @return a String representing the description of this waypoint.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Returns the dGpsStationId of this waypoint.
	 *
	 * @return an Integer representing the dGpsStationId of this waypoint.
	 */
	public int getdGpsStationId() {
		return this.dGpsStationId;
	}

	/**
	 * Returns the elevation of this waypoint.
	 *
	 * @return A Double representing the elevation of this waypoint.
	 */
	public double getElevation() {
		return this.elevation;
	}

	/**
	 * Returns the fix of this waypoint.
	 *
	 * @return A {@link Fix} representing the fix of this waypoint.
	 */
	public Fix getFix() {
		return this.fix;
	}

	/**
	 * Returns the geoid height of this waypoint.
	 *
	 * @return A String representing the geoid height of this waypoint.
	 */
	public double getGeoIdHeight() {
		return this.geoIdHeight;
	}

	/**
	 * Returns the hdop of this waypoint.
	 *
	 * @return a Double representing the hdop of this waypoint.
	 */
	public double getHdop() {
		return this.hdop;
	}

	/**
	 * Returns the latitude of this waypoint.
	 *
	 * @return a Double value representing the latitude of this waypoint.
	 */
	public double getLatitude() {
		return this.latitude;
	}

	/**
	 * Returns the longitude of this waypoint.
	 *
	 * @return a Double value representing the longitude of this waypoint.
	 */
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * Returns the magnetic declination of this waypoint.
	 *
	 * @return A Double representing the magnetic declination of this waypoint.
	 */
	public double getMagneticVariation() {
		return this.magneticVariation;
	}

	/**
	 * Returns the name of this waypoint.
	 *
	 * @return A String representing the name of this waypoint.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the pdop of this waypoint.
	 *
	 * @return a Double representing the pdop of this waypoint.
	 */
	public double getPdop() {
		return this.pdop;
	}

	/**
	 * Returns the sat of this waypoint.
	 *
	 * @return an Integer representing the sat of this waypoint.
	 */
	public int getSat() {
		return this.sat;
	}

	/**
	 * Returns the src of this waypoint.
	 *
	 * @return A String representing the src of this waypoint.
	 */
	public String getSrc() {
		return this.src;
	}

	/**
	 * Returns the sym of this waypoint.
	 *
	 * @return A String representing the sym of this waypoint.
	 */
	public String getSym() {
		return this.sym;
	}

	/**
	 * Returns the time of this waypoint.
	 *
	 * @return a Date representing the name of this waypoint.
	 */
	public Date getTime() {
		return this.time;
	}

	/**
	 * Returns the type of this waypoint.
	 *
	 * @return A String representing the type of this waypoint.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Returns the vdop of this waypoint.
	 *
	 * @return a Double representing the vdop of this waypoint.
	 */
	public double getVdop() {
		return this.vdop;
	}

	/**
	 * Setter for waypoint ageOfGPSData property. This maps to
	 * &lt;ageOfGPSData&gt; tag value.
	 *
	 * @param ageOfGPSData
	 *            A String representing the ageOfGPSData of this waypoint.
	 */
	public void setAgeOfGPSData(double ageOfGPSData) {
		this.ageOfGpsData = ageOfGPSData;
	}

	/**
	 * Setter for waypoint comment property. This maps to &lt;cmt&gt; tag value.
	 *
	 * @param comment
	 *            A String representing the comment of this waypoint.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Setter for waypoint description property. This maps to &lt;desc&gt; tag
	 * value.
	 *
	 * @param description
	 *            A String representing the description of this waypoint.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Setter for waypoint dGpsStationId property. This maps to
	 * &lt;dGpsStationId&gt; tag value.
	 *
	 * @param dGpsStationId
	 *            an Integer representing the dGpsStationId of this waypoint.
	 */
	public void setdGpsStationId(int dGpsStationId) {
		this.dGpsStationId = dGpsStationId;
	}

	/**
	 * Setter for waypoint elevation property. This maps to &lt;ele&gt; tag
	 * value.
	 *
	 * @param elevation
	 *            a Double value representing the elevation of this waypoint.
	 */
	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	/**
	 * Setter for waypoint fix property. This maps to &lt;fix&gt; tag value.
	 *
	 * @param fix
	 *            a {@link Fix} representing the fix of this waypoint.
	 */
	public void setFix(Fix fix) {
		this.fix = fix;
	}

	/**
	 * Setter for waypoint geoid height property. This maps to
	 * &lt;geoidheight&gt; tag value.
	 *
	 * @param geoIdHeight
	 *            A String representing the geoid height of this waypoint.
	 */
	public void setGeoIdHeight(double geoIdHeight) {
		this.geoIdHeight = geoIdHeight;
	}

	/**
	 * Setter for waypoint hdop property. This maps to &lt;hdop&gt; tag value.
	 *
	 * @param hdop
	 *            a Double representing the name of this waypoint.
	 */
	public void setHdop(double hdop) {
		this.hdop = hdop;
	}

	/**
	 * Setter for waypoint latitude property. This maps to "lat" attribute
	 * value.
	 *
	 * @param latitude
	 *            a Doube value representing the latitude of this waypoint.
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Setter for waypoint longitude property. This maps to "long" attribute
	 * value.
	 *
	 * @param longitude
	 *            a Doube value representing the longitude of this waypoint.
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Setter for waypoint magnetic declination property. This maps to
	 * &lt;magvar&gt; tag value.
	 *
	 * @param magVar
	 *            A String representing the magnetic declination of this
	 *            waypoint.
	 */
	public void setMagneticVariation(double magVar) {
		this.magneticVariation = magVar;
	}

	/**
	 * Setter for waypoint name property. This maps to &lt;name&gt; tag value.
	 *
	 * @param name
	 *            A String representing the name of this waypoint.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for waypoint pdop property. This maps to &lt;pdop&gt; tag value.
	 *
	 * @param pdop
	 *            a Double representing the pdop of this waypoint.
	 */
	public void setPdop(double pdop) {
		this.pdop = pdop;
	}

	/**
	 * Setter for waypoint sat property. This maps to &lt;sat&gt; tag value.
	 *
	 * @param sat
	 *            an Integer representing the sat of this waypoint.
	 */
	public void setSat(int sat) {
		this.sat = sat;
	}

	/**
	 * Setter for waypoint src property. This maps to &lt;src&gt; tag value.
	 *
	 * @param src
	 *            a String representing the src of this waypoint.
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * Setter for waypoint sym property. This maps to &lt;sym&gt; tag value.
	 *
	 * @param sym
	 *            a String representing the sym of this waypoint.
	 */
	public void setSym(String sym) {
		this.sym = sym;
	}

	/**
	 * Setter for waypoint time property. This maps to &lt;time&gt; tag value.
	 *
	 * @param time
	 *            a Date representing the time of this waypoint.
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * Setter for waypoint type property. This maps to &lt;type&gt; tag value.
	 *
	 * @param type
	 *            a String representing the type of this waypoint.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Setter for waypoint vdop property. This maps to &lt;vdop&gt; tag value.
	 *
	 * @param vdop
	 *            A String representing the vdop of this waypoint.
	 */
	public void setVdop(double vdop) {
		this.vdop = vdop;
	}

	/**
	 * Returns a String representation of this waypoint.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = "";
		if (this.time != null) {
			date = sdf.format(this.time);
		}
		sb.append("[");
		sb.append("name:'" + this.name + "' ");
		sb.append("lat:" + this.latitude + " ");
		sb.append("lon:" + this.longitude + " ");
		sb.append("elv:" + this.elevation + " ");
		sb.append("time:" + date + " ");
		sb.append("fix:" + this.fix + " ");
		if (this.extensionData != null) {
			sb.append("extensions:{");
			Iterator<String> it = this.extensionData.keySet().iterator();
			while (it.hasNext()) {
				sb.append(it.next());
				if (it.hasNext()) {
					sb.append(",");
				}
			}
			sb.append("}");
		}
		sb.append("]");
		return sb.toString();
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
}
