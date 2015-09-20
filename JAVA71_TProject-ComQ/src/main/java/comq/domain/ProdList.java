package comq.domain;

public class ProdList {

	private int prolId;
	private int estId;
	private String prolKind;
	private int prodId;
	private String anlAns;

	public ProdList() {}
	public ProdList(int estId, String prolKind, int prodId, String anlAns) {
		this.estId = estId;
		this.prolKind = prolKind;
		this.prodId = prodId;
		this.anlAns = anlAns;
	}
	public ProdList(int prolId, int estId, String prolKind, int prodId, String anlAns) {
		this(estId, prolKind, prodId, anlAns);
		this.prolId = prolId;
	}
	
	public int getProlId() {
		return prolId;
	}
	public void setProlId(int prolId) {
		this.prolId = prolId;
	}
	public int getEstId() {
		return estId;
	}
	public void setEstId(int estId) {
		this.estId = estId;
	}
	public String getProlKind() {
		return prolKind;
	}
	public void setProlKind(String prolKind) {
		this.prolKind = prolKind;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getAnlAns() {
		return anlAns;
	}
	public void setAnlAns(String anlAns) {
		this.anlAns = anlAns;
	}
	@Override
	public String toString() {
		return "ProdList [prolId=" + prolId + ", estId=" + estId
				+ ", prolKind=" + prolKind + ", prodId=" + prodId + ", anlAns="
				+ anlAns + "]";
	}
}
