package com.xiaxinyu.email.core;

/**
 * Created by Summer.Xiasz on 2016/06/02.
 */
public class EmailBean {
	private String fromAccount;
	private String toAccout;
	private String ccAccout;
	private String bccAccount;
	private String title;
	private String content;
	private String username;
	private String password;
	private String host;
	private String protocol;
	private Boolean authFlag;
	private String htmlTemplatePath;
	private String attachments;

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccout() {
		return toAccout;
	}

	public void setToAccout(String toAccout) {
		this.toAccout = toAccout;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Boolean getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag(Boolean authFlag) {
		this.authFlag = authFlag;
	}

	public String getHtmlTemplatePath() {
		return htmlTemplatePath;
	}

	public void setHtmlTemplatePath(String htmlTemplatePath) {
		this.htmlTemplatePath = htmlTemplatePath;
	}

	public String getCcAccout() {
		return ccAccout;
	}

	public void setCcAccout(String ccAccout) {
		this.ccAccout = ccAccout;
	}

	public String getBccAccount() {
		return bccAccount;
	}

	public void setBccAccount(String bccAccount) {
		this.bccAccount = bccAccount;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
}
