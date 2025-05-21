package data;

public class Subscriber {
	private int id;
	private String username;
	private String password;
	private String phoneNumber;
	private String email;
	private int code;
	
	
	/**
	 * @param id
	 * @param usrname
	 * @param password
	 * @param phoneNumber
	 * @param email
	 * @param code
	 */
	
	public Subscriber(int id, String usrname, String password, String phoneNumber, String email, int code) {
		super();
		this.id = id;
		this.username = usrname;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String usrname) {
		this.username = usrname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public String toString()
	{
		return "username "+username+" password "+password+" code "+code;
	}
}
