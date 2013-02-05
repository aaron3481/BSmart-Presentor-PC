package bsp.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//import org.apache.poi.hslf.record.AnimationInfoAtom;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.xslf.usermodel.*;

public class POITest {
	public static void main(String[] args) throws Exception {
		XMLSlideShow show = new XMLSlideShow(
				new FileInputStream(
						"C:/Presentation_Move.pptx"));

		XSLFSlide slides[] = show.getSlides();

		// slides[0].getTitle();

		// XSLFTextRun tr = (XSLFTextRun) slides[0].getNotes().iterator();
		// for(int i=0;i<55;i++){
		PackagePart part = slides[0]
				.getPackagePart()
				.getPackage()
				.getPart(
						PackagingURIHelper
								.createPartName("/ppt/slides/slide1.xml"));

		System.out.println(slides[0].getPackagePart());
		System.out.println(slides[0].getPackagePart().getInputStream());

		BufferedReader in = new BufferedReader(new InputStreamReader(slides[0]
				.getPackagePart().getInputStream()));
		String line;
		String result = "";
		while ((line = in.readLine()) != null) {
			result += line;
			// System.out.println(line);
		}
		// System.out.println(result);

		// System.out.println(slides[0].getXmlObject().getTiming());

		result = slides[1].getXmlObject().getTiming().toString();

		int count = countAnim(result, 0);

		// System.out.println(slides[0].getShapes()[0].getShapeId());

		System.out.println(count);
		System.out.println(result);

		// System.out.println(slides[1].getXmlObject().getTiming());
		// System.out.println(result.lastIndexOf("<p:spTgt spid="));

		// AnimationInfoAtom ant = new AnimationInfoAtom();

		/*
		 * XSLFSlide s = slides[0]; XSLFShape sp[] = s.getShapes(); XSLFShape
		 * spp = sp[0];
		 * 
		 * System.out.println(spp.getXmlObject());
		 */

		// in.close();

		// part.getRelationships().getRelationshipByID("timing");
		// System.out.println(part.getPartName());
		// }
		// System.out.println(slides[0].getParent());
		// tr.getText();
		int index = 1;
		System.out.println(slides[0].getShapes().length);
		for (XSLFSlide slide : slides) {
			System.out.println("Printing slide #" + index + "'s note:");
			index++;

			XSLFShape[] ss = slide.getShapes();

			for (XSLFShape SS : ss) {
				// System.out.println(SS.getXmlObject().toString()+"\n");
				break;
			}

			break;

			/*
			 * XSLFNotes notes = slide.getNotes();
			 * 
			 * if (notes != null) {
			 * //System.out.println("shape length: "+(notes.
			 * getShapes().length-2));
			 * 
			 * XSLFShape shapes[] = notes.getShapes();
			 * 
			 * for (int i=1; i<shapes.length-1;i++) { XSLFShape shape =
			 * shapes[i];
			 * 
			 * System.out.println(shape.getXmlObject().toString());
			 * 
			 * if (shape instanceof XSLFTextShape) { XSLFTextShape tsh =
			 * (XSLFTextShape) shape; //System.out.println(tsh.getText()); } }
			 * 
			 * System.out.println(); }
			 */

		}

	}

	public static int countAnim(String xml, int carry) {
		int result = xml.lastIndexOf("\n              <p:par>");
		if (result == -1)
			result = xml.lastIndexOf("\n                      <p:par>");
		System.out.println(result);
		if (result != -1) {
			carry++;
			return countAnim(xml.substring(0, result), carry);
		} else
			return carry;

	}
}
