/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.entity.act;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 活动栏目管理Entity
 * @author wanghe
 * @version 2018-05-25
 */
public class TdActivitytype extends DataEntity<TdActivitytype> {
	
	private static final long serialVersionUID = 1L;
	private TdActivitytype parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	private Integer sort;		// 排序
	private String isshow;		// isshow
	
	public TdActivitytype() {
		super();
	}

	public TdActivitytype(String id){
		super(id);
	}

	@JsonBackReference
	@ExcelField(title="父级编号", align=2, sort=7)
	public TdActivitytype getParent() {
		return parent;
	}

	public void setParent(TdActivitytype parent) {
		this.parent = parent;
	}
	
	@ExcelField(title="所有父级编号", align=2, sort=8)
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@ExcelField(title="名称", align=2, sort=9)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="排序", align=2, sort=10)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@ExcelField(title="isshow", align=2, sort=11)
	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	
}