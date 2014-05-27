package com.microsoftapi.searchapp.response;

public class BaseResponse {
	
	
	protected int TotalItem;
	protected int StartId;
	protected int EndId;
	
	public int getTotalItem() {
		return TotalItem;
	}
	public void setTotalItem(int totalItem) {
		TotalItem = totalItem;
	}
	public int getStartId() {
		return StartId;
	}
	public void setStartId(int startId) {
		StartId = startId;
	}
	public int getEndId() {
		return EndId;
	}
	public void setEndId(int endId) {
		EndId = endId;
	}
}
