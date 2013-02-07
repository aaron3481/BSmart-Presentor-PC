package bsp.fileloader;

public class Loader {
	private javax.swing.JPanel employer; //the containing panel who owns this loader
	
	public Loader(javax.swing.JPanel emp){
		this.setEmployer(emp);
	}
	
	public String[] getProperty(){
		return null;
	}
	
	public javax.swing.JPanel getEmployer() {
		return employer;
	}

	public void setEmployer(javax.swing.JPanel employer) {
		this.employer = employer;
	}

	abstract class LoadTask extends
	javax.swing.SwingWorker<Void,Void> {}
	
}
