package rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Mars {

	public static int maxX;
	public static int maxY;

	
	static ArrayList<Rover> Landing(String fileName) {
		ArrayList<Rover> rovers = new ArrayList<Rover>();

		try {
			File myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);
			
			if (myReader.hasNextLine()) {
				String data[] = myReader.nextLine().split(" ");
				maxX = Integer.parseInt(data[0]);
				maxY = Integer.parseInt(data[1]);
			}
			
			while (myReader.hasNextLine()) {
				String data[] = myReader.nextLine().split(" ");
				Rover rover = new Rover(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2]);
				if (myReader.hasNextLine())
					rover.setOrders(myReader.nextLine());
				else 
					rover.setOrders(null);
				rovers.add(rover);
			}
			
			myReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("There's something wrong with your file, buddy!");
			e.printStackTrace();
		}

		return rovers;
	}
	
	
	static void Exploring(ArrayList<Rover> rovers)
	{
		for (Rover r : rovers) {
			r.doOrders();
		}
	}
	
	
	static void SendingData(ArrayList<Rover> rovers)
	{
		for (Rover r : rovers) {
			System.out.println(r);
		}
	}
	

	public static void main(String[] args) throws Exception {
		if (args.length < 1)
			throw new Exception("Please, type the name of the file to read as first parameter");

		ArrayList<Rover> rovers = Landing(args[0]);
		Exploring(rovers);
		SendingData(rovers);
	}
}
