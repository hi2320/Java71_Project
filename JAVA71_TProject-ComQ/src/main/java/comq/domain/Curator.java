package comq.domain;

import java.util.List;

public class Curator {

	private int curId;
	private String purpose;
	
	private List<Question> questionList;
	
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
	
	
	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	@Override
  public String toString() {
	  return "\nCurator [curId=" + curId + ", purpose=" + purpose
	      + ", questionList=" + questionList + "]\n";
  }

	
	
	
}
