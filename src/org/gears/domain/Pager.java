package org.gears.domain;

public class Pager {

	//현재 페이지 번호
	private int current;
	//토탈 
	private int total;
	//start
	private int start;
	//end
	private int end;
	//prev
	private boolean prev;
	//next
	private boolean next;
	
	//default 
	private double viewCount = 10.0;
	
	public Pager(int pageNum, int totalCount){
		
		if(pageNum <= 0){
			pageNum = 1;
		}
		
		this.current = pageNum;
		this.total = totalCount;
		calcPage();
	}
	
	private void calcPage(){
		
		int tempEnd = (int)(Math.ceil(this.current/viewCount) * 10);
		this.start = tempEnd - 9;
		this.end = tempEnd * viewCount > total ? (int)Math.ceil(total/viewCount):tempEnd;
		
		this.prev = this.start == 1?false:true;
		this.next = this.total > this.end * viewCount? true : false;
	}

	
	
	public double getViewCount() {
		return viewCount;
	}

	public void setViewCount(double viewCount) {
		this.viewCount = viewCount;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Pager [current=" + current + ", total=" + total + ", start=" + start + ", end=" + end + ", prev=" + prev
				+ ", next=" + next + "]";
	}
	
	
	
}




