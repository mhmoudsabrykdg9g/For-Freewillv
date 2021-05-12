package ftnt.qa.autotest.ui.framework.utils;

import org.openqa.selenium.Cookie;

/**
 * @author Lei.Wu
 * 保存登录后返回的COOKIE
 */

public class CookiesUtil {
	
	private static Cookie ck = null;

	public static Cookie getCk() {
		return ck;
	}

	public static void setCk(Cookie ck) {
		CookiesUtil.ck = ck;
	}
}