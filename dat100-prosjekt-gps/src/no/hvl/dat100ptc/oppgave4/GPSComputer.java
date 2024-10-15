package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double distance = 0;
		for(int i=0; i<gpspoints.length-1; i++)
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
		
		return distance;
		}

	
	
	public double totalElevation() {

		double elevation = -gpspoints[0].getElevation();
		for(int i=0; i<gpspoints.length-1; i++) {
			System.out.println(gpspoints[i].toString());
			elevation += Math.abs(gpspoints[i+1].getElevation()-gpspoints[i].getElevation());
		}
		return elevation;
	}

	public int totalTime() {
		int tid = gpspoints[gpspoints.length-1].getTime()-gpspoints[0].getTime();
return tid;
	}
		

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		for(int i=0; i<gpspoints.length-1; i++) 
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);

return speeds;
	}
	
	public double maxSpeed() {
		
		double speedArr[] = speeds();
		
		double maxspeed = speedArr[0];
		for(int i=0; i<speedArr.length; i++)
			if(maxspeed < speedArr[i])
				maxspeed = speedArr[i];
		
		return maxspeed;	
	}

	public double averageSpeed() {

double average = totalDistance()/totalTime();
		
		System.out.println(average);
return average;		
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;		
		double speedmph = speed * MS;

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}

}
