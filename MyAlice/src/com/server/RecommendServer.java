package com.server;

import java.util.List;

import oracle.net.aso.b;

import com.bean.MusicInfo;
import com.dao.RecommendDao;

/**
 * 推荐服务
 * @author Administrator
 *
 */
public class RecommendServer {
	public static String getRecommendMusicInfos(){
		List<MusicInfo> list = RecommendDao.getReconmmendMusicInfos();
		StringBuffer buffer = new StringBuffer("");
		for (MusicInfo musicInfo : list) {
			buffer.append(musicInfo.toString());
		}
		return buffer.toString();
	}
}
