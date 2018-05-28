/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.entity.pro;


import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 项目栏目管理Entity
 * @author wanghe
 * @version 2018-05-25
 */
public class TDprojecttype extends DataEntity<TDprojecttype> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 栏目名称
	private Integer sort;		// 排序
	
	public TDprojecttype() {
		super();
	}

	public TDprojecttype(String id){
		super(id);
	}

	@ExcelField(title="栏目名称", align=2, sort=7)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="排序", align=2, sort=8)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}