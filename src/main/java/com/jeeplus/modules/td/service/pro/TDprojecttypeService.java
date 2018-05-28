/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.service.pro;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.td.entity.pro.TDprojecttype;
import com.jeeplus.modules.td.dao.pro.TDprojecttypeDao;

/**
 * 项目栏目管理Service
 * @author wanghe
 * @version 2018-05-25
 */
@Service
@Transactional(readOnly = true)
public class TDprojecttypeService extends CrudService<TDprojecttypeDao, TDprojecttype> {

	public TDprojecttype get(String id) {
		return super.get(id);
	}
	
	public List<TDprojecttype> findList(TDprojecttype tDprojecttype) {
		return super.findList(tDprojecttype);
	}
	
	public Page<TDprojecttype> findPage(Page<TDprojecttype> page, TDprojecttype tDprojecttype) {
		return super.findPage(page, tDprojecttype);
	}
	
	@Transactional(readOnly = false)
	public void save(TDprojecttype tDprojecttype) {
		super.save(tDprojecttype);
	}
	
	@Transactional(readOnly = false)
	public void delete(TDprojecttype tDprojecttype) {
		super.delete(tDprojecttype);
	}
	
	
	
	
}