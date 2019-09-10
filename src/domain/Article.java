package domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Article {
	private String id;
	private String title;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String author;
	private String type;
	private String content;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", createTime=" + createTime + ", author=" + author
				+ ", type=" + type + ", content=" + content + "]";
	}
}
