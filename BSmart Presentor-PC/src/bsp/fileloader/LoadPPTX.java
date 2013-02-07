package bsp.fileloader;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;

import javax.swing.JProgressBar;

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
	private Record record;
	private boolean isPrepare;

	public LoadPPTX(javax.swing.JPanel emp, String path) {
		super(emp);
		
		try {
			presentation = new XMLSlideShow(new FileInputStream(path));
			slides = presentation.getSlides();
			record = new Record(path.substring(path.lastIndexOf('\\') + 1),
					path, slides.length);
			isPrepare = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LoadTask task = setUpTask();
		
		task.addPropertyChangeListener(new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				taskListenerPerform(evt);
			}
			
		});

		prepare();
		record.printRecord();
	}

	@Override
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
		int click = 1;
		int index = 0;
		String note = "";

		// proc.setMaximum(slides.length);
		// proc.setValue(0);
		// lab.setText("Loading: 0%");

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
	
	private LoadTask setUpTask(){
		return new LoadTask(){
			
			@Override
			protected Void doInBackground() throws Exception {
				
				int cumulateClick = 0;
				int click = 1;
				int index = 0;
				String note = "";

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

				}
				return null;
			}
			
		};
	}
	
	private void taskListenerPerform(java.beans.PropertyChangeEvent evt){
		this.getEmployer().getPropertyChangeListeners("progress");
	}

}
