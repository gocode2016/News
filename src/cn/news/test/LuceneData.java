package cn.news.test;

public class LuceneData {
	private String id;
	private String title;
	private String introduction;
	private String content;

	public LuceneData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LuceneData(String id, String title, String introduction,
			String content) {
		super();
		this.id = id;
		this.title = title;
		this.introduction = introduction;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		return "" + id + "\t" + title + "\t" + introduction + "\t" + content
				+ "";
	}
}
