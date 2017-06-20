package com.util.ikanalyzer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
/**
 * 使用ik分词器进行中文分词
 * @author Administrator
 *
 */
public class ChineseSegmenter {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("待分词语句:");
			String input = scanner.nextLine();
			if (input.toLowerCase().equals("bye")) {
				break;
			}
			System.out.println("分词的结果："
					+ IKAnalysis(input).replaceAll(" ", " /"));
		}
		scanner.close();
	}

	/**
	 * 分词 <br>
	 * 1.如果不包含中文，那么不处理<br>
	 * 2.如果包含中文，那么<br>
	 * <span>a).处理特殊字符</span> <br>
	 * <span>b).分词后添加空格</span> <br>
	 * @param str	需要处理的字符串
	 * @return	处理完毕的字符串
	 */
	public static String IKAnalysis(String str) {

		if (str.getBytes().length == str.length()) {
			// 如果不包含中文，就直接返回。
			return str;
		} else {
			// 由于IK分词器，不支持特殊字符，所以将 * 改为中文字符“这是星号”,中文分词以后再将“这是星号”修正为为 *
			// 同理将 _ 改为中文字符串“这是下划线”，中文分词以后再将“这是下划线”修正为 _
			str = str.replaceAll("\\*", "这是星号").replaceAll("_", "这是下划线");
		}
		StringBuffer sb = new StringBuffer();
		try {
			byte[] bt = str.getBytes();
			InputStream ip = new ByteArrayInputStream(bt);
			Reader read = new InputStreamReader(ip);
			// 设置为智能分词
			IKSegmenter iks = new IKSegmenter(read, true);
			Lexeme t;
			while ((t = iks.next()) != null) {
				// 在每个分词元之后添加空格
				sb.append(t.getLexemeText() + " ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString().replaceAll("这是星号", "*").replaceAll("这是下划线", "_");
	}
}
