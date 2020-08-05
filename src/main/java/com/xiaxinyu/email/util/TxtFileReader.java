package com.xiaxinyu.email.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by Summer.Xiasz on 2016/06/02.
 */
public class TxtFileReader {
	public static String readText(String path) throws IOException{
    	BufferedReader br = new BufferedReader(new FileReader(path)); 
		String key = ""; 
		String s = br.readLine(); 
		while(StringUtils.isNotBlank(s)){
			key += s;
			s = br.readLine(); 
		}
		br.close();
		return key;
    }
}
