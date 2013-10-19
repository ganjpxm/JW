/**
 * $Id: UploadedFile.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */

package org.ganjp.jpw.core.model;

import java.io.Serializable;

public class UploadedFile implements Serializable {

	private static final long serialVersionUID = -38331060124340967L;
	private String name;
	private Integer size;
	private String url;
	private String thumbnail_url;
	private String delete_url;
	private String delete_type;
	private String realPath;
	
	public UploadedFile() {
		super();
	}
	
	public UploadedFile(String name, Integer size, String url) {
		super();
		this.name = name;
		this.size = size;
		this.url = url;
	}
	
	public UploadedFile(String name, Integer size, String url, String realPath) {
		super();
		this.name = name;
		this.size = size;
		this.url = url;
		this.realPath = realPath;
	}
	
	public UploadedFile(String name, Integer size, String url,
			String thumbnail_url, String delete_url, String delete_type) {
		super();
		this.name = name;
		this.size = size;
		this.url = url;
		this.thumbnail_url = thumbnail_url;
		this.delete_url = delete_url;
		this.delete_type = delete_type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
	public String getDelete_url() {
		return delete_url;
	}
	public void setDelete_url(String delete_url) {
		this.delete_url = delete_url;
	}
	public String getDelete_type() {
		return delete_type;
	}
	public void setDelete_type(String delete_type) {
		this.delete_type = delete_type;
	}
	
	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	@Override
	public String toString() {
		return "UploadedFile [name=" + name + ", size=" + size + ", url=" + url
				+ ", thumbnail_url=" + thumbnail_url + ", delete_url="
				+ delete_url + ", delete_type=" + delete_type + "]";
	}
	
}
