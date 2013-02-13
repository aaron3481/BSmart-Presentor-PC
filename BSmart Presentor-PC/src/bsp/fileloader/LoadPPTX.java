package bsp.fileloader;

import java.io.FileInputStream;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFNotes;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming;

public class LoadPPTX extends Loader {

	private final String patten1 = "\n              <p:par>";
	private final String patten2 = "\n                      <p:par>";

	private XMLSlideShow presentation;
	private XSLFSlide[] slides;
	// private XSLFNotes notes;

	public LoadPPTX(String path) {
		try {
			presentation = new XMLSlideShow(new FileInputStream(path));
			slides = presentation.getSlides();
			record = new Record(path.substring(path.lastIndexOf('\\') + 1),
					path, slides.length);
			isPrepare = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	// This function do the actual "load" work to a 
	// Record class
	// The number of click is including the last click to switch to next slide
	public void prepare(bsp.ui.MainPanel.PreparePPTXTask proc) {
		int cumulateClick = 0;
		int click = 1;
		int index = 0;
		String note = "";
		double size = slides.length;

		for (XSLFSlide slide : slides) {
			click = 1;
			String xml = "";
			note = "";

			CTSlideTiming timing = slide.getXmlObject().getTiming();
			if (timing != null) {
				xml = slide.getXmlObject().getTiming().toString();
				click += countAnim(xml, 0);
			}

			cumulateClick += click;

			XSLFNotes notes = slide.getNotes();
			if (notes != null) {
				XSLFShape[] shapes = notes.getShapes();
				for (int i = 1; i < shapes.length - 1; i++) {
					if (shapes[i] instanceof XSLFTextShape) {
						note = ((XSLFTextShape) shapes[i]).getText();
					}
				}
			}
			record.addSlide(index++, click, cumulateClick, note);
			proc.setProg((int)((double)index/size*100.0));
			
		}
		record.printRecord();
		isPrepare = true;
	}

	// Count number of animation effects that a xml fragment have.
	private int countAnim(String xml, int carry) {
		int result = xml.lastIndexOf(patten1);
		if (result == -1)
			result = xml.lastIndexOf(patten2);
		if (result != -1) {
			carry++;
			return countAnim(xml.substring(0, result), carry);
		} else
			return carry;
	}
	
	public void test(){
		CTSlideTiming tim = slides[0].getXmlObject().getTiming();
		if(tim != null)
			System.out.println(countAnim(tim.toString(),0));
	}
	
	public static void main(String[]args){
		//LoadPPTX ppt = new LoadPPTX("C:/Users/aaron/Documents/UWaterloo/Presentation_12.pptx");
		LoadPPTX ppt = new LoadPPTX("C:/Users/Aaron/Documents/Presentation_Move.pptx");
		ppt.test();
	}
}
