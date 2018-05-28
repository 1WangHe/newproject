package app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.ActivityContent;
import app.mapper.ActivityMapper;
import app.service.ActivityService;
@Service
public class ActivityServiceImpl implements ActivityService{
	@Autowired
	private ActivityMapper activityMapper;
	
	@Override
	public ActivityContent getcontent(ActivityContent content) {	
		return activityMapper.getcontent(content);
	}
	@Override
	public List<ActivityContent> findcontentlist(ActivityContent content) {
		
		return activityMapper.findcontentlist(content);
	}
	

}
