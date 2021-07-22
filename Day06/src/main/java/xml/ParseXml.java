package xml;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/22 14:16
 * @Description:
 */
public class ParseXml {

	public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
		/*
		 */
		String path = ParseXml.class.getClassLoader().getResource("").getPath();

		System.out.println(path);

		File file = new File(path + "employee.xml");

		//1.通过Jsoup框架将xml解析成一个Dom树对象
		Document document = Jsoup.parse(file, "UTF-8");
		//System.out.println(document);

		//2.将document树对象转换成一个能被XPath语法解析的树结构
		JXDocument doc = new JXDocument(document);

		//3.使用XPath语法来解析
		List<JXNode> nodes = doc.selN("//emp[@id=WNSH0001]/name/text()");
		System.out.println(nodes.size());
		System.out.println(nodes);
		String name = doc.selN("//emp[@id=WNSH0001]/name/text()").get(0).toString();
		String gender = doc.selN("//emp[@id=WNSH0001]/gender/text()").get(0).toString();
		String age = doc.selN("//emp[@id=WNSH0001]/age/text()").get(0).toString();
		System.out.println(name + "" + gender + "" + age);


	}

}
