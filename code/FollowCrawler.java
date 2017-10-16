package cn.edu.hfut.dmic.webcollector.weiboapi;  
  
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;  
import cn.edu.hfut.dmic.webcollector.model.Links;  
import cn.edu.hfut.dmic.webcollector.model.Page;  
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;  
  
/** 
 * 
 *获取用户的关注列表的爬虫，用于进行目标用户库的拓展
 
 */  
public class FollowCrawler extends DeepCrawler{
	public String self_id;
	public String ty;
  
    public FollowCrawler(String crawlPath,String UserId,String Type,String user) throws Exception {  
        super(crawlPath);  
    	self_id=UserId;
        //获取cookie 
        String cookie=WeiboCN.getSinaCookie(user, "pachong");  
        HttpRequesterImpl myRequester=(HttpRequesterImpl) this.getHttpRequester();  
        myRequester.setCookie(cookie);
       
        ty=Type;
    }  

    //解析用户的微博关注并通过正则匹配获取其关注列表中的用户id
    public Links visitAndGetNextLinks(Page page) {  
        String html= page.getHtml();
        List <String> list_string = MyRegex.getMatcherSubstrs(html, "uid=(\\d{10})",1);
       
		//对于用户关注列表中的用户，将抓取的id并写入文档
        for (int i = 0; i < list_string.size(); i++) {

        	try{
        	     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("E:\\Weibo\\"+ty+"-"+self_id+".txt"),true));
        	     if (!list_string.get(i).equals(self_id))
        	     {
        	    	 writer.write(list_string.get(i));
            	     writer.write("\r\n");  
        	     }
        	     writer.close();
  
        	     
        	}catch(Exception e){

        	     }
		}
        

        
    
        return null;  
    }  
      
  

//设置多线程同时爬取
    public void run() throws Exception{
    	this.setThreads(3);
    	for(int i=1;i<=20;i++){  
          this.addSeed("http://weibo.cn/"+self_id+"/"+ty+"?vt=4&page="+i);  
    	}
    	this.start(1);
    }
    
    
      
}  