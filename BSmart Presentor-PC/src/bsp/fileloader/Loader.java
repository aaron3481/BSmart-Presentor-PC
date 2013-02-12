package bsp.fileloader;

public class Loader {
	
	protected Record record;
	protected boolean isPrepare;
	
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
	
	public void prepare(bsp.ui.MainPanel.PreparePPTXTask proc){
		
	}
}
