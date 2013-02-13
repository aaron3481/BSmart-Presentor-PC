/*
 * This class is a support class for PPT file. However, PPT is not
 * fully support on current version. Some constrain may apply:
 * 	-  User can convert PPT file to PPTX file by clicking save as
 *     and then load again. OR
 *  -  Continuous to load the PPT file with specific format. The text
 *     animation is not encourage to be used. If you decide to use, 
 *     please make sure that a whole text box is animated. (i.e. all
 *     paragraphs within the same text box MUST be animated) Otherwise
 *     the control may not correct.
 */


package bsp.fileloader;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.Shape;
import org.apache.poi.hslf.model.TextShape;

/**
 * 
 * @author Jiannan Cai
 */
public class LoadPPT extends Loader {
	private SlideShow show;
	// private XSLFSlideShow show2;
	private Slide slides[];

	public LoadPPT(String path) {
		try {
			show = new SlideShow(new FileInputStream(path));
			// show2 = new
			// XSLFSlideShow("C:/Users/aaron/Documents/UWaterloo/Presentation_2.ppt");
			slides = show.getSlides();
			record = new Record(path.substring(path.lastIndexOf('\\') + 1),
					path, slides.length);
			System.out.println(slides.length);
			isPrepare = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tt() {
		Slide te = slides[0];

		Shape[] sps = te.getShapes();
		System.out.println(sps.length);
		Shape sp = sps[1];

		/*ArrayList<EscherRecord> lAnimInfo = new ArrayList<EscherRecord>();
		sp.getSpContainer().getRecordsById(
				(short) RecordTypes.AnimationInfoAtom.typeID, lAnimInfo);

		System.out.println(lAnimInfo.size());

		System.out.println(sp.getSheet());*/
		
		System.out.println(((TextShape)sp).getTextRun().getRichTextRuns()[0]);
		
		
		//System.out.println(RecordTypes.recordName(4116));
		
		// System.out.println(((TextShape)te.getShapes()[0]).getTextRun().getRichTextRuns()[1]);

		// System.out.println(te.getNotesSheet().getTextRuns()[0].getText());
		//System.out.println(sp.getSpContainer().getChildById((short) 0xF011).getChild(0));
		//System.out.println(sp.getSpContainer().toXml());
		
		System.out.println(te.getSlideRecord().getChildRecords()[0]);
		System.out.println(te.getSlideRecord().getChildRecords()[1]);
		System.out.println(te.getSlideRecord().getChildRecords()[2]);
		System.out.println(te.getSlideRecord().getChildRecords()[3]);
		System.out.println(te.getSlideRecord().getChildRecords()[4]);
		
		System.out.println();
		
		//System.out.println(((PPDrawing)te.getSlideRecord().getChildRecords()[1]).getEscherRecords()[0].getChildRecords().size());
		//System.out.println(((PPDrawing)te.getSlideRecord().getChildRecords()[1]).getEscherRecords()[0]);
		
		//te.getSheetContainer().getPPDrawing().getEscherRecords().length
		//System.out.println(te.getSheetContainer().getPPDrawing().getEscherRecords()[0].getChildRecords().get(1));
		
		/*
		try {
			out = new FileOutputStream("D:/text.txt");
		
			te.getSlideRecord().getChildRecords()[4].writeOut(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public void pa() {
		Dimension pgsize = show.getPageSize();

		// Slide[] slide = ppt.getSlides();

		for (int i = 0; i <20; i++) {

			BufferedImage img = new BufferedImage(pgsize.width/2, pgsize.height/2,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = img.createGraphics();
			// clear the drawing area
			//graphics.setPaint(show.getSlides()[0].getBackground().getFill().getForegroundColor());
			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width,
					pgsize.height));

			// render
			slides[i].draw(graphics);

			// save the output
			FileOutputStream out;
			try {
				out = new FileOutputStream(
						"C:\\Users\\aaron\\Desktop\\temp\\slide-" + (i + 1) + ".png");
				javax.imageio.ImageIO.write(img, "png", out);
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {
		// LoadPPT test = new
		// LoadPPT("C:/Users/Aaron/Documents/Presentation_Move.ppt");
		LoadPPT test = new LoadPPT(
				"C:/Users/aaron/Documents/UWaterloo/Presentation_12.ppt");
		test.tt();
		
		
		
		//test.pa();
	}

}
