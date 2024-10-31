package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double latitudes[] = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}

		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double longitudes[] = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}

		return longitudes;
	}

	public static double[] getElevation(GPSPoint[] gpspoints) {

		double elevations[] = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			elevations[i] = gpspoints[i].getElevation();
		}

		return elevations;
	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double latitude1 = toRadians(gpspoint1.getLatitude()), latitude2 = toRadians(gpspoint2.getLatitude()),
				deltaLatitude = latitude2 - latitude1,

				deltaLongitude = toRadians(gpspoint2.getLongitude()) - toRadians(gpspoint1.getLongitude()),

				a = pow(sin(deltaLatitude / 2), 2) + cos(latitude1) * cos(latitude2) * pow(sin(deltaLongitude / 2), 2),
				c = 2 * atan2(sqrt(a), sqrt(1 - a));

		return R * c;
	}

	/*
	 * private static double compute_a(double phi1, double phi2, double deltaphi,
	 * double deltadelta) {
	 * 
	 * throw new UnsupportedOperationException(TODO.method());
	 * 
	 * // TODO
	 * 
	 * }
	 * 
	 * private static double compute_c(double a) {
	 * 
	 * throw new UnsupportedOperationException(TODO.method());
	 * 
	 * // TODO
	 * 
	 * }
	 */

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs = gpspoint2.getTime() - gpspoint1.getTime();
		double distance = distance(gpspoint1, gpspoint2);

		return distance / (double) secs;
	}

	public static String formatTime(int secs) {

		String timeTypes[] = new String[3];
		timeTypes[0] = String.valueOf(secs / (60 * 60)); // hh
		secs = secs % (60 * 60);

		timeTypes[1] = String.valueOf(secs / (60)); // mm
		secs = secs % (60);

		timeTypes[2] = String.valueOf(secs); // ss

		for (int i = 0; i < 3; i++)
			if (timeTypes[i].length() == 1)
				timeTypes[i] = "0" + timeTypes[i];

		String timestr = "  " + timeTypes[0] + ":" + timeTypes[1] + ":" + timeTypes[2];
		return timestr;
	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str = String.valueOf(Math.round(d * 100.0) / 100.0);

		while (str.length() < TEXTWIDTH)
			str = " " + str;

		return str;
	}
}
