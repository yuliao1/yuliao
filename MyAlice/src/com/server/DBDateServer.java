package com.server;

import java.util.ArrayList;
import java.util.List;

import com.bean.MusicInfo;
import com.bean.User;

/**
 * 数据库歌曲信息录入
 * @author Administrator
 *
 */
public class DBDateServer {
	
	/**
	 * 解析从csv中读取的字符串
	 * @param list
	 */
	public static List<MusicInfo> patternCSVDatas(List<String> list){
		List<MusicInfo> infos = new ArrayList<MusicInfo>();
		for (String String : list) {
			
		}
		return infos;
	}
	/**
	 * 解析str为MusicInfo对象
	 * @param str
	 * @return
	 */
	public static MusicInfo patternCSVData(String str){
		return null;
	}
}
