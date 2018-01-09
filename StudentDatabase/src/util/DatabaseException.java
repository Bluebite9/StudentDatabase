package util;

public class DatabaseException extends Exception{

	public static final long serialVersionUID = -996993253874300869L;
	
	public static DatabaseException invalidName = new DatabaseException("Invalid name");
	public static DatabaseException invalidList = new DatabaseException("Invalid list");
	public static DatabaseException invalidYear = new DatabaseException("Invalid year");
	public static DatabaseException invalidSemester = new DatabaseException("Invalid semester");
	public static DatabaseException invalidType = new DatabaseException("Invalid type");
	public static DatabaseException invalidCredits = new DatabaseException("Invalid credits");
	public static DatabaseException invalidExamType = new DatabaseException("Invalid exam type");
	public static DatabaseException invalidMark = new DatabaseException("Invalid mark");
	public static DatabaseException invalidDate = new DatabaseException("Invalid date. Date format is YYYY-MM-DD");
	public static DatabaseException invalidSession = new DatabaseException("Invalid session");
	public static DatabaseException invalidNumberOfStudents = new DatabaseException("Invalid number of students");
	public static DatabaseException invalidSubgroup = new DatabaseException("Invalid subgroup");
	public static DatabaseException invalidScholarshipType = new DatabaseException("Invalid scholarship type");
	public static DatabaseException invalidCreditsNumber = new DatabaseException("Invalid credits number");
	public static DatabaseException invalidAddress = new DatabaseException("Invalid address");
	public static DatabaseException invalidDegree = new DatabaseException("Invalid degree");
	public static DatabaseException invalidBeginningYear = new DatabaseException("Invalid beginning year");
	public static DatabaseException invalidShortName = new DatabaseException("Name too long. Maximum is 10 characters");
	public static DatabaseException invalidMediumName = new DatabaseException("Name too long. Maximum is 45 characters");
	public static DatabaseException invalidLongName = new DatabaseException("Name too long. Maximum is 100 characters");

	public DatabaseException(String message) {
		super(message);
	}
	
}
