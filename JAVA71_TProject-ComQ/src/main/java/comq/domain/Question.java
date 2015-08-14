package comq.domain;

import java.util.List;

public class Question {
	
	private int queId;
	private int curId;
	private String qSente;
	private String qProd;
	private String qType;
	
	private List<Answer> answerList;
	
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

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}


	@Override
  public String toString() {
	  return "Question [queId=" + queId + ", curId=" + curId + ", qSente="
	      + qSente + ", qProd=" + qProd + ", qType=" + qType + ", answerList="
	      + answerList + "]";
  }

	
	
}
