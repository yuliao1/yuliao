package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.MusicInfo;
import com.bean.User;
import com.constant.ISQL;
import com.util.ikanalyzer.DBUtil;

public class MusicInfoDao extends BasicDao{
	/**
	 * 插入音乐信息
	 * @param listValues
	 */
	public static void initDBMusicInfoDatas(List<MusicInfo> listValues){
		List<MusicInfo> listDBUsers = getAllMusicInfoData();
		patternListUsers(listDBUsers, listValues);
		//更新与插入
		updateSQLByBatch(DBFacotry.getConnectionByC3P0(), ISQL.updateMusicInfoTable, listDBUsers);
		System.out.println("更新完毕");
		insertSQLByBatch(DBFacotry.getConnectionByC3P0(), ISQL.insertIntoMusicInfoTable, listValues);
		System.out.println("插入完毕");
	}
	/**
	 * 将数组集合转换成对象集合
	 * @param listValues	数组：歌曲名字 	歌手/组合 	男/女 	专辑 	年份	 	分类
	 * @param listMusicInfo
	 * @return
	 */
	private static List<MusicInfo>  transArraysToMusicInfos(List<String[]> listValues){
		List<MusicInfo> listUsers = new ArrayList<MusicInfo>();
		for (String[] strs : listValues) {
			MusicInfo info = new MusicInfo();
			info.setMusicName(strs[0].trim());
			info.setSinger(strs[1].trim());
			info.setSex(strs[2].trim());
			info.setAlbum(strs[3].trim());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			try {
				date = dateFormat.parse(strs[4].trim());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			info.setCreateDate(date);
			info.setClasses(strs[4].trim());
			listUsers.add(info);
		}
		return listUsers;
	}
	/**
	 * 歌曲名称		歌手/组合		男/女		专辑		年份		分类
	 * @param listUsers	需要更新的集合，更新类别
	 * @param listValues	需要插入的集合
	 */
	private static void patternListUsers(List<MusicInfo> listMusicInfo,List<MusicInfo> listValues){
		System.out.println(listMusicInfo.size()+","+listValues.size());
		for (int i = listMusicInfo.size()-1; i>=0 ; i--) {
			MusicInfo musicInfo = listMusicInfo.get(i);
			String musicName = musicInfo.getMusicName();
			String singer = musicInfo.getSinger();
			String sex  =musicInfo.getSex();
			String classes  =musicInfo.getClasses();
			boolean toRemove = false;
			for (int j = listValues.size()-1; j >=0 ; j--) {
				String tempName = listValues.get(j).getMusicName();
				String tempSinger = listValues.get(j).getSinger();
				String tempSex = listValues.get(j).getSex();
				String tempClasses = listValues.get(j).getClasses();
				//如果歌曲名、歌手相同、类别不相同，那么合并类别，
				if(musicName.equals(tempName)&&singer.equals(tempSinger)&&sex.equals(tempSex)){
					//如果类别不相同,那么合并
					if(!classes.equals(tempClasses)){
						//将两个中的类别合并...TODU
						String[] tempCS = tempClasses.split(",");
						for (String tempC : tempCS) {
							if(!classes.contains(tempC)){
								classes+=","+tempC;
							}
						}
						//仅仅是类别不同，需要修改listMusicInfo中的值，然后后期更新集合到数据库，不需要插入
						listValues.remove(j);
						listMusicInfo.get(i).setClasses(classes);
					}else{
						//这个是相同的歌曲，不需要插入，也不需要更新
						listValues.remove(j);
						toRemove=true;
					}
				}else{
					//如果是不同的歌曲，那么不需要更新，需要插入，那么这个集合的数据删除
//					System.out.println(listMusicInfo.size());
					toRemove=true;
				}
			}
			if(toRemove){
				listMusicInfo.remove(i);
			}
		}
	}
	/**
	 * 获取所有的音乐表中的信息
	 * @return
	 */
	public static List<MusicInfo> getAllMusicInfoData(){
		Connection connection = DBFacotry.getConnectionByC3P0();
		Statement s =null;
		List<MusicInfo> list = new ArrayList<MusicInfo>();
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(ISQL.selectFromMusicInfo);
			list = DBUtil.getObjectsByResult(rs, MusicInfo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//closeDB(connection);
		}
		return list;
	}
}
