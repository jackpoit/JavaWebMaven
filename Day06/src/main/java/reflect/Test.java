package reflect;

import com.woniuxy.entity.User;

/**
 * @Author: rua
 * @Date: 2021/7/22 15:27
 * @Description: 如何获取到一个类的字节码对象
 */
public class Test {
	public static void main(String[] args) {
		//标准的创建对象
//		User user = new User();

		//如何通过字节码对象来创建对象?
		//泛型中的?是高级通配符,表示任意数据类型

		try {
			//高频使用
			Class<?> clazz1 = Class.forName("com.woniuxy.entity.User");
			//中频
			Class<?> clazz2 = User.class; //可以当锁用  是对象且唯一
			//低频
			User user = new User();
			Class<? extends User> clazz3 = user.getClass();

			System.out.println(clazz1);
			System.out.println(clazz2);
			System.out.println(clazz1==clazz2);
			System.out.println(clazz1==clazz3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
