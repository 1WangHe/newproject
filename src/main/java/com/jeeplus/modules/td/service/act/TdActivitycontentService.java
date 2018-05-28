/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.service.act;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.td.entity.act.TdActivitycontent;
import com.jeeplus.modules.td.dao.act.TdActivitycontentDao;

/**
 * 活动内容管理Service
 * @author wanghe
 * @version 2018-05-25
 */
@Service
@Transactional(readOnly = true)
public class TdActivitycontentService extends CrudService<TdActivitycontentDao, TdActivitycontent> {

	public TdActivitycontent get(String id) {
		return super.get(id);
	}
	
	public List<TdActivitycontent> findList(TdActivitycontent tdActivitycontent) {
		return super.findList(tdActivitycontent);
	}
	
	public Page<TdActivitycontent> findPage(Page<TdActivitycontent> page, TdActivitycontent tdActivitycontent) {
		return super.findPage(page, tdActivitycontent);
	}
	
	@Transactional(readOnly = false)
	public void save(TdActivitycontent tdActivitycontent) {
		super.save(tdActivitycontent);
	}
	
	@Transactional(readOnly = false)
	public void delete(TdActivitycontent tdActivitycontent) {
		super.delete(tdActivitycontent);
	}
	
	
	
	
}