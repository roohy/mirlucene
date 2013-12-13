package edu.sharif.ce.fall92.mir.pa2.base;

import java.io.Serializable;

import edu.sharif.ce.fall92.mir.pa2.base.StackExchangeAdvancedSearchInterface.SortingMeasure;

public final class SearchQuery implements Serializable {
	private static final long serialVersionUID = -6746759309992037605L;

	Boolean accepted;
	Integer views;
	String url;
	String q;
	SortingMeasure sort;
	Integer fromDate, toDate;

	public SearchQuery(Boolean accepted, Integer views, String url, String q, SortingMeasure sort, Integer fromDate,
			Integer toDate) {
		this.accepted = accepted;
		this.views = views;
		this.url = url;
		this.q = q;
		this.sort = sort;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public Integer getViews() {
		return views;
	}

	public String getUrl() {
		return url;
	}

	public String getQ() {
		return q;
	}

	public SortingMeasure getSort() {
		return sort;
	}

	public Integer getFromDate() {
		return fromDate;
	}

	public Integer getToDate() {
		return toDate;
	}
}