package org.mydomain.model;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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
		ResourceBundle resources = ResourceBundle.getBundle("powerbiembedded");
		try {
			workspaceid = resources.getString("WORKSPACE_ID");
		} catch (MissingResourceException e) {
			throw new RuntimeException("WORKSPACE_ID is wrong. Please input value at powerbiembedde.properties", e);
		}
		try {
			workspaceCollectionName = resources.getString("WORKSPACE_COLLECTION_NAME");
		} catch (MissingResourceException e) {
			throw new RuntimeException(
					"WORKSPACE_COLLECTION_NAME is wrong. Please input value at powerbiembedde.properties", e);
		}
		try {
			accessToken = resources.getString("ACCESSTOKEN");
		} catch (MissingResourceException e) {
			throw new RuntimeException("ACCESSTOKEN is wrong. Please input value at powerbiembedde.properties", e);
		}
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
