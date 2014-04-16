package com.screaminggreen.beans;

import java.io.Serializable;

public class ProfessorBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7892904874716952545L;
	private String webId;
	private String email;
	private String password;
	
	public ProfessorBean () {}

	public String getWebId() {
		return webId;
	}

	public void setWebId(String webId) {
		this.webId = webId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
