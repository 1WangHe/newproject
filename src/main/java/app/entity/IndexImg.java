package app.entity;

import java.io.Serializable;

public class IndexImg extends Page{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String img;
	private Integer modeltype;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getModeltype() {
		return modeltype;
	}
	public void setModeltype(Integer modeltype) {
		this.modeltype = modeltype;
	}
	
}
