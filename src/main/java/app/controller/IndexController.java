package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.entity.ActivityContent;
import app.entity.Index;
import app.entity.ProjectContent;
import app.service.ActivityService;
import app.service.IndexService;
import app.service.ProjectService;
import app.utils.ResultUtile;

@Controller
public class IndexController {
	@Autowired
	private IndexService indexService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ProjectService projectService;
	@ResponseBody
	@RequestMapping("indexall")
	public ResultUtile indexall(){
		ResultUtile ru=new ResultUtile();
		try{
			Index index=indexService.findIndexAll();
			if(index==null){
				ru.setSuccess(false);
				ru.setErrorcode(0);
				return ru;
			}
			ru.setSuccess(true);
			ru.setErrorcode(1);
			ru.setBody(index);
			return ru;
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			ru.setSuccess(false);
			ru.setErrorcode(501);
			return ru;
		}
	}
	
	
	@ResponseBody
	@RequestMapping("activityall")
	public ResultUtile activityall(){
		ResultUtile ru=new ResultUtile();
		try{
			Index index=indexService.findactivityAll();
			if(index==null){
				ru.setSuccess(false);
				ru.setErrorcode(0);
				return ru;
			}
			ru.setSuccess(true);
			ru.setErrorcode(1);
			ru.setBody(index);
			return ru;
				
		}catch(Exception e){
			System.out.println(e.getMessage());
			ru.setSuccess(false);
			ru.setErrorcode(501);
			return ru;
		}
	}
	
	@ResponseBody
	@RequestMapping("activityContetById")
	public ResultUtile activityContetById(ActivityContent content){
		ResultUtile ru=new ResultUtile();
		try{
			ActivityContent index=activityService.getcontent(content);
			if(index==null){
				ru.setSuccess(false);
				ru.setErrorcode(0);
				return ru;
			}
			ru.setSuccess(true);
			ru.setErrorcode(1);
			ru.setBody(index);
			return ru;
				
		}catch(Exception e){
			System.out.println(e.getMessage());
			ru.setSuccess(false);
			ru.setErrorcode(501);
			return ru;
		}
	}
	
	@ResponseBody
	@RequestMapping("activityContetByTypeId")
	public ResultUtile activityContetByTypeId(ActivityContent content){
		ResultUtile ru=new ResultUtile();
		try{
			List<ActivityContent> index=activityService.findcontentlist(content);
			if(index==null){
				ru.setSuccess(false);
				ru.setErrorcode(0);
				return ru;
			}
			ru.setSuccess(true);
			ru.setErrorcode(1);
			ru.setBody(index);
			return ru;
				
		}catch(Exception e){
			System.out.println(e.getMessage());
			ru.setSuccess(false);
			ru.setErrorcode(501);
			return ru;
		}
	}
	
	@ResponseBody
	@RequestMapping("projectContetById")
	public ResultUtile projectContetById(ProjectContent content){
		ResultUtile ru=new ResultUtile();
		try{
			ProjectContent index=projectService.getcontent(content);
			if(index==null){
				ru.setSuccess(false);
				ru.setErrorcode(0);
				return ru;
			}
			ru.setSuccess(true);
			ru.setErrorcode(1);
			ru.setBody(index);
			return ru;
				
		}catch(Exception e){
			System.out.println(e.getMessage());
			ru.setSuccess(false);
			ru.setErrorcode(501);
			return ru;
		}
	}
	
	@ResponseBody
	@RequestMapping("projectContetByTypeId")
	public ResultUtile projectContetByTypeId(ProjectContent content){
		ResultUtile ru=new ResultUtile();
		try{
			List<ProjectContent> index=projectService.findcontentlist(content);
			if(index==null){
				ru.setSuccess(false);
				ru.setErrorcode(0);
				return ru;
			}
			ru.setSuccess(true);
			ru.setErrorcode(1);
			ru.setBody(index);
			return ru;
				
		}catch(Exception e){
			System.out.println(e.getMessage());
			ru.setSuccess(false);
			ru.setErrorcode(501);
			return ru;
		}
	}

	
	
	
}
