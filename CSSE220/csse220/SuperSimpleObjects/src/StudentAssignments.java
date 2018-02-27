import java.util.HashMap;
public class StudentAssignments {
		private String student;
		private HashMap<String, Integer> grades;
		
		public StudentAssignments(String student){
			this.student = student; 
			this.grades = new HashMap<String, Integer>();
		}
		
		public void addAssignment(String assignment, int score) {
			grades.put(assignment, score);
		}
		
		public void printGradeReport() {
			String toPrint = this.student;
			for (String assignment : grades.keySet()) {
				toPrint +=" " + assignment + "-" + this.grades.get(assignment);
			}
			System.out.print(toPrint);
		}
		public StudentAssignments() {
			this("Studenty McStudenton");
		}
}
