package bsp.fileloader;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

;

public class LoadPDF extends Loader {

	PDDocument doc;
	PDDocument note;

	boolean hasNote;

	public LoadPDF(String path, String notePath) {

		if (notePath == null || notePath.equals(""))
			hasNote = false;
		else
			hasNote = true;

		try {
			doc = PDDocument.load(new java.io.File(path));
			if (hasNote)
				note = PDDocument.load(new java.io.File(notePath));
			record = new Record(path.substring(path.lastIndexOf('\\') + 1),
					path, doc.getNumberOfPages());
			isPrepare = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void prepare(bsp.ui.MainPanel.PrepareTask task) {
		String notes = "";
		boolean hasNext = false;
		double size = doc.getNumberOfPages();

		String[] result;

		if (hasNote) {
			try {
				java.io.StringWriter writer = new java.io.StringWriter();
				PDFTextStripper stripper = new PDFTextStripper();
				stripper.writeText(note, writer);
				notes = writer.getBuffer().toString();
				hasNext = true;
				note.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		result = nextNote(notes);

		for (int i = 0; i < doc.getNumberOfPages(); i++) {
			if(hasNote && hasNext){
				int page = Integer.parseInt(result[0]);
				if(page == i+1){
					record.addSlide(i, 1, i+1, result[1]);
					result = nextNote(result[2]);
					if(result==null)
						hasNext = false;
				}
				else
					record.addSlide(i, 1, i+1, "");
			}else
				record.addSlide(i, 1, i+1, "");
			task.setProg((int)((double)(i+1)/size*100.0));
		}
		
		isPrepare = true;
		try {
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// result[0] = slide number
	// result[1] = next note
	// result[2] = remain part
	// result = null = no more
	private String[] nextNote(String notes) {
		if (notes.indexOf("<@Slide-") == -1 ||
				notes.equals(""))
			return null;
		else {
			String result[] = new String[3];

			result[0] = notes.substring(8, notes.indexOf(">")).trim();
			result[1] = notes.substring(notes.indexOf(">") + 1,
					notes.indexOf("</@Slide>")).trim();
			result[2] = notes.substring(notes.indexOf("</@Slide>") + 9).trim();

			return result;
		}
	}
}
