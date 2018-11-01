package cn.owntt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class test {
	public static void main(String[] args) throws Exception {
		String s1 = "d:\\mp3";
		File f = new File(s1);
    	String[] files = f.list();
    	for (String s : files) {
    		FileInputStream fis = new FileInputStream(s1 + "\\" + s);
    		File ff = new File(s1 + "\\" + s);
			System.out.println(s+ "   " + fis.available() + " " + ff.length());
		}
    	
	}
}
