package ftnt.qa.autotest.ui.framework.utils;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ftnt.qa.autotest.ui.framework.constants.CommonConstants;

public class EmailUtil {
	public static final String profilepath = CommonConstants.PROPEERTIES_FILE_PATH;

	private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	public static void main(String[] args) throws ConfigurationException {
		try {
			sendMsg("邮件正文为");
			 String path= "D:\\test1.xlsx";
			sendEmailsWithAttachments(path,"测试结果","请查收");
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendMsg(String content) throws EmailException, ConfigurationException {
		HtmlEmail mail = new HtmlEmail();
		String hostname  = CommonsPropertiesUtils.ReadSingleProperties(profilepath, "mail.host");
		String password = CommonsPropertiesUtils.ReadSingleProperties(profilepath, "mail.password");
		String username = CommonsPropertiesUtils.ReadSingleProperties(profilepath, "mail.username");
	    String[] toList = CommonsPropertiesUtils.ReadMultipleProperties(profilepath, "mail.touser");
	    String subject = CommonsPropertiesUtils.ReadSingleProperties(profilepath, "mail.subject");
	    
		mail.setHostName(hostname); // 邮件服务器域名
		//mail.setSmtpPort(smtpPort); // 邮件服务器smtp协议端口
		mail.setAuthentication(username, password); // 邮箱账户
		mail.setCharset("UTF-8"); // 邮件的字符集

		//mail.setSSLOnConnect(true); // 是否启用SSL
		//mail.setSslSmtpPort(sslSmtpPort); // 若启用，设置smtp协议的SSL端口号
 
		mail.setFrom(username); // 发件人地址
		for (String to : toList) {
			mail.addTo(to); // 收件人地址，可以设置多个
		}
		
		mail.setSubject(subject); // 邮件主题  
		mail.setHtmlMsg(content); // 邮件正文  
		String rString = mail.send(); // 发送邮件  
		logger.info("邮件发送成功："+rString);
	}
	
	 public static void sendEmailsWithAttachments(String title, String context, String... filepath) throws EmailException, ConfigurationException {

			String hostname  = CommonsPropertiesUtils.ReadSingleProperties(profilepath, "mail.host");
			String password = CommonsPropertiesUtils.ReadSingleProperties(profilepath, "mail.password");
			String username = CommonsPropertiesUtils.ReadSingleProperties(profilepath, "mail.username");
		    String[] toList = CommonsPropertiesUtils.ReadMultipleProperties(profilepath, "mail.touser");
		    
	        // Create the email message
		    HtmlEmail email = new HtmlEmail();
	        email.setHostName(hostname); // 邮件服务器域名
			//mail.setSmtpPort(smtpPort); // 邮件服务器smtp协议端口
	        email.setAuthentication(username, password); // 邮箱账户
	        email.setCharset("UTF-8"); // 邮件的字符集

			//mail.setSSLOnConnect(true); // 是否启用SSL
			//mail.setSslSmtpPort(sslSmtpPort); // 若启用，设置smtp协议的SSL端口号
	        email.setSubject(title);
	        email.setFrom(username); // 发件人地址
	        email.setHtmlMsg(context);
	        
	        for (String to : toList) {
	        	email.addTo(to); // 收件人地址，可以设置多个
			}
	        // add the attachment
	        for (String fp : filepath) {
		    	 EmailAttachment attachment = new EmailAttachment();
			        attachment.setPath(fp);
			        attachment.setDisposition(EmailAttachment.ATTACHMENT);
			        attachment.setDescription("测试结果");
			        File f = new File(fp);
			        attachment.setName(f.getName());
			        email.attach(attachment);
			}
	        // send the email
	        email.send();
	    }

}
