package comq.domain;

public class Answer {

	private int ansId;
	private int queId;
	private String aSente;
	private String aSpec;
	
	public Answer() {
		System.out.println(this.getClass());
	}
	
	public int getAnsId() {
		return ansId;
	}
	public void setAnsId(int ansId) {
		this.ansId = ansId;
	}
	public int getQueId() {
		return queId;
	}
	public void setQueId(int queId) {
		this.queId = queId;
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
	  return "\nAnswer [ansId=" + ansId + ", queId=" + queId + ", aSente=" + aSente
	      + ", aSpec=" + aSpec + "]";
  }
	
}
