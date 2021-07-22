package reflect;

import com.woniuxy.annotation.WebServlet;

/**
 * @Author: rua
 * @Date: 2021/7/22 19:16
 * @Description:
 */


//@WebServlet(value = "/add")  //当注解只有一个属性value时,可以省略value
@WebServlet({"/addd","/add"})
public class MyServlet {
	public void doGet(){
		System.out.println("执行了doGet");
	}
	public void doPost(){
		System.out.println("执行了doPost");
	}


}
