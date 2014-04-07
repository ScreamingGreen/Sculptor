package com.screaminggreen.beans;

public class SessionBean {
	
	private ProfessorBean currentProfBean;
	
	public SessionBean() { }

	public ProfessorBean getCurrentProfBean() {
		return currentProfBean;
	}

	public void setCurrentProfBean(ProfessorBean currentProfBean) {
		this.currentProfBean = currentProfBean;
	}
	
	
}
