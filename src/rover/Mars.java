package rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Mars {

	private int maxX;
	private int maxY;
	
	private static Logger myLogger = Logger.getLogger(Mars.class.toString());
	
	public int getMaxY() {
		return maxY;
	}

	private void setMax(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public int getMaxX() {
		return maxX;
	}


	public ArrayList<Rover> land(String landingData) throws Exception {
		ArrayList<Rover> rovers = new ArrayList<>();

		try {
			File myObj = new File(landingData);
			Scanner myReader = new Scanner(myObj);
			
			if (myReader.hasNextLine()) {
				String data[] = myReader.nextLine().split(" ");
				if (data.length != 2)
					throw new Exception("Bondaries of the map badly defined");
				setMax(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
			}
			
			while (myReader.hasNextLine()) {
				String data[] = myReader.nextLine().split(" ");
				if (data.length != 3 || (data[2].charAt(0) != 'N' && data[2].charAt(0) != 'E' && data[2].charAt(0) != 'W' && data[2].charAt(0) != 'S'))
					throw new Exception("Something wrong about the landing data of a rover");
				Rover rover = new Rover(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2].charAt(0));
				if (myReader.hasNextLine())
					rover.setOrders(myReader.nextLine());
				else
					rover.setOrders("");
				rovers.add(rover);
			}
			
			myReader.close();
		} catch (FileNotFoundException e) {
			myLogger.log(Level.SEVERE, "There's something wrong with your file: [0] ", e.getMessage());
		}
		return rovers;
	}
	
	
	
	public void explore(ArrayList<Rover> rovers)
	{
		for (Rover r : rovers) {
			r.doOrders(this.getMaxX(),this.getMaxY());
		}
	}
	
	
	
	public void sendData(ArrayList<Rover> rovers)
	{
		//myLogger.setLevel(Level.FINEST);
		//ConsoleHandler handler = new ConsoleHandler();
        //handler.setLevel(Level.FINEST);
        //myLogger.addHandler(handler);
		for (Rover r : rovers) {
			//myLogger.log(Level.FINEST, r.toString());
			System.out.println(r);
		}
	}
	
	
	
	public void LaunchSpaceship(String[] landingData) throws Exception
	{
		if (landingData.length < 1)
			throw new Exception("Please, type the name of the file to read as first parameter");
		
		ArrayList<Rover> rovers = land(landingData[0]);
		explore(rovers);
		sendData(rovers);
	}
	
}
