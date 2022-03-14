package rover;

public class Rover {
	private int x;
	private int y;
	private char orientation;
	private String orders;

	private final String ORIENTATION_STRING = "NESW";

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public char getOrientation() {
		return orientation;
	}

	protected void setOrders(String orders) {
		this.orders = orders;
	}

	protected Rover(int x, int y, char orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	private void move(int maxX, int maxY) {
		if (this.orientation == 'N' && this.y < maxY)
			this.y = this.y + 1;
		else if (this.orientation == 'S' && this.y > 0)
			this.y = this.y - 1;
		else if (this.orientation == 'E' && this.x < maxX)
			this.x = this.x + 1;
		else if (this.orientation == 'W' && this.x > 0)
			this.x = this.x - 1;
	}

	private void rotateR() {
		int i = ORIENTATION_STRING.indexOf(this.orientation);
		this.orientation = ORIENTATION_STRING.charAt((i + 1) % 4);
	}

	private void rotateL() {
		int i = ORIENTATION_STRING.indexOf(this.orientation);
		if (i == 0)
			i = 4;
		this.orientation = ORIENTATION_STRING.charAt((i - 1) % 4);
	}

	public void doOrders(int maxX, int maxY) {
		for (int i = 0; i < this.orders.length(); i++) {
			if (this.orders.charAt(i) == 'M')
				this.move(maxX, maxY);
			if (this.orders.charAt(i) == 'R')
				this.rotateR();
			if (this.orders.charAt(i) == 'L')
				this.rotateL();
		}
	}

	public String toString() {
		return this.x + " " + this.y + " " + this.orientation;
	}
}
