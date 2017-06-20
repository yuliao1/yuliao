package com.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 歌曲信息实体类
 * @author Administrator
 *
 */
public class MusicInfo {
	private int id;
	private String musicName;
	private String wordCreator;
	private String songCreator;
	private String singer;
	private String sex;
	private String album;
	private Date createDate;
	private String classes;
	public MusicInfo() {
	}
	
	public MusicInfo(String musicName, String wordCreator, String songCreator,
			String singer, String sex, String album, Date createDate,
			int quality, String classes) {
		this.musicName = musicName;
		this.wordCreator = wordCreator;
		this.songCreator = songCreator;
		this.singer = singer;
		this.sex = sex;
		this.album = album;
		this.createDate = createDate;
		this.classes = classes;
	}

	public MusicInfo(String musicName, String wordCreator, String songCreator,
			String singer, String sex, String album, Date createDate,
			String classes) {
		super();
		this.musicName = musicName;
		this.wordCreator = wordCreator;
		this.songCreator = songCreator;
		this.singer = singer;
		this.sex = sex;
		this.album = album;
		this.createDate = createDate;
		this.classes = classes;
	}

	public MusicInfo(int id, String musicName, String wordCreator,
			String songCreator, String singer, String sex, String album,
			Date createDate, int quality, String classes) {
		this.id = id;
		this.musicName = musicName;
		this.wordCreator = wordCreator;
		this.songCreator = songCreator;
		this.singer = singer;
		this.sex = sex;
		this.album = album;
		this.createDate = createDate;
		this.classes = classes;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getWordCreator() {
		return wordCreator;
	}

	public void setWordCreator(String wordCreator) {
		this.wordCreator = wordCreator;
	}

	public String getSongCreator() {
		return songCreator;
	}

	public void setSongCreator(String songCreator) {
		this.songCreator = songCreator;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result + ((classes == null) ? 0 : classes.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((musicName == null) ? 0 : musicName.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((singer == null) ? 0 : singer.hashCode());
		result = prime * result
				+ ((songCreator == null) ? 0 : songCreator.hashCode());
		result = prime * result
				+ ((wordCreator == null) ? 0 : wordCreator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MusicInfo other = (MusicInfo) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (classes == null) {
			if (other.classes != null)
				return false;
		} else if (!classes.equals(other.classes))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (id != other.id)
			return false;
		if (musicName == null) {
			if (other.musicName != null)
				return false;
		} else if (!musicName.equals(other.musicName))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (singer == null) {
			if (other.singer != null)
				return false;
		} else if (!singer.equals(other.singer))
			return false;
		if (songCreator == null) {
			if (other.songCreator != null)
				return false;
		} else if (!songCreator.equals(other.songCreator))
			return false;
		if (wordCreator == null) {
			if (other.wordCreator != null)
				return false;
		} else if (!wordCreator.equals(other.wordCreator))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("<br>    ");
		if(musicName!=null){
			buffer.append("歌曲名：" + musicName);
		}
		if(wordCreator!=null){
			buffer.append(", 词作者：" + wordCreator );
		}
		if(songCreator!=null){
			buffer.append(", 曲作者："
					+ songCreator );
		}
		if(singer!=null){
			buffer.append(", 演唱：" + singer );
		}
		if(sex!=null){
			buffer.append(", 性别：" + sex );
		}
		if(album!=null){
			buffer.append( ", 专辑：" + album );
		}
		if(createDate!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			buffer.append(", 时间：" + dateFormat.format(createDate) );
		}
		if(classes!=null){
			buffer.append(",歌曲类别：" +classes );
		}
		
		return buffer.toString();
	}
	
}
