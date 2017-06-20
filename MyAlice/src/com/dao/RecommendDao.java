package com.dao;

import java.sql.ResultSet;
import java.util.List;

import com.bean.MusicInfo;
import com.constant.ISQL;
import com.util.ikanalyzer.DBUtil;

/**
 * 推荐数据
 * @author Administrator
 *
 */
public class RecommendDao extends BasicDao{
	/**
	 * 获取是10条推荐数据
	 * @return
	 */
	public static List<MusicInfo> getReconmmendMusicInfos(){
		ResultSet rs = getResultSet(ISQL.getRandomDatasFromMusicInfo);
		List<MusicInfo> list = DBUtil.getObjectsByResult(rs, MusicInfo.class);
		return list;
	}
}
