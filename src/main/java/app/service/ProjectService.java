package app.service;

import java.util.List;

import app.entity.ProjectContent;

public interface ProjectService {

	ProjectContent getcontent(ProjectContent content);

	List<ProjectContent> findcontentlist(ProjectContent content);


}
