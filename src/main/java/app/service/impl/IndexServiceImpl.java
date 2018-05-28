package app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.ActivityContent;
import app.entity.ActivityType;
import app.entity.Index;
import app.entity.IndexImg;
import app.entity.ProjectContent;
import app.entity.ProjectType;
import app.mapper.ActivityMapper;
import app.mapper.IndexMapper;
import app.mapper.ProjectMapper;
import app.service.IndexService;
@Service
public class IndexServiceImpl implements IndexService{
	@Autowired
	private IndexMapper indexMapper;
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Override
	public Index findIndexAll() {
		List<IndexImg>imgs=indexMapper.findindexlist(new IndexImg());
		List<ActivityType>atypes=activityMapper.findtypelist(new ActivityType());
		ActivityContent ac=new ActivityContent();
		String id=null;
		if(atypes!=null){
			id=atypes.get(0).getId();
		}
		ac.setId(id);
		List<ActivityContent>acontents=activityMapper.findcontentlist(ac);
		List<ProjectType>ptypes=projectMapper.findtypelist(new ProjectType());
		ProjectContent pc=new ProjectContent();
		id=null;
		if(ptypes!=null){
			id=ptypes.get(0).getId();
		}
		pc.setId(id);
		List<ProjectContent>pcontents=projectMapper.findcontentlist(pc);
		Index index=new Index();
		index.setIndeximgs(imgs);
		index.setActivitytypes(atypes);
		index.setActivitycontents(acontents);
		index.setProjecttypes(ptypes);
		index.setProjectcontents(pcontents);
		return index;
	}
	@Override
	public Index findactivityAll() {
		Index index=new Index();
		List<ActivityType>atypes=activityMapper.findsorttypelist(new ActivityType());
		System.out.println(atypes);
		String id=null;
		if(atypes!=null){
		
			System.out.println(atypes.get(0).getId());
			id=atypes.get(0).getId();
		}
		ActivityContent ac=new ActivityContent();
		ac.setId(id);
		List<ActivityContent>acontents=activityMapper.findcontentlist(ac);
		index.setActivitytypes(atypes);
		index.setActivitycontents(acontents);
		return index;
	}

}
