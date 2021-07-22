package reflect;

import com.woniuxy.annotation.MyAnnotation;
import com.woniuxy.entity.User;

/**
 * @Author: rua
 * @Date: 2021/7/22 19:05
 * @Description:
 */
public class AnnotationTest {
	public static void main(String[] args) {

		//反射来解析类,属性和方法上的注解
		Class<User> clazz = User.class;
		//1.判断类上是否存在注解
		if (clazz.isAnnotationPresent(MyAnnotation.class)){
			//若存在就返回这个类上的注解
			MyAnnotation annotation = clazz.getDeclaredAnnotation(MyAnnotation.class);
			String value = annotation.value();
			System.out.println(value);
		}else {
			System.out.println("没有找到");
		}

	}
}
