package com.screaminggreen.beans;

import java.io.Serializable;

public class SessionBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4330131385161045244L;
	private ProfessorBean profBean;
	
	public SessionBean() { }

	public ProfessorBean getProfBean() {
		return profBean;
	}

	public void setProfBean(ProfessorBean profBean) {
		this.profBean = profBean;
	}
	
	
}
