package app.mapper;

import java.util.List;

import app.entity.ProjectContent;
import app.entity.ProjectType;

public interface ProjectMapper {

	List<ProjectType> findtypelist(ProjectType type);
	
	List<ProjectContent> findcontentlist(ProjectContent content);
	
	ProjectType gettype(ProjectType type);
	
	ProjectContent getcontent(ProjectContent content);
}
