package org.mydomain.powerbiembedded.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportList {
	@JsonProperty("@odata.context")
	String odatacontext;

	public String getOdatacontext() {
		return odatacontext;
	}

	public void setOdatacontext(String odatacontext) {
		this.odatacontext = odatacontext;
	}

	public Report[] getReports() {
		return reports;
	}

	public void setReports(Report[] reports) {
		this.reports = reports;
	}

	@JsonProperty("value")
	Report[] reports;
}