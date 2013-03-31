package bsp.fileloader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		this.path = path;
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
	public void prepare(bsp.ui.MainPanel.PrepareTask proc) {
		int cumulateClick = 0;
		int click = 1;
		int index = 0;
		String note = "";
		double size = slides.length * 2;

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
			proc.setProg((int) ((double) index / size * 100.0));

		}
		
		deleteAll();
		
		float scale = (float) 0.6f;
		String userDir = System.getProperty("user.dir") + "\\tempData";
		for (int i = 0; i < slides.length; i++) {
			String file = userDir + "\\s" + (i + 1) + ".png";
			Dimension pgsize = presentation.getPageSize();
			int width = (int) (pgsize.width * scale);
			int height = (int) (pgsize.height * scale);

			BufferedImage img = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = img.createGraphics();
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
					RenderingHints.VALUE_FRACTIONALMETRICS_ON);

			graphics.setPaint(Color.white);
			graphics.fill(new Rectangle2D.Float(0, 0, width, height));

			graphics.scale((double) width / pgsize.width, (double) height
					/ pgsize.height);

			slides[i].draw(graphics);

			FileOutputStream out;
			try {
				out = new FileOutputStream(file);
				ImageIO.write(img, "png", out);
				out.close();
				index++;
				proc.setProg((int) ((double) index / size * 100.0));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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

	private void deleteAll() {
		String userDir = System.getProperty("user.dir") + "\\tempData";
		File dir = new File(userDir);

		assert (dir.isDirectory());

		String[] list = dir.list();

		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				File file = new File(dir, list[i]);
				file.delete();
			}
		}

	}

	public void test() {
		CTSlideTiming tim = slides[0].getXmlObject().getTiming();
		if (tim != null)
			System.out.println(countAnim(tim.toString(), 0));
	}

	public static void main(String[] args) {
		LoadPPTX ppt = new LoadPPTX(
				"C:/Users/Aaron/Documents/Presentation_Move.pptx");
		ppt.test();
	}
}
