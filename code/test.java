package cn.edu.hfut.dmic.webcollector.weiboapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/** 
 * 
 *反爬虫机制设置：通过5个账号循环爬取实现
 
 */ 
public class test {
	public static void main(String[] args) throws Exception{
		String seed_user =null;
		String gumin=null;
		int k = 2;
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\Weibo\\chong.txt")));
		while ((gumin=br2.readLine())!=null)
		{   
			int a = k%250;
			if ((k-1)%50==0){
				Thread.sleep(100000);
			}
			
			
			if ((a>=1)&&(a<=50)){
		        System.out.println("2230193393@qq.com is working.");
		        System.out.println(a);
				BreadCrawler2 crawler1 = new BreadCrawler2("/home/hu/data/wb",true,"2230193393@qq.com",gumin);
				 crawler1.write();
			}

			
			
				if ((a>=51)&&(a<=100)){
					System.out.println(a);
					System.out.println("553533930@qq.com is working.");
					BreadCrawler2 crawler2 = new BreadCrawler2("/home/hu/data/wb",true,"553533930@qq.com",gumin);
					 crawler2.write();
				
				}
				 
				if ((a>=101)&&(a<=150)){
					System.out.println("3171914127@qq.com is working.");
					System.out.println(a);
					BreadCrawler2 crawler3 = new BreadCrawler2("/home/hu/data/wb",true,"3171914127@qq.com",gumin);
					 crawler3.write();
				
				}
				
				if ((a>=151)&&(a<=200)){
					System.out.println("13307130009@fudan.edu.cn is working.");
					System.out.println(a);
					BreadCrawler2 crawler4 = new BreadCrawler2("/home/hu/data/wb",true,"13307130009@fudan.edu.cn",gumin);
					 crawler4.write();
				
				}
				
			if ((a>=201)&&(a<=249)||(a==0)){	
				System.out.println("617613950@qq.com is working.");
				System.out.println(a);
				BreadCrawler2 crawler5 = new BreadCrawler2("/home/hu/data/wb",true,"617613950@qq.com",gumin);
				 crawler5.write();
			
			}
			
			
	
		
			
			k++;
	
		}
		br2.close();
	}

		
		
		
	
		
	
		

//		 System.out.println(crawler.get_fans());
	}
	

