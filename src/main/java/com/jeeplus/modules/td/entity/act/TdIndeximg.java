/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.entity.act;


import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 推广图片管理Entity
 * @author wanghe
 * @version 2018-05-25
 */
public class TdIndeximg extends DataEntity<TdIndeximg> {
	
	private static final long serialVersionUID = 1L;
	private String img;		// 图片
	private String urltype;		// 路径类型
	private Integer sort;		// 排序
	
	public TdIndeximg() {
		super();
	}

	public TdIndeximg(String id){
		super(id);
	}

	@ExcelField(title="图片", align=2, sort=7)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@ExcelField(title="路径类型", align=2, sort=8)
	public String getUrltype() {
		return urltype;
	}

	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}
	
	@ExcelField(title="排序", align=2, sort=9)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}