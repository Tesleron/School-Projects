package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {
	private ArrayList<Department> allDepartments;
	private String companyName;

	public Company(String name) {
		companyName = name;
		allDepartments = new ArrayList<Department>();
	}

	public int calcEfficieny() {
		int allDeps = getAllDepartments().size();
		double totalEmpEfficieny = 0;

		for (int i = 0; i < allDeps; i++) {
			for (int j = 0; j < allDepartments.get(i).getRoles().size(); j++) {
				for (int k = 0; k < allDepartments.get(i).getRoles().get(j).getEmployees().size(); k++) {
					ePreferences currentDepPreference = allDepartments.get(i).getPreference();
					ePreferences currentEmpPreference = allDepartments.get(i).getRoles().get(j).getEmployees().get(k)
							.getPreference();
					int depHourDelta = allDepartments.get(i).getHourDelta();
					int empHourDelta = allDepartments.get(i).getRoles().get(j).getEmployees().get(k).getHourDelta();
					int difference = depHourDelta - empHourDelta;

					if (currentDepPreference.equals(ePreferences.HOME)
							&& currentEmpPreference.equals(ePreferences.HOME)) {
						totalEmpEfficieny += (8 * 0.1);
					} else if (currentDepPreference.equals(currentEmpPreference) && difference == 0) {
						totalEmpEfficieny += (empHourDelta * 0.2); // employee is 20% more efficient in his worktime
					} else {
						totalEmpEfficieny += (difference * (-0.2)); // employee is 20% less efficient in case there is no double bingo
					}

				}
			}
		}
		return (int) Math.round(totalEmpEfficieny);

	}

	public void addDepartment(Department d) {
		allDepartments.add(d);
	}

	public void setAllDepartments(ArrayList<Department> allDepartments) {
		this.allDepartments = allDepartments;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public ArrayList<Department> getAllDepartments() {
		return allDepartments;
	}

	public String getCompanyName() {
		return companyName;
	}

}
