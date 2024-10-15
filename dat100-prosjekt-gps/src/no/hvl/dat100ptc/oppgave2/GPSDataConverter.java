package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	private static int TIME_STARTINDEX = 11;
	private static int TIME_ENDINDEX = 23;

	public static int toSeconds(String timestr) {

		String timePart = timestr.substring(TIME_STARTINDEX, TIME_ENDINDEX);
		String parts[] = timePart.split(":");

		int partValue[] = new int[parts.length];
		for (int i = 0; i < parts.length; i++) {
			partValue[i] = (int) Math.round(Double.valueOf(parts[i]));
		}

		int hr = partValue[0] * 60 * 60, min = partValue[1] * 60, sec = partValue[2];

		return hr + min + sec;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
		int time = toSeconds(timeStr);
		double latitude = Double.valueOf(latitudeStr);
		double longitude = Double.valueOf(longitudeStr);
		double elevation = Double.valueOf(elevationStr);
		
		return new GPSPoint(time, latitude, longitude, elevation);
	}

}
