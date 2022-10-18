package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

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

		double[] latitudes = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;

	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitudes = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;

		double phi1 = toRadians(gpspoint1.getLatitude());
		double phi2 = toRadians(gpspoint2.getLatitude());
		
		double dPhi = phi2 - phi1;
		
		double dLambda = toRadians(gpspoint1.getLongitude() - gpspoint2.getLongitude());
		
		double a = pow(sin(dPhi/2),2) + cos(phi1) * cos(phi2) * pow(sin(dLambda/2),2);
		
		double c = 2 * atan2(sqrt(a),sqrt(1-a));
		
		d = R * c;
		return d;


	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		
		double speed;

		int sec1 = gpspoint1.getTime();
		int sec2 = gpspoint2.getTime();
		
		secs = sec2 - sec1;
		
		speed = (distance(gpspoint1,gpspoint2) / secs) * 3.6;
		return  (double) round(speed * 100) / 100;


	}

	public static String formatTime(int secs) {

		int hr = secs / 3600;
		int min = secs / 60 % 60;
		int sec = secs % 60;
		
		// 3 strenger med tier posisjon og ener posisjon
		
		String secStr = sec/10 + "" + sec%10 + "";
		String minStr = min/10 + "" + min%10 + "";
		String hrStr = hr/10 + "" + hr%10 + "";
		
		return "  " + hrStr + ":" + minStr + ":" + secStr; 


	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		double avrundet = (double) round(d * 100) / 100;
		
		String avrundetString = avrundet + "";
		str = " ";
		for (int i = 0; i <= avrundetString.length();i++) {
			str += " ";
		}
		
		str += avrundetString;

		return str;

	}
}
