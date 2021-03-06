package bsp.fileloader;

public class Loader {

	protected Record record;
	protected boolean isPrepare;
	protected String path;

	public String[] getProperty() {
		// result[0] = file type;
		// result[1] = file name;
		// result[2] = file path;
		// result[3] = slide count;
		// result[4] = note count;
		if (isPrepare)
			return new String[] { record.getType(), record.getFileName(),
					record.getPath(), "" + record.getSlideCount(),
					"" + record.getNoteCount() };
		else
			return null;

	}

	public void prepare(bsp.ui.MainPanel.PrepareTask task) {

	}
}
