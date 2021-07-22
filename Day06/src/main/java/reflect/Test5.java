package reflect;

/**
 * @Author: rua
 * @Date: 2021/7/22 16:07
 * @Description:
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 面试:泛型擦除现象
 */
public class Test5 {
	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		ArrayList<String> list = new ArrayList<>();
		list.add("admin");
		list.add("jack");
		list.add("tom");

		//请问能否在集合中添加一个int 10 boolean true

		//编译期实现不了,那么在运行期来干预
		// (反射: 字节码对象)

		//反射的前提:获取字节码
		Class<? extends ArrayList> clazz = list.getClass();
		Method add = clazz.getDeclaredMethod("add", Object.class);
		add.setAccessible(true);
		add.invoke(list,10);
		add.invoke(list,true);

		Object s = list.get(4);
		System.out.println(s);

		System.out.println(list);

		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			System.out.println(obj);
			//System.out.println(list.get(i));
			//不能直接打印  底层会调用 println(String) 这个方法 会报错
		}


	}
}
