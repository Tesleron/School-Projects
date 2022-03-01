package View;

import Model.Company;

public interface Viewable {
	void InsertEntitiesPopup();
	void showEntitiesPopup(Company company);
	void showChangeRolePref(Company company);
	void showChangeDepPref(Company company);
	void addDepartmentWindow();
	void addRoleWindow();
	void addEmployeeWindow();
	void afterSubmitEmpWindow();
	void prefEditorWindow(String selectedDepartment);
	void prefRoleEditorWindow(String selectedRole);
	void alertCompanyNull(String s);
	void alertEditedSuccessfuly();
	void showProfit(int res);
	void showLoss(int res);
	boolean askBeforeInsertion();
	
}
