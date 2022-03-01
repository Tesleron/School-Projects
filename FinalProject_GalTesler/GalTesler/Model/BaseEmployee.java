package Model;

public class BaseEmployee extends Employee {
	private int monthlyPayment;

	public BaseEmployee(int hourDeltaFromMvc, int hourlyPayment, ePreferences preference) {
		super(preference, hourDeltaFromMvc);
		monthlyPayment = 160 * hourlyPayment;
	}

	public String toString() {
		return super.toString() + "Employee type:" + getClass().getSimpleName() + "\n Monthly payment:"
				+ monthlyPayment;
	}

}
