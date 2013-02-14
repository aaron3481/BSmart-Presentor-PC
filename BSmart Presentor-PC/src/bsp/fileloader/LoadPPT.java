/*
 * This class is a support class for PPT file. However, PPT is not
 * fully support on current version. Some constrain may apply:
 * 	-  User can convert PPT file to PPTX file by clicking save as
 *     and then load again. OR
 *  -  If you are willing to use PPT file, please make sure there
 *     are no any text animations. Otherwise it will not perform
 *     correctly.
 */

package bsp.fileloader;

import java.io.FileInputStream;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;

/**
 * 
 * @author Jiannan Cai
 */
public class LoadPPT extends Loader {
	private SlideShow show;
	private Slide slides[];

	public LoadPPT(String path) {
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

	@Override
	public void prepare(bsp.ui.MainPanel.PrepareTask task) {
		String note = "";
		double size = slides.length;
		int cumulate = 0;

		for (Slide slide : slides) {
			if (slide.getNotesSheet() != null)
				for (TextRun run : slide.getNotesSheet().getTextRuns())
					note += run.getText();
			
			record.addSlide(cumulate, 1, cumulate+1, note);
			cumulate++;
			note = "";
			task.setProg((int)((double)cumulate/size*100.0));
		}
		
		record.printRecord();
		isPrepare = true;
	}
	public static void main(String[] args) {
		
	}

}
