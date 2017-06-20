package com.aiml;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.aiml.core.AliceBot;
import com.aiml.core.Context;
import com.aiml.core.parser.AliceBotParser;
import com.aiml.core.parser.AliceBotParserConfigurationException;
import com.aiml.core.parser.AliceBotParserException;
import com.aiml.core.util.Searcher;

public class AliceBotMother {
	private static AliceBot bot ;
	private static ByteArrayOutputStream gossip;

	public void setUp() {
		gossip = new ByteArrayOutputStream();
	}

	public String gossip() {
		return gossip.toString();
	}

	public static AliceBot newInstance(String path){
		Searcher searcher = new Searcher();
		System.out.println("context:"+path);
		try {
			AliceBotParser parser = new AliceBotParser();
			bot = parser.parse(
					new FileInputStream(path+"/Bots/context.xml"),
					new FileInputStream(path+"/Bots/splitters.xml"), 
					new FileInputStream(path+"/Bots/substitutions.xml"),
					searcher.search(
							path+"/Bots/mydomain", ".*\\.aiml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (AliceBotParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AliceBotParserConfigurationException e) {
			e.printStackTrace();
		}

		Context context = bot.getContext();
		context.outputStream(gossip);
		System.out.println("词典加载完毕");
		return bot;
	}

	public static AliceBot getTheBot() {
		return bot;
	}
}
