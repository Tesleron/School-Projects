package Model;

public class BaseAndSalesEmployee extends Employee{
	private double monthlyPayment;
	
	public BaseAndSalesEmployee(int hourDeltaFromMvc/*, Role role*/,int hourlyPayment,double salePrecentage,ePreferences preference) {
		super(preference,hourDeltaFromMvc);
		monthlyPayment = 160*hourlyPayment + salePrecentage;		
	}
	
	public String toString() {
		return super.toString() + "Employee type:" + getClass().getSimpleName() + "\n Monthly payment:"
				+ monthlyPayment;
	}
	
}
