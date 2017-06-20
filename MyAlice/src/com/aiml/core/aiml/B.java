package com.aiml.core.aiml;


import org.xml.sax.Attributes;



import com.aiml.core.Match;

public class B extends TemplateElement
{
  /*
  Constructors
  */

  public B(Attributes attributes)
  {
  }

  public B(Object... children)
  {
    super(children);
  }

  /*
  Methods
  */

  public String process(Match match)
  {
    return "";
  }
}