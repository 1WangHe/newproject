package app.mapper;

import java.util.List;

import app.entity.ActivityContent;
import app.entity.ActivityType;


public interface ActivityMapper {

	List<ActivityType> findtypelist(ActivityType type);

	List<ActivityContent> findcontentlist(ActivityContent content);

	List<ActivityType> findsorttypelist(ActivityType activityType);
	
	ActivityType gettype(ActivityType type);
	
	ActivityContent getcontent(ActivityContent content);

}
