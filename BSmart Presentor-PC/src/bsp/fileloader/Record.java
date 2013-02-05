package bsp.fileloader;

public class Record {
	private String fileName;
	private String path;
	private int slideCount, noteCount;
	private Slide[] slides;

	public Record(String fileName, String path, int slideCout) {
		this.fileName = fileName;
		this.path = path;
		this.slideCount = slideCout;
		noteCount = 0;
		slides = new Slide[slideCount];
	}

	public String getPath() {
		return path;
	}

	public String getFileName() {
		return fileName;
	}

	public int getSlideCount() {
		return slideCount;
	}

	public int getNoteCount() {
		return noteCount;
	}

	public void addSlide(int slide, int numClick, int cumulateClick, String note) {
		assert (slide <= slideCount && slide > 0);
		if (!note.equals(""))
			noteCount++;
		slides[slide - 1] = new Slide(slide, numClick, cumulateClick, note);
	}

	public void printRecord() {
		System.out.println("File: " + fileName + "\tPath: " + path);
		System.out.println("Total Slides Counnt: " + slideCount
				+ "\tTotal Note Count: " + noteCount);
		System.out.println("---------------------------------------------------------------");
		
		for(Slide slide : slides){
			//System.out.format(format, args)
		}
		
	}

}
