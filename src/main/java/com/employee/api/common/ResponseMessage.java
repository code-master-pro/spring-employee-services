package com.employee.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseMessage<T> {

	private String status;
	private T description;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object errorDetail;

	public ResponseMessage() {

	}

	public ResponseMessage(String status, T description) {
		this.status = status;
		this.description = description;
	}

	public ResponseMessage(String status, T description, Object errorDetail) {
		this.status = status;
		this.description = description;
		this.errorDetail = errorDetail;
	}

	public ResponseMessage(T description) {
		this.description = description;
	}

	public ResponseMessage(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getDescription() {
		return description;
	}

	public void setDescription(T description) {
		this.description = description;
	}

	public Object getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(Object errorDetail) {
		this.errorDetail = errorDetail;
	}

}
