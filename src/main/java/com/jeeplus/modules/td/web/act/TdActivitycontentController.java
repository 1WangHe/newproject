/**
 * Copyright &copy; 2015-2020 <a href="http://www.hailtour.com/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.td.web.act;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.jeeplus.modules.td.entity.act.TdActivitycontent;
import com.jeeplus.modules.td.entity.act.TdActivitytype;
import com.jeeplus.modules.td.entity.pro.TDprojecttype;
import com.jeeplus.modules.td.service.act.TdActivitycontentService;
import com.jeeplus.modules.td.service.act.TdActivitytypeService;

/**
 * 活动内容管理Controller
 * @author wanghe
 * @version 2018-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/td/act/tdActivitycontent")
public class TdActivitycontentController extends BaseController {

	@Autowired
	private TdActivitycontentService tdActivitycontentService;
	
	@Autowired
	private TdActivitytypeService tdActivitytypeService;
	@ModelAttribute
	public TdActivitycontent get(@RequestParam(required=false) String id) {
		TdActivitycontent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tdActivitycontentService.get(id);
		}
		if (entity == null){
			entity = new TdActivitycontent();
		}
		return entity;
	}
	
	/**
	 * 保存活动内容成功列表页面
	 */
	
	@RequestMapping(value = {"list", ""})
	public String list(TdActivitycontent tdActivitycontent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TdActivitycontent> page = tdActivitycontentService.findPage(new Page<TdActivitycontent>(request, response), tdActivitycontent); 
		model.addAttribute("page", page);
		TdActivitytype type=new TdActivitytype();
		List<TdActivitytype>typelist=tdActivitytypeService.findList(type);
		model.addAttribute("typelist", typelist);
		return "modules/td/act/tdActivitycontentList";
	}

	/**
	 * 查看，增加，编辑保存活动内容成功表单页面
	 */
	
	@RequestMapping(value = "form")
	public String form(TdActivitycontent tdActivitycontent, Model model) {
		TdActivitytype type=new TdActivitytype();
		List<TdActivitytype>typelist=tdActivitytypeService.findList(type);
		model.addAttribute("typelist", typelist);
		model.addAttribute("tdActivitycontent", tdActivitycontent);
		return "modules/td/act/tdActivitycontentForm";
	}

	/**
	 * 保存保存活动内容成功
	 */
	
	@RequestMapping(value = "save")
	public String save(TdActivitycontent tdActivitycontent, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, tdActivitycontent)){
			return form(tdActivitycontent, model);
		}
		if(!tdActivitycontent.getIsNewRecord()){//编辑表单保存
			TdActivitycontent t = tdActivitycontentService.get(tdActivitycontent.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(tdActivitycontent, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			tdActivitycontentService.save(t);//保存
		}else{//新增表单保存
			tdActivitycontentService.save(tdActivitycontent);//保存
		}
		addMessage(redirectAttributes, "保存保存活动内容成功成功");
		return "redirect:"+Global.getAdminPath()+"/td/act/tdActivitycontent/?repage";
	}
	
	/**
	 * 删除保存活动内容成功
	 */

	@RequestMapping(value = "delete")
	public String delete(TdActivitycontent tdActivitycontent, RedirectAttributes redirectAttributes) {
		tdActivitycontentService.delete(tdActivitycontent);
		addMessage(redirectAttributes, "删除保存活动内容成功成功");
		return "redirect:"+Global.getAdminPath()+"/td/act/tdActivitycontent/?repage";
	}
	
	/**
	 * 批量删除保存活动内容成功
	 */

	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			tdActivitycontentService.delete(tdActivitycontentService.get(id));
		}
		addMessage(redirectAttributes, "删除保存活动内容成功成功");
		return "redirect:"+Global.getAdminPath()+"/td/act/tdActivitycontent/?repage";
	}
	
	/**
	 * 导出excel文件
	 */

    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TdActivitycontent tdActivitycontent, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "保存活动内容成功"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TdActivitycontent> page = tdActivitycontentService.findPage(new Page<TdActivitycontent>(request, response, -1), tdActivitycontent);
    		new ExportExcel("保存活动内容成功", TdActivitycontent.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出保存活动内容成功记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/td/act/tdActivitycontent/?repage";
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
			List<TdActivitycontent> list = ei.getDataList(TdActivitycontent.class);
			for (TdActivitycontent tdActivitycontent : list){
				try{
					tdActivitycontentService.save(tdActivitycontent);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条保存活动内容成功记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条保存活动内容成功记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入保存活动内容成功失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/td/act/tdActivitycontent/?repage";
    }
	
	/**
	 * 下载导入保存活动内容成功数据模板
	 */

    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "保存活动内容成功数据导入模板.xlsx";
    		List<TdActivitycontent> list = Lists.newArrayList(); 
    		new ExportExcel("保存活动内容成功数据", TdActivitycontent.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/td/act/tdActivitycontent/?repage";
    }
	
	
	@ResponseBody
	 @RequestMapping(value="upimage")
	public String upimage(MultipartFile file, HttpServletRequest request){
		
		 File targetFile=null;
	        String msg="";//返回存储路径
	        String fileName=file.getOriginalFilename();//获取文件名加后缀
	        if(fileName!=null&&fileName!=""){   
	            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
	            String path = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
	            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
	            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
	            File f=new File(path);
	            if(!f.exists()){
	            	f.mkdirs();
	            }
	            //先判断文件是否存在
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
 
	            String fileAdd = sdf.format(new Date());
	            File file1 =new File(path+"/"+fileAdd+"/"); 
	            //如果文件夹不存在则创建    
	            if(!file1 .exists()){       
	                file1 .mkdir();  
	            }
	            targetFile = new File(path+"/"+fileAdd+"/"+fileName);
//	          targetFile = new File(path, fileName);
	            try {
	                file.transferTo(targetFile);
//	              msg=returnUrl+fileName;
	                msg=returnUrl+fileAdd+"/"+fileName;
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return msg;
		
	}

}