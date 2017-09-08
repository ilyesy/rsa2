package com.talan.rsa.utilities;

public class DataTablesUtility {
	
	private int length;
	private int start;
//	private String[] search;
	
	
	
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
//	public String[] getSearch() {
//		return search;
//	}
//	public void setSearch(String[] search) {
//		this.search = search;
//	}
	public DataTablesUtility(int length, int start) {
		this.length = length;
		this.start = start;
//		this.search = search;
	}

	
	public DataTablesUtility() {
		super();
	}
}
