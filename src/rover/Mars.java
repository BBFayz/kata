package rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Rover {
	private int x;
	private int y;
	private String orientation;
	private String orders;
	
	public static final String ORIENTATION_STRING = "NESW";
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String getOrientation() {
		return orientation;
	}
	
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	public String getOrders() {
		return orders;
	}
	
	public void setOrders(String orders) {
		this.orders = orders;
	}
	
	public Rover(int x, int y, String orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	
	private void move()
	{
		if (this.getOrientation().charAt(0) == 'N')
			setY(this.getY() + 1);
		else if (this.getOrientation().charAt(0) == 'S')
			setY(this.getY() - 1);
		else if (this.getOrientation().charAt(0) == 'E')
			setX(this.getX() + 1);
		else if (this.getOrientation().charAt(0) == 'W')
			setX(this.getX() - 1);
	}
	
	private void rotateR()
	{
		int i = ORIENTATION_STRING.indexOf(this.orientation);
		this.setOrientation(ORIENTATION_STRING.charAt((i+1)%4));
	}
	
	private void setOrientation(char orientation) {
		this.orientation = String.valueOf(orientation);
	}

	private void rotateL()
	{
		int i = ORIENTATION_STRING.indexOf(this.orientation);
		this.setOrientation(ORIENTATION_STRING.charAt((i+1)%4));
	}
	
	public void doOrders()
	{
		for (int i = 0; i < this.orders.length(); i++)
		{
			if (this.orders.charAt(i) == 'M')
				this.move();
			if (this.orders.charAt(i) == 'R')
				this.rotateR();
			if (this.orders.charAt(i) == 'L')
				this.rotateL();
		}
	}
}

public class Mars {
	static int maxX;
	static int maxY;

	static ArrayList<Rover> Parsing(String fileName) {
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
				rovers.add(rover);
			}
			
			myReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("There's something wrong with your file, buddy!");
			e.printStackTrace();
		}

		return rovers;
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 1)
			throw new Exception("Please, type the name of the file to read as first parameter");

		ArrayList<Rover> rovers = Parsing(args[0]);
		
		for (Rover r : rovers) {
			r.doOrders();
			System.out.println(r.getX() + " " + r.getY() + " " + r.getOrientation());
		}
	}
}
