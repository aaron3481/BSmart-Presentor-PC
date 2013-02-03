package bsp.fileloader;

import java.io.FileInputStream;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFNotes;


public class LoadPPTX {
	
	private XMLSlideShow presentation;
	private XSLFSlide [] slides;
	private XSLFNotes notes;
	
	public LoadPPTX(String path){
		try{
			presentation = new XMLSlideShow(new FileInputStream(path));
			slides = presentation.getSlides();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String[] getProperty(){
		//result[0] = file type;
		//result[1] = file name;
		//result[2] = slide count;
		//result[3] = note count;
		
		
		presentation.getProperties().getCoreProperties();
		
		return null;
	}
	
	
}
