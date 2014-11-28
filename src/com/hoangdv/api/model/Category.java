package com.hoangdv.api.model;

public class Category {
	private int PK_iCategoryId;
	private String sName;
	private int iCount;
	private Article article;
	public int getPK_iCategoryId() {
		return PK_iCategoryId;
	}
	public void setPK_iCategoryId(int pK_iCategoryId) {
		PK_iCategoryId = pK_iCategoryId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public int getiCount() {
		return iCount;
	}
	public void setiCount(int iCount) {
		this.iCount = iCount;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	
}
