package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Role implements Changeable, Synchronizable, Serializable {
	private ArrayList<Employee> roleOwners;
	private int roleHourDelta;
	private ePreferences rolePreference;
	private boolean isChangeAble;
	private boolean isSynchronizable;

	public Role(boolean isChangeable, ePreferences rolePreference, boolean isSynchronizable, int hourDelta) {
		roleOwners = new ArrayList<Employee>();		
		this.rolePreference = rolePreference;
		setIsChangeAble(isChangeable);
		setHourDelta(hourDelta);
		setIsSynchronizable(isSynchronizable);
	}

	@Override

	public String toString() {
		String s;
		s = "Role preference: " + rolePreference + "\n isSynchronizable: " + isSynchronizable
				+ "\n isChangeAble: " + isChangeAble + "\n Hour delta: " + roleHourDelta
				+ "\n Employee list in role: \n";
		for (int i = 0; i < roleOwners.size(); i++) {
			s += (i + 1) + ")" + roleOwners.get(i).toString() + "\n";
		}
		return s;
	}

	public boolean addEmployeeToRole(Employee emp) {
		return roleOwners.add(emp);
	}
	
	public void setIsChangeAble(boolean isChangeAble) {
		this.isChangeAble = isChangeAble;
	}
	public void setHourDelta(int hourDelta) {
		this.roleHourDelta = hourDelta;
	}
	public void setIsSynchronizable(boolean isSynchronizable) {
		this.isSynchronizable = isSynchronizable;
	}

	public boolean setRolePreference(ePreferences pref) {// to change role pref in future
		if (isChangeAble) {
			rolePreference = pref;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ePreferences getPreference() {
		return rolePreference;
	}

	@Override
	public boolean isSynchonizable() {
		return false;
	}
	@Override
	public int getHourDelta() {
		return roleHourDelta;
	}

	public boolean getIsChangeAble() {
		return isChangeAble;
	}
	
	public ArrayList<Employee> getEmployees(){
		return roleOwners;
	}

}
