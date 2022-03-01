package Model;

public class HourlyEmployee extends Employee{
	private int hourlyPayment;

	public HourlyEmployee(int hourDeltaFromMvc/*, Role role*/,int hourlyPayment,ePreferences preference) {		
		super(preference,hourDeltaFromMvc);
		this.hourlyPayment=hourlyPayment;
	}
	
	public String toString() {
		return super.toString() + "Employee type:" + getClass().getSimpleName() + "\n Hourly payment:"
				+ hourlyPayment;
	}

}
