/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.web.pro;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.td.entity.pro.TDprojecttype;
import com.jeeplus.modules.td.service.pro.TDprojecttypeService;

/**
 * 项目栏目管理Controller
 * @author wanghe
 * @version 2018-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/td/pro/tDprojecttype")
public class TDprojecttypeController extends BaseController {

	@Autowired
	private TDprojecttypeService tDprojecttypeService;
	
	@ModelAttribute
	public TDprojecttype get(@RequestParam(required=false) String id) {
		TDprojecttype entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tDprojecttypeService.get(id);
		}
		if (entity == null){
			entity = new TDprojecttype();
		}
		return entity;
	}
	
	/**
	 * 保存项目栏目成功列表页面
	 */
	
	@RequestMapping(value = {"list", ""})
	public String list(TDprojecttype tDprojecttype, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TDprojecttype> page = tDprojecttypeService.findPage(new Page<TDprojecttype>(request, response), tDprojecttype); 
		model.addAttribute("page", page);
		return "modules/td/pro/tDprojecttypeList";
	}

	/**
	 * 查看，增加，编辑保存项目栏目成功表单页面
	 */
	@RequestMapping(value = "form")
	public String form(TDprojecttype tDprojecttype, Model model) {
		model.addAttribute("tDprojecttype", tDprojecttype);
		return "modules/td/pro/tDprojecttypeForm";
	}

	/**
	 * 保存保存项目栏目成功
	 */
	@RequestMapping(value = "save")
	public String save(TDprojecttype tDprojecttype, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, tDprojecttype)){
			return form(tDprojecttype, model);
		}
		if(!tDprojecttype.getIsNewRecord()){//编辑表单保存
			TDprojecttype t = tDprojecttypeService.get(tDprojecttype.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(tDprojecttype, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			tDprojecttypeService.save(t);//保存
		}else{//新增表单保存
			tDprojecttypeService.save(tDprojecttype);//保存
		}
		addMessage(redirectAttributes, "保存保存项目栏目成功成功");
		return "redirect:"+Global.getAdminPath()+"/td/pro/tDprojecttype/?repage";
	}
	
	/**
	 * 删除保存项目栏目成功
	 */
	@RequestMapping(value = "delete")
	public String delete(TDprojecttype tDprojecttype, RedirectAttributes redirectAttributes) {
		tDprojecttypeService.delete(tDprojecttype);
		addMessage(redirectAttributes, "删除保存项目栏目成功成功");
		return "redirect:"+Global.getAdminPath()+"/td/pro/tDprojecttype/?repage";
	}
	
	/**
	 * 批量删除保存项目栏目成功
	 */
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			tDprojecttypeService.delete(tDprojecttypeService.get(id));
		}
		addMessage(redirectAttributes, "删除保存项目栏目成功成功");
		return "redirect:"+Global.getAdminPath()+"/td/pro/tDprojecttype/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TDprojecttype tDprojecttype, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "保存项目栏目成功"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TDprojecttype> page = tDprojecttypeService.findPage(new Page<TDprojecttype>(request, response, -1), tDprojecttype);
    		new ExportExcel("保存项目栏目成功", TDprojecttype.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出保存项目栏目成功记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/td/pro/tDprojecttype/?repage";
    }

	/**
	 * 导入Excel数据

	 */
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TDprojecttype> list = ei.getDataList(TDprojecttype.class);
			for (TDprojecttype tDprojecttype : list){
				try{
					tDprojecttypeService.save(tDprojecttype);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条保存项目栏目成功记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条保存项目栏目成功记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入保存项目栏目成功失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/td/pro/tDprojecttype/?repage";
    }
	
	/**
	 * 下载导入保存项目栏目成功数据模板
	 */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "保存项目栏目成功数据导入模板.xlsx";
    		List<TDprojecttype> list = Lists.newArrayList(); 
    		new ExportExcel("保存项目栏目成功数据", TDprojecttype.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/td/pro/tDprojecttype/?repage";
    }
	
	
	

}