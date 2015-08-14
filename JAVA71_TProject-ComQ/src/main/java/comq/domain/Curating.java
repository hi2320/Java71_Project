package comq.domain;

public class Curating {

	private int curId;
	private String purpose;
	
	private int queId;
	private String qSente;
	private String qProd;
	private String qType;
	
	private int ansId;
	private String aSente;
	private String aSpec;
	
	public Curating() {
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
	public int getQueId() {
		return queId;
	}
	public void setQueId(int queId) {
		this.queId = queId;
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
	public int getAnsId() {
		return ansId;
	}
	public void setAnsId(int ansId) {
		this.ansId = ansId;
	}
	public String getaSente() {
		return aSente;
	}
	public void setaSente(String aSente) {
		this.aSente = aSente;
	}
	public String getaSpec() {
		return aSpec;
	}
	public void setaSpec(String aSpec) {
		this.aSpec = aSpec;
	}
	
	@Override
  public String toString() {
	  return "Curating [curId=" + curId + ", purpose=" + purpose + ", queId="
	      + queId + ", qSente=" + qSente + ", qProd=" + qProd + ", qType="
	      + qType + ", ansId=" + ansId + ", aSente=" + aSente + ", aSpec="
	      + aSpec + "]";
  }
	 
}
