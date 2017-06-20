package com.aiml.core.aiml;


import org.xml.sax.Attributes;



import com.aiml.core.Match;

/**
 * @author Administrator
 *
 */
public class A extends TemplateElement
{
  /*
  Constructors
  */
  public A(Attributes attributes)
  {
  }

  public A(Object... children)
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