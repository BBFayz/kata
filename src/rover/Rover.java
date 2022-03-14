package rover;

public class Rover {
	private int x;
	private int y;
	private String orientation;
	private String orders;

	private final String ORIENTATION_STRING = "NESW";

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

	private void move() {
		int oldX = this.x;
		int oldY = this.y;

		if (this.orientation.charAt(0) == 'N')
			setY(this.y + 1);
		else if (this.orientation.charAt(0) == 'S')
			setY(this.y - 1);
		else if (this.orientation.charAt(0) == 'E')
			setX(this.x + 1);
		else if (this.orientation.charAt(0) == 'W')
			setX(this.x - 1);

		if (this.x > Mars.maxX || this.x < 0 || this.y > Mars.maxY || this.y < 0) {
			this.x = oldX;
			this.y = oldY;
		}
	}

	private void rotateR() {
		int i = ORIENTATION_STRING.indexOf(this.orientation);
		this.setOrientation(ORIENTATION_STRING.charAt((i + 1) % 4));
	}

	private void setOrientation(char orientation) {
		this.orientation = String.valueOf(orientation);
	}

	private void rotateL() {
		int i = ORIENTATION_STRING.indexOf(this.orientation);
		if (i == 0)
			i = 4;
		this.setOrientation(ORIENTATION_STRING.charAt((i - 1) % 4));
	}

	public void doOrders() {
		for (int i = 0; i < this.orders.length(); i++) {
			if (this.orders.charAt(i) == 'M')
				this.move();
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
