package com.qdch.portal.littleproject.entity;

import com.qdch.portal.common.persistence.DataEntity;

public class MarketDynamicModel extends DataEntity<MarketDynamicModel>{
private String title;
private String summary;
private String url;
private String data_source;
private String publish_date;
private String content;
private String string_agg;
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getSummary() {
	return summary;
}
public void setSummary(String summary) {
	this.summary = summary;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getData_source() {
	return data_source;
}
public void setData_source(String data_source) {
	this.data_source = data_source;
}
public String getPublish_date() {
	return publish_date;
}
public void setPublish_date(String publish_date) {
	this.publish_date = publish_date;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getString_agg() {
	return string_agg;
}
public void setString_agg(String string_agg) {
	this.string_agg = string_agg;
}

}
