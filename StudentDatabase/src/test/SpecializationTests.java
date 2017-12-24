package test;

import java.sql.SQLException;
import java.util.ArrayList;

import repository.SpecializationDBHelper;
import schemas.Specialization;
import util.DBUtil;

public class SpecializationTests {

	public static void allTests() {
		getAllSpecializations();
		getSpecializationById();
		getSpecializationsByDomain();
		getSpecializationsByName();
		insertAndPermanentDelteDepartment();
	}

	private static void getAllSpecializations() {
		try {
			SpecializationDBHelper specializationDBHelper = new SpecializationDBHelper();
			ArrayList<Specialization> specializations = specializationDBHelper.getAllSpecializations();

			if (specializations.size() != 1) {
				throw new Exception("Specializations are too many or too few");
			}

			DBUtil.printNames(specializations);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSpecializationById() {
		try {
			SpecializationDBHelper specializationDBHelper = new SpecializationDBHelper();
			ArrayList<Specialization> specializations = specializationDBHelper.getSpecializationById(1);

			if (specializations.size() != 1) {
				throw new Exception("Specializations are too many or too few");
			}

			DBUtil.printNames(specializations);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSpecializationsByName() {
		try {
			SpecializationDBHelper specializationDBHelper = new SpecializationDBHelper();
			ArrayList<Specialization> specializations = specializationDBHelper.getSpecializationsByName("Calculatoare");

			if (specializations.size() != 1) {
				throw new Exception("Specializations are too many or too few");
			}

			DBUtil.printNames(specializations);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSpecializationsByDomain() {
		try {
			SpecializationDBHelper specializationDBHelper = new SpecializationDBHelper();
			ArrayList<Specialization> specializations = specializationDBHelper.getSpecializationsByDomain(1);

			if (specializations.size() != 1) {
				throw new Exception("Specializations are too many or too few");
			}

			DBUtil.printNames(specializations);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertAndPermanentDelteDepartment() {
		try {
			SpecializationDBHelper specializationDBHelper = new SpecializationDBHelper();
			Specialization specialization = new Specialization(0, "bla bla", 1);

			int inserted = specializationDBHelper.insert(specialization);

			if (inserted == 1) {
				System.out.println("Specialization inserted");
				ArrayList<Specialization> specializations = specializationDBHelper.getAllSpecializations();
				int lastId = getLastId(specializations);
				int deleted = specializationDBHelper.permanentDelete(lastId);

				if (deleted == 1) {
					System.out.println("Specialization deleted");
				} else {
					throw new Exception("Specialization not deleted");
				}
			} else {
				throw new Exception("Specialization not inserted");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int getLastId(ArrayList<Specialization> list) {
		return list.get(list.size() - 1).getId();
	}

}
