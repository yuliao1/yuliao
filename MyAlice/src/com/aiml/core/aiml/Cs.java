package com.aiml.core.aiml;

import org.xml.sax.Attributes;

import com.aiml.core.Match;

public class Cs extends TemplateElement {
	public Cs() {
	}

	public Cs(Attributes attributes) {
	}

	public int hashCode() {
		return 131072;
	}

	public String process(Match match) {
		return "我在测试自定义标签，哈哈。";
	}

}
