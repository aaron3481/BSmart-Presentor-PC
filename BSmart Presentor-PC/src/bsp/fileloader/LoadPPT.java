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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		double size = slides.length*2;
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
		
		
		float scale = (float) 0.6f;
		String userDir = System.getProperty("user.dir") + "\\tempData";
		for (int i = 0; i < slides.length; i++) {
			String file = userDir + "\\s" + (i + 1) + ".png";
			Dimension pgsize = show.getPageSize();
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

			// String fname = file.replaceAll("\\.ppt", "-" + (i+1) + ".png");
			FileOutputStream out;
			try {
				out = new FileOutputStream(file);
				ImageIO.write(img, "png", out);
				out.close();
				cumulate++;
				task.setProg((int) ((double) cumulate / size * 100.0));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		isPrepare = true;
	}
	public static void main(String[] args) {
		
	}

}
