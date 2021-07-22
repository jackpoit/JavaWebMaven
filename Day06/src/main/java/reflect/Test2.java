package reflect;

import com.woniuxy.entity.User;

/**
 * @Author: rua
 * @Date: 2021/7/22 15:43
 * @Description: 通过字节码对象创建一个实例
 */
public class Test2 {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//1.获取字节码对象
		Class<?> clazz = Class.forName("com.woniuxy.entity.User");
		//2.通过字节码对象来创建实例对象    (new的底层)
		Object obj = clazz.newInstance();
		//默认调用无参构造初始化对象
		//构造方法是用来初始化的 不是用来创建对象的
		//一定要给类写无参构造
		if (obj instanceof User){
			User u= (User) obj;
		}
		System.out.println(obj);


	}
}
