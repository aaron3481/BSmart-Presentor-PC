package bsp.fileloader;

import java.io.FileInputStream;

import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.Shape;
import org.apache.poi.hslf.model.TextRun;

public class LoadPPT extends Loader{
	private SlideShow show; 
	private Slide slides[];
	
	public LoadPPT(String path){
		try {
			show = new SlideShow(new FileInputStream(path));
			slides = show.getSlides();
			record = new Record(path.substring(path.lastIndexOf('\\') + 1),
					path, slides.length);
			System.out.println(slides.length);
			isPrepare = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tt(){
		Slide te = slides[0];
		te.getSlideRecord().getSlideAtom();
	}
	
	public static void main(String[]args){
		LoadPPT test = new LoadPPT("C:/Users/Aaron/Documents/Presentation_Move.ppt");
	}
	
}
