package test;

public class CarLogDTO {
	public String id;
	public String pwd;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public CarLogDTO() {
		
	}
	public CarLogDTO(String id, String pwd) {
		
		this.id = id;
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "CarLogDTO [id=" + id + ", pwd=" + pwd + "]";
	}
	

	
}
