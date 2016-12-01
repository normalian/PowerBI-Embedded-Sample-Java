package org.mydomain.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.mydomain.powerbiembedded.model.Report;
import org.mydomain.powerbiembedded.model.ReportList;

@Named
@SessionScoped
public class ViewDto implements Serializable {
	private String reportId;
	private String appToken;

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	private ReportList reportList;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public ReportList getReportList() {
		return reportList;
	}

	public void setReportList(ReportList reportList) {
		this.reportList = reportList;
	}

	private Report report;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
}
