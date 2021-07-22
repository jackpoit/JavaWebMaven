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
