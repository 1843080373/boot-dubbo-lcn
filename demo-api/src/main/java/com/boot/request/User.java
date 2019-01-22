package com.boot.request;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User() {
		super();
	}

	public User(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}

}
