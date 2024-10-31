package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	private int time;
	private double latitude;
	private double longitude;
	private double elevation;
	
	public GPSPoint(int newTime, double newLatitude, double newLongitude, double newElevation) {

		setTime(newTime);
		setLatitude(newLatitude);
		setLongitude(newLongitude);
		setElevation(newElevation);
	}

	// set functions
	public void setTime(int time) {
		this.time = time;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	// get functions
	public int getTime() {
		return time;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getElevation() {
		return elevation;
	}

	public String toString() {

		String str = getTime() + " (" + getLatitude() + "," + getLongitude() + ") " + getElevation() + "\n";
		return str;
	}
}
