package test;

public class CarDTO {
	
	private String company; //제조사
	private String name; // 차량이름
	private String year;// 연도
	private int efficiency;//연비
	private int money;//가격
		
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public CarDTO() {
		
	}
	public CarDTO(String company, String name, String year, int efficiency, int money) {
		
		this.company = company;
		this.name = name;
		this.year = year;
		this.efficiency = efficiency;
		this.money = money;
	}
	@Override
	public String toString() {
		return "CarDTO [company=" + company + ", name=" + name + ", year=" + year + ", efficiency=" + efficiency
				+ ", money=" + money + "]";
	}
	
	
	
	

}
