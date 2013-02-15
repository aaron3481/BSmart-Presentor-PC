package bsp.test;

public class A {
	private static A instance;
	
	public synchronized static void getInstance(){
		if(instance==null)
			instance = new A();
	}
	
	
	public int number = 5;
	
	public void print(){
		
	}
	
	public void printNum(){
		
	}
	
	public void parent(){
		System.out.println("Parent Print");
	}
}
