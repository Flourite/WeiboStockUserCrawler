package cn.edu.hfut.dmic.webcollector.weiboapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;

/*
 * 参数的设置：包括保存路径，IP，微博文本分析的关键词
 */
public class Config {
	public static String URL = "/home/hu/data/weibo";
	public static String IP = "182.201.249.134";
	public static int PORT = 8090;
	public List<String> users;
	
	
	public  List<String> highfre;
	public Config() {
		 users=new ArrayList<String>();
		 users.add("3171914127@qq.com");
		 users.add("3039624420@qq.com");
		// TODO Auto-generated constructor stub
		 highfre=new ArrayList<String>();
		 highfre.add("股");
		 highfre.add("盘");
		 highfre.add("多");
		 highfre.add("空");
		 highfre.add("散");
		 highfre.add("票");
		 highfre.add("涨");
		 highfre.add("停");
		 highfre.add("板");
		 highfre.add("跌");
		 highfre.add("成");
		 highfre.add("交");
		 highfre.add("量");
		 highfre.add("点");
		 highfre.add("仓");
		 highfre.add("位");
		 highfre.add("证");
		 highfre.add("券");
		 highfre.add("市");
		 highfre.add("场");
		 highfre.add("牛");
		 highfre.add("熊");
		 highfre.add("买");
		 highfre.add("卖");
		 highfre.add("抛");
		 highfre.add("抄");
		 highfre.add("底");
		 highfre.add("指");
		 highfre.add("逃");
		 highfre.add("数");
		 highfre.add("顶");
		 highfre.add("底");
		 highfre.add("割");
		 highfre.add("肉");
		 highfre.add("止");
		 highfre.add("损");
		 highfre.add("盈");
		 highfre.add("赚");
		 highfre.add("钱");
		 highfre.add("亏");
		 highfre.add("机构");
		 highfre.add("国家队");
		 highfre.add("散户");
		 highfre.add("新股");
		 highfre.add("牛股");
		 highfre.add("牛市");
		 highfre.add("熊市");
		 highfre.add("A股");
		 highfre.add("证金");
		 highfre.add("救市");
		 highfre.add("高送转");
		 highfre.add("主板");
		 highfre.add("创业板");
		 highfre.add("中小板");
		 highfre.add("蓝筹");
		 highfre.add("股灾");
		 highfre.add("顶");
	}
	

		

		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	


