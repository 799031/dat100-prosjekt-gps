package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));

		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);

		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);

		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	private int[] scalePoints(double data[], double minval, double step) {
		int points[] = new int[data.length];

		for (int i = 0; i < data.length; i++) {
			points[i] = (int) ((data[i] - minval) * step);
		}
		return points;
	}

	public void showRouteMap(int ybase) {

		int xPoints[] = scalePoints(GPSUtils.getLongitudes(gpspoints), minlon, xstep);
		int yPoints[] = scalePoints(GPSUtils.getLatitudes(gpspoints), minlat, ystep);

		for (int i = 0; i < xPoints.length; i++) {
			xPoints[i] += MARGIN;
			yPoints[i] = ybase - yPoints[i];
		}

		int circleRadius = 3;

		setColor(0, 255, 0);
		fillCircle(xPoints[0], yPoints[0], circleRadius);

		for (int i = 0; i < xPoints.length - 1; i++) {
			drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
			fillCircle(xPoints[i + 1], yPoints[i + 1], circleRadius);
		}

	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0);
		setFont("Courier", 12);

		String listInfo[] = GPSComputer.statisticsStr().split("\n");

		for (int i = 0; i < listInfo.length; i++)
			drawString(listInfo[i], TEXTDISTANCE, TEXTDISTANCE + (TEXTDISTANCE * i));

	}

	public void replayRoute(int ybase) {

		int xPoints[] = scalePoints(GPSUtils.getLongitudes(gpspoints), minlon, xstep);
		int yPoints[] = scalePoints(GPSUtils.getLatitudes(gpspoints), minlat, ystep);

		for (int i = 0; i < xPoints.length; i++) {
			xPoints[i] += MARGIN;
			yPoints[i] = ybase - yPoints[i];
		}
		
		double speeds[] = GPSComputer.speeds();
		double maxSpeed = GPSUtils.findMax(speeds);
		
		maxSpeed /= 10;
		for (int i = 0; i < speeds.length; i++) {
			speeds[i] = Math.max((speeds[i]/maxSpeed),1);
		}
		
		int circleRadius = 4;
		
		setColor(0, 0, 255);
		int circleId = fillCircle(xPoints[0], yPoints[0], circleRadius);
		for (int i = 1; i < xPoints.length; i++) {
			setSpeed((int)speeds[i-1]);
			moveCircle(circleId,xPoints[i],yPoints[i]);
		}
		
	}

}
