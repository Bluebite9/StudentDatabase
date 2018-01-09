package test;

public class AllTests {

	public static void main(String[] args) {
		String departments = "Departments passes";
		String domains = "Domains passes";
		String faculties = "Faculties passes";
		String groups = "Groups passes";
		String marks = "Marks passes";
		String professors = "Professors passes";
		String specializations = "Specializations passes";
		String students = "Students passes";
		String subjects = "Subjects passes";

		try {
			DepartmentTests.allTests();
		} catch (Exception e) {
			e.printStackTrace();
			departments = "Departments failed";
		}
		try {
			DomainTests.allTests();
		} catch (Exception e) {
			e.printStackTrace();
			domains = "Domains failed";
		}
		try {
			FacultyTests.allTests();
		} catch (Exception e) {
			e.printStackTrace();
			faculties = "Faculties failed";
		}
		try {
			GroupTests.allTests();
		} catch (Exception e) {
			e.printStackTrace();
			groups = "Groups failed";
		}
		try {
			MarkTests.allTests();
		} catch (Exception e) {
			e.printStackTrace();
			marks = "Marks failed";
		}
		try {
			ProfessorTests.allTests();
		} catch (Exception e) {
			e.printStackTrace();
			professors = "Professors failed";
		}
		try {
			SpecializationTests.allTests();
		} catch (Exception e) {
			e.printStackTrace();
			specializations = "Specializations failed";
		}
		try {
			StudentTests.allTests();
		} catch (Exception e) {
			e.printStackTrace();
			students = "Students failed";
		}
		try {
			SubjectTests.allTests();
		} catch (Exception e) {
			e.printStackTrace();
			subjects = "Subjects failed";
		}

		System.out.println(departments);
		System.out.println(domains);
		System.out.println(faculties);
		System.out.println(groups);
		System.out.println(marks);
		System.out.println(professors);
		System.out.println(specializations);
		System.out.println(students);
		System.out.println(subjects);

	}

}
