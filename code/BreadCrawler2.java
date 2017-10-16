package cn.edu.hfut.dmic.webcollector.weiboapi;
import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;

import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;
import cn.edu.hfut.dmic.webcollector.net.Proxys;
import cn.edu.hfut.dmic.webcollector.net.RandomProxyGenerator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.HTML;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/** 
 *核心爬虫：
 *获取目标用户的微博内容并进行文本分析
  首先获取目标用户的前5页微博并写入文档
  然后对文档进行文本分析，统计与股票有关的关键词的词频
  最后写入文档保存
 
 */ 

public class BreadCrawler2 extends BreadthCrawler {
	//初始化
		public String texts=null;
		public String text=null;
		public String html="";
		public String fans=null;
		public String selfid=null;
	//获取微博页面
	 public void visit(Page page, Links nextLinks){
		this.html+= page.getHtml();
		}
	//获取目标用户的前5页微博内容
	 public BreadCrawler2(String crawlPath, boolean autoParse,String userid,String gumin) throws Exception {
		   
	        super(crawlPath, autoParse);
	        selfid=gumin;
	        String cookie=WeiboCN.getSinaCookie(userid, "pachong");
	        HttpRequesterImpl myRequester=(HttpRequesterImpl) this.getHttpRequester();  
	        myRequester.setCookie(cookie);
	        this.setThreads(50);
	        for (int i = 1; i <= 5; i++) {
				this.addSeed("http://weibo.cn/"+gumin+"?page="+i);
			}
	        this.setResumable(false);
	        this.setTopN(50);

	        /*如果希望尽可能地爬取，这里可以设置一个很大的数，爬虫会在没有待爬取URL时自动停止*/
	        this.start(50);
	        text="";
	        texts="";
	        fans="";	 
	 }
	 
	 
	 public String get_text(){
		return texts;
	 }
	 
	 public String get_html() {
		 return html;
		 
		
	}
	 //获取目标用户的粉丝数，用于剔除僵尸号等噪音用户
	 public String get_fans(){
		 Pattern p4=Pattern.compile("粉丝\\[(.*?)\\]");
		 Matcher m4=p4.matcher(html);
		 while (m4.find()){
			   fans=m4.group(1);
		   }
		return fans;
		 
	 }
	 //对于微博用户的发言进行文本分析，统计与股票相关的关键词的出现频次并写入文档
	 public void write() {
		 Pattern p=Pattern.compile("<span class=\"ctt\">([\\s\\S]*?)</span>"); 
//		 System.out.println(html);	
		 Matcher m=p.matcher(html); 
		   while (m.find()){
			   text=m.group(1);
			   Pattern p2=Pattern.compile("([\\s\\S]*?已关注)"); 
			   	 Matcher m2=p2.matcher(text); 
			   	String string = m2.replaceAll(""); 
//			   	System.out.println(string);
			   	Pattern pattern = Pattern.compile("<.*?>");
			   	Matcher matcher = pattern.matcher(string);
			   	String string2 = matcher.replaceAll("");
//			   	System.out.println(string2); 
			   	texts+=string2;
		   } 

	   	

//		 System.out.println(texts); 
		 BufferedWriter writer;
		 try {
			 writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E://Weibo_data//Weibos//weibo_content"+selfid+".txt",true)));
			 writer.write(texts);
			 writer.close();
		 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		
		 
		 Config config1 = new Config();
		 BufferedWriter writer1;
		try {
			writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E://Weibo_data//data_test33.txt",true)));
			writer1.write(selfid);
			writer1.write(" ");
			for (int i = 0; i < config1.highfre.size(); i++) {
				int app = Tools.Char_No(texts, config1.highfre.get(i));
				float f = (float)app/texts.length();
				
				f*=100;
				
				writer1.write(" ");
				writer1.write(f+"");
				writer1.write(" ");
				
			}
		    writer1.write(String.valueOf(texts.length()));
		    writer1.write(" ");
		    writer1.write(get_fans());
			writer1.write("\r\n");
			
//			writer.write("\r\n");
			writer1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}












