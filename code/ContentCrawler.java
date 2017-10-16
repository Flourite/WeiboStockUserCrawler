package cn.edu.hfut.dmic.webcollector.weiboapi;

/*
 * ��ȡ�û�΢�����ݵ�����
   ���Ƚ��е�½���ڶ�������Ŀ���û�������΢��ҳ�沢������ץȡ
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
		// ͨ������cookie���е�½
		String cookie=WeiboCN.getSinaCookie("13307130009@fudan.edu.cn", "pachong");  
        HttpRequesterImpl myRequester=(HttpRequesterImpl) this.getHttpRequester();  
       myRequester.setProxy(Config.IP, Config.PORT);//�������л�IP

        myRequester.setCookie(cookie);
        self_id=UserId;
        texts="";
   
	
	@Override
	//����ҳ�沢ץȡ΢��
	public Links visitAndGetNextLinks(Page page) {
		
		Elements weibos=page.getDoc().select("div.c");  
	        for(Element weibo:weibos){  
//	            System.out.println(weibo.text()); 
//	        	texts.add(weibo.text());
	        	texts+=weibo.text();
	        }
		return null;
	}
	
	//Ҫ��ȡ���ݵ�Ŀ���û��Ľ���
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
