package test;

import java.sql.SQLException;
import java.util.ArrayList;

import repository.DomainDBHelper;
import schemas.Domain;
import util.DBUtil;

public class DomainTests {

	public static void allTests() {
		getAllDomains();
		getDomainById();
		getDomainByFaculty();
		insertAndPermanentDelteDepartment();
	}

	private static void getAllDomains() {
		try {
			DomainDBHelper domainDBHelper = new DomainDBHelper();
			ArrayList<Domain> domains = domainDBHelper.getAllDomains();

			if (domains.size() != 1) {
				throw new Exception("Domains are too many or too few");
			}

			DBUtil.printNames(domains);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getDomainById() {
		try {
			DomainDBHelper domainDBHelper = new DomainDBHelper();
			ArrayList<Domain> domains = domainDBHelper.getDomainById(1);

			if (domains.size() != 1) {
				throw new Exception("Domains are too many or too few");
			}

			DBUtil.printNames(domains);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getDomainByFaculty() {
		try {
			DomainDBHelper domainDBHelper = new DomainDBHelper();
			ArrayList<Domain> domains = domainDBHelper.getDomainsByFaculty(1);

			if (domains.size() != 1) {
				throw new Exception("Domains are too many or too few");
			}

			DBUtil.printNames(domains);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertAndPermanentDelteDepartment() {
		try {
			DomainDBHelper domainDBHelper = new DomainDBHelper();
			Domain domain = new Domain(0, "bla bla", 1);

			int inserted = domainDBHelper.insert(domain);

			if (inserted == 1) {
				System.out.println("Domain inserted");
				ArrayList<Domain> departments = domainDBHelper.getAllDomains();
				int lastId = getLastId(departments);
				int deleted = domainDBHelper.permanentDelete(lastId);

				if (deleted == 1) {
					System.out.println("Domain deleted");
				} else {
					throw new Exception("Domain not deleted");
				}
			} else {
				throw new Exception("Domain not inserted");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int getLastId(ArrayList<Domain> list) {
		return list.get(list.size() - 1).getId();
	}

}
