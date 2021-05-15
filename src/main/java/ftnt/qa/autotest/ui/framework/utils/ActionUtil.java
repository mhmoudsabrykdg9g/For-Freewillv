package ftnt.qa.autotest.ui.framework.utils;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 
 * @author Lei.Wu
 * 模拟 鼠标，键盘的操作
 */

public class ActionUtil {
	
	private static Actions act;
	
	public static void setAction(WebDriver driver){
		act = new Actions(driver);
	}
	
	public static Actions getActionObj(){
		return act;
	}
	
	public static void SEND_TAB_KEY(){
		act.sendKeys(Keys.TAB).perform();
	}
	
	public static void SEND_BACKSPACE_KEY(){
		act.sendKeys(Keys.BACK_SPACE).perform();
	}
	
	public static void SEND_SPACE_KEY(){
		act.sendKeys(Keys.SPACE).perform();
	}
	
	public static void MOUSE_LEFT_CLICK(){
		act.click().perform();
	}
	
	public static void MOUSE_LEFT_CLICK(WebElement e){
		act.click(e).perform();
	}
	
	public static void MOUSE_DOUBLE_CLICK(WebElement e){
		act.doubleClick(e).perform();
	}
	
	public static void MOUSE_DOUBLE_CLICK(){
		act.doubleClick().perform();
	}
}