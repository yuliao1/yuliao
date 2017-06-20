package com.constant;

/**
 * 用户查询数据的查询模板
 */
public interface ISearchTemple {
/*	  
 	  String getMusicInfoByName="《xxx》";
	  String getMusicNameBySinger = "xxx有哪些歌曲";
	  String getMusicNameByClass = "xxx类型的歌曲";
	  String getMusicNameByWordCreator = "xxx创作的歌曲";
	  String getMusicNameBySongCreator = "xxx的编曲";
	  String getAlbumInfoBySinger = "xxx歌手的专辑";
	  String getMusicInfoByAlnum = "xxx专辑的歌曲";
	  String getMusicInfoByYear = "xx年的歌曲";
	  String getMusicInfoByYears = "xx年到xx年的歌曲";
	  String getMusicInfoBySex = "xx年到xx年的歌曲";
	  */
	String[] searchTemple={
			"《xxx》",
			"xxx有哪些歌曲",
			"xxx歌手的专辑",
			"xxx专辑的歌曲",
			"xx年的歌曲",
			"帮助",
			"训练"
	};
	  /**1.歌曲名代号*/
	  int musicInfoByName = 1;
	  /**2.xxx有哪些歌曲代号*/
	  int musicNameBySinger = 2;
	  /**3.xxx类型的歌曲*/
	  int musicNameByClass = 3;
	  /**4.xxx创作的歌曲*/
	  int musicNameByWordCreator = 4;
	  /**5.xxx的编曲*/
	  int musicNameBySongCreator = 5;
	  /**6.xxx歌手的专辑*/
	  int albumInfoBySinger = 6;
	  /**7.xxx专辑的歌曲*/
	  int musicInfoByAlnum = 7;
	  /**8.xx年的歌曲*/
	  int musicInfoByYear = 8;
	  /**9.女性的歌曲*/
	  int musicInfoBySex = 9;
	  
}
