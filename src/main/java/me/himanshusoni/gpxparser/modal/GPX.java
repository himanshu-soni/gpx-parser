package me.himanshusoni.gpxparser.modal;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class holds gpx information from a &lt;gpx&gt; node. <br>
 * <p>
 * GPX specification for this tag:
 * </p>
 * <code>
 * &lt;gpx version="1.1" creator=""xsd:string [1]"&gt;<br>
 * &nbsp;&nbsp;&nbsp;&lt;metadata&gt; xsd:string &lt;/metadata&gt; [0..1]<br>
 * &nbsp;&nbsp;&nbsp;&lt;wpt&gt; xsd:string &lt;/wpt&gt; [0..1]<br>
 * &nbsp;&nbsp;&nbsp;&lt;rte&gt; xsd:string &lt;/rte&gt; [0..1]<br>
 * &nbsp;&nbsp;&nbsp;&lt;trk&gt; xsd:string &lt;/trk&gt; [0..1]<br>
 * &nbsp;&nbsp;&nbsp;&lt;extensions&gt; extensionsType &lt;/extensions&gt; [0..1]<br>
 * &lt;/gpx&gt;<br>
 * </code>
 */
public class GPX extends Extension {

	// Attributes
        // TFE, 20180201: support multiple xmlns attributes
	private HashMap<String, String> xmlns;
	private String creator;
	private String version = "1.1";

	// Nodes
	private Metadata metadata;
	private HashSet<Route> routes;
	private HashSet<Track> tracks;
	private HashSet<Waypoint> waypoints;

	public GPX() {
		this.waypoints = new HashSet<>();
		this.tracks = new HashSet<>();
		this.routes = new HashSet<>();
	}

	/**
	 * Adds a new Route to a gpx object
	 *
	 * @param route
	 *            a {@link Route}
	 */
	public void addRoute(Route route) {
		if (this.routes == null) {
			this.routes = new HashSet<>();
		}
		this.routes.add(route);
	}

	/**
	 * Adds a new track to a gpx object
	 *
	 * @param track
	 *            a {@link Track}
	 */
	public void addTrack(Track track) {
		if (this.tracks == null) {
			this.tracks = new HashSet<>();
		}
		this.tracks.add(track);
	}

	/**
	 * Adds a new waypoint to a gpx object
	 *
	 * @param waypoint
	 *            a {@link Waypoint}
	 */
	public void addWaypoint(Waypoint waypoint) {
		if (this.waypoints == null) {
			this.waypoints = new HashSet<>();
		}
		this.waypoints.add(waypoint);

	}

	/**
	 * Returns the xmlns of this gpx object
	 *
	 * @return A HashMap<String, String> representing the xmlns of a gpx object
	 */
        public HashMap<String, String> getXmlns() {
            return xmlns;
        }

	/**
	 * Returns the creator of this gpx object
	 *
	 * @return A String representing the creator of a gpx object
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * Getter for the list of routes from a gpx object
	 *
	 * @return a HashSet of {@link Route}
	 */
	public HashSet<Route> getRoutes() {
		return this.routes;
	}

	/**
	 * Getter for the list of Tracks from a gpx objecty
	 *
	 * @return a HashSet of {@link Track}
	 */
	public HashSet<Track> getTracks() {
		return this.tracks;
	}

	/**
	 * Returns the version of a gpx object
	 *
	 * @return A String representing the version of this gpx object
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * Getter for the list of waypoints from a gpx objecty
	 *
	 * @return a HashSet of {@link Waypoint}
	 */
	public HashSet<Waypoint> getWaypoints() {
		return this.waypoints;
	}

	/**
	 * Setter for gpx xmlns property. This maps to <i>xmlns</i> attribute
	 * value.
	 *
	 * @param xmlns
	 *            A HashMap<String, String> representing the xmlns of a gpx file.
	 */
        public void setXmlns(HashMap<String, String> xmlns) {
            this.xmlns = xmlns;
        }
    
	/**
	 * Setter for gpx creator property. This maps to <i>creator</i> attribute
	 * value.
	 *
	 * @param creator
	 *            A String representing the creator of a gpx file.
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * Setter for the list of routes from a gpx object
	 *
	 * @param routes
	 *            a HashSet of {@link Route}
	 */
	public void setRoutes(HashSet<Route> routes) {
		this.routes = routes;
	}

	/**
	 * Setter for the list of tracks from a gpx object
	 *
	 * @param tracks
	 *            a HashSet of {@link Track}
	 */
	public void setTracks(HashSet<Track> tracks) {
		this.tracks = tracks;
	}

	/**
	 * Setter for the list of waypoints from a gpx object
	 *
	 * @param waypoints
	 *            a HashSet of {@link Waypoint}
	 */
	public void setWaypoints(HashSet<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public void setVersion(String version) {
		this.version = version;
	}
        
	/**
	 * Adds a new xmlns string into the xmlns data with the key set by xmlnsId.
	 * 
	 * @param xmlnsId a unique key representing the id of the xmlns content.
	 * @param value a string holding the parsed information.
	 */
        public void addXmlns(final String xmlnsId, final String value) {
            if(xmlns == null) {
                xmlns = new HashMap<>();
            }
            xmlns.put(xmlnsId, value);
        }
}
