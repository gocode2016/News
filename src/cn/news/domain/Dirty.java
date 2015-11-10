package cn.news.domain;

public class Dirty {
	private Integer dirtyId;
	private String content;
	private String updateTo;
	public Dirty() {
		super();
	}
	public Dirty(Integer dirtyId, String content, String updateTo) {
		super();
		this.dirtyId = dirtyId;
		this.content = content;
		this.updateTo = updateTo;
	}
	public Integer getDirtyId() {
		return dirtyId;
	}
	public void setDirtyId(Integer dirtyId) {
		this.dirtyId = dirtyId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpdateTo() {
		return updateTo;
	}
	public void setUpdateTo(String updateTo) {
		this.updateTo = updateTo;
	}
	
	
}
