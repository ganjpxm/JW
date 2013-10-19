/**
 * $Id: Email.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.model;

import java.io.File;

public class Email {
	
	public static String HOST;
	public static Integer PORT;
	public static String USER_NAME;
	public static String PASSWORD;

	public String host;
	public Integer port;
	public String userName;
	public String password;
	
	private String subject;
	private String content;
	private File[] attachments; 
	
	private String from;
	private String[] tos;
	private String[] ccs; 
	private String[] bccs;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getTos() {
		return tos;
	}
	public void setTos(String[] tos) {
		this.tos = tos;
	}
	public String[] getCcs() {
		return ccs;
	}
	public void setCcs(String[] ccs) {
		this.ccs = ccs;
	}
	public String[] getBccs() {
		return bccs;
	}
	public void setBccs(String[] bccs) {
		this.bccs = bccs;
	}
	public File[] getAttachments() {
		return attachments;
	}
	public void setAttachments(File[] attachments) {
		this.attachments = attachments;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
