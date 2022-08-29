
package com.rank.rgil.event;


import java.io.Serializable;

import com.rank.rgil.exception.ErrorResponse;

/**
 * 
 * @author Sukanta
 * @since 30-Nov-2018
 * @Purpose This object will return the dynamic response to the client application
 */
public class ResponseEvent<T> implements Serializable{

	private static final long serialVersionUID = 7528116395502899296L;

	private T payload;
	
	private ErrorResponse error; 
	
	private Boolean status;
	
	public ResponseEvent(T payload) {
		this.payload = payload;
		this.setStatus(true);
	}
	
	public ResponseEvent(ErrorResponse error) {
		this.error = error;
		this.setStatus(false);
	}
	
	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public ErrorResponse getError() {
		return error;
	}

	public void setError(ErrorResponse error) {
		this.error = error;
	}
	


	public static <P> ResponseEvent<P> response(P payload) {
		return new ResponseEvent<P>(payload);
	}
	
	public static <P> ResponseEvent<P> error(ErrorResponse error) {
		return new ResponseEvent<P>(error);
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
