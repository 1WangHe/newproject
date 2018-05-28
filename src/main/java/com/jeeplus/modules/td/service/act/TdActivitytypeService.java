/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.service.act;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.td.entity.act.TdActivitytype;
import com.jeeplus.modules.td.dao.act.TdActivitytypeDao;

/**
 * 活动栏目管理Service
 * @author wanghe
 * @version 2018-05-25
 */
@Service
@Transactional(readOnly = true)
public class TdActivitytypeService extends CrudService<TdActivitytypeDao, TdActivitytype> {

	public TdActivitytype get(String id) {
		return super.get(id);
	}
	
	public List<TdActivitytype> findList(TdActivitytype tdActivitytype) {
		return super.findList(tdActivitytype);
	}
	
	public Page<TdActivitytype> findPage(Page<TdActivitytype> page, TdActivitytype tdActivitytype) {
		return super.findPage(page, tdActivitytype);
	}
	
	@Transactional(readOnly = false)
	public void save(TdActivitytype tdActivitytype) {
		super.save(tdActivitytype);
	}
	
	@Transactional(readOnly = false)
	public void delete(TdActivitytype tdActivitytype) {
		super.delete(tdActivitytype);
	}
	
	
	
	
}