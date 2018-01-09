package test;

import java.util.ArrayList;

import repository.DomainDBHelper;
import schemas.Domain;
import util.Util;

public class DomainTests {

	public static void allTests() throws Exception {
		System.out.println("--------------------DOMAIN TESTS--------------------");
		getAllDomains();
		getDomainById();
		getDomainByFaculty();
		insertAndPermanentDelteDepartment();
		System.out.println();
	}

	private static void getAllDomains() throws Exception {
		DomainDBHelper domainDBHelper = new DomainDBHelper();
		ArrayList<Domain> domains = domainDBHelper.getAllDomains();

		if (domains.size() != 1) {
			throw new Exception("Domains are too many or too few");
		}

		Util.printNames(domains);
	}

	private static void getDomainById() throws Exception {
		DomainDBHelper domainDBHelper = new DomainDBHelper();
		ArrayList<Domain> domains = domainDBHelper.getDomainById(1);

		if (domains.size() != 1) {
			throw new Exception("Domains are too many or too few");
		}

		Util.printNames(domains);
	}

	private static void getDomainByFaculty() throws Exception {
		DomainDBHelper domainDBHelper = new DomainDBHelper();
		ArrayList<Domain> domains = domainDBHelper.getDomainsByFaculty(1);

		if (domains.size() != 1) {
			throw new Exception("Domains are too many or too few");
		}

		Util.printNames(domains);
	}

	private static void insertAndPermanentDelteDepartment() throws Exception {
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
	}

	private static int getLastId(ArrayList<Domain> list) {
		return list.get(list.size() - 1).getId();
	}

}
