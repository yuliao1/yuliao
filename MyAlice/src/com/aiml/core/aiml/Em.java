package com.aiml.core.aiml;


import org.xml.sax.Attributes;



import com.aiml.core.Match;


public class Em extends TemplateElement
{
  /*
  Constructors
  */

  public Em(Attributes attributes)
  {
  }

  public Em(Object... children)
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