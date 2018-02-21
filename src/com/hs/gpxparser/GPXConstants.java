package com.hs.gpxparser;

public interface GPXConstants {

	/* Root Node */
	String NODE_GPX = "gpx";

	/* GPX nodes and attributes */
        // TFE, 20180201: some gpx parser fail if no xmlns defined
	String ATTR_XMLNS = "xmlns";
	String ATTR_CREATOR = "creator";
	String ATTR_VERSION = "version";

	String NODE_METADATA = "metadata";
	String NODE_TRK = "trk";
	String NODE_RTE = "rte";
	String NODE_WPT = "wpt";
	String NODE_EXTENSIONS = "extensions";
	/* End GPX nodes and attributes */

	/* metadata nodes and attributes */
	String NODE_NAME = "name";
	String NODE_DESC = "desc";
	String NODE_AUTHOR = "author";
	String NODE_COPYRIGHT = "copyright";
	String NODE_LINK = "link";
	String NODE_TIME = "time";
	String NODE_KEYWORDS = "keywords";
	String NODE_BOUNDS = "bounds";
	// String NODE_EXTENSIONS = "extensions";
	/* end metadata nodes and attributes */

	/* Waypoint nodes and attributes */
	String ATTR_LAT = "lat";
	String ATTR_LON = "lon";

	String NODE_ELE = "ele";
	// String NODE_TIME = "time";
	String NODE_MAGVAR = "magvar";
	String NODE_GEOIDHEIGHT = "geoidheight";
	// String NODE_NAME = "name";
	String NODE_CMT = "cmt";
	// String NODE_DESC = "desc";
	String NODE_SRC = "src";
	// String NODE_LINK = "link";
	String NODE_SYM = "sym";
	String NODE_TYPE = "type";
	String NODE_FIX = "fix";
	String NODE_SAT = "sat";
	String NODE_HDOP = "hdop";
	String NODE_VDOP = "vdop";
	String NODE_PDOP = "pdop";
	String NODE_AGEOFGPSDATA = "ageofdgpsdata";
	String NODE_DGPSID = "dgpsid";
	// String NODE_EXTENSIONS = "extensions";
	/* end Waypoint nodes and attributes */

	/* Route nodes */
	// String NODE_NAME = "name";
	// String NODE_CMT = "cmt";
	// String NODE_DESC = "desc";
	// String NODE_SRC = "src";
	// String NODE_LINK = "link";
	String NODE_NUMBER = "number";
	// String NODE_TYPE = "type";
	// String NODE_EXTENSIONS = "extensions";
	String NODE_RTEPT = "rtept";
	/* end route nodes */

	/* Track nodes and attributes */
	// String NODE_NAME = "name";
	// String NODE_CMT = "cmt";
	// String NODE_DESC = "desc";
	// String NODE_SRC = "src";
	// String NODE_LINK = "link";
	// String NODE_NUMBER = "number";
	// String NODE_TYPE = "type";
	// String NODE_EXTENSIONS = "extensions";
	String NODE_TRKSEG = "trkseg";
	/* end Track nodes and attributes */

	/* Track segment nodes */
	String NODE_TRKPT = "trkpt";
	// String NODE_EXTENSIONS = "extensions";
	/* end Track segment nodes */

	/* Copyright Nodes and Attributes */
	String ATTR_AUTHOR = "author";
	String NODE_YEAR = "year";
	String NODE_LICENSE = "license";
	/* End Copyright Nodes and Attributes */

	/* Link Nodes and Attributes */
	String ATTR_HREF = "href";
	String NODE_TEXT = "text";
	// String NODE_TYPE = "type";
	/* End Link Nodes and Attributes */

	/* Email attributes */
	String ATTR_ID = "id";
	String ATTR_DOMAIN = "domain";
	/* End Email attributes */

	/* Person Nodes */
	// String NODE_NAME = "name";
	String NODE_EMAIL = "email";
	// String NODE_LINK = "link";
	/* End Person Nodes */

	/* Point Nodes and Attributes */
	// String ATTR_LAT = "lat";
	// String ATTR_LON = "lon";

	// String NODE_ELE = "ele";
	// String NODE_TIME = "time";
	/* End Point Nodes and Attributes */

	/* Point Segment Nodes */
	String NODE_PT = "pt";
	/* End Point Segment Nodes */

	/* Bounds Attributes */
	String ATTR_MINLAT = "minlat";
	String ATTR_MINLON = "minlon";
	String ATTR_MAXLAT = "maxlat";
	String ATTR_MAXLON = "maxlon";
	/* End Bounds Attributes */

}
