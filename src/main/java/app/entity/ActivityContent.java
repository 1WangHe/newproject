package app.entity;

import java.io.Serializable;
import java.util.Date;

public class ActivityContent extends Page{
	private static final long serialVersionUID = 1L;
	private String id;
	private String pic;		// 图片
	private String title;		// 标题
	private String content;		// 内容
	private Date starttime;		// 开始时间
	private Date endtime;		// 结束时间

	private Integer state;     //状态1 未开始，2，进行中 3已结束

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Integer getState() {
		Date date=new Date();
		if(starttime==null){
			if(endtime==null){
				return 2;
			}
			if(endtime.getTime()<=date.getTime()){
				return 3;
			}
			return 2;
		}
		if(starttime.getTime()>date.getTime()){
			return 1;
		}
		if(endtime==null||endtime.getTime()>date.getTime()){
			return 2;
		}
		return 3;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	

}
