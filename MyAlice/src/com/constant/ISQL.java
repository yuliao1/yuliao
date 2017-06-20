package com.constant;
/**
 * 数据库相关的sql语句
 */
public interface ISQL {
	/*----------------------------Oracle相关账户的一些信息-----------------------------------*/
	/**数据库管理员账户名-一般不用修改*/
	String SYSTE_DBA_USER = "sys as sysdba";
	/**数据库管理员密码--需要填写自己的*/
	String SYSTE_DBA_PASSWORD = "123";
	/**登录的序列名*/
	String loginInfoSeq = "loginInfoSeq";
	/**创建一个用户*/
	String sql1 ="create user ? identified by ?";
	/***/
	String sql2 = "grant connect,resource to ?";
	
	/*-------------------------------------------音乐信息表----------------------------------------*/
	/**
	 * sql：音乐信息表是否创建
	 */
	String hasMusicInfoTable = "select * from user_tables where table_name='MUSICINFO'";
	
	/**sql：创建音乐信息表
 		Id		主键、唯一
		歌曲名称		不唯一，不可为空
		词创作者		可为空，不唯一，可多个值，“，”间隔
		曲创作者		可为空，不唯一，可多个值，“，”间隔
		歌手名称		可为空，不唯一，可多个值，“，”间隔
		专辑		可为空，不唯一，单个值
		创作时间		可为空，不唯一，单个值
		类别		默认“流行”， 不唯一，可多个值，“，”间隔

	 */
	String createMusicInfoTable="create table musicInfo("
			+ "id number primary key,"
			+ "musicName varchar2(300),"
			+ "wordCreator varchar2(300),"
			+ "songCreator varchar2(300),"
			+ "singer varchar2(300),"
			+ "sex varchar2(300),"
			+ "album varchar2(300),"
			+ "createDate date,"
			+ "classes varchar2(2000))";
	/**
	 * sql：是否有音乐表的序列
	 */
	String hasSequenceForMusicInfo = "select sequence_name from all_sequences where sequence_name='MUSICSEQ'";
	/**
	 * sql：创建音乐信息表的序列
	 */
	String createMusicInfoSequence = "create sequence musicSeq"
			+ " increment by 1"//累加1
			+ " start with 1"//起始值1
			+ " nomaxvalue"//没有最大值
			+ " nocycle"//一直累加，不循环
			+ " cache 10"//缓存为10
			;
	
	/**
	 * sql:插入音乐信息表数据
	 * 	Id		主键、唯一,通过序列自动插入值
		歌曲名称		不唯一，不可为空
		词创作者		可为空，不唯一，可多个值，“，”间隔
		曲创作者		可为空，不唯一，可多个值，“，”间隔
		歌手名称		可为空，不唯一，可多个值，“，”间隔
		专辑		可为空，不唯一，单个值
		创作时间		可为空，不唯一，单个值
		类别		默认“流行”， 不唯一，可多个值，“，”间隔
	 */
	String updateMusicInfoTable= "update musicinfo set  "
			+ "musicName=?,"//2
			+ "wordCreator=?,"//3
			+ "songCreator=?,"//4
			+ "singer=?,"//5
			+ "sex=?,"//6
			+ "album=?,"//7
			+ "createDate=?,"//8
			+ "classes=? "//9
			+ " where id=?";
	
	/**
	 * sql:插入音乐信息表数据
	 * 	Id		主键、唯一,通过序列自动插入值
		歌曲名称		不唯一，不可为空
		词创作者		可为空，不唯一，可多个值，“，”间隔
		曲创作者		可为空，不唯一，可多个值，“，”间隔
		歌手名称		可为空，不唯一，可多个值，“，”间隔
		专辑		可为空，不唯一，单个值
		创作时间		可为空，不唯一，单个值
		类别		默认“流行”， 不唯一，可多个值，“，”间隔
	 */
	String insertIntoMusicInfoTable= "insert into "
			+ "musicInfo("
			+ "id,"//1
			+ "musicName,"//2
			+ "wordCreator,"//3
			+ "songCreator,"//4
			+ "singer,"//5
			+ "sex,"//6
			+ "album,"//7
			+ "createDate,"//8
			+ "classes"//9
			+ ") "
			+ " values(musicSeq.nextval,?,?,?,?,?,?,?,?)";
	
	
	/**
	 * sql:查询所有数据
	 */
	String selectFromMusicInfo = "select * from musicInfo";
	/**推荐数据：随机从数据库音乐表中获取10条数据*/
	String getRandomDatasFromMusicInfo = "select * from (select * from musicinfo order by dbms_random.random()) where rownum<=2";

	
	/*----------------------------------------------用户信息表-----------------------------*/
	String  hasLoginInfoTable = "select * from user_tables where table_name='LOGININFO'";
	
	
	
	/*-----------------------------------------------登录表-----------------------------------*/
	/**sql：创建登录表
	Id编号		主键、唯一
	username用户名	唯一，不可为空
	password密码		不为null
	status权限	1-管理员	2-普通用户	3-一级用户	不为null
	 */
	String createLoginInfoTable="create table loginInfo("
			+ "id number primary key,"
			+ "username varchar2(20) unique,"
			+ "password varchar2(20) not null,"
			+ "status varchar2(10) not null"
			+ ")";
	/**
	 * sql：是否有用户登录表的序列
	 */
	String hasSequenceForLoginInfo = "select sequence_name from all_sequences where sequence_name='LOGININFOSEQ'";

	/**
	 * sql：创建登录表的序列
	 */
	String createLoginInfoSequence = "create sequence loginInfoSeq"
			+ " increment by 1"//累加1
			+ " start with 1"//起始值1
			+ " nomaxvalue"//没有最大值
			+ " nocycle"//一直累加，不循环
			+ " cache 10"//缓存为10
			;
	
	/**
	 * sql:插入登录表数据
	 * 	Id		主键、唯一,通过序列自动插入值
		username 用户名
		password	密码
		status		权限
	 */
	String insertIntoLoginInfoTable= "insert into "
			+"loginInfo"
			+"("
			+ "id,"
			+ "username,"
			+ "password,"
			+ "status"
			+ ") "
			+ " values(loginInfoSeq.nextval,?,?,?)";
	
	/**
	 * sql:更新登录表数据
	 * 	Id		主键、唯一,通过序列自动插入值
		username 用户名
		password	密码
		status		权限
	 */
	String updateLoginInfoTable= "update "
			+"loginInfo "
			+ "set username=?,"
			+ "password=?,"
			+ "status=?"
			+ " where id=?";
	
	
	/**
	 * sql：查询登录表信息，用户是否能够登录
	 * status 1-管理员	2-普通用户	3-一级用户
	 */
	String selectUser  ="select * from loginInfo where username=? and password=? and status=?";
	/**
	 * sql：查询登录表信息，用户是否能够登录
	 * status 1-管理员	2-普通用户	3-一级用户
	 */
	String selectUserById  ="select * from loginInfo where id=?";
	/**
	 * sql：查询登录表信息
	 * status 1-管理员	2-普通用户	3-一级用户
	 */
	String selectUsers  ="select * from loginInfo ";

	
		
	/**
	 * 向登录数据库添加一个管理员账户
	 * statuts 1-管理员	2-普通用户	3-一级用户
	 */
	String insertLoginInfoAdmin = "insert into loginInfo values("
			+loginInfoSeq+".nextval,"
			+ "'admin',"
			+ "'123',"
			+ "'1')";
	
	/*-----------------------------------------记录表--------------------------------------------------*/
	/**
	 * 记录表创建
	 * id
	 * 记录人id
	 * 问题
	 * 答案
	 * 
	 */
	String createRecordTable = "create table record("
			+ "id number primary key,"
			+ "userId varchar2(300),"
			+ "question varchar2(300),"
			+ "answer varchar2(4000),"
			+ "createDate date"
			+ ")";
	/**
	 * 判断：是否存在记录表
	 */
	String hasRecordTable = "select * from user_tables where table_name='RECORD'";
	/**
	 * 创建：记录序列创建
	 */
	String createRecordSequence = "create sequence recordSeq"
			+ " increment by 1"//累加1
			+ " start with 1"//起始值1
			+ " nomaxvalue"//没有最大值
			+ " nocycle"//一直累加，不循环
			+ " cache 10"//缓存为10
			;
	
	/**
	 * 判断：是否存在记录序列
	 */
	String hasSequenceForRecord = "select sequence_name from all_sequences where sequence_name='RECORDSEQ'";

	/**
	 * 向记录表中插入聊天信息
	 */
	String insertIntoRecordTable = "insert into record("
			+ "id,"
			+ "userId,"
			+ "question,"
			+ "answer,"
			+ "createDate"
			+ ")"
			+ " values(recordSeq.nextval,?,?,?,?)";
	
	/**
	 * 从记录表中查询数据--根据用户id
	 */
	String selectFromRecordTable = "select * from  record where userId=? order by createDate desc";
	
	
	/*-------------------------------订阅表----------------------------------------------------*/
	/**
	 * 创建：订阅表创建
	 * id
	 * 订阅人id
	 * 订阅内容
	 * 
	 */
	String createSubscribeTable = "create table subscribe("
			+ "id number primary key,"
			+ "userId varchar2(300),"
			+ "content varchar2(300),"
			+ "sendOfDate date"//订阅的信息何时向用户发送
			+ ")";
	/**
	 * 判断：是否存在订阅表
	 */
	String hasSubscribeTable = "select * from user_tables where table_name='SUBSCRIBE'";
	/**
	 * 创建：订阅序列创建
	 */
	String createSubscribeSequence = "create sequence subscribeSeq"
			+ " increment by 1"//累加1
			+ " start with 1"//起始值1
			+ " nomaxvalue"//没有最大值
			+ " nocycle"//一直累加，不循环
			+ " cache 10"//缓存为10
			;
	
	/**
	 * 判断：是否存在订阅序列
	 */
	String hasSequenceForSubscribe = "select sequence_name from all_sequences where sequence_name='SUBSCRIBESEQ'";

	/**
	 * 插入：向订阅表中插入订阅信息
	 */
	String insertIntoSubscribeTable = "insert into subscribe("
			+ "id,"
			+ "userId,"
			+ "content,"
			+ "sendOfDate)"
			+ " values(subscribeSeq.nextval,?,?,?)";
	
	/**
	 * 查询：根据某人的id查询订阅表中的信息
	 */
	String selectFromSubscribeTableById = "select * from subscribe where userId=?";
	/**
	 * 查询：根据所有人订阅表中的信息
	 */
	String selectFromSubscribeTable = "select * from subscribe";

	


}





















