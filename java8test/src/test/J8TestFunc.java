package test;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.Base64.*;
import java.util.stream.*;
import java.util.Map.Entry;
import java.util.function.Predicate;

public class J8TestFunc {

	public static void test() {
		
		//1. java globalization
		//date time
		j8Date();
		
		//time duration
		j8Duration();
		
		//time zone
		j8Zone();
		
		//2. java base64
		//base64 encode/decode
		j8Base64();
		
		//3. java Option object (for null element)
		j8OptionObject();		
		
		HashMap<String, Integer> firstIssue = new HashMap<>(); 
		firstIssue.put("Fortune", 1930);
		firstIssue.put("BusinessWeek", 1929);
		firstIssue.put("Forbes", 1917);
		firstIssue.put("Fast Company", 1995);

		//4. java default protection
		//default value setting
		j8DefaultValueTest(firstIssue);
		
		//5. java functional interface
		//Functional Interface
		//lambda indruction
		j8FunctionalInterface();
		
		//6. java lambda
		//lambda
		j8BasicLambdaTest(firstIssue);
		
		//7. java stream
		//stream
		j8StreamTest(firstIssue);
		
		//8. java Comparator
		j8Comparator(firstIssue);
		
		//9. java generics  甲骨文的犯行
		//generic
		j8TestFunc(firstIssue);
		
//		StreamSupport s;
	}
	
	/**
	 * 補充(magiclen):https://magiclen.org/java-8-date-time-api/
	 */
	public static void j8Date() {
		//old version
		breakLine();
		{
			Calendar calendar = Calendar.getInstance();
			int y = calendar.get(Calendar.YEAR);
			int M = calendar.get(Calendar.MONTH);
			int d = calendar.get(Calendar.DAY_OF_MONTH);
			int h = calendar.get(Calendar.HOUR_OF_DAY);
			int m = calendar.get(Calendar.MINUTE);
			int s = calendar.get(Calendar.SECOND);
		}
		
		//instance time
//		//case
//		Instant instant = Instant.parse("2017-07-01T00:08:00Z");
//		print(instant.toString());
//		//case 1
		long l = System.currentTimeMillis();
		Instant instant = Instant.ofEpochMilli(l);
		print(instant.toString());
		//case 2
		instant = Instant.now();
		print(instant.toString());
//		//..............useless
		
		//local date time
		breakLine();
		LocalDate nowdate = LocalDate.now();
		int y = nowdate.getYear();
		int M = nowdate.getMonth().getValue();
		int d = nowdate.getDayOfMonth();
		LocalDate date = LocalDate.parse(
				String.format("%04d%02d%02d", y, M, d), 
				DateTimeFormatter.BASIC_ISO_DATE);
		print(date);
		
		LocalTime nowtime = LocalTime.now();
		int h = nowtime.getHour();
		int m = nowtime.getMinute();
		int s = nowtime.getSecond();
		LocalTime time = LocalTime.parse(
				String.format("%02d:%02d:%02d", h, m, s), 
				DateTimeFormatter.ISO_TIME);
		print(time);
		
//		LocalDateTime dateTime1 = LocalDateTime.parse(
//				"Thu, 5 Jun 2014 05:10:40 GMT", 
//				DateTimeFormatter.RFC_1123_DATE_TIME);
//		print(dateTime1);
//		LocalDateTime dateTime2 = LocalDateTime.parse(
//				"2014-11-03T11:15:30", 
//				DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//		print(dateTime2);
//		LocalDateTime dateTime3 = LocalDateTime.parse(
//				"2014-10-05T08:15:30+02:00", 
//				DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//		print(dateTime3);
		
		//DateTimeFormatter
		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
		DateTimeFormatter formatter = builder.appendLiteral("今天是:")
				.appendPattern("u") // use Pattern
				.appendLiteral("年")
			    .appendValue(ChronoField.MONTH_OF_YEAR) // use value
			    .appendLiteral("月")
			    .appendValue(ChronoField.DAY_OF_MONTH)
			    .appendLiteral("日")
			    .appendLiteral("，時間:")
			    .appendValue(ChronoField.HOUR_OF_DAY)
			    .appendLiteral(":")
			    .appendText(ChronoField.MINUTE_OF_HOUR, TextStyle.SHORT)// use text
			    .appendLiteral(":")
			    .appendValue(ChronoField.SECOND_OF_MINUTE)
			    .toFormatter(); 
	    LocalDateTime dateTime  = LocalDateTime.now(); 
	    String str =  dateTime.format(formatter); 
	    print(str);
	}
	
	public static void j8Duration() {
		LocalDate nowdate = LocalDate.now();
		int y = nowdate.getYear();
		int M = nowdate.getMonth().getValue();
		int d = nowdate.getDayOfMonth();
		LocalTime nowtime = LocalTime.now();
		int h = nowtime.getHour();
		int m = nowtime.getMinute();
		int s = nowtime.getSecond();
		
		//basic
		breakLine();
		Duration duration = Duration.ofMinutes(20);
		print(duration);
		duration = Duration.parse(duration.toString());//PT20M
		
		//time duration
		LocalTime time = LocalTime.parse(
				String.format("%02d:%02d:%02d", h, m, s), 
				DateTimeFormatter.ISO_TIME);
		LocalTime time2 = time.plus(Duration.ofHours(10))
				.plus(Duration.ofMinutes(1));
		Duration duration12 = Duration.between(time, time2);
		print(duration12);
		
		//date time plus
		Instant instant = Instant.parse(
				String.format("%04d-%02d-%02dT%02d:%02d:%02dZ", y, M, d, h, m, s));
		print(instant);
		
		instant = Instant.now();
		instant = instant.plus(Duration.ofHours(8));
		print(instant.toString());
	}
	
	public static void j8Zone() {
		ZoneId zoneId = ZoneId.systemDefault();
		print(zoneId);
		zoneId = ZoneId.of("UTC+8");
		print(zoneId);
		
		LocalDateTime time = 
				LocalDateTime.ofInstant(Instant.now(), zoneId);
		print(time);
	}
	
	public static void j8Base64() {
		Encoder encoder = Base64.getEncoder();
		Decoder decoder =  Base64.getDecoder();
		
		String 請加密我 = "請加密我";
		String 請解密我 = "";
		try {
			breakLine();
			請解密我 = encoder.encodeToString(
					請加密我.getBytes("utf-8"));
			print(請解密我);
			請解密我 = new String(decoder.decode(請解密我), "utf-8");
			print(請解密我);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void j8DefaultValueTest(
			HashMap<String, Integer> firstIssue) {
		
		breakLine();
		print( firstIssue.getOrDefault("BusinessWeek", -1) );
		print( firstIssue.get("Life") );
		print( firstIssue.getOrDefault("Life", -1) );
		
		breakLine();
		print("pre-value:"+firstIssue.putIfAbsent("Fortune", 1933));//回傳前值
		firstIssue.forEach((k,v) -> {
			print("first issue of " + k + ":" + v);
		});
		
		breakLine();
		print("pre-value:"+firstIssue.put("Fortune", 1933));//回傳前值
		firstIssue.forEach((k,v) -> {
			print("first issue of " + k + ":" + v);
		});
	}
	
	public static void j8OptionObject() {
		//case 1
		breakLine();
		String lottery = "money";
		Optional<String> opt = Optional.of(lottery);
		print(opt.get());
		//case 2
		try {
			breakLine();
			lottery = null;
			opt = Optional.of(lottery);
			print(opt.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//case 3
		try {
			breakLine();
			opt = Optional.ofNullable(lottery);
			print(opt.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//case 4
		breakLine();
		if (opt.isPresent()) {
			print(opt.get());
		} else {
			print("element is null!!");
		}
		
		//case 5, excellent
		breakLine();
		print(opt.orElse("element is null!!"));
		
		//case 6
		breakLine();
		String[] real_lottery = new String[]{"money",null};
		Optional<String[]> real_opt = Optional.of(real_lottery);
		print(real_opt.get()[1]);
	}
	
	/**
	 * 補充(TonyBlog):http://blog.tonycube.com/2015/10/java-java8-1-functional-interfaces.html
	 */
	public static void j8FunctionalInterface() {
		
		breakLine();
		
		doInterface(new J8extend());
		
		doInterface(new J8Interface2() {
            @Override
            public void hello() {
                print("Override by innerClass");
            }
        });
		
		doInterface(() -> print("Override by lambda"));
		
		//Lambda
		breakLine();
		new J8LambdaPrototype();
	}
	
	public static void doInterface(J8Interface2 sfi){
        sfi.hello();
    }
	
	/**
	 * 補充(良葛):https://www.slideshare.net/JustinSDK/java-8-retrolambda
	 * 補充(importnew):http://www.importnew.com/16436.html
	 * @param firstIssue
	 */
	public static void j8BasicLambdaTest(HashMap<String, Integer> firstIssue) {
		//case 1
		breakLine();
		for(Entry<String, Integer> set : firstIssue.entrySet()) {
			print("first issue of " + set.getKey() + ":" + set.getValue());
		}
		breakLine();
		//case 2
		for(String key : firstIssue.keySet()) {
			print("first issue of " + key + ":" + firstIssue.get(key));
		}
		breakLine();
		//case 3
		firstIssue.forEach((k,v) -> print("first issue of " + k + ":" + v));
		
		//case 4
		breakLine();
		firstIssue.forEach((k,v) -> {
			//當然可使用區塊
			if ("Forbes".equals(k)){
				print("It's Forbes!");
			}
		});
		
		//foreach
		breakLine();
		firstIssue.keySet().forEach(i -> print(i));
		breakLine();
		firstIssue.keySet().forEach(System.out::println);
		
		//runnable inner class
		breakLine();
		new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	System.out.println("Before Java8");
		    }
		}).start();
		
		new Thread( () -> {
			System.out.println("In Java8");
		}).start();
	}
	
	/**
	 * 補充(winterbe):http://winterbe.com/posts/2015/03/25/java8-examples-string-number-math-files/
	 * 補充(tony blog):http://blog.tonycube.com/2015/10/java-java8-3-stream.html
	 * 補充(MagicLen):https://magiclen.org/java-8-lambda/
	 * @param firstIssue
	 */
	public static void j8StreamTest(HashMap<String, Integer> firstIssue) {
		
		//1. collection as Stream 
		breakLine();
		//case 1
		Collection<Integer> collection = firstIssue.values();
		int year = 1995;
		boolean allMatch = collection.stream().allMatch(
				new Predicate<Integer>(){
			@Override
			public boolean test(Integer o) {
				return o > year;
			}
		});
		print("allMatch:" + allMatch);
		//case 2
		print("allMatch:" + 
				collection.stream().allMatch(o -> (o > year)));
		
		//2. IntStream、LongStream、DoubleStream
  		breakLine();
  		IntStream.range(0, 10)
  			.forEach(i -> print(i));
  		IntStream.rangeClosed(0, 10)
  			.forEach(System.out::println);
  		
  		breakLine();
  		//IntSummaryStatistics
  		//LongSummaryStatistics
  		//DoubleSummaryStatistics
  		IntSummaryStatistics iss = 
  				IntStream.range(0, 10).summaryStatistics();
  		System.out.println("Count:" + iss.getCount());
  		System.out.println("Count:" + iss.getMax());
  		System.out.println("Count:" + iss.getMin());
  		System.out.println("Count:" + iss.getSum());
  		//...and others
  		
  		//3. File as Steam
  		j8IOStream();
		
		//4. array as Stream 
		breakLine();
		String[] array = new String[]{
				"tb01_id", "tb01_case", "tb01_date"};
		
		Stream<String> stream = Stream.of(array);
		stream = stream.map(each -> each.toUpperCase());//map
		String result = stream.collect(
				Collectors.joining(",", "【", "】"));//joining
		print(result);
	    
		//simplify
	    breakLine();
	    print(Stream.of(array)
                .map(String::toUpperCase)
                .collect(Collectors.joining(",", "【", "】")));
	    
	    breakLine();
	    ArrayList<String> names = new ArrayList<String>(Arrays.asList(array));
		//filter == Predicate
		names.add("tb02_date");
		names.stream()
			.filter(col -> col.startsWith("tb01_"))
			.filter(Objects::nonNull)
			.forEach(System.out::println);
		
	    //example
		breakLine();
		StringBuffer sb = new StringBuffer();
	    String sql = Stream.of(array)
	    				.collect(Collectors.joining(",", "SELECT ", " FROM table01"));
	    sb.append(sql);
	    
	    ArrayList<String> andCond = new ArrayList<String>();
	    andCond.add("tb01_userid = :userid");
	    andCond.add("tb01_date = :date");
	    sql = andCond.stream()
	    		.collect(Collectors.joining(" AND ", "WHERE ", ""));
	    sb.append(sql);
	    print(sb.toString());
	    
	    
	    //sort
	    breakLine();
	    String[] intArray = new String[]{"15","10","20"};
	    print(
		    Stream.of(intArray)
		    	.sorted()
		    	.collect(Collectors.joining(",")));
	    
	    //sum
	    breakLine();
	    print("sum:" + Stream.of(intArray).mapToInt(
	    	each -> Integer.parseInt(each)).sum()
	    );
	    
	    //average
	    print("average:" + Stream.of(intArray).mapToInt(
	    	each -> Integer.parseInt(each)).average().getAsDouble()
	    );
	    
	    //max / min
	    breakLine();
	    print("min:" + firstIssue.values().stream().min(
	    	Comparator.comparing(n -> n)).get());
	    print("max:" + firstIssue.values().stream().max(
	    	Comparator.comparing(n -> n)).get());
	    
	    //reduce!!
	    breakLine();
	    print("reduce:" + Stream.of(intArray)
	    	.mapToInt(each -> Integer.parseInt(each))
		    .reduce((total, each) ->  total - each)
//		    .reduce((total, each) ->  {return total -= each;} )
		    .getAsInt());
	    
	    //sequential / parallel <-> CPU
	    int upperBound = 20000;
	    breakLine();
	    LocalTime nowtime1 = LocalTime.now();
	    print(nowtime1);
	    print(
	    	IntStream.range(0, upperBound).sequential().count());
	    LocalTime nowtime2 = LocalTime.now();
	    print(nowtime2);
	    Duration duration = Duration.between(nowtime1, nowtime2);
		print(duration);
	    print(
		    	IntStream.range(0, upperBound).parallel().count());
	    LocalTime nowtime3 = LocalTime.now();
	    print(nowtime3);
	    duration = Duration.between(nowtime2, nowtime3);
		print(duration);
	    
		/**
		 	!!!!!!! attention !!!!!!! warning !!!!!!!
		 	
		    intermediate operation:
		    	filter()、sorted()、map()、distinct()、subStream()、ect...
		    terminal operation:
		    	forEach()、toArray()、findFirst()、anyMatch()、ect...
	    **/
  		//
	    try{
	  		Stream<String> o = Stream.of(intArray);
		    print(o.collect(Collectors.joining(",", "【", "】")));
		    print(o.mapToInt(each -> Integer.parseInt(each)));
		    print(o.count());
	    } catch (Exception e) {
	    	print(e.getMessage());
	    }
	}
	
	/**
	 * 補述(mkyong):https://www.mkyong.com/java8/java-8-stream-read-a-file-line-by-line/
	 */
	public static void j8IOStream() {
		String filepath = "D:/yoyoyoy.di";
		StringBuffer sb = new StringBuffer();
		//case 1 BufferedReader
		breakLine();
		try (
			BufferedReader br = 
				Files.newBufferedReader(Paths.get(filepath))) {
			Stream<String> fileStream = br.lines();
			fileStream.forEach(l -> sb.append(l));
		} catch (IOException e) {
			e.printStackTrace();
		}
		print(sb.toString());
		sb.setLength(0);
		
		//case 2 file
  		breakLine();
  		try (Stream<String> fileStream = 
  				Files.lines(Paths.get(filepath))) {
  			fileStream.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
  		
  		//case 3 folder list
  		breakLine();
  		try (Stream<Path> files = 
  				Files.list(Paths.get("D:/zz_di"))) {
  			files.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
  		
  		//case 4 trace folder
  		breakLine();
  		try(Stream<Path> trace = 
  				Files.walk(Paths.get("D:/zz_di"), 2)) {
  			trace.forEach(System.out::println);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
  		
  		//case 5 find folder/file
  		breakLine();
  		try (Stream<Path> matches = Files.find(
  				Paths.get("D:/"), 2, 
  				(path, basicFileAttributes) -> {
  					return path.toFile().isFile()
  						&& path.toFile().getName().startsWith("zz")
  						&& path.toFile().getName().endsWith(".di");
  				})
  		){
  			matches.forEach(path -> {
  				try (Stream<String> fileStream = 
  						Files.lines(path)){
  	  			print(fileStream.collect(Collectors.joining("")));
				} catch (Exception e) {
					e.printStackTrace();
				}
  			});
		} catch (Exception e) {
			print("file not found");
		}
	}
	
	/**
	 * 補充(良葛):https://openhome.cc/Gossip/Java/ComparableComparator.html
	 * @param firstIssue
	 */
	public static void j8Comparator(HashMap<String, Integer> firstIssue) {
		//Comparator
	    breakLine();
	    ArrayList<String> names = new ArrayList<String>(Arrays.asList(
	    		firstIssue.keySet().toArray(new String[firstIssue.keySet().size()])));
	    ArrayList<String> names_sorted = new ArrayList<String>(names);
	    //old version - 正向
	    Collections.sort(names_sorted);
        print(names_sorted);
	    //old version - 反向
        names_sorted = new ArrayList<String>(names);
		java.util.Collections.sort(names_sorted, new Comparator<String>(){
			@Override
			public int compare(String s1, String s2) {
				return -s1.compareTo(s2);
			}
		});
		print(names_sorted);
		//lambda - 正向
		names_sorted = new ArrayList<String>(names);
		Collections.sort(names_sorted, String::compareTo);
		print(names_sorted);
		//comparator - 反向
		Collections.sort(names_sorted, (s1,s2) -> (-1) * s1.compareTo(s2));
//		Collections.sort(names_sorted, (s1,s2) -> s2.compareTo(s1));
		print(names_sorted);
		//comparator - 正向
		names_sorted.sort(String::compareTo);
		print(names_sorted);
		//comparator - 反向
		names_sorted.sort(Comparator.nullsFirst(Comparator.reverseOrder()));
		print(names_sorted);
	}
	
	/**
	 * 補充(MagicLen):https://magiclen.org/generic-convert-extends-super/
	 * @param firstIssue
	 */
	public static void j8TestFunc(HashMap<String, Integer> firstIssue) {
		
		//邪魔歪道，可以大幅減少參數的使用，有十足的掌握再使用，不然建議嚴守規範就好
		ArrayList<String> list6 = new ArrayList<>();
		((ArrayList)list6).add(2000L);
	}
	
	public static void breakLine() {
		print("\n---------------------我是分隔線---------------------\n");
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}
}
