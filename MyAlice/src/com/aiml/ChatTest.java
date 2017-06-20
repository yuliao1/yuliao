package com.aiml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.aiml.core.AliceBot;

public class ChatTest 
{
	public static final String END = "bye";
	
	public static String input()
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
//		 AliceBotMother mother = new AliceBotMother();		  
//	     mother.setUp();
//	     AliceBot bot = mother.newInstance();
//	     System.err.println("Alice>" + bot.respond("welcome"));
//		 while(true)
//		 {
//			 String input = ChatTest.input();
//			 if(ChatTest.END.equalsIgnoreCase(input))
//				 break;
//			 
//			 System.err.println("Alice>" + bot.respond(input));
//		 }
		 
	}
}
