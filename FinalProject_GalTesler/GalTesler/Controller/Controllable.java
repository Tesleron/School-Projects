package Controller;

public interface Controllable {
	void sendCompanyData(String cmpName);

	void sendDepartmentData(String pref, boolean changeable, boolean synchronizable, int hourDelta);

	void sendRoleData(String pref, boolean changeable, boolean synchronizable, int hourDelta);

	void sendEmployeeData(String pref, int hourDelta, String employeeType, int baseSalary, int sales, int hourly);

	void updateDepData(int depIndex, String pref, boolean changeable, boolean synchronizable, int hourDelta);

	public void updateRoleData(int depIndex, String pref, boolean changeable, boolean synchronizable, int hourDelta,
			int roleIndex);

	public int getWinOrLoss();
}
