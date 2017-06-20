package com.server;

import java.util.List;

import com.bean.MusicInfo;
import com.constant.ISQL;
import com.constant.ISearchTemple;
import com.dao.AIMLDao;
/**
 * 用户问答系统-数据库资料搜索
 * @author Administrator
 *
 */
public class ChatSearchAwnserServer {
	/**
	 * 根据问题去查询数据库
	 * @param question
	 * @return
	 */
	public static List<MusicInfo> getAnwserFromDB(String question) {
		int code = pattern(question);
		if(code==-1){
			return null;
		}
		
		String sql = getSQLByCode(code,question);
		System.out.println(sql+"\n\r"+question);
		return AIMLDao.getListFromDB(sql);
	}
	/**
	 * 根据不同的分类生成不同的sql语句
	 * @param code	分类编号
	 * @param question	用户问题
	 * @return	查询MusicInfo表的sql语句
	 */
	private static String getSQLByCode(int code, String question) {
		String sql = ISQL.selectFromMusicInfo+" where ";
		int start=0;
		int end=0;
		String value="";
		switch (code) {
		case ISearchTemple.musicInfoByName:
			start = question.indexOf("《");
			end = question.indexOf("》");
			value = question.substring(start+1,end);
			sql += "musicName like'%"+value+"%'";
			break;
		case ISearchTemple.musicNameBySinger:
			end = question.indexOf("有哪些歌曲");
			value = question.substring(start,end);
			sql += "singer  like '%"+value.trim()+"%'";
			break;
		case ISearchTemple.musicNameByClass:
			end = question.indexOf("类型的歌曲");
			value = question.substring(start,end);
			sql ="select * from (select * from musicinfo where classes like '%"+value+"%' order by dbms_random.random()) where rownum<=3 ";
			break;
		case ISearchTemple.musicNameByWordCreator:
			end = question.indexOf("创作的歌曲");
			value = question.substring(start,end);
			sql += "wordCreator like '%"+value+"%'";

			break;
		case ISearchTemple.musicNameBySongCreator:
			end = question.indexOf("的编曲");
			value = question.substring(start,end);
			sql += "songCreator like '%"+value+"%'";

			break;
		case ISearchTemple.albumInfoBySinger:
			end = question.indexOf("歌手的专辑");
			value = question.substring(start,end);
			sql = "select singer,album from musicInfo where singer like '%"+value+"%'";
			break;
		case ISearchTemple.musicInfoByAlnum:
			end = question.indexOf("专辑的歌曲");
			value = question.substring(start,end);
			sql += " album like '%"+value+"%'";
			break;
		case ISearchTemple.musicInfoByYear:
			//某一年的歌曲
			end = question.indexOf("年的歌曲");
			value = question.substring(start,end);
			int year = 90;
			try {
				year = Integer.valueOf(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sql += " createDate >to_date('"+(year-1)+"','yyyy') and createDate<to_date('"+(year+1)+"','yyyy')";

			break;
		case ISearchTemple.musicInfoBySex:
			//男性、女性的歌曲
			end = question.indexOf("性的歌曲");
			value = question.substring(start,end);
			sql += " sex like '%"+value+"%'";
			break;
		}
		return sql;
	}
	/**
	 * 根据问题获取不同的sql语句分类
	 * @param question
	 * @return 类别编号
	 */
	private static int pattern(String question) {
		/**歌曲查询*/
		if(question.startsWith("《")&&question.endsWith("》")){
			return ISearchTemple.musicInfoByName;
		}
		if(question.contains("有哪些歌曲")){
			return ISearchTemple.musicNameBySinger;
		}
		if(question.contains("类型的歌曲")){
			return ISearchTemple.musicNameByClass;
		}
		if(question.contains("创作的歌曲")){
			return ISearchTemple.musicNameByWordCreator;
		}
		if(question.contains("的编曲")){
			return ISearchTemple.musicNameBySongCreator;
		}
		if(question.contains("的专辑")){
			return ISearchTemple.albumInfoBySinger;
		}
		if(question.contains("专辑的歌曲")){
			return ISearchTemple.musicInfoByAlnum;
		}
		if(question.contains("年的歌曲")){
			return ISearchTemple.musicInfoByYear;
		}
		if(question.contains("性的歌曲")){
			return ISearchTemple.musicInfoBySex;
		}
		return -1;
	}
}
