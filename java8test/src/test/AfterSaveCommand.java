package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum AfterSaveCommand {
	
	preview(new Object(){
		@SuppressWarnings("unused")
		public void run(String p) {
			System.out.println("preview " + p);
		}
	}),
	
	doNothing(new Object(){
		@SuppressWarnings("unused")
		public void run() {
			System.out.println("do nothing");
		}
	});
	
	private Object run;
	private Method runMethod;
	
	AfterSaveCommand(Object run) {
		this.run = run;
		for (Method method : this.run.getClass().getMethods()) {
			this.runMethod = method;
			break;
		}
	}
	
	public void rumCommand(Object... obj) {
		try {
			this.runMethod.invoke(this.run, obj);
		} catch (IllegalAccessException 
				| IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
