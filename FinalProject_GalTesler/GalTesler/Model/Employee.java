package Model;

import java.io.Serializable;

public abstract class Employee implements Changeable, Serializable {
	private ePreferences myPreference;
	private int hourDelta;
	private boolean workFromHome = false;
	private static int empSerial = 1000;
	private int empNum;

	public Employee(ePreferences preference, int hourDeltaFromMvc) {
		setMyPreference(preference);
		setHourDelta(hourDeltaFromMvc);
		empNum = empSerial;
		empSerial++;
	}

	public void setMyPreference(ePreferences preference) {
		if (preference.equals(ePreferences.HOME)) {
			setWorkFromHome();
		}
		myPreference = preference;
	}

	public void setHourDelta(int hourDeltaFromMvc) {
		hourDelta = hourDeltaFromMvc;
	}

	@Override

	public String toString() {
		String s;
		s = "Employee preference: " + myPreference + "\n empNum: " + empNum + "\n Hour delta: " + hourDelta + "\n";
		return s;
	}

	@Override
	public ePreferences getPreference() {
		return myPreference;
	}
	public int getHourDelta() {
		return hourDelta;
	}

	public void setWorkFromHome() {
		workFromHome = true;
	}


}
