package bsp.fileloader;

import java.io.FileInputStream;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFNotes;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class LoadPPTX {

	private final String patten1 = "\n              <p:par>";
	private final String patten2 = "\n                      <p:par>";
	
	private XMLSlideShow presentation;
	private XSLFSlide[] slides;
	//private XSLFNotes notes;
	private Record record;
	private boolean isPrepare;

	public LoadPPTX(String path) {
		try {
			presentation = new XMLSlideShow(new FileInputStream(path));
			slides = presentation.getSlides();
			record = new Record(path.substring(path.lastIndexOf('/') + 1),
					path, slides.length);
			isPrepare = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		prepare();
	}

	public String[] getProperty() {
		// result[0] = file type;
		// result[1] = file name;
		// result[2] = file path;
		// result[3] = slide count;
		// result[4] = note count;
		if (isPrepare)
			return new String[] {
					record.getFileName().substring(
							record.getFileName().lastIndexOf('.') + 1),
					record.getFileName(), record.getPath(),
					"" + record.getSlideCount(), "" + record.getNoteCount() };
		else
			return null;

	}

	private void prepare() {
		int cumulateClick = 0;
		int click=1;
		int index = 0;
		String note = "";
		
		for(XSLFSlide slide : slides){
			click = 1;
			String xml="";
			note = "";
			
			xml = slide.getXmlObject().getTiming().toString();
			click += countAnim(xml,0);
			cumulateClick+=click;
			
			XSLFNotes notes = slide.getNotes();
			XSLFShape [] shapes = notes.getShapes();
			for(int i=1;i<shapes.length-1;i++){
				if(shapes[i] instanceof XSLFTextShape){
					note = ((XSLFTextShape)shapes[i]).getText();
				}
			}	
		}
		
		record.addSlide(index++,click, cumulateClick, note);
		isPrepare = true;
	}
	
	//Count number of animation effects that a xml fragment have.
	private int countAnim(String xml, int carry) {
		int result = xml.lastIndexOf(patten1);
		if (result == -1)
			result = xml.lastIndexOf(patten2);
		System.out.println(result);
		if (result != -1) {
			carry++;
			return countAnim(xml.substring(0, result), carry);
		} else
			return carry;
	}

}
