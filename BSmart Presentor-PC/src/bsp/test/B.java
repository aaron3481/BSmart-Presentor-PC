package bsp.test;

public class B extends A{
	
	public void print(){
		System.out.println("This is B");
	}

	public void printNum() {
		System.out.println(number);
		parent();
	}
	
	public void child(){
		System.out.println("child");
	}
}
