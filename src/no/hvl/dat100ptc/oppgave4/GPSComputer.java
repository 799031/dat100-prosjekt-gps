package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {

	private static GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		GPSComputer.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return GPSComputer.gpspoints;
	}

	public static double totalDistance() {

		double distance = 0;
		for (int i = 0; i < gpspoints.length - 1; i++)
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);

		return distance;
	}

	public static double totalElevation() {

		double elevation = -gpspoints[0].getElevation();
		for (int i = 0; i < gpspoints.length - 1; i++) {
			elevation += Math.abs(gpspoints[i + 1].getElevation() - gpspoints[i].getElevation());
		}
		return elevation;
	}

	public static int totalTime() {
		int tid = gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
		return tid;
	}

	public static double[] speeds() {

		double[] speeds = new double[gpspoints.length - 1];
		for (int i = 0; i < gpspoints.length - 1; i++)
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);

		return speeds;
	}

	public static double maxSpeed() {

		double speedArr[] = speeds();

		double maxspeed = speedArr[0];
		for (int i = 0; i < speedArr.length; i++)
			if (maxspeed < speedArr[i])
				maxspeed = speedArr[i];

		return maxspeed;
	}

	public static double averageSpeed() {

		double average = totalDistance() / totalTime();

		return average;
	}

	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public static double kcal(double weight, int secs, double speed) {

		double met = 0;
		double speedmph = speed * MS;
		if (speedmph < 10) // leisure, to work or for pleasure
			met = 4;
		else if (speedmph < 12) // leisure, slow, light effort
			met = 6;
		else if (speedmph < 14) // leisure, moderate effort
			met = 8;
		else if (speedmph < 16) // racing or leisure, fast, vigorous effort
			met = 10;
		else if (speedmph < 20) // leisure, slow, light effort
			met = 12;
		else // racing, not drafting
			met = 16;

		double timeHours = secs / 3600.0, kcal = met * weight * timeHours;
		return kcal;
	}

	public static double totalKcal(double weight) {

		int timeSecs = totalTime();
		double avgSpeed = averageSpeed();
		double totalkcal = kcal(weight, timeSecs, avgSpeed);

		return totalkcal;
	}

	private static int decimales = 2;

	private static double round(double num) {
		double pow10 = Math.pow(10, decimales);
		return Math.round(num * pow10) / pow10;
	}

	private static double WEIGHT = 80.0;

	public static String statisticsStr() {
		String totalTime = "Total Time     :" + GPSUtils.formatTime(totalTime()) + "\n";
		String totalDistance = "Total distance :      " + String.valueOf(round(totalDistance())) + " km\n";
		String totalElevation = "Total elevation:     " + String.valueOf(round(totalElevation())) + " m\n";
		String maxSpeed = "Max speed      :      " + String.valueOf(round(maxSpeed())) + " km/t\n";
		String averageSpeed = "Average speed  :      " + String.valueOf(round(averageSpeed())) + " km/t\n";
		String energy = "Energy         :     " + String.valueOf(round(totalKcal(GPSComputer.WEIGHT))) + " kcal\n";

		String str = totalTime + totalDistance + totalElevation + maxSpeed + averageSpeed + energy;
		return str;
	}

	public void displayStatistics() {
		String divider = "==============================================";
		String statistics = divider + "\n" + statisticsStr() + divider;

		System.out.println(statistics);
	}

}
