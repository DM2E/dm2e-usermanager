package de.mpiwg.dm2e.userManager.utils;

import java.io.Serializable;

public class SelectableObject<N> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean selected;
	private N obj;
	
	public SelectableObject(N obj){
		this.obj = obj;
		this.selected = false;
	}
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public N getObj() {
		return obj;
	}
	public void setObj(N obj) {
		this.obj = obj;
	}
}