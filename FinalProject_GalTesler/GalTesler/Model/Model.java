package Model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Model {
	private Company currentCompany;

	public Model() {

	}

	public boolean createCompany(String name) {
		this.currentCompany = new Company(name);
		return true;
	}
	
	public boolean createDepartment(String pref, boolean changeable, boolean synchronizable, int hourDelta) {
		if (!pref.equals("LATER") && !pref.equals("EARLIER"))
			hourDelta = 0;

		currentCompany.addDepartment(new Department(changeable, ePreferences.valueOf(pref), synchronizable, hourDelta));
		return true;
	}

	public boolean createRole(String pref, boolean changeable, boolean synchronizable, int hourDelta) {
		if (!pref.equals("LATER") && !pref.equals("EARLIER"))
			hourDelta = 0;
		currentCompany.getAllDepartments().get(currentCompany.getAllDepartments().size() - 1)
				.addRole(new Role(changeable, ePreferences.valueOf(pref), synchronizable, hourDelta));
		return true;
	}

	public boolean createEmployee(String pref,/* boolean changeable,*/ int hourDelta, String employeeType, int baseSalary,
			int hourly, int sales) {

		if (!pref.equals("LATER") && !pref.equals("EARLIER"))
			hourDelta = 0;

		if (employeeType.equals("BASE EMPLOYEE")) {
			currentCompany.getAllDepartments().get(currentCompany.getAllDepartments().size() - 1).getRoles()
					.get(currentCompany.getAllDepartments().get(currentCompany.getAllDepartments().size() - 1)
							.getRoles().size() - 1)
					.addEmployeeToRole(new BaseEmployee(hourDelta, baseSalary, ePreferences.valueOf(pref)));
		} else if (employeeType.equals("HOURLY EMPLOYEE")) {
			currentCompany.getAllDepartments().get(currentCompany.getAllDepartments().size() - 1).getRoles()
					.get(currentCompany.getAllDepartments().get(currentCompany.getAllDepartments().size() - 1)
							.getRoles().size() - 1)
					.addEmployeeToRole(new HourlyEmployee(hourDelta, hourly, ePreferences.valueOf(pref)));

		} else {
			currentCompany.getAllDepartments().get(currentCompany.getAllDepartments().size() - 1).getRoles()
					.get(currentCompany.getAllDepartments().get(currentCompany.getAllDepartments().size() - 1)
							.getRoles().size() - 1)
					.addEmployeeToRole(new BaseAndSalesEmployee(hourDelta, hourly, sales, ePreferences.valueOf(pref)));
		}
		return true;
	}

	public void updateDepData(int depIndex, String pref, boolean changeable, boolean synchronizable, int hourDelta) {
		getCompany().getAllDepartments().get(depIndex).setDepPref(ePreferences.valueOf(pref));
		getCompany().getAllDepartments().get(depIndex).setIsChangeAble(changeable);
		getCompany().getAllDepartments().get(depIndex).setIsSynchronizable(synchronizable);
		getCompany().getAllDepartments().get(depIndex).setHourDelta(hourDelta);

		if (synchronizable == true) {
			updateEmpDataDep(depIndex, pref, hourDelta);
		}
	}

	public void updateRoleData(int depIndex, String pref, boolean changeable, boolean synchronizable, int hourDelta,
			int roleIndex) {
		getCompany().getAllDepartments().get(depIndex).getRoles().get(roleIndex)
				.setRolePreference(ePreferences.valueOf(pref));
		getCompany().getAllDepartments().get(depIndex).getRoles().get(roleIndex).setIsChangeAble(changeable);
		getCompany().getAllDepartments().get(depIndex).getRoles().get(roleIndex).setIsSynchronizable(synchronizable);
		getCompany().getAllDepartments().get(depIndex).getRoles().get(roleIndex).setHourDelta(hourDelta);

		if (synchronizable == true) {
			updateEmpDataRole(depIndex, roleIndex, pref, hourDelta);
		}
	}

	public void updateEmpDataDep(int depIndex, String pref, int hourDelta) {
		for (int i = 0; i < currentCompany.getAllDepartments().get(depIndex).getRoles().size(); i++) {
			for (int j = 0; j < currentCompany.getAllDepartments().get(depIndex).getRoles().get(i).getEmployees()
					.size(); j++) {
				currentCompany.getAllDepartments().get(depIndex).getRoles().get(i).getEmployees().get(j)
						.setMyPreference(ePreferences.valueOf(pref));
				currentCompany.getAllDepartments().get(depIndex).getRoles().get(i).getEmployees().get(j)
						.setHourDelta(hourDelta);
			}
		}
	}

	public void updateEmpDataRole(int depIndex, int roleIndex, String pref, int hourDelta) {
		for (int i = 0; i < currentCompany.getAllDepartments().get(depIndex).getRoles().get(roleIndex).getEmployees()
				.size(); i++) {
			currentCompany.getAllDepartments().get(depIndex).getRoles().get(roleIndex).getEmployees().get(i)
					.setMyPreference(ePreferences.valueOf(pref));
			currentCompany.getAllDepartments().get(depIndex).getRoles().get(roleIndex).getEmployees().get(i)
					.setHourDelta(hourDelta);

		}
	}
	
	public void loadCompany() throws FileNotFoundException, ClassNotFoundException, IOException {
		this.currentCompany = new ReadObject().readCompany();
	}
	
	public void saveToFile() throws FileNotFoundException, IOException {
		WriteObject object = new WriteObject(currentCompany);
	}
	
	// GETTERS

	public Company getCompany() {
		return currentCompany;
	}

}
