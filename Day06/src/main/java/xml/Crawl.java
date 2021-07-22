package xml;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/22 14:37
 * @Description:
 */

/**
 * 爬虫过程
 * 1.(数据挖掘) 发送请求获取到响应正文
 * 2.(数据清洗) 通过语法保留自己想要的数据
 * 3.(数据持久化) : 数据库  CSV
 * 4.(数据分析) : 专业的数据分析工具来建模,图表呈现
 *
 *
 */

public class Crawl {
	public static void main(String[] args) throws IOException, XpathSyntaxErrorException {

		//简易版爬虫程序 JSoup+XPath
		String url="https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html";

		//1.使用JSoup发送请求到服务器获取到响应正文后,转换成一个DOM对象
		Connection conn = Jsoup.connect(url); //客户端与服务器建立连接
		Document document = conn.get(); //获取响应正文
//		System.out.println(document);

		//2.数据清洗 通过nXPath来解析
		JXDocument doc=new JXDocument(document);
		List<JXNode> nodes = doc.selN("//div[@class=pcon]/div[@class=ht]/a");
		for (JXNode a : nodes) {
			String href = a.sel("/@href").get(0).toString();
			String city = a.sel("/text()").get(0).toString();
			System.out.println(href+":"+city);

		}


	}
}
