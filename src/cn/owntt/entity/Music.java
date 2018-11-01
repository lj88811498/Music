package cn.owntt.entity;

import java.util.Date;

/**
 * 音乐
 */
public class Music {

	private int id;

	private String name;
	
	private String author;
	
	private String mSize;
	
	private String url;
	
	private Date createTime;

	private double mTop;
	
	/**
	 * @return the mTop
	 */
	public double getmTop() {
		return mTop;
	}

	/**
	 * @param mTop the mTop to set
	 */
	public void setmTop(double mTop) {
		this.mTop = mTop;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getmSize() {
		return mSize;
	}

	public void setmSize(String mSize) {
		this.mSize = mSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Music [author=" + author + ", createTime=" + createTime
				+ ", id=" + id + ", mSize=" + mSize + ", name=" + name
				+ ", url=" + url + "]";
	}

	
}
