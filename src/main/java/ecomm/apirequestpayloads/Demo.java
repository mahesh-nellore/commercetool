package ecomm.apirequestpayloads;

public class Demo {

	public static void main(String[] args) {
		Test t = new Test();
		t.setEmplopyeeName("Priya");
		System.out.println(t.getEmplopyeeName());
		System.out.println(t.getEmployeeId());
	}
}

class Test{
	private String emplopyeeName;
	
	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	public String getEmplopyeeName() {
		return emplopyeeName;
	}
	
	public void setEmplopyeeName(String emplopyeeName) {
			this.emplopyeeName = emplopyeeName;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	private int employeeId;
}