package com.hs.gpxparser.modal;

import java.util.ArrayList;

/**
 * Created by Himanshu on 7/5/2015.
 */
public class TrackSegment extends Extension {
	private ArrayList<Waypoint> waypoints;

	public ArrayList<Waypoint> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(ArrayList<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	public void addWaypoint(Waypoint wp) {
		if (waypoints == null) {
			waypoints = new ArrayList<>();
		}
		waypoints.add(wp);
	}

}
