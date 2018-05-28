package app.entity;

import java.io.Serializable;

public class Page implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pagestart=0;
	private Integer pagesize=10;
	public Page(){}
	public Integer getPagestart() {
		
		return pagestart;
	}
	public void setPagestart(Integer pagestart) {
		this.pagestart = pagestart;
	}
	public Integer getPagesize() {
		
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	
}
