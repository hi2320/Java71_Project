package comq.domain;

public class User {
	
	private int userId;
	private String email;
	private String pwd;
	private String nName;
	private String proPic;
	private int phone;
	private String userKind;
		
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getnName() {
		return nName;
	}
	public void setnName(String nName) {
		this.nName = nName;
	}
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getUserKind() {
		return userKind;
	}
	public void setUserKind(String string) {
		this.userKind = string;
	}
	
	@Override
  public String toString() {
	  return "User [userId=" + userId + ", eMail=" + email + ", pwd=" + pwd
	      + ", nName=" + nName + ", proPic=" + proPic + ", phone=" + phone
	      + ", userKind=" + userKind + "]";
  }
	
	
}
