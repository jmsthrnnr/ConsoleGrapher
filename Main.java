import java.util.*;

public class Main {
	int XY = 0;

	public static int ScanInt() {
		Scanner read = new Scanner(System.in);
		int j = read.nextInt();
		return j;
	}

	public static String ScanLine() {
		Scanner scan = new Scanner(System.in);
		String c = scan.next();
		return c;
	}

	public static int ScanFirst() {
		Scanner scan = new Scanner(System.in);
		int c = scan.nextInt();
		c = (c * 2) + 1;
		return c;
	}

	public static void main(String[] args) {
		
		int count = 0;
		while (count < 1) {

			System.out.println("Input quadrant size:");
			int XY = ScanFirst();

			// System.out.println("Input x size:");
			// int x = ScanInt();

			// System.out.println("Input y size:");
			// int y = ScanInt();

			System.out.println("Input main grid character:");
			String mainChar = ScanLine();

			Point aPoint = new Point(XY, XY);

			Grid aGrid = new Grid(aPoint, mainChar);

			aGrid.makeDisplay();

			axis(aGrid);

			System.out.println(aGrid.getDisplay());

			System.out.println("Input action\n1. replace point\n2. animate point\n3. draw line\n4. graph");
			int option = ScanInt();

			System.out.println("Input replacement character:");
			String repl = ScanLine();

			if (option == 1) {
				replace(repl, aGrid);
			} else if (option == 2) {
				animate(repl, aGrid);
			} else if (option == 3) {
				drawLine(repl, aGrid);
			}else if(option == 4){
				grapher(repl, aGrid);
			}
			System.out.println("Make another?\n0. yes\n1. no");
			count = ScanInt();
		}
	
		/*
	int XY = ScanFirst();
		Point pointA = new Point(XY, XY);
		Grid aGrid = new Grid(pointA, ".");
		aGrid.makeDisplay();
		axis(aGrid);
		System.out.println(aGrid.getDisplay());
		*/
	}

	public static void axis(Grid aGrid) {
		int x = aGrid.getX();
		int y = aGrid.getY();
		int mx = (int) ((x / 2.0) + 0.5);
		int my = (int) ((y / 2.0) + 0.5);
		for (int i = 1; i <= x; i++) {
			aGrid.replaceCell(new Point(i, my), "-");
		}

		for (int j = 1; j <= y; j++) {
			aGrid.replaceCell(new Point(mx, j), "|");
		}

		aGrid.replaceCell(new Point(mx, my), "+");
	}

	public static void replace(String repl, Grid aGrid) {
		int count = 0;
		int ax;
		int ay;

		while (count < 1) {
			System.out.println("Input x value of point:");
			ax = ScanInt();

			System.out.println("Input y value of point:");
			ay = ScanInt();

			Point pointA = new Point(ax, ay);
			aGrid.replaceCell(pointA.origin(aGrid), repl);

			System.out.println(aGrid.getDisplay());

			System.out.println("Replace another?\n0. yes\n1. no");
			count = ScanInt();
		}
		System.out.println(aGrid.getDisplay());
	}

	public static void animate(String repl, Grid aGrid) {
		System.out.println("Input x value of point:");
		int ax = ScanInt();
		ax = ax + (int) (((aGrid.getX() / 2) + 0.5) + 1);

		System.out.println("Input y value of point:");
		int ay = ScanInt();
		ay = -ay + (int) (((aGrid.getY() / 2) + 0.5) + 1);

		System.out.println("Input direction\n1. vertical\n2. horizontal");
		int dir = ScanInt();

		int w = aGrid.getX();
		int h = aGrid.getY();

		try {
			if (dir == 1) {
				for (int i = ax; i <= w; i++) {
					for (int j = ay; j <= h; j++) {
						System.out.println("\033[H\033[2J");
						System.out.flush();
						aGrid.replaceCell(new Point(i, j), repl);
						System.out.println(aGrid.getDisplay());
						Thread.sleep(50);
					}
				}
			} else if (dir == 2) {
				for (int i = ay; i <= h; i++) {
					for (int j = ax; j <= w; j++) {
						System.out.println("\033[H\033[2J");
						System.out.flush();
						aGrid.replaceCell(new Point(i, j), repl);
						System.out.println(aGrid.getDisplay());
						Thread.sleep(50);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void drawLine(String repl, Grid aGrid) {

		System.out.println("Input x value of first point:");
		int ax = ScanInt();

		System.out.println("Input y value of first point:");
		int ay = ScanInt();

		System.out.println("Input x value of second point:");
		int bx = ScanInt();

		System.out.println("Input y value of second point:");
		int by = ScanInt();

		Point pointA = new Point(ax, ay);

		Point pointB = new Point(bx, by);

		recurLine(pointA, pointB, repl, aGrid);

		System.out.println(aGrid.getDisplay());
	}

	public static void recurLine(Point pointA, Point pointB, String repl, Grid aGrid) {
		// System.out.println("from: " + ax + " " + ay + " to: " + bx + " " + by);
		int ax = pointA.getX();
		int ay = pointA.getY();
		int bx = pointB.getX();
		int by = pointB.getY();
		int yDiff = by - ay;
		int xDiff = bx - ax;
		

		
		if (yDiff == 0) {
			for (int i = 0; i <= Math.abs(xDiff); i++) {
				aGrid.replaceCell((ax + i), ay, repl);
			}
		} else if (xDiff == 0) {
			for (int i = 0; i <= Math.abs(yDiff); i++) {
				aGrid.replaceCell(ax, (ay + i), repl);
			}
		} else {
			if (Math.abs(xDiff) == Math.abs(yDiff)) {

				int slope = yDiff / xDiff;
				if (slope == 1) {
					

					for (int i = 0; i <= Math.abs(yDiff); i++) {
						aGrid.replaceCell((ax + i), (ay + i), repl);
					}


				} else {

					for (int i = 0; i <= Math.abs(yDiff); i++) {
						aGrid.replaceCell((ax - i), (ay + i), repl);
					}
				}
			} else {
				Point pointM = pointA.midpoint(pointB);
				recurLine(pointA, pointM.origin(aGrid), repl, aGrid);
				recurLine(pointM.origin(aGrid), pointB, repl, aGrid);
			}
		}
		
	}
	
	public static void slopeLine(Point pointA, Point pointB, String repl, Grid aGrid) {
		// System.out.println("from: " + ax + " " + ay + " to: " + bx + " " + by);
		int ax = pointA.getX();
		int ay = pointA.getY();
		int bx = pointB.getX();
		int by = pointB.getY();
		int yDiff = Math.abs(by - ay);
		int xDiff = Math.abs(bx - ax);

		if (yDiff == 0) {
			for (int i = 0; i <= xDiff; i++) {
				aGrid.replaceCell((ax + i), ay, repl);
			}
		} else if (xDiff == 0) {
			for (int i = 0; i <= yDiff; i++) {
				aGrid.replaceCell(ax, (ay + i), repl);
			}
		} else {
			double slope = yDiff / xDiff;
			if (Math.abs(xDiff) == Math.abs(yDiff)) {
				
				if (slope == 1) {
					for (int i = 0; i <= yDiff; i++) {
						aGrid.replaceCell((ax + i), (ay + i), repl);
					}
				} else if(slope == -1){
					for (int i = 0; i <= yDiff; i++) {
						aGrid.replaceCell((ax - i), (ay + i), repl);
					}
				}
			} else {
				for(int i = 0; i < yDiff; i ++){
					aGrid.replaceCell((int) (ax + (i * slope)), (int) (ay + (i * (1/slope))), repl);
				}
			}
		}
	}

	public static void grapher(String repl, Grid gridA) {
		int x1;
		int y1;
		int xMin = -((int) ((gridA.getX() / 2)));
		int xMax = ((int) ((gridA.getX() / 2)));
		int yMin = -((int) ((gridA.getY() / 2)));
		int yMax = ((int) ((gridA.getY() / 2)));

		for(int t = -10; t < 10; t ++){
			x1 = t;
			y1 = (int) (3 * (Math.sin(t)));
			Point pointA = new Point(t, (int) (3 * (Math.sin(t))));
			Point pointB = new Point(t + 1, (int) (3 * (Math.sin(t + 1))));

			if(x1 >= xMin && x1 <= xMax && y1 >= yMin && y1 <= yMax){
				
				gridA.replaceCell(pointA.origin(gridA), repl);
				System.out.println(x1 + " " + y1);
			}
		}
		System.out.println(gridA.getDisplay());
	}

	
}