package com.rank.rgil.response;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * @author Sukanta
 * @since 06-Dec-2018
 * @Purpose the following Class is used for storing Feedback details
 */
@JsonInclude(Include.NON_NULL)
public class FeedbackQuestionsResponse implements Serializable {

    /**
	 * serialVersionUID
	 */
	@JsonIgnore
	private static final long serialVersionUID = 6843052166991523713L;
	
	String questionOne;
    String questionTwo;
    String questionThree;
    private boolean resStatus;
    private String resMessage;

    public String getQuestionOne() {
        return questionOne;
    }

    public boolean isResStatus() {
        return resStatus;
    }

    public void setResStatus(boolean resStatus) {
        this.resStatus = resStatus;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public void setQuestionOne(String questionOne) {
        this.questionOne = questionOne;
    }

    public String getQuestionTwo() {
        return questionTwo;
    }

    public void setQuestionTwo(String questionTwo) {
        this.questionTwo = questionTwo;
    }

    public String getQuestionThree() {
        return questionThree;
    }

    public void setQuestionThree(String questionThree) {
        this.questionThree = questionThree;
    }

}
