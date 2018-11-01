/**
 * 
 */
package cn.owntt.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Monkey
 * @version 创建时间：2018年8月9日 上午9:40:46
 * 类说明
 */
/**
 * @author Monkey
 * @version 创建时间：2018年8月9日 上午9:40:46
 * 类说明
 */
public class CharRegExUtil {
	
	/**
	 * 正则替换所有特殊字符
	 * @param orgStr
	 * @return
	 */
	public static String replaceSpecStr(String orgStr){
		if (null!=orgStr&&!"".equals(orgStr.trim())) {
		  //String regEx="[\\s~·`!！@#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。.》>、/？?]";
			
			//保留-_避免音乐名称出现
			String regEx="[\\s~·`!！@#￥$%^……&*（()）\\" + "\\" + "=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。.》>、/？?]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(orgStr);
			return m.replaceAll("");
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(replaceSpecStr("asdasd!!@#!\\xF0\\x9F"));
		System.out.println(replaceSpecStr("asdasdsad撒的撒的阿1---_"));
	}
}
