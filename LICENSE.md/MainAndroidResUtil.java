package com.chenkui.androidscreen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainAndroidResUtil {
	
	public static void main(String[] args) {
	
		new MainAndroidResUtil().start();
		
	}
	
	public  void start(){
		
		setAndroidScreen();
		File resFile = new File("./res");
		if (resFile.exists()) {
			System.out.println("当前目录存在同名文件夹，请处理");

		} else {
			resFile.mkdir();
			System.out.println("创建成功");
		}
		
		System.out.println(resFile.getAbsolutePath());

		// 设置以480*800为计算标准
		// 根文件创建成功，则创建其他文件夹
		File file;
		Iterator<String> iterator = androidSreenSizeAll.iterator();
		while (iterator.hasNext()) {

			String dirName = iterator.next();
			System.out.println("dirName   ==== " + dirName);
			file = new File("./res/values-" + dirName);

			if (!file.exists()) {
				file.mkdir();

				String desValue = dirName;

				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(desValue);

				String numValueOne = "";
				String numValueTwo = "";

				if (m.find()) {
					numValueOne = m.group(0);
					if (m.find()) {
						numValueTwo = m.group(0);
					}
				}

				System.out.println("numValueOne   ===  " + numValueOne);
				System.out.println("numValueTwo   ===  " + numValueTwo);

				// 获取最小值
				int num;
				int intNumOne = Integer.parseInt(numValueOne);
				int intNumTwo = Integer.parseInt(numValueTwo);
				System.out.println("------------------numValueTwo  === "
						+ numValueTwo);
				if (intNumOne > intNumTwo) {
					num = intNumTwo;
				} else {
					num = intNumOne;
				}

				// 获取原始效果尺寸480x800,1280x800

				int intSrcSize = Integer.parseInt("480");
				// int intSrcSize = Integer.parseInt("800");
				float scale = (float) ((num * 1.0) / intSrcSize);

				File dimensFile = new File(file.getAbsoluteFile()
						+ "/dimens.xml");

				try {
					outContent(dimensFile, scale);
				} catch (IOException e) {
					System.out.println("生成文件错误，请稍后重试");
					return;
				}

			}
		}

		
		
	}

	public  void outContent(File desFile, float scale) throws IOException {

		BufferedWriter bw = null;
		FileOutputStream fos;

		fos = new FileOutputStream(desFile);
		bw = new BufferedWriter(new OutputStreamWriter(fos));

		String hear = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		bw.write(hear + "\n");
		String tag = "<resources>";
		bw.write(tag + "\n");

		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		for (int i = 1; i < 600; i++) {
			float pxValue = scale * i;
			// double d = 3.14159;
			String strPXValue = df.format(pxValue);
			String contentLine = "<dimen name=\"x" + i + "\">" + strPXValue
					+ "px</dimen>" + "\n" + "<dimen name=\"_x" + i + "\">-"
					+ strPXValue + "px</dimen>" + "\n";

			bw.write(contentLine);
		}
		String end = "</resources>" + "\n";
		bw.write(end);

		bw.close();
		fos.close();
	}

	 LinkedList<String> androidSreenSizeAll = new LinkedList<String>();

	public  void setAndroidScreen() {
		androidSreenSizeAll.add("hdpi-960x540");

		androidSreenSizeAll.add("hdpi-1024x600");

		androidSreenSizeAll.add("hdpi-1280x720");

		androidSreenSizeAll.add("ldpi-400x240");

		androidSreenSizeAll.add("ldpi-480x320");

		androidSreenSizeAll.add("mdpi-800x480");

		androidSreenSizeAll.add("mdpi-800x600");

		androidSreenSizeAll.add("mdpi-1024x600");

		androidSreenSizeAll.add("mdpi-1024x768");

		androidSreenSizeAll.add("mdpi-1280x720");

		androidSreenSizeAll.add("xhdpi-960x640");

		androidSreenSizeAll.add("xhdpi-1184x720");

		androidSreenSizeAll.add("xhdpi-1280x720");

		androidSreenSizeAll.add("xhdpi-1280x800");

		androidSreenSizeAll.add("xhdpi-1776x1080");
		// oppo r9 ,MEIZU MX6,
		androidSreenSizeAll.add("xhdpi-1920x1080");
		// 三星S6
		androidSreenSizeAll.add("xhdpi-2560x1440");
	}

}
