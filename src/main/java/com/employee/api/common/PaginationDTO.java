package com.employee.api.common;
import java.util.Locale;
import java.util.Optional;

import org.springframework.data.domain.Sort;

/**
 * Created by zohaib.kori on 10/22/2018.
 */
public class PaginationDTO {

	private int page;
	private int size;
	private int totalPages;
	private long totalElements;
	private String sortOrder;
	private String sortBy;

	public PaginationDTO() {

	}

	public PaginationDTO(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public PaginationDTO(int page, int size, int totalPages, long totalElements) {
		this.page = page;
		this.size = size;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
	}

	public PaginationDTO(String sortOrder, String sortBy) {
		this.sortOrder = sortOrder;
		this.sortBy = sortBy;
	}

	public PaginationDTO(int page, int size, String sortOrder, String sortBy) {
		this.page = page;
		this.size = size;
		this.sortOrder = sortOrder;
		this.sortBy = sortBy;
	}

	public PaginationDTO(int page, int size, int totalPages, long totalElements, String sortBy, String sortOrder) {
		this.page = page;
		this.size = size;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.sortOrder = sortOrder;
		this.sortBy = sortBy;
	}

	public static enum Direction {

		ASC, DESC;

		/**
		 * Returns whether the direction is ascending.
		 *
		 * @return
		 * @since 1.13
		 */
		public boolean isAscending() {
			return this.equals(ASC);
		}

		/**
		 * Returns whether the direction is descending.
		 *
		 * @return
		 * @since 1.13
		 */
		public boolean isDescending() {
			return this.equals(DESC);
		}

		/**
		 * Returns the {@link Sort.Direction} enum for the given {@link String} value.
		 *
		 * @param value
		 * @return
		 * @throws IllegalArgumentException in case the given value cannot be parsed
		 *                                  into an enum value.
		 */
		public static Direction fromString(String value) {

			try {
				return Direction.valueOf(value.toUpperCase(Locale.US));
			} catch (Exception e) {
				throw new IllegalArgumentException(String.format(
						"Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).",
						value), e);
			}
		}

		/**
		 * Returns the {@link Sort.Direction} enum for the given {@link String} or null
		 * if it cannot be parsed into an enum value.
		 *
		 * @param value
		 * @return
		 */
		public static Optional<Direction> fromOptionalString(String value) {

			try {
				return Optional.of(fromString(value));
			} catch (IllegalArgumentException e) {
				return Optional.empty();
			}
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
}
