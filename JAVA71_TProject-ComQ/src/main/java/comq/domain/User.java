package comq.domain;

public class User {
	
	private int userId;
	private String email;
	private String pwd;
	private String proPic;
	private String userAccess;
		
	private boolean Active;
	
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
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	public String getUserAccess() {
		return userAccess;
	}
	public void setUserAccess(String userAccess) {
		this.userAccess = userAccess;
	}
	public boolean isActive() {
		return Active;
	}
	public void setActive(boolean active) {
		Active = active;
	}
	@Override
  public String toString() {
	  return "User [userId=" + userId + ", email=" + email + ", pwd=" + pwd
	      + ", proPic=" + proPic + ", userAccess=" + userAccess + ", Active="
	      + Active + "]";
  }
	
}
