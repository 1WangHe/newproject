/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.web.act;

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
import com.jeeplus.modules.td.entity.act.TdIndeximg;
import com.jeeplus.modules.td.service.act.TdIndeximgService;

/**
 * 推广图片管理Controller
 * @author wanghe
 * @version 2018-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/td/act/tdIndeximg")
public class TdIndeximgController extends BaseController {

	@Autowired
	private TdIndeximgService tdIndeximgService;
	
	@ModelAttribute
	public TdIndeximg get(@RequestParam(required=false) String id) {
		TdIndeximg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tdIndeximgService.get(id);
		}
		if (entity == null){
			entity = new TdIndeximg();
		}
		return entity;
	}
	
	/**
	 * 保存推广图片成功列表页面
	 */
	
	@RequestMapping(value = {"list", ""})
	public String list(TdIndeximg tdIndeximg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TdIndeximg> page = tdIndeximgService.findPage(new Page<TdIndeximg>(request, response), tdIndeximg); 
		model.addAttribute("page", page);
		return "modules/td/act/tdIndeximgList";
	}

	/**
	 * 查看，增加，编辑保存推广图片成功表单页面
	 */
	
	@RequestMapping(value = "form")
	public String form(TdIndeximg tdIndeximg, Model model) {
		model.addAttribute("tdIndeximg", tdIndeximg);
		return "modules/td/act/tdIndeximgForm";
	}

	/**
	 * 保存保存推广图片成功
	 */
	
	@RequestMapping(value = "save")
	public String save(TdIndeximg tdIndeximg, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, tdIndeximg)){
			return form(tdIndeximg, model);
		}
		if(!tdIndeximg.getIsNewRecord()){//编辑表单保存
			TdIndeximg t = tdIndeximgService.get(tdIndeximg.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(tdIndeximg, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			tdIndeximgService.save(t);//保存
		}else{//新增表单保存
			tdIndeximgService.save(tdIndeximg);//保存
		}
		addMessage(redirectAttributes, "保存保存推广图片成功成功");
		return "redirect:"+Global.getAdminPath()+"/td/act/tdIndeximg/?repage";
	}
	
	/**
	 * 删除保存推广图片成功
	 */
	
	@RequestMapping(value = "delete")
	public String delete(TdIndeximg tdIndeximg, RedirectAttributes redirectAttributes) {
		tdIndeximgService.delete(tdIndeximg);
		addMessage(redirectAttributes, "删除保存推广图片成功成功");
		return "redirect:"+Global.getAdminPath()+"/td/act/tdIndeximg/?repage";
	}
	
	/**
	 * 批量删除保存推广图片成功
	 */
	
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			tdIndeximgService.delete(tdIndeximgService.get(id));
		}
		addMessage(redirectAttributes, "删除保存推广图片成功成功");
		return "redirect:"+Global.getAdminPath()+"/td/act/tdIndeximg/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TdIndeximg tdIndeximg, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "保存推广图片成功"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TdIndeximg> page = tdIndeximgService.findPage(new Page<TdIndeximg>(request, response, -1), tdIndeximg);
    		new ExportExcel("保存推广图片成功", TdIndeximg.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出保存推广图片成功记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/td/act/tdIndeximg/?repage";
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
			List<TdIndeximg> list = ei.getDataList(TdIndeximg.class);
			for (TdIndeximg tdIndeximg : list){
				try{
					tdIndeximgService.save(tdIndeximg);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条保存推广图片成功记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条保存推广图片成功记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入保存推广图片成功失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/td/act/tdIndeximg/?repage";
    }
	
	/**
	 * 下载导入保存推广图片成功数据模板
	 */
	
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "保存推广图片成功数据导入模板.xlsx";
    		List<TdIndeximg> list = Lists.newArrayList(); 
    		new ExportExcel("保存推广图片成功数据", TdIndeximg.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/td/act/tdIndeximg/?repage";
    }
	
	
	

}