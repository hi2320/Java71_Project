package comq.domain;

public class Curator {

	private int curId;
	private String purpose;
	
	public Curator() {
		System.out.println(this.getClass());
	}
	
	public int getCurId() {
		return curId;
	}
	public void setCurId(int curId) {
		this.curId = curId;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Override
  public String toString() {
	  return "Curator [curId=" + curId + ", purpose=" + purpose + "]";
  }
	
	
}
