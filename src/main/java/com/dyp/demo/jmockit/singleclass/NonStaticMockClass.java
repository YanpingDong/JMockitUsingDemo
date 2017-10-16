package com.dyp.demo.jmockit.singleclass;

public class NonStaticMockClass {
	
	private String field = "field value";
	
	public String publicMethod(String arg)
	{
		return "publicStaticMethod: " + arg;
	}
	
	public String publicMethod()
	{
		return this.privateMethod("abc", 123);
	}
	
	private String privateMethod(String strArg, Integer intArg)
	{
		return "privateMethod: " + strArg + "," + intArg;
	}
	
	public String getField()
	{
		return field;
	}
}
