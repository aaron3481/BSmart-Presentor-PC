package bsp.fileloader;

import java.io.FileInputStream;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFNotes;

public class LoadPPTX {

	private XMLSlideShow presentation;
	private XSLFSlide[] slides;
	private XSLFNotes notes;
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
		
	}

}
