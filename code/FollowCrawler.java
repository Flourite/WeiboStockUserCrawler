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
 *��ȡ�û��Ĺ�ע�б�����棬���ڽ���Ŀ���û������չ
 
 */  
public class FollowCrawler extends DeepCrawler{
	public String self_id;
	public String ty;
  
    public FollowCrawler(String crawlPath,String UserId,String Type,String user) throws Exception {  
        super(crawlPath);  
    	self_id=UserId;
        //��ȡcookie 
        String cookie=WeiboCN.getSinaCookie(user, "pachong");  
        HttpRequesterImpl myRequester=(HttpRequesterImpl) this.getHttpRequester();  
        myRequester.setCookie(cookie);
       
        ty=Type;
    }  

    //�����û���΢����ע��ͨ������ƥ���ȡ���ע�б��е��û�id
    public Links visitAndGetNextLinks(Page page) {  
        String html= page.getHtml();
        List <String> list_string = MyRegex.getMatcherSubstrs(html, "uid=(\\d{10})",1);
       
		//�����û���ע�б��е��û�����ץȡ��id��д���ĵ�
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
      
  

//���ö��߳�ͬʱ��ȡ
    public void run() throws Exception{
    	this.setThreads(3);
    	for(int i=1;i<=20;i++){  
          this.addSeed("http://weibo.cn/"+self_id+"/"+ty+"?vt=4&page="+i);  
    	}
    	this.start(1);
    }
    
    
      
}  