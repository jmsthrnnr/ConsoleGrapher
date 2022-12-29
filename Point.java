

public class Point {
	int x;
	int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Point(Point pointA){
		this.x = pointA.getX();
		this.y = pointA.getY();
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public String string(){
		return ("("+ x + ", " + y + ")");
	}
	
	public Point origin(Grid aGrid){
		int halfX = (int) ((aGrid.getX() / 2) + 0.5) + 1;
		int halfY = (int) ((aGrid.getY() / 2) + 0.5) + 1;
		x = x + halfX;
		y = -y + halfY;
		return new Point(x,y);
	}
	
	public Point midpoint(Point pointA){
		int mx = (x + pointA.getX()) / 2;
		int my = (y + pointA.getY()) / 2;
		return new Point(mx, my);
	}
}
