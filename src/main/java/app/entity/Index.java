package app.entity;

import java.io.Serializable;
import java.util.List;

public class Index implements Serializable{
	private List<IndexImg> indeximgs;
	private List<ActivityType> activitytypes;
	private List<ActivityContent> activitycontents;
	private List<ProjectType> projecttypes;
	private List<ProjectContent> projectcontents;
	public List<IndexImg> getIndeximgs() {
		return indeximgs;
	}
	public void setIndeximgs(List<IndexImg> indeximgs) {
		this.indeximgs = indeximgs;
	}
	public List<ActivityType> getActivitytypes() {
		return activitytypes;
	}
	public void setActivitytypes(List<ActivityType> activitytypes) {
		this.activitytypes = activitytypes;
	}
	public List<ActivityContent> getActivitycontents() {
		return activitycontents;
	}
	public void setActivitycontents(List<ActivityContent> activitycontents) {
		this.activitycontents = activitycontents;
	}
	public List<ProjectType> getProjecttypes() {
		return projecttypes;
	}
	public void setProjecttypes(List<ProjectType> projecttypes) {
		this.projecttypes = projecttypes;
	}
	public List<ProjectContent> getProjectcontents() {
		return projectcontents;
	}
	public void setProjectcontents(List<ProjectContent> projectcontents) {
		this.projectcontents = projectcontents;
	}
	
}
