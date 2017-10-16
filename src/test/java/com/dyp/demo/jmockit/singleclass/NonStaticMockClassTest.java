package com.dyp.demo.jmockit.singleclass;

import org.junit.Test;

import com.dyp.demo.jmockit.singleclass.NonStaticMockClass;

import mockit.Deencapsulation;
import mockit.Delegate;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;


public class NonStaticMockClassTest {
	
	@Test
	public void publicStaticMethodByMockUpTest() {
		NonStaticMockClass o = new NonStaticMockClass();
		System.out.println(o.publicMethod("test"));
		new MockUp<NonStaticMockClass>() {
			@Mock
			public String publicMethod(String arg)
			{
				if("test".equalsIgnoreCase(arg))
					return "mocked publicMethod: " + arg;
				else
					return "mocked publicMethod: other info" ;
			}
		};
		
		System.out.println(o.publicMethod("test"));
		System.out.println(o.publicMethod("other"));
	}
	
	@Test
	public void publicMethodByDelegateTest() {
		final NonStaticMockClass o = new NonStaticMockClass();
		System.out.println(o.publicMethod("test"));
		new Expectations(o) {
			{
				o.publicMethod(anyString);
				//Delegate中的String指的是返回类型
				result = new Delegate<String>() {
					//方法名可以和要代理的名子不一样，但参数个数和类型要匹配
					@SuppressWarnings("unused")
					public String publicMethodNewName(String arg) {
						if("test".equalsIgnoreCase(arg))
							return "mocked publicMethod: " + arg;
						else
							return "mocked publicMethod: other info" ;
					}
				};
			}
		};

		System.out.println(o.publicMethod("test"));
		System.out.println(o.publicMethod("other"));	
	}
	
	//MockUp可以做私有方法Mock而Delegate不行
	@Test
	public void publicMethodInvokePrivateMothodTest() {
		final NonStaticMockClass o = new NonStaticMockClass();
		System.out.println(o.publicMethod());
		//影响该类所有实例
		new MockUp<NonStaticMockClass>() {
			//未mock函数不受影响
			@Mock
			private String privateMethod(String strArg, Integer intArg)
			{
				return "mocked privateMethod: " + strArg + "," + intArg;
			}
		};
		
		System.out.println(o.publicMethod());
		
	}
	
	@Test
	public void getFieldTest(){
		final NonStaticMockClass o = new NonStaticMockClass(); 
		System.out.println(o.getField());
		
		Deencapsulation.setField(o, "field", "mocked field value");
		
		System.out.println(o.getField());
	}

}
