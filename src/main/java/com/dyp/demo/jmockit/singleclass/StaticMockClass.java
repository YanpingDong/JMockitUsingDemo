package com.dyp.demo.jmockit.singleclass;

public class StaticMockClass {
	
	private static String field = "field value";
	
	public static String publicStaticMethod(String arg)
	{
		return "publicStaticMethod: " + arg;
	}

	public static String publicStaticMethod()
	{
		return privateStaticMethod("publicStaticMetod invoke");
	}
	
	public static String getField()
	{
		return StaticMockClass.field;
	}
	
	private static String privateStaticMethod(String arg)
	{
		return "privateStaticMethod: " + arg;
	}
	
}
