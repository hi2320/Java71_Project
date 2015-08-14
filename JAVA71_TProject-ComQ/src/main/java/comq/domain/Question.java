package comq.domain;

public class Question {
	
	private int queId;
	private int curId;
	private String qSente;
	private String qProd;
	private String qType;
	
	public Question() {
		System.out.println(this.getClass());
	}
	
	public int getQueId() {
		return queId;
	}
	public void setQueId(int queId) {
		this.queId = queId;
	}
	public int getCurId() {
		return curId;
	}
	public void setCurId(int curId) {
		this.curId = curId;
	}
	public String getqSente() {
		return qSente;
	}
	public void setqSente(String qSente) {
		this.qSente = qSente;
	}
	public String getqProd() {
		return qProd;
	}
	public void setqProd(String qProd) {
		this.qProd = qProd;
	}
	public String getqType() {
		return qType;
	}
	public void setqType(String qType) {
		this.qType = qType;
	}
	
	@Override
  public String toString() {
	  return "Question [queId=" + queId + ", curId=" + curId + ", qSente="
	      + qSente + ", qProd=" + qProd + ", qType=" + qType + "]";
  }
	
}
