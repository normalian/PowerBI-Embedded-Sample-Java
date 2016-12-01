package org.mydomain.action;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mydomain.model.AppConfig;
import org.mydomain.model.ViewDto;
import org.mydomain.powerbiembedded.helper.PowerBIHelper;
import org.mydomain.powerbiembedded.model.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@RequestScoped
public class IndexAction implements Serializable {
	static final Logger LOG = LoggerFactory.getLogger(IndexAction.class);
	@Inject
	private ViewDto viewDto;

	@Inject
	private AppConfig appConfig;

	public String goDetailPage(String reportId) {
		LOG.info("your selected reportId = " + reportId);
		viewDto.setReportId(reportId);
		String appToken = PowerBIHelper.createAppToken(appConfig.getWorkspaceid(), reportId,
				appConfig.getWorkspaceCollectionName(), appConfig.getAccessToken());
		LOG.info("your appToken = " + appToken);
		viewDto.setAppToken(appToken);
		return "/reportdetail.xhtml";
	}

	public Report[] getReports() {
		if (viewDto.getReportList() == null) {
			viewDto.setReportList(PowerBIHelper.getReports(appConfig.getWorkspaceid(),
					appConfig.getWorkspaceCollectionName(), appConfig.getAccessToken()));
		}
		return viewDto.getReportList().getReports();
	}
}
