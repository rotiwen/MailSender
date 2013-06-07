package com.ipensee.entity;

public class News {
	
	private String KeyWords;				// 关键词
	private String NewTypeName;				// 信息类型
	private String NewObject;				// 信息头
	private String url;						// 信息头链接
	private String News;					// 信息内容
	private String fileName;				// 附件名称
	private String FileOrder;				// 附件链接
	private String EditDate;				// 发布日期
	private String NewFrom;					// 信息来源
	private String NewAuthor;				// 作者
	private String IsForward;				// 是否属于转发
	
	public String getKeyWords() {
		return KeyWords;
	}
	public void setKeyWords(String keyWords) {
		KeyWords = keyWords;
	}
	public String getNewTypeName() {
		return NewTypeName;
	}
	public void setNewTypeName(String newTypeName) {
		NewTypeName = newTypeName;
	}
	public String getNewObject() {
		return NewObject;
	}
	public void setNewObject(String newObject) {
		NewObject = newObject;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNews() {
		return News;
	}
	public void setNews(String news) {
		News = news;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOrder() {
		return FileOrder;
	}
	public void setFileOrder(String fileOrder) {
		FileOrder = fileOrder;
	}
	public String getEditDate() {
		return EditDate;
	}
	public void setEditDate(String editDate) {
		EditDate = editDate;
	}
	public String getNewFrom() {
		return NewFrom;
	}
	public void setNewFrom(String newFrom) {
		NewFrom = newFrom;
	}
	public String getNewAuthor() {
		return NewAuthor;
	}
	public void setNewAuthor(String newAuthor) {
		NewAuthor = newAuthor;
	}
	public String getIsForward() {
		return IsForward;
	}
	public void setIsForward(String isForward) {
		IsForward = isForward;
	}

}
