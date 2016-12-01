package org.mydomain.model;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named
public class AppConfig implements Serializable {
	private String workspaceid = "[YOUR WORKSPACE ID]";
	private String workspaceCollectionName = "[YOUR COLLECTION NAME]";
	private String accessToken = "[YOUR ACCESS TOKEN]";

	@PostConstruct
	public void init() {
	}

	public String getWorkspaceid() {
		return workspaceid;
	}

	public void setWorkspaceid(String workspaceid) {
		this.workspaceid = workspaceid;
	}

	public String getWorkspaceCollectionName() {
		return workspaceCollectionName;
	}

	public void setWorkspaceCollectionName(String workspaceCollectionName) {
		this.workspaceCollectionName = workspaceCollectionName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
