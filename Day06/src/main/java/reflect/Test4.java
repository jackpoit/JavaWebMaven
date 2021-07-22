package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: rua
 * @Date: 2021/7/22 16:26
 * @Description: 重重点:通过反射技术来获取方法,并执行方法
 */

public class Test4 {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		//1.获取字节码对象
		Class<?> clazz = Class.forName("com.woniuxy.entity.User");
		//2.查看类中的所有方法
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method);
		}
		//3. 如何执行指定的方法
		//3.1 先要找到这个方法  show()
//		clazz.getDeclaredMethod(方法名,形参的字节码列表);
		//java.lang.NoSuchMethodException  方法没有找到

		Method show = clazz.getDeclaredMethod("show");
		//如何动态运行这个方法语法:Object 返回值=方法对象.invoke(对象,实参列表);
		Object obj = clazz.newInstance(); //实例对象
		Object result = show.invoke(obj);
		//动态执行该方法并返回方法执行后的结果
		//若result返回null,则表示方法的返回值类型是void
		System.out.println("show方法的返回值:"+result);

		Method add = clazz.getDeclaredMethod("add", int.class, int.class);

		add.setAccessible(true);
		Object result2 = add.invoke(obj, 1, 2);
		System.out.println("add方法的返回值:"+result2);

		//不能用包装类  不会进行自动装箱


	}
}
