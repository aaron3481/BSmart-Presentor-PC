package bsp.fileloader;

public class Slide{
	private int slide;
	private int numberOfClick;
	private int cumulateClick;
	private String note;
	
	public Slide(int slide, int numClick, int cumClick, String note){
		this.slide = slide;
		numberOfClick = numClick;
		cumulateClick = cumClick;
		this.note = note;
	}
	
	public int getSlide(){
		return slide;
	}
	
	public int getNumClick(){
		return numberOfClick;
	}
	
	public int getCumlateClick(){
		return cumulateClick;
	}
	
	public String getNote(){
		return note;
	}
}