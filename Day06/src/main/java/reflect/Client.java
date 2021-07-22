package reflect;

import com.woniuxy.annotation.WebServlet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: rua
 * @Date: 2021/7/22 19:30
 * @Description:
 */
public class Client {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		String href="add";
		String methodType="doGet";
		Class<MyServlet> clazz = MyServlet.class;    //获得MyServlet类对象
		if (clazz.isAnnotationPresent(WebServlet.class)){
			MyServlet obj = clazz.newInstance();// 获得MyServlet类实例
			WebServlet anno = clazz.getDeclaredAnnotation(WebServlet.class); //获得注解类对象
			//1.此处得到的anno是绑定clazz的 注解对象吗
			//2.接口的类对象 ? 是将写的值("/add")存为属性吗
			//3.value() 抽象方法的实质 是直接返回值吗
			String[] value = anno.value();
			for (String url : value) {
				if (("/"+href).equals(url)){
					//根据请求来找具体的方法
					try {
						Method method = clazz.getDeclaredMethod(methodType);
						method.setAccessible(true);
						method.invoke(obj);
					}catch (NoSuchMethodException | InvocationTargetException e){
						e.printStackTrace();
					}
				}else {
					System.out.println("没有找到");
				}
			}
		}

	}
}
