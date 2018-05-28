/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.service.pro;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.td.entity.pro.TDprojectcontent;
import com.jeeplus.modules.td.dao.pro.TDprojectcontentDao;

/**
 * 项目内容管理Service
 * @author wanghe
 * @version 2018-05-25
 */
@Service
@Transactional(readOnly = true)
public class TDprojectcontentService extends CrudService<TDprojectcontentDao, TDprojectcontent> {

	public TDprojectcontent get(String id) {
		return super.get(id);
	}
	
	public List<TDprojectcontent> findList(TDprojectcontent tDprojectcontent) {
		return super.findList(tDprojectcontent);
	}
	
	public Page<TDprojectcontent> findPage(Page<TDprojectcontent> page, TDprojectcontent tDprojectcontent) {
		return super.findPage(page, tDprojectcontent);
	}
	
	@Transactional(readOnly = false)
	public void save(TDprojectcontent tDprojectcontent) {
		super.save(tDprojectcontent);
	}
	
	@Transactional(readOnly = false)
	public void delete(TDprojectcontent tDprojectcontent) {
		super.delete(tDprojectcontent);
	}
	
	
	
	
}