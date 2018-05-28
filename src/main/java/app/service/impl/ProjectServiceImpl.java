package app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.ProjectContent;
import app.mapper.ProjectMapper;
import app.service.ProjectService;
@Service
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	private ProjectMapper projectMapper;
	@Override
	public ProjectContent getcontent(ProjectContent content) {
		
		return projectMapper.getcontent(content);
	}
	@Override
	public List<ProjectContent> findcontentlist(ProjectContent content) {
		
		return projectMapper.findcontentlist(content);
	}
	

}
