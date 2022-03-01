package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Changeable, Synchronizable, Serializable {
//	public enum ePreferences {EARLIER,LATER,SAME,HOME};
	private ePreferences departmentPreference;
	private ArrayList<Role> Roles;
	private boolean isChangeAble;
	private boolean isSynchronizable;
	private int hourDelta;

	public Department(boolean isChangeAble, ePreferences p, boolean isSynchronizable, int hourDelta) {
		Roles = new ArrayList<Role>();
		setDepPref(p);
		setIsChangeAble(isChangeAble);
		setHourDelta(hourDelta);
		setIsSynchronizable(isSynchronizable);
		
		
	}

	public void setIsChangeAble(boolean isChangeAble) {
		this.isChangeAble = isChangeAble;
	}
	public void setHourDelta(int hourDelta) {
		this.hourDelta = hourDelta;
	}
	public void setIsSynchronizable(boolean isSynchronizable) {
		this.isSynchronizable = isSynchronizable;
	}
	public void setDepPref(ePreferences p) {
		departmentPreference = p;
	}

	public ArrayList<Role> getRoles() {
		return Roles;
	}

	@Override

	public String toString() {
		String s;
		s = "Department preference: " + departmentPreference + "\n isSynchronizable: " + isSynchronizable
				+ "\n isChangeAble: " + isChangeAble + "\n Hour delta: " + hourDelta
				+ "\n Role list in department: \n";
		for (int i = 0; i < Roles.size(); i++) {
			s += (i + 1) + ")" + Roles.get(i).toString() + "\n";
		}
		return s;
	}

	public void addRole(Role r) {
		Roles.add(r);
	}

	@Override
	public ePreferences getPreference() {
		return departmentPreference;
	}

	@Override
	public int getHourDelta() {
		return hourDelta;
	}
	@Override
	public boolean isSynchonizable() {
		return isSynchronizable;
	}
	
	public boolean getIsChangeAble() {
		return isChangeAble;
	}

}
