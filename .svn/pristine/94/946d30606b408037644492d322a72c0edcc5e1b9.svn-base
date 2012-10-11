package com.tbse.algebro;


public class SpecialArea {
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	
	public SpecialArea(int x1, int x2, int y1, int y2) {
		this.startX = x1;
		this.endX = x2;
		this.startY = y1;
		this.endY = y2;
	}
	
	public boolean contains(int x, int y) {
//		Log.d("ab", "contains..."+startX +"<="+ x +"<="+ endX+" && "+startY +"<="+ y +"<="+ endY);
		return startX <= x && x <= endX && startY <= y && y <= endY;
	}

	public boolean contains(float x, float y) {
		return contains(Math.round(x), Math.round(y));
	}
	
	public String toString() {
		return "x in ("+startX +"," + endX+") && y in ("+startY +","+ endY+")";
	}
}
