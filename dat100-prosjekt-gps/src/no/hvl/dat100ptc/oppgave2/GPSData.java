package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {
		gpspoints = new GPSPoint[antall];
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	protected boolean insertGPS(GPSPoint gpspoint) {

		if (antall >= gpspoints.length)
			return false;
		gpspoints[antall] = gpspoint;
		antall++;
		return true;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint newGPS = GPSDataConverter.convert(time, latitude, longitude, elevation);
		return insertGPS(newGPS);
	}

	public void print() {
		for (int i = 0; i < antall; i++)
		System.out.print(gpspoints[i].toString());
		System.out.println();
	}
}
