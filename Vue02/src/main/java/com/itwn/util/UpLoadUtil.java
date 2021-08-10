package com.itwn.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class UpLoadUtil {
	private final static String SERVER_PATH="D:/upload/images/";
	/**
	 * @description 文件上传 
	 * @param fileName 上传文件表单的name值
	 * @param fileDir  上传文件至服务器的目录名字
	 * @param req 请求对象
	 **/
	public static String up(String fileName,String fileDir,HttpServletRequest req)throws ServletException, IOException  {
		Part part=req.getPart(fileName);
		//1.保证有文件对象
		if(part!=null&& part.getSize()!=0) {
			//2.上传文件名字
			String name=part.getSubmittedFileName();
			//3.保证上传至服务器文件名字唯一
			name=UUID.randomUUID().toString().replace("-", "")+name;
			//5.得到上传文件值服务器的全路径
			String path=SERVER_PATH+"/"+fileDir+"/"+name;
			//6.开始上传
			part.write(path);
			return "upload/"+fileDir+"/"+name;
		}
		return null;
	}
	/**
	 * @description 下载
	 * @param loadParamName 下载超链接参数名字
	 * @param fileDir 下载文件所在的服务器目录
	 * @param req  请求对象
	 * @param resp 响应对象
	 * */
	public static void load(String loadParamName,String fileDir,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//1.得到下载文件名
		String fileName=req.getParameter(loadParamName);
		//2.得到下载文件的全路径名
		String loadPath=req.getServletContext().getRealPath("/"+fileDir+"/"+fileName);
		
		//设置弹出框编码格式
		fileName=URLEncoder.encode(fileName,"UTF-8");
		//3. 弹出下载框
		resp.setHeader("content-disposition", "attachment;filename="+fileName);
		//4.文件读写操作
		InputStream ins=new FileInputStream(loadPath);
		OutputStream out=resp.getOutputStream();
		byte[] bs=new byte[1024];
		int temp=-1;
		while((temp=ins.read(bs))!=-1) {
			out.write(bs,0,temp);
		}
		ins.close();
		out.close();
	}
	
	/***
	 * @description 将数值字符串分割成数值数组
	 * @param ids  
	 * */
	public static int[] strToArray(String ids) {
		String[] idStrs=ids.split(",");
		int[] arr=new int[idStrs.length];
		for(int i=0;i<arr.length;i++) {
			arr[i]=Integer.parseInt(idStrs[i]);
		}
		return arr;
	}
	
	/**
	 * @description 批量导入数据 
	 * @param fileName 文件name值
	 * @param cla 对应实体类Class对象
	 * @param req 
	 * */
	public static <T>List<T> getList(String fileName,Class<T> cla,HttpServletRequest req)throws ServletException, IOException{
		Part part=req.getPart(fileName);
		//1.必须是.csv文件才能批量导入
		if(part!=null&&part.getSize()>0&&part.getSubmittedFileName().endsWith(".csv")) {
			List<String> listStr=new ArrayList<String>();
			//字节流===>字符流===> 字符缓冲流
			BufferedReader reader=new BufferedReader(new InputStreamReader(part.getInputStream()));
			String temp=null;
			while((temp=reader.readLine())!=null) {
				listStr.add(temp);
			}
			reader.close();
			List<T> list=new ArrayList<T>();
			String[] fieldNames=listStr.get(0).split(",");
			for(int i=1;i<listStr.size();i++) {
				String[] userStr=listStr.get(i).split(",");
				T obj=null;
				try {
					obj = cla.newInstance();
					for(int j=0;j<fieldNames.length;j++) {
						Field f=cla.getDeclaredField(fieldNames[j]);
						f.setAccessible(true);
						f.set(obj, userStr[j]);
					}
					list.add(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
			return list;
		}
		return null;
	}
}
