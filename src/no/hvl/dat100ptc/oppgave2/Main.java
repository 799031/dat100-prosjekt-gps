package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		GPSPoint p1 = new GPSPoint(100, 2.1,4.5,30),
				p2 = new GPSPoint(110, 2.3,4.1,200);
		
		GPSData data = new GPSData(2);
		data.insertGPS(p1);
		data.insertGPS(p2);
		data.print();
	}
}
