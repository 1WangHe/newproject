/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.entity.pro;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 项目内容管理Entity
 * @author wanghe
 * @version 2018-05-25
 */
public class TDprojectcontent extends DataEntity<TDprojectcontent> {
	
	private static final long serialVersionUID = 1L;
	private String pic;		// 图片
	private String title;		// 标题
	private String content;		// 内容
	private Date starttime;		// 开始时间
	private Date endtime;		// 结束时间
	private Integer sort;		// 排序
	private String address;		// 地点
	private String typeid;		// 项目类型
	private String typename;
	private Integer state;
	public TDprojectcontent() {
		super();
	}

	public TDprojectcontent(String id){
		super(id);
	}

	@ExcelField(title="图片", align=2, sort=7)
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	@ExcelField(title="标题", align=2, sort=8)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ExcelField(title="内容", align=2, sort=9)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="开始时间", align=2, sort=10)
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	
	@ExcelField(title="结束时间", align=2, sort=11)
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	@ExcelField(title="排序", align=2, sort=12)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@ExcelField(title="地点", align=2, sort=13)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@ExcelField(title="项目类型", align=2, sort=14)
	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Integer getState() {
		Date date=new Date();
		if(starttime==null){
			if(endtime==null){
				return 2;
			}
			if(endtime.getTime()<=date.getTime()){
				return 3;
			}
			return 2;
		}
		if(starttime.getTime()>date.getTime()){
			return 1;
		}
		if(endtime==null||endtime.getTime()>date.getTime()){
			return 2;
		}
		return 3;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}