
public class Grid {
	int y;
	int x;
	String c = "";
	String frame = "";
	String disp = "";

	public Grid(Point aPoint, String mainChar) {
		x = aPoint.getX();
		y = aPoint.getY();
		c = mainChar;
	}

	public String getDisplay() {
		return disp;
	}

	public String makeDisplay() {
		for (int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
				disp = disp + c + " ";
			}
			disp = disp + "\n";
		}
		return disp;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String framework() {
		for (int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
				frame = frame + c;
			}
			frame = frame + "\n";
		}
		return frame;
	}

	public String replaceCell(int column, int row, String character) {

		int index = ((((x * 2) + 1) * (row - 1)) - 1) + ((column * 2) - 1);

		// y is how many rows there are, x is how many columns.

		disp = disp.substring(0, index) + character + disp.substring((index + 1), disp.length());
		return disp;
	}

	public String replaceCell(Point pointA, String character) {

		int column = pointA.getX();
		int row = pointA.getY();

		int index = ((((x * 2) + 1) * (row - 1)) - 1) + ((column * 2) - 1);

		// y is how many rows there are, x is how many columns.

		disp = disp.substring(0, index) + character + disp.substring((index + 1), disp.length());
		return disp;
	}

}
