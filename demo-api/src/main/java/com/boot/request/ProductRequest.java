package com.boot.request;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private BigDecimal account;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductRequest(String name, BigDecimal account) {
		super();
		this.name = name;
		this.account = account;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public ProductRequest() {
		super();
	}
	
}