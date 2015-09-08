package comq.domain;

public class User {
	
	private int userId;
	private String email;
	private String pwd;
	private String nName;
	private String proPic;
	private String phone;
	private String userKind;
		
	private boolean isActive;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserKind() {
		return userKind;
	}
	public void setUserKind(String string) {
		this.userKind = string;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
  public String toString() {
	  return "User [userId=" + userId + ", email=" + email + ", pwd=" + pwd
	      + ", nName=" + nName + ", proPic=" + proPic + ", phone=" + phone
	      + ", userKind=" + userKind + ", isActive=" + isActive + "]";
  }
	
}
