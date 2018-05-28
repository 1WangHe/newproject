/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.service.act;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.td.entity.act.TdIndeximg;
import com.jeeplus.modules.td.dao.act.TdIndeximgDao;

/**
 * 推广图片管理Service
 * @author wanghe
 * @version 2018-05-25
 */
@Service
@Transactional(readOnly = true)
public class TdIndeximgService extends CrudService<TdIndeximgDao, TdIndeximg> {

	public TdIndeximg get(String id) {
		return super.get(id);
	}
	
	public List<TdIndeximg> findList(TdIndeximg tdIndeximg) {
		return super.findList(tdIndeximg);
	}
	
	public Page<TdIndeximg> findPage(Page<TdIndeximg> page, TdIndeximg tdIndeximg) {
		return super.findPage(page, tdIndeximg);
	}
	
	@Transactional(readOnly = false)
	public void save(TdIndeximg tdIndeximg) {
		super.save(tdIndeximg);
	}
	
	@Transactional(readOnly = false)
	public void delete(TdIndeximg tdIndeximg) {
		super.delete(tdIndeximg);
	}
	
	
	
	
}