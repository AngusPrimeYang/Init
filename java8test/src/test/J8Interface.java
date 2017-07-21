package test;

@FunctionalInterface //<--- java8 update
public interface J8Interface {
	
	/**
	 * function in interface all default " Public " 
	 * @param msg
	 */
	
    void hello();
    
//  abstract void hi(String msg);
    
    default void show1() {
    	System.out.println("show default");
    }
    
    static void show2() {
    	System.out.println("show static");
    }
}