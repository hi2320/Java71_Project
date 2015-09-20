package comq.domain;

import java.sql.Date;

public class Estimate {

	private int estId;
	private int userId;
	private int curId;
	private String estName;
	private Date estDate;
	
	public Estimate() {}
	public Estimate(int userId, int curId, String estName) {
		this.userId = userId;
		this.curId = curId;
		this.estName = estName;
	}
	public Estimate(int estId, int userId, int curId, String estName, Date estDate) {
		this(userId, curId, estName);
		this.estId = estId;
		this.estDate = estDate;
	}
	
	public int getEstId() {
		return estId;
	}
	public void setEstId(int estId) {
		this.estId = estId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCurId() {
		return curId;
	}
	public void setCurId(int curId) {
		this.curId = curId;
	}
	public String getEstName() {
		return estName;
	}
	public void setEstName(String estName) {
		this.estName = estName;
	}
	public Date getEstDate() {
		return estDate;
	}
	public void setEstDate(Date estDate) {
		this.estDate = estDate;
	}

	@Override
	public String toString() {
		return "Estimate [estId=" + estId + ", userId=" + userId + ", curId="
				+ curId + ", estName=" + estName + ", estDate=" + estDate + "]";
	}
}
