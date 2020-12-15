package kr.or.ksmart37.ksmart_api.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReadTest {
	//파일 실제로 불러오기 D드라이브 하위 FileReadTest.txt 파일 기준
	public static void main(String[] args) {
		//파일리드 테스트
		File f = new File("D://FileReadTest.txt");
		System.out.println(f.toString());
		
		System.out.println(f.isFile());
		
		try {
			Path p = Paths.get("D://FileReadTest.txt");
			BufferedReader br =Files.newBufferedReader(p);
			//버퍼 리더까지만 도달하면 다 똑같다.
			String temp;
			String text = "";
			while((temp = br.readLine()) != null) {
				text += temp +"\n";
			}
			System.out.println(text);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
