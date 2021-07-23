package reflect;

import com.woniuxy.entity.User;

import java.lang.reflect.Field;

/**
 * @Author: rua
 * @Date: 2021/7/22 15:50
 * @Description: 通过字节码对象来获取类中的所有属性
 */
public class Test3 {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
		//1.获取字节码
		Class<?> clazz = Class.forName("com.woniuxy.entity.User");
		//2.获取所有属性 (属性都被封装成了Field对象)

//		Field[] fields = clazz.getFields(); //默认获取的是public修饰的属性

		Field[] fields = clazz.getDeclaredFields();
		//忽略一切访问修饰符来获取属性

		for (Field field : fields) {
			System.out.println(field);
		}

		//3. @important 通过反射如何动态的给对象的属性赋值
		   //1.找到需要赋值的属性
		Field password = clazz.getDeclaredField("password");
		//java.lang.NoSuchFieldException

		//解释:属性.set(实例对象,值);
		Object obj = clazz.newInstance();
		password.setAccessible(true);
		//只要使用了Declared,就要关闭程序对修饰符的自检

//		password.set(obj,123456);
		password.set(obj,"123456");

		//set内部会自动强转


		User u=(User) obj;
		System.out.println(u);

		//反射的应用场景:传统的面向对象解决不了的问题,通过反射来解决

	}
}
