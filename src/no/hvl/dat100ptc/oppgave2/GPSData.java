package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;


public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		gpspoints = new GPSPoint[n];
		antall = 0;

	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	protected boolean insertGPS(GPSPoint gpspoint) {

		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			return true;
		}
		return false;

	}
	

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		//int timeK = Integer.parseInt(time.substring(17, 19));
		int timeK = GPSDataConverter.toSeconds(time);
		double latitudeK = Double.parseDouble(latitude);
		double longitudeK = Double.parseDouble(longitude);
		double elevationK = Double.parseDouble(elevation);

		GPSPoint gpspoint = new GPSPoint(timeK, latitudeK, longitudeK, elevationK);

		if (insertGPS(gpspoint)) {
			return true;
		} else {
			return false;
		}

	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		for (int i = 0; i < gpspoints.length; i++) {
			System.out.print(gpspoints[i].toString());

		}

		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
