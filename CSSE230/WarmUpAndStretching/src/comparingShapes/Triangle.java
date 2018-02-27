package comparingShapes;

/**
 * An implement of triangles with area and perimeter.
 * 
 * @author boutell. Created Dec 1, 2013. Edited by Zachary Taylor
 */
public class Triangle {
	private double a, b, c; // 3 sides

	/**
	 * Creates a triangle with the given sides.
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * 
	 */
	public Triangle(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * @return The area of this triangle.
	 */
	public double area() {
		// DONE: Implement this. Hint: lookup and use Heron's formula.
		double s = (this.a + this.b + this.c) / 2;
		return Math.sqrt(s * (s - this.a) * (s - this.b) * (s - this.c));
	}

	/**
	 * @return The perimeter of this triangle.
	 */
	public double perimeter() {
		return this.a + this.b + this.c;
	}

	@Override
	public String toString() {
		return String.format("Triangle with a=%.2f,b=%.2f,c=%.2f", this.a, this.b, this.c);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Triangle)) {
			return false;
		}
		Triangle other = (Triangle) obj;
		// CONSIDER: not very robust, but works for testing purposes.
		return this.a == other.a && this.b == other.b && this.c == other.c;
	}
}
