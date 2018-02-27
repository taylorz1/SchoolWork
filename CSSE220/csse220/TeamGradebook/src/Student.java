import java.util.ArrayList;
/**
 * 
 * DONE This class generates a student.
 *
 * @author taylorz1.
 *         Created Sep 15, 2016.
 */
public class Student {
	private String name;
	private ArrayList<Double> grades;
	private float average;
	// DONE: You'll probably need to have some more fields here

	/**
	 * makes a new student object
	 * 
	 * @param newName
	 *            the name of the student
	 */
	public Student(String newName) {
		// DONE: initialize fields here
		this.name = newName;
		this.grades = new ArrayList<>();
		this.average = 0;
	}

	/**
	 * gets the name of the student
	 * 
	 * @return the name of the student
	 */
	public String getName() {
		// DONE your code here
		return this.name;
	}

	// DONE: You'll need to add some new methods here
	public void addGrade(double grade) {
		this.grades.add(grade);
	}

	public float getAverage() {
		double sum = 0;
		if (this.grades.size() != 0) {
			for (int i = 0; i < this.grades.size(); i++) {
				sum += this.grades.get(i);
			}
			this.average = (float) (sum / this.grades.size());
			return this.average;
		}
		return this.average;
	}
}