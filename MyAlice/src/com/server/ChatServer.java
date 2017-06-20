package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.aiml.AliceBotMother;
import com.aiml.core.AliceBot;
import com.bean.MusicInfo;
import com.constant.ISearchTemple;
import com.dao.DBFacotry;



public class ChatServer 
{
	public static final String END = "bye";
	private static AliceBot bot ;
	static{
		 AliceBotMother mother = new AliceBotMother();		  
	     mother.setUp();
	     try {
			bot = AliceBotMother.getTheBot();
			bot.respond("welcome");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据问题获取机器人的回答
	 * @param question	问题
	 * @param status	权限
	 * @return	
	 */
	public static String respond(String question,int status){
		System.out.println("问题："+question);
		if("帮助".equals(question)){
			return searchTemple();
		}
		
		//获取配置文件中的回答
		 String anwser = bot.respond(question.trim());
		 //如果配置文件中的回答以#开头，那么根据问题去查询数据库
		 List<MusicInfo> list =null;
		 if(anwser.startsWith("#")){
			try {
				if(status==1||status==3){
					//获取数据库回答
					 list= ChatSearchAwnserServer.getAnwserFromDB(question);
				}else{
					return "对不起喵，不知道该怎么回答，要不提升一下权限看看数据库里有没有。或者输入“训练”，给一个好的答案？";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			 if(list==null){
				 return "额，您的问法不能识别啊~，输入“帮助”进入帮助页面";
			 }
			 if(list.isEmpty()){
				 anwser = "sorry，数据库里也没有，我们会尽快补齐相关信息。";
				 return anwser;
			 }else{
				 return toString(list);
			 }
		 }else{
			 System.out.println(anwser);
			 return anwser;
		 }
	}
	
	private static String toString(List<MusicInfo> list) {
		StringBuffer buffer = new StringBuffer("");
		for (MusicInfo musicInfo : list) {
			buffer.append(musicInfo.toString()+"\n\r");
		}
		return buffer.toString();
	}

	/**
	 * 资料查询模板
	 * @return
	 */
	public static String searchTemple(){
		String str = "";
		for (String string : ISearchTemple.searchTemple) {
			str+=string+",";
		}
		return str.substring(0, str.length()-1);
	}
	/**
	 * 本地测试
	 * @return
	 */
	private static String input()
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("you say>");
		String input = "";
		try 
		{
			input = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;
	}
	
	public static void main(String[] args) throws Exception
	{
//		 while(true)
//		 {
//			 String input = ChatServer.input();
//			 if(ChatServer.END.equalsIgnoreCase(input))
//				 break;
//			 
//			 System.err.println("Alice>" + respond(input));
//		 }
		 System.err.println("Alice>" + respond("adf ",1));
	}
}
