package ftnt.qa.autotest.ui.framework.utils;


/**
 * @author xin.wang
 * 获取测试图片地址
 */

public class PictureUtil {
	
	private static String baseURL;

	public static  void setBaseUrl(String baseURL){
	       PictureUtil.baseURL = baseURL;
	}
	
	public static String getPicUrl(String picName){
		return baseURL+picName;
	}
}