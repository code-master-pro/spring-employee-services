package com.employee.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseGenericDTO<T> {

	private T data;
	private ResponseMessage<T> message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private PaginationDTO pagination;

	public T getData() {
		return data;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseGenericDTO() {
		this.message = new ResponseMessage("200", "SUCCESS");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseGenericDTO(T data) {
		this.message = new ResponseMessage("200", "SUCCESS");
		this.data = data;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseGenericDTO(T data, PaginationDTO pagination) {

		this.data = data;
		this.pagination = pagination;
		this.message = new ResponseMessage("200", "SUCCESS");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseGenericDTO(T data, int page, int size) {

		this.data = data;
		this.pagination = new PaginationDTO(page, size);
		this.message = new ResponseMessage("200", "SUCCESS");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseGenericDTO(T data, int page, int size, String sortOrder, String sortBy) {

		this.data = data;
		this.pagination = new PaginationDTO(page, size, sortOrder, sortBy);
		this.message = new ResponseMessage("200", "SUCCESS");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseGenericDTO(T data, String sortOrder, String sortBy) {

		this.data = data;
		this.pagination = new PaginationDTO(sortOrder, sortBy);
		this.message = new ResponseMessage("200", "SUCCESS");
	}

	public ResponseGenericDTO(ResponseMessage<T> message) {
		this.message = message;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseMessage<T> getMessage() {
		return message;
	}

	public void setMessage(ResponseMessage<T> message) {
		this.message = message;
	}

	public PaginationDTO getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDTO pagination) {
		this.pagination = pagination;
	}

}
