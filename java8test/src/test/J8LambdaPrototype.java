package test;

public class J8LambdaPrototype {
	
	AfterSaveCommand asd = null;
	AfterSaveCommand_new asdn = null;
	Object[] asdArgs = null;

	public J8LambdaPrototype() {
		
		asd = AfterSaveCommand.preview;
		asdArgs = new Object[]{"1060100020"};
		asd.rumCommand(asdArgs);
		System.out.println();
		
		asdArgs = new Object[]{"1060100020", 3, null, 20};
		asdn = AfterSaveCommand_new.preview;
		asdn.rumCommand(asdArgs);
	}
}
