package app.service;

import java.util.List;

import app.entity.ActivityContent;

public interface ActivityService {

	ActivityContent getcontent(ActivityContent content);

	

	List<ActivityContent> findcontentlist(ActivityContent content);

}
