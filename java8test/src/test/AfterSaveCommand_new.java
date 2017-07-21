package test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public enum AfterSaveCommand_new {
	
	preview((o)->{
		System.out.println("new preview " + o[0]);
		
		System.out.println("\nfind String parameter:");
		AfterSaveMethod.checkParamsType(o, 
				each -> {
					return (each instanceof String)
							&& ((String)each).matches("[0-9]+");
				},each -> {
					System.out.println(each);
				});
		
		System.out.println("\nsum of all Integer parameter times 100:");
		System.out.println(
				AfterSaveMethod.sumIntParams(o, each-> each * 100));
	}),
	
	doNothing((o)->{
		System.out.println("new do nothing");
	});
	
	private AfterSaveMethod run;
	
	AfterSaveCommand_new(AfterSaveMethod run) {
		this.run = run;
	}
	
	public void rumCommand(Object... obj) {
		this.run.run(obj);
	}
}

@FunctionalInterface
interface AfterSaveMethod {
	
	void run(Object... o);
	
	/**
	 * ¦³¥|ºØ
	 * Predicate<T>
	 * Function<T, R>
	 * BiConsumer<T, U>
	 * UnaryOperator<T>
	 * ¸É¥R(¨}¸¯):https://openhome.cc/Gossip/Java/ConsumerFunctionPredicateSupplier.html
	 * ¸É¥R(IT Architect.HK):http://itarchitect.hk/2014/05/02/java-lambda-%E6%95%99%E5%AD%B8-part-2/
	 */
	static void checkParamsType(Object[] o, 
			Predicate<Object> matcher, Consumer<Object> action) {
		
		if (o != null && o.length > 0) {
			for (Object each : o) {
				if (matcher.test(each)) {
					action.accept(each);
				}
			}
		}
	}
	
	static int sumIntParams(Object[] o, 
			Function<Integer, Integer> function) {
		int sum = 0;
		if (o != null && o.length > 0) { 
			for (Object each : o) {
				if (each instanceof Integer) {
					sum += function.apply((int)each);
					
				}
			}
		}
		
		return sum;
	}
}