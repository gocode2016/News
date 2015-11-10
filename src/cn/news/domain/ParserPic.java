package cn.news.domain;

public class ParserPic {
	private String picIntroduction;
	private String picUrl;
	public ParserPic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ParserPic(String picIntroduction, String picUrl) {
		super();
		this.picIntroduction = picIntroduction;
		this.picUrl = picUrl;
	}
	public String getPicIntroduction() {
		return picIntroduction;
	}
	public void setPicIntroduction(String picIntroduction) {
		this.picIntroduction = picIntroduction;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@Override
	public String toString() {
		return "ParserPic [picIntroduction=" + picIntroduction + ", picUrl=" + picUrl + "]";
	}
	
}
