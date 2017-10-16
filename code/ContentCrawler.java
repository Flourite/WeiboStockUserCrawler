package cn.edu.hfut.dmic.webcollector.weiboapi;

/*
 * 获取用户微博内容的爬虫
   首先进行登陆，第二步对于目标用户，解析微博页面并将内容抓取
 */
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

public class ContentCrawler extends DeepCrawler{
	private String self_id;
	public String texts;

	public ContentCrawler(String crawlPath,String UserId) throws Exception {
		super(crawlPath);
		// 通过填入cookie进行登陆
		String cookie=WeiboCN.getSinaCookie("13307130009@fudan.edu.cn", "pachong");  
        HttpRequesterImpl myRequester=(HttpRequesterImpl) this.getHttpRequester();  
       myRequester.setProxy(Config.IP, Config.PORT);//可设置切换IP

        myRequester.setCookie(cookie);
        self_id=UserId;
        texts="";
   
	
	@Override
	//解析页面并抓取微博
	public Links visitAndGetNextLinks(Page page) {
		
		Elements weibos=page.getDoc().select("div.c");  
	        for(Element weibo:weibos){  
//	            System.out.println(weibo.text()); 
//	        	texts.add(weibo.text());
	        	texts+=weibo.text();
	        }
		return null;
	}
	
	//要爬取内容的目标用户的建立
	public void run() throws Exception{
		this.setThreads(1);
		for (int i = 1; i <= 5; i++) {
			this.addSeed("http://weibo.cn/u/"+self_id+"?page="+i);
		}
		
		this.start(1);
	}
	
	public String get_text()
	{
		return texts;
		
	}
}
