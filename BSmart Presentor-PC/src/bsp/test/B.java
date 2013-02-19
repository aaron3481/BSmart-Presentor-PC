package bsp.test;

import java.util.Properties;

import javax.swing.JOptionPane;

public class B {
	
	public void print(){
		System.out.println("This is B");
	}

	
	public void child(){
		System.out.println("child");
	}
	
	public String[] nextNote(String notes) {
		if (notes.indexOf("<@Slide-") == -1)
			return null;
		else {
			String result[] = new String[3];

			result[0] = notes.substring(8, notes.indexOf(">")).trim();
			//System.out.println(result[0]);
			result[1] = notes.substring(notes.indexOf(">") + 1,
					notes.indexOf("</@Slide>")).trim();
			//System.out.println(result[1]);
			result[2] = notes.substring(notes.indexOf("</@Slide>")+9).trim();
			//System.out.println(result[2]);

			return result;
		}
	}
	
	public static void main(String[]ak){
		
		Properties sysProps = System.getProperties();
	    System.out.println(sysProps.getProperty("user.dir"));
	    System.out.println(sysProps.getProperty("os.version"));
		
	    JOptionPane.showMessageDialog(null,
				"Cannot initialnize local device. Program exiting...",
				"Connection Manager Error", JOptionPane.ERROR_MESSAGE);
	    
		/*String c = "<@Slide-1>\naleijf  kejlwej fke\n\n\njw j\njkjejesl jkekfff jjfjfl aej\n</@Slide>\n<@Slide-25>\nWa kaka\n</@Slide>\n";
		
		B b = new B();
		
		//System.out.println(c.indexOf("</@Slide>"));
		
		String [] a = b.nextNote(c);
		
		//System.out.println(c+"\n\n\n");
		System.out.println(a[0]);
		System.out.println(a[1]);
		System.out.println(a[2]);
		
		c = a[2];
		
		a = b.nextNote(c);
		System.out.println("\n\n\n------------------------------------------------");
		System.out.println(a[0]);
		System.out.println(a[1]);
		System.out.println(a[2]);*/
	}
}
