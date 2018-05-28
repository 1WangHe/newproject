/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.entity.act;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 活动内容管理Entity
 * @author wanghe
 * @version 2018-05-25
 */
public class TdActivitycontent extends DataEntity<TdActivitycontent> {
	
	private static final long serialVersionUID = 1L;
	private String pic;		// 封面图片
	private String title;		// 标题
	private String content;		// 内容
	private Integer number;		// 报名数量
	private String typeid;		// 类别id
	private Integer sort;		// 排序
	private Date starttime;		// 开始时间
	private Date endtime;		// 结束时间
	private String typename;
	private Integer state;
	public TdActivitycontent() {
		super();
	}

	public TdActivitycontent(String id){
		super(id);
	}

	@ExcelField(title="封面图片", align=2, sort=7)
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
	
	@ExcelField(title="报名数量", align=2, sort=10)
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@ExcelField(title="类别id", align=2, sort=11)
	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	
	@ExcelField(title="排序", align=2, sort=12)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="开始时间", align=2, sort=13)
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="结束时间", align=2, sort=14)
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
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