package bsp.test;

import java.io.FileInputStream;
import org.apache.poi.xslf.usermodel.*;

public class POITest {
	public static void main(String[] args) throws Exception {
		XMLSlideShow show = new XMLSlideShow(
				new FileInputStream(
						"C:/Users/aaron/Documents/UWaterloo/2013Winter/CS446/Porject/Presentation_1.pptx"));

		XSLFSlide slides[] = show.getSlides();

		// slides[0].getTitle();

		// XSLFTextRun tr = (XSLFTextRun) slides[0].getNotes().iterator();

		// tr.getText();
		int index = 1;

		for (XSLFSlide slide : slides) {
			System.out.println("Printing slide #" + index + "'s note:");
			index++;

			XSLFNotes notes = slide.getNotes();

			if (notes != null) {
				System.out.println("shape length: "+(notes.getShapes().length-2));
				
				XSLFShape shapes[] = notes.getShapes();
				
				for (int i=1; i<shapes.length-1;i++) {
					XSLFShape shape = shapes[i];
					if (shape instanceof XSLFTextShape) {
						XSLFTextShape tsh = (XSLFTextShape) shape;
						System.out.println(tsh.getText());
					}
				}
				
				System.out.println();
			}

		}

	}
}
