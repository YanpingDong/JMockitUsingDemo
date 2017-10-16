package com.dyp.demo.jmockit.singleclass;

import org.junit.Test;

import com.dyp.demo.jmockit.singleclass.StaticMockClass;

import mockit.Deencapsulation;
import mockit.Delegate;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;

public class StaticMockClassTest {

	@Test
	public void publicStaticMethodByMockUpTest() {
		System.out.println(StaticMockClass.publicStaticMethod("test"));
		new MockUp<StaticMockClass>() {
			@Mock
			public String publicStaticMethod(String arg)
			{
				if("test".equalsIgnoreCase(arg))
					return "mocked publicStaticMethod: " + arg;
				else
					return "mocked publicStaticMethod: other info" ;
			}
		};
		
		System.out.println(StaticMockClass.publicStaticMethod("test"));
		System.out.println(StaticMockClass.publicStaticMethod("other"));
	}
	
	@Test
	public void publicMethodByDelegateTest() {
		
		System.out.println(StaticMockClass.publicStaticMethod());
		new Expectations(StaticMockClass.class) {
			{
				StaticMockClass.publicStaticMethod(anyString);
				result = new Delegate<String>() {
					@SuppressWarnings("unused")
					public String publicStaticMethodNewName(String arg) {
						if("test".equalsIgnoreCase(arg))
							return "mocked publicStaticMethod: " + arg;
						else
							return "mocked publicStaticMethod: other info" ;
					}
				};
			}
		};

		System.out.println(StaticMockClass.publicStaticMethod("test"));
		System.out.println(StaticMockClass.publicStaticMethod("other"));	
	}
	
	@Test
	public void publicStaticMethodInvokePrivateStaticMothodTest(){
		
		System.out.println(StaticMockClass.publicStaticMethod());
		new MockUp<StaticMockClass>() {
			@Mock
			private String privateStaticMethod(String arg)
			{
				return "mocked privateStaticMethod: " + arg;
			}
		};
		
		System.out.println(StaticMockClass.publicStaticMethod());
	}
	
	@Test
	public void getFieldTest(){
		System.out.println(StaticMockClass.getField());
		Deencapsulation.setField(StaticMockClass.class, "field", "mocked field value");
		
		System.out.println(StaticMockClass.getField());
	}

}
