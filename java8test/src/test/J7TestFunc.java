package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class J7TestFunc {

	public static void test() {
		//IO stream(implements AutoCloseable)�BException �޲z�ɯ�
		/**
		  	public interface AutoCloseable {
			    void close() throws Exception;
			}
		 */
		try {
			j7ExceptionTest();
		} catch (FileNotFoundException e) {
			System.out.println("�䤣���ɮ�");
		} catch (IOException e) {
			System.out.println("�ɮ׶}�ҥ���");
		}
		
		//switch case�i�H��String�F
		j7SwitchTest();
		
		//²�ƪx��ϥ�
		j7FunctionalTest();
		
		//�ܼƵ��ȧ�n��z
		j7ParameterTest();
	}

//	public static void j7ExceptionTest() throws Exception{
	public static void j7ExceptionTest() throws FileNotFoundException, IOException{
		File file = new File("D:/yoyoyoy.di");
		String encoding = "UTF-8";
		
		StringBuffer stringBuffer = null;
		try ( FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, encoding);
				BufferedReader bufferedReader = new BufferedReader( isr ) ){
    		stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			System.out.println(stringBuffer.toString());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void j7SwitchTest() {
		
		int i=0;
		switch(i) {
			case 0:
			case 1:
			default:
				break;
		}
		
		switch("c2") {
			case "c1":
			case "c2":
			case "c3":
				break;
		}
	}
	
	public static void j7FunctionalTest() {
		ArrayList<String> list1_old = new ArrayList<String>();
		ArrayList<String> list_new = new ArrayList<>();
		
		HashMap<String, Integer> hashMap1 = new HashMap<String, Integer>(); 
		HashMap<String, Integer> hashMap2 = new HashMap<>();
		
//		HashMap<String, Integer>[] hashMap3 = new HashMap<String, Integer>[3];
		HashMap<String, Integer>[] hashMap4 = new HashMap[4];
	}
	

	public static void j7ParameterTest() {
		int x = 1234567890;
		int y = 0x1234;
		int z = 0b01100101;//�i�H��2�i��F
		long m = 987654321L;
		
		int x2 = 1_234_567_890;
		int y2 = 0x1A_2B;
		int z2 = 0b0110_0101;
		long m2 = 98_765_321L;
	}
	
	public static void breakLine() {
		System.out.println(
				"\n---------------------�ڬO���j�u---------------------\n");
	}
}
