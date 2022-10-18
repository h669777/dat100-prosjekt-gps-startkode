package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
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

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		ystep = MAPYSIZE / (Math.abs(maxlat-minlat));
		return ystep;
	}

	public void showRouteMap(int ybase) {
		
		int sumx = 0;
		int sumy = 0;		
		
		double[] lonArr = GPSUtils.getLongitudes(gpspoints);
		double[] latArr = GPSUtils.getLatitudes(gpspoints);

		for (int i = 0; i < gpspoints.length-1; i++) {
			
			double lon = lonArr[i+1] - lonArr[i];
			double lat = latArr[i+1] - latArr[i];
			
			
			double x = xstep()*lon;
			long x1 = Math.round(x);
			
			// så x1 kan brukes i drawLine (kan ikke ha long)
			int x2 = (int) x1;
			
			double y = ystep()*lat;
			long y1 = Math.round(y);
			
			// så y1 kan brukes i drawLine (kan ikke ha long)
			int y2 = (int) y1;
			
			sumx+= x1;
			sumy+= y1;
			
			System.out.println(latArr[i]);
			
			
			setColor(0,250,0);
			fillCircle(MARGIN+sumx,(-sumy/2)+120,2);
			
			drawLine(MARGIN+sumx,(-sumy/2)+120,MARGIN+sumx-x2,((-sumy+y2)/2)+120);

		}
		
		
	}
	private static double WEIGHT = 80.0;
	public void showStatistics() {

		setColor(0,0,0);
		setFont("Courier",12);
		
		drawString("Total Time     :" + GPSUtils.formatTime(gpscomputer.totalTime()),20,20);
		drawString("Total distance :    " + GPSComputer.rund (gpscomputer.totalDistance() /1000)  + " km",20,40);
		drawString("Total elevation:    " + GPSComputer.rund (gpscomputer.totalElevation()) + " m",20,60);
		drawString("Max speed      :    " + gpscomputer.maxSpeed() + " km/t",20,80);
		drawString("Average speed  :    " + GPSComputer.rund(gpscomputer.averageSpeed()) + " km/t",20,100);
		drawString("Energy         :    " + GPSComputer.rund(gpscomputer.totalKcal(WEIGHT)) +" kcal ",20,120);
	}

}
