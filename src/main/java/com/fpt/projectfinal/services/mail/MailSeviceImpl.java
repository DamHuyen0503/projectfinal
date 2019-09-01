package com.fpt.projectfinal.services.mail;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fpt.projectfinal.daos.category.CategoryDao;
import com.fpt.projectfinal.daos.subscriber.SubscriberDao;
import com.fpt.projectfinal.models.Category;
import com.fpt.projectfinal.models.Post;
import com.fpt.projectfinal.models.Subscriber;
@Service
public class MailSeviceImpl implements MailService {

	@Autowired
	SubscriberDao subscriberDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@SuppressWarnings("unused")
	public String AutoSendMail(int categoryID) throws EmailException, MalformedURLException {
		List<Subscriber> listSubscriber = subscriberDao.listSubscriberByCategory(categoryID);
		String email_from = "huyendt0503@gmail.com";
		String password = "huyen@0503";
		  for (Subscriber sub : listSubscriber) {
	           String htmlEmailTemplate = "<h2>Hello!</h2>"
	                   +"This is Apache Logo <br/>"
	                   +"<img src='proper/commons-email/uploadOneFile/commons-logo.png'/>";            
	           ImageHtmlEmail email = new ImageHtmlEmail();
	           email.setHostName("smtp.googlemail.com");
	           email.setSmtpPort(465);
	           email.setAuthenticator(new DefaultAuthenticator(email_from, password));
	           email.setSSLOnConnect(true);
	           email.setFrom(email_from, "TamLyHocHanhPhuc");
	           email.addTo(sub.getEmail());
	           email.setSubject("TamLyHocHanhPhuc upload new post");
	           URL url = new URL("http://commons.apache.org");
	           email.setDataSourceResolver(new DataSourceUrlResolver(url) );
	           email.setHtmlMsg(htmlEmailTemplate);
	           email.setTextMsg("Your email client does not support HTML messages");
	           email.send();
	           System.out.println("Sent!!");
          }
		  return "successful";
	           
	     
	}

	@Async
	@Override
	public void notifySubscriber(Post post) throws EmailException, MalformedURLException {
		Locale locale = new Locale("vn", "VN");
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
		String pattern = "EEEEE MMMMM yyyy";
		SimpleDateFormat simpleDateFormat =
		        new SimpleDateFormat(pattern, dateFormatSymbols);
		List<Subscriber> listSubscriber = subscriberDao.listSubscriberByCategory(post.getCategory().getCategoryID());
		String email_from = "tamlyhochanhphuc@gmail.com";
		String password = "01645737427";
		  for (Subscriber sub : listSubscriber) {
	           String htmlEmailTemplate = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
	           		"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\r\n" + 
	           		"<head>\r\n" + 
	           		"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n" + 
	           		"  <!--[if !mso]><!-->\r\n" + 
	           		"  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
	           		"  <!--<![endif]-->\r\n" + 
	           		"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
	           		"  <meta name=\"format-detection\" content=\"telephone=no\">\r\n" + 
	           		"  <meta name=\"x-apple-disable-message-reformatting\">\r\n" + 
	           		"  <title></title>\r\n" + 
	           		"  <style type=\"text/css\">\r\n" + 
	           		"    @media screen {\r\n" + 
	           		"      @font-face {\r\n" + 
	           		"        font-family: 'Fira Sans';\r\n" + 
	           		"        font-style: normal;\r\n" + 
	           		"        font-weight: 400;\r\n" + 
	           		"        src: local('Fira Sans Regular'), local('FiraSans-Regular'), url(https://fonts.gstatic.com/s/firasans/v8/va9E4kDNxMZdWfMOD5Vvl4jLazX3dA.woff2) format('woff2');\r\n" + 
	           		"        unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\r\n" + 
	           		"      }\r\n" + 
	           		"      @font-face {\r\n" + 
	           		"        font-family: 'Fira Sans';\r\n" + 
	           		"        font-style: normal;\r\n" + 
	           		"        font-weight: 400;\r\n" + 
	           		"        src: local('Fira Sans Regular'), local('FiraSans-Regular'), url(https://fonts.gstatic.com/s/firasans/v8/va9E4kDNxMZdWfMOD5Vvk4jLazX3dGTP.woff2) format('woff2');\r\n" + 
	           		"        unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\r\n" + 
	           		"      }\r\n" + 
	           		"      @font-face {\r\n" + 
	           		"        font-family: 'Fira Sans';\r\n" + 
	           		"        font-style: normal;\r\n" + 
	           		"        font-weight: 500;\r\n" + 
	           		"        src: local('Fira Sans Medium'), local('FiraSans-Medium'), url(https://fonts.gstatic.com/s/firasans/v8/va9B4kDNxMZdWfMOD5VnZKveRhf6Xl7Glw.woff2) format('woff2');\r\n" + 
	           		"        unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\r\n" + 
	           		"      }\r\n" + 
	           		"      @font-face {\r\n" + 
	           		"        font-family: 'Fira Sans';\r\n" + 
	           		"        font-style: normal;\r\n" + 
	           		"        font-weight: 500;\r\n" + 
	           		"        src: local('Fira Sans Medium'), local('FiraSans-Medium'), url(https://fonts.gstatic.com/s/firasans/v8/va9B4kDNxMZdWfMOD5VnZKveQhf6Xl7Gl3LX.woff2) format('woff2');\r\n" + 
	           		"        unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\r\n" + 
	           		"      }\r\n" + 
	           		"      @font-face {\r\n" + 
	           		"        font-family: 'Fira Sans';\r\n" + 
	           		"        font-style: normal;\r\n" + 
	           		"        font-weight: 700;\r\n" + 
	           		"        src: local('Fira Sans Bold'), local('FiraSans-Bold'), url(https://fonts.gstatic.com/s/firasans/v8/va9B4kDNxMZdWfMOD5VnLK3eRhf6Xl7Glw.woff2) format('woff2');\r\n" + 
	           		"        unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\r\n" + 
	           		"      }\r\n" + 
	           		"      @font-face {\r\n" + 
	           		"        font-family: 'Fira Sans';\r\n" + 
	           		"        font-style: normal;\r\n" + 
	           		"        font-weight: 700;\r\n" + 
	           		"        src: local('Fira Sans Bold'), local('FiraSans-Bold'), url(https://fonts.gstatic.com/s/firasans/v8/va9B4kDNxMZdWfMOD5VnLK3eQhf6Xl7Gl3LX.woff2) format('woff2');\r\n" + 
	           		"        unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\r\n" + 
	           		"      }\r\n" + 
	           		"      @font-face {\r\n" + 
	           		"        font-family: 'Fira Sans';\r\n" + 
	           		"        font-style: normal;\r\n" + 
	           		"        font-weight: 800;\r\n" + 
	           		"        src: local('Fira Sans ExtraBold'), local('FiraSans-ExtraBold'), url(https://fonts.gstatic.com/s/firasans/v8/va9B4kDNxMZdWfMOD5VnMK7eRhf6Xl7Glw.woff2) format('woff2');\r\n" + 
	           		"        unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\r\n" + 
	           		"      }\r\n" + 
	           		"      @font-face {\r\n" + 
	           		"        font-family: 'Fira Sans';\r\n" + 
	           		"        font-style: normal;\r\n" + 
	           		"        font-weight: 800;\r\n" + 
	           		"        src: local('Fira Sans ExtraBold'), local('FiraSans-ExtraBold'), url(https://fonts.gstatic.com/s/firasans/v8/va9B4kDNxMZdWfMOD5VnMK7eQhf6Xl7Gl3LX.woff2) format('woff2');\r\n" + 
	           		"        unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\r\n" + 
	           		"      }\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    #outlook a {\r\n" + 
	           		"      padding: 0;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    .ReadMsgBody,\r\n" + 
	           		"    .ExternalClass {\r\n" + 
	           		"      width: 100%;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    .ExternalClass,\r\n" + 
	           		"    .ExternalClass p,\r\n" + 
	           		"    .ExternalClass td,\r\n" + 
	           		"    .ExternalClass div,\r\n" + 
	           		"    .ExternalClass span,\r\n" + 
	           		"    .ExternalClass font {\r\n" + 
	           		"      line-height: 100%;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    div[style*=\"margin: 14px 0\"],\r\n" + 
	           		"    div[style*=\"margin: 16px 0\"] {\r\n" + 
	           		"      margin: 0 !important;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    table,\r\n" + 
	           		"    td {\r\n" + 
	           		"      mso-table-lspace: 0;\r\n" + 
	           		"      mso-table-rspace: 0;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    table,\r\n" + 
	           		"    tr,\r\n" + 
	           		"    td {\r\n" + 
	           		"      border-collapse: collapse;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    body,\r\n" + 
	           		"    td,\r\n" + 
	           		"    th,\r\n" + 
	           		"    p,\r\n" + 
	           		"    div,\r\n" + 
	           		"    li,\r\n" + 
	           		"    a,\r\n" + 
	           		"    span {\r\n" + 
	           		"      -webkit-text-size-adjust: 100%;\r\n" + 
	           		"      -ms-text-size-adjust: 100%;\r\n" + 
	           		"      mso-line-height-rule: exactly;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    img {\r\n" + 
	           		"      border: 0;\r\n" + 
	           		"      outline: none;\r\n" + 
	           		"      line-height: 100%;\r\n" + 
	           		"      text-decoration: none;\r\n" + 
	           		"      -ms-interpolation-mode: bicubic;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    a[x-apple-data-detectors] {\r\n" + 
	           		"      color: inherit !important;\r\n" + 
	           		"      text-decoration: none !important;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    body {\r\n" + 
	           		"      margin: 0;\r\n" + 
	           		"      padding: 0;\r\n" + 
	           		"      width: 100% !important;\r\n" + 
	           		"      -webkit-font-smoothing: antialiased;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    .pc-gmail-fix {\r\n" + 
	           		"      display: none;\r\n" + 
	           		"      display: none !important;\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    @media screen and (min-width: 621px) {\r\n" + 
	           		"      .pc-email-container {\r\n" + 
	           		"        width: 620px !important;\r\n" + 
	           		"      }\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    @media screen and (max-width:620px) {\r\n" + 
	           		"      .pc-sm-p-30 {\r\n" + 
	           		"        padding: 30px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-sm-p-30-25 {\r\n" + 
	           		"        padding: 30px 25px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-sm-p-25 {\r\n" + 
	           		"        padding: 25px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-sm-mw-100pc {\r\n" + 
	           		"        max-width: 100% !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-sm-m-0-auto {\r\n" + 
	           		"        margin: 0 auto !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-sm-ta-center {\r\n" + 
	           		"        text-align: center !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-sm-p-24-20-30 {\r\n" + 
	           		"        padding: 24px 20px 30px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-sm-p-35-10-15 {\r\n" + 
	           		"        padding: 35px 10px 15px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-sm-p-35-30 {\r\n" + 
	           		"        padding: 35px 30px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-sm-p-21-10-14 {\r\n" + 
	           		"        padding: 21px 10px 14px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-sm-h-20 {\r\n" + 
	           		"        height: 20px !important\r\n" + 
	           		"      }\r\n" + 
	           		"    }\r\n" + 
	           		"\r\n" + 
	           		"    @media screen and (max-width:525px) {\r\n" + 
	           		"      .pc-xs-p-20 {\r\n" + 
	           		"        padding: 20px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-xs-p-20-15 {\r\n" + 
	           		"        padding: 20px 15px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-xs-p-15 {\r\n" + 
	           		"        padding: 15px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-xs-p-15-10-20 {\r\n" + 
	           		"        padding: 15px 10px 20px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-xs-h-100 {\r\n" + 
	           		"        height: 100px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-xs-br-disabled br {\r\n" + 
	           		"        display: none !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-xs-fs-30 {\r\n" + 
	           		"        font-size: 30px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-xs-lh-42 {\r\n" + 
	           		"        line-height: 42px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-xs-p-25-0-5 {\r\n" + 
	           		"        padding: 25px 0 5px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-xs-p-25-20 {\r\n" + 
	           		"        padding: 25px 20px !important\r\n" + 
	           		"      }\r\n" + 
	           		"      .pc-xs-p-5-0 {\r\n" + 
	           		"        padding: 5px 0 !important\r\n" + 
	           		"      }\r\n" + 
	           		"    }\r\n" + 
	           		"  </style>\r\n" + 
	           		"  \r\n" + 
	           		"</head>\r\n" + 
	           		"<body style=\"width: 100% !important; margin: 0; padding: 0; mso-line-height-rule: exactly; -webkit-font-smoothing: antialiased; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; background-color: #f4f4f4\" class=\"\">\r\n" + 
	           		"  <span style=\"color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;\">This is preheader text. Some clients will show this text as a preview.</span>\r\n" + 
	           		"  <table class=\"pc-email-body\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"table-layout: fixed;\">\r\n" + 
	           		"    <tbody>\r\n" + 
	           		"      <tr>\r\n" + 
	           		"        <td class=\"pc-email-body-inner\" align=\"center\" valign=\"top\">\r\n" + 
	           		"          <table class=\"pc-email-container\" width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"margin: 0 auto; max-width: 620px;\">\r\n" + 
	           		"            <tbody>\r\n" + 
	           		"              <tr>\r\n" + 
	           		"                <td align=\"left\" valign=\"top\" style=\"padding: 0 10px;\">\r\n" + 
	           		"                  <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n" + 
	           		"                    <tbody>\r\n" + 
	           		"                      <tr>\r\n" + 
	           		"                        <td height=\"20\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\r\n" + 
	           		"                      </tr>\r\n" + 
	           		"                    </tbody>\r\n" + 
	           		"                  </table>\r\n" + 
	           		"                  <!-- BEGIN MODULE: Menu 1 -->\r\n" + 
	           		"                  <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n" + 
	           		"                    <tbody>\r\n" + 
	           		"                      <tr>\r\n" + 
	           		"                        <td class=\"pc-sm-p-25 pc-xs-p-15\" bgcolor=\"#ffffff\" valign=\"middle\" style=\"padding: 0px 35px; background-color: #ffffff; font-size: 0; border-radius: 8px; box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1)\">\r\n" + 
	           		"                          <!--[if (gte mso 9)|(IE)]><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"><tr><td width=\"109\" valign=\"middle\"><![endif]-->\r\n" + 
	           		"                          <div class=\"pc-sm-mw-100pc\" style=\"display: inline-block; width: 100%; max-width: 109px; vertical-align: middle\">\r\n" + 
	           		"                            <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n" + 
	           		"                              <tbody>\r\n" + 
	           		"                                <tr>\r\n" + 
	           		"                                  <td valign=\"top\" style=\"padding: 10px 0;\">\r\n" + 
	           		"                                    <table class=\"pc-sm-m-0-auto\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n" + 
	           		"                                      <tbody>\r\n" + 
	           		"                                        <tr>\r\n" + 
	           		"                                          <td valign=\"middle\" style=\"padding: 0 5px;\">\r\n" + 
	           		"                                            <a href=\"https://www.facebook.com/tamlyhochanhphuc/\" style=\"text-decoration: none;\"><img src=\"http://3.223.185.12:8080/uploadOneFile/697057-facebook-512-ucx.png\" width=\"50\" height=\"50\" alt=\"facebook\" style=\"border: 0; line-height: 100%; outline: 0; -ms-interpolation-mode: bicubic; color: #ffffff;\"></a>\r\n" + 
	           		"                                          </td>\r\n" + 
	           		"                                        </tr>\r\n" + 
	           		"                                      </tbody>\r\n" + 
	           		"                                    </table>\r\n" + 
	           		"                                  </td>\r\n" + 
	           		"                                </tr>\r\n" + 
	           		"                              </tbody>\r\n" + 
	           		"                            </table>\r\n" + 
	           		"                          </div>\r\n" + 
	           		"                          <!--[if (gte mso 9)|(IE)]></td><td width=\"421\" valign=\"middle\"><![endif]-->\r\n" + 
	           		"                          <div class=\"pc-sm-mw-100pc\" style=\"display: inline-block; width: 100%; max-width: 421px; vertical-align: middle\">\r\n" + 
	           		"                            <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n" + 
	           		"                              <tbody>\r\n" + 
	           		"                                <tr>\r\n" + 
	           		"                                  <td class=\"pc-sm-ta-center\" valign=\"top\" style=\"padding: 10px 5px;\">\r\n" + 
	           		"                                    <a href=\"http://tamlyhochanhphuc.tk\" style=\"text-decoration: none;\"><img src=\"http://3.223.185.12:8080/uploadOneFile/logoFull.png\" width=\"293\" height=\"83\" alt=\"Tâm lý học hạnh phúc\" style=\"max-width: 100%;border: 0;line-height: 100%;outline: 0;-ms-interpolation-mode: bicubic;color: #ffffff;font-size: 14px;color: #142731;\"></a>\r\n" + 
	           		"                                  </td>\r\n" + 
	           		"                                </tr>\r\n" + 
	           		"                              </tbody>\r\n" + 
	           		"                            </table>\r\n" + 
	           		"                          </div>\r\n" + 
	           		"                          <!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]-->\r\n" + 
	           		"                        </td>\r\n" + 
	           		"                      </tr>\r\n" + 
	           		"                    </tbody>\r\n" + 
	           		"                  </table>\r\n" + 
	           		"                  <!-- END MODULE: Menu 1 -->\r\n" + 
	           		"                  <!-- BEGIN MODULE: Content 1 -->\r\n" + 
	           		"                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\r\n" + 
	           		"                    <tbody>\r\n" + 
	           		"                      <tr>\r\n" + 
	           		"                        <td height=\"8\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\r\n" + 
	           		"                      </tr>\r\n" + 
	           		"                    </tbody>\r\n" + 
	           		"                  </table>\r\n" + 
	           		"                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\">\r\n" + 
	           		"                    <tbody>\r\n" + 
	           		"                      <tr>\r\n" + 
	           		"                        <td class=\"pc-sm-p-35-10-15 pc-xs-p-25-0-5\" style=\"padding: 40px 20px 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);\" valign=\"top\" bgcolor=\"#ffffff\">\r\n" + 
	           		"                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\">\r\n" + 
	           		"                            <tbody>\r\n" + 
	           		"                              <tr>\r\n" + 
	           		"                                <td class=\"pc-fb-font\" style=\"padding: 0 20px; text-align: center; font-family: 'Fira Sans', Helvetica, Arial, sans-serif; font-size: 24px; font-weight: 700; line-height: 1.42; letter-spacing: -0.4px; color: #151515;\" valign=\"top\">Bài viết mới nhất</td>\r\n" + 
	           		"                              </tr>\r\n" + 
	           		"                              <tr>\r\n" + 
	           		"                                <td height=\"10\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\r\n" + 
	           		"                              </tr>\r\n" + 
	           		"                            </tbody>\r\n" + 
	           		"                            <tbody>\r\n" + 
	           		"                              <tr>\r\n" + 
	           		"                                <td style=\"font-size: 0; text-align: center;\" valign=\"top\">\r\n" + 
	           		"                                  <!--[if (gte mso 9)|(IE)]><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"><tr><td width=\"50%\" valign=\"top\"><![endif]-->\r\n" + 
	           		"                                  <div style=\"display: inline-block; width: 100%; vertical-align: top;\">\r\n" + 
	           		"                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\">\r\n" + 
	           		"                                      <tbody>\r\n" + 
	           		"                                        <tr>\r\n" + 
	           		"                                          <td style=\"padding: 20px;\" valign=\"top\">\r\n" + 
	           		"                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\">\r\n" + 
	           		"                                              <tbody>\r\n" + 
	           		"                                                <tr>\r\n" + 
	           		"                                                  <td background=\"http://3.223.185.12:8080/uploadOneFile/" + post.getImage() + "\" valign=\"top\" align=\"center\" bgcolor=\"#151515\" style=\"background-position: center; background-size: cover; border-radius: 6px; background-color: #151515; background-image: url('http://3.223.185.12:8080/uploadOneFile/" + post.getImage() +"')\">\r\n" + 
	           		"                                                    <!--[if gte mso 9]>\r\n" + 
	           		"                                                        <v:rect xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"true\" stroke=\"false\" style=\"width: 240px;\">\r\n" + 
	           		"                                                            <v:fill type=\"frame\" src=\"/http://3.223.185.12:8080/uploadOneFile/" + post.getImage() + "\" color=\"#151515\"></v:fill>\r\n" + 
	           		"                                                            <v:textbox style=\"mso-fit-shape-to-text: true;\" inset=\"0,0,0,0\">\r\n" + 
	           		"                                                                <div style=\"font-size: 0; line-height: 0;\">\r\n" + 
	           		"                                                                    <table align=\"center\" width=\"240\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n" + 
	           		"                                                                        <tr>\r\n" + 
	           		"                                                                            <td style=\"font-size: 14px; line-height: 1.5;\" valign=\"top\">\r\n" + 
	           		"                                                                                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\r\n" + 
	           		"                                                                                    <tr>\r\n" + 
	           		"                                                                                        <td colspan=\"3\" height=\"105\" style=\"line-height: 1px; font-size: 1px;\">&nbsp;</td>\r\n" + 
	           		"                                                                                    </tr>\r\n" + 
	           		"                                                                                    <tr>\r\n" + 
	           		"                                                                                        <td width=\"25\" style=\"line-height: 1px; font-size: 1px;\" valign=\"top\">&nbsp;</td>\r\n" + 
	           		"                                                                                        <td valign=\"top\" align=\"left\">\r\n" + 
	           		"                                                        <![endif]-->\r\n" + 
	           		"                                                    <!--[if !gte mso 9]><!-->\r\n" + 
	           		"                                                    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\r\n" + 
	           		"                                                      <tbody>\r\n" + 
	           		"                                                        <tr>\r\n" + 
	           		"                                                          <td style=\"padding: 105px 25px;\" valign=\"top\">\r\n" + 
	           		"                                                            <!--<![endif]-->\r\n" + 
	           		"                                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\">\r\n" + 
	           		"                                                              <tbody>\r\n" + 
	           		"                                                                <tr>\r\n" + 
	           		"                                                                  <td class=\"pc-fb-font\" style=\"text-align: center; line-height: 20px; font-family: 'Fira Sans', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 500; color: #40BE65;\" valign=\"top\">\r\n" + 
	           		"                                                                    <a style=\"text-decoration: none; color: #40BE65;\">" + simpleDateFormat.format(post.getCreatedDate()) + "</a>\r\n" + 
	           		"                                                                  </td>\r\n" + 
	           		"                                                                </tr>\r\n" + 
	           		"                                                                <tr>\r\n" + 
	           		"                                                                  <td height=\"8\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\r\n" + 
	           		"                                                                </tr>\r\n" + 
	           		"                                                              </tbody>\r\n" + 
	           		"                                                              <tbody>\r\n" + 
	           		"                                                                <tr>\r\n" + 
	           		"                                                                  <td class=\"pc-fb-font\" style=\"text-align: center; line-height: 34px; font-family: 'Fira Sans', Helvetica, Arial, sans-serif; font-size: 24px; font-weight: 700; letter-spacing: -0.4px; color: #ffffff;\" valign=\"top\">\r\n" + 
	           		"                                                                    <a href=\"http://tamlyhochanhphuc.tk/post-detail/"+ post.getPostID() + "\" style=\"text-decoration: none; color: #ffffff;\">" + post.getTitle() + "</a>\r\n" + 
	           		"                                                                  </td>\r\n" + 
	           		"                                                                </tr>\r\n" + 
	           		"                                                              </tbody>\r\n" + 
	           		"                                                            </table>\r\n" + 
	           		"                                                            <!--[if !gte mso 9]><!-->\r\n" + 
	           		"                                                          </td>\r\n" + 
	           		"                                                        </tr>\r\n" + 
	           		"                                                      </tbody>\r\n" + 
	           		"                                                    </table>\r\n" + 
	           		"                                                    <!--<![endif]-->\r\n" + 
	           		"                                                    <!--[if gte mso 9]>\r\n" + 
	           		"                                                                                        </td>\r\n" + 
	           		"                                                                                        <td width=\"25\" style=\"line-height: 1px; font-size: 1px;\" valign=\"top\">&nbsp;</td>\r\n" + 
	           		"                                                                                    </tr>\r\n" + 
	           		"                                                                                    <tr>\r\n" + 
	           		"                                                                                        <td colspan=\"3\" height=\"105\" style=\"line-height: 1px; font-size: 1px;\">&nbsp;</td>\r\n" + 
	           		"                                                                                    </tr>\r\n" + 
	           		"                                                                                </table>\r\n" + 
	           		"                                                                            </td>\r\n" + 
	           		"                                                                        </tr>\r\n" + 
	           		"                                                                    </table>\r\n" + 
	           		"                                                                </div>\r\n" + 
	           		"                                                            </v:textbox>\r\n" + 
	           		"                                                        </v:rect>\r\n" + 
	           		"                                                        <![endif]-->\r\n" + 
	           		"                                                  </td>\r\n" + 
	           		"                                                </tr>\r\n" + 
	           		"                                              </tbody>\r\n" + 
	           		"                                            </table>\r\n" + 
	           		"                                          </td>\r\n" + 
	           		"                                        </tr>\r\n" + 
	           		"                                      </tbody>\r\n" + 
	           		"                                    </table>\r\n" + 
	           		"                                  </div>\r\n" + 
	           		"                                </td>\r\n" + 
	           		"                              </tr>\r\n" + 
	           		"                            </tbody>\r\n" + 
	           		"                          </table>\r\n" + 
	           		"                        </td>\r\n" + 
	           		"                      </tr>\r\n" + 
	           		"                    </tbody>\r\n" + 
	           		"                  </table>\r\n" + 
	           		"                  <!-- END MODULE: Content 1 -->\r\n" + 
	           		"                  <!-- BEGIN MODULE: Call to Action 1 -->\r\n" + 
	           		"                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\r\n" + 
	           		"                    <tbody>\r\n" + 
	           		"                      <tr>\r\n" + 
	           		"                        <td height=\"8\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\r\n" + 
	           		"                      </tr>\r\n" + 
	           		"                    </tbody>\r\n" + 
	           		"                  </table>\r\n" + 
	           		"                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\">\r\n" + 
	           		"                    <tbody>\r\n" + 
	           		"                      <tr>\r\n" + 
	           		"                        <td class=\"pc-sm-p-35-30 pc-xs-p-25-20\" style=\"padding: 40px 30px 32px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);\" valign=\"top\" bgcolor=\"#ffffff\">\r\n" + 
	           		"                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\">\r\n" + 
	           		"                            <tbody>\r\n" + 
	           		"                              <tr>\r\n" + 
	           		"                                <td class=\"pc-xs-fs-30 pc-xs-lh-42 pc-fb-font\" style=\"font-family: 'Fira Sans', Helvetica, Arial, sans-serif; font-size: 36px; font-weight: 900; line-height: 46px; letter-spacing: -0.6px; color: #151515; text-align: center;\" valign=\"top\">Follow Us.</td>\r\n" + 
	           		"                              </tr>\r\n" + 
	           		"                              <tr>\r\n" + 
	           		"                                <td height=\"10\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\r\n" + 
	           		"                              </tr>\r\n" + 
	           		"                            </tbody>\r\n" + 
	           		"                            <tbody>\r\n" + 
	           		"                              <tr>\r\n" + 
	           		"                                <td class=\"pc-fb-font\" style=\"font-family: 'Fira Sans', Helvetica, Arial, sans-serif; font-size: 15px; font-weight: 300; line-height: 28px; color: #9B9B9B; text-align: center\" valign=\"top\"><span style=\"color: #40be65;\">Cảm ơn bạn đã quan tâm đến trang web của chúng tôi!&nbsp;</span><br><span style=\"color: #40be65;\">Hi vọng bài viết sẽ mang đến nhiều thông tin hữu ích cho bạn.!</span></td>\r\n" + 
	           		"                              </tr>\r\n" + 
	           		"                              <tr>\r\n" + 
	           		"                                <td height=\"11\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\r\n" + 
	           		"                              </tr>\r\n" + 
	           		"                            </tbody>\r\n" + 
	           		"                            <tbody>\r\n" + 
	           		"                              <tr>\r\n" + 
	           		"                                <td style=\"font-size: 0; text-align: center;\" valign=\"top\">\r\n" + 
	           		"                                  <!--[if (gte mso 9)|(IE)]><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" role=\"presentation\"><tr><td valign=\"top\"><![endif]-->\r\n" + 
	           		"                                  <div style=\"display: inline-block; vertical-align: top;\">\r\n" + 
	           		"                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n" + 
	           		"                                      <tbody>\r\n" + 
	           		"                                        <tr>\r\n" + 
	           		"                                          <td style=\"padding: 8px;\" valign=\"top\">\r\n" + 
	           		"                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n" + 
	           		"                                              <tbody>\r\n" + 
	           		"                                                <tr>\r\n" + 
	           		"                                                  <td style=\"padding: 13px 17px; border-radius: 8px; background-color: #427BDC;\" bgcolor=\"#427BDC\" valign=\"top\" align=\"center\">\r\n" + 
	           		"                                                    <a class=\"pc-fb-font\" href=\"https://www.facebook.com/tamlyhochanhphuc/\" style=\"line-height: 1.5; text-decoration: none; word-break: break-word; font-weight: 500; display: block; font-family: 'Fira Sans', Helvetica, Arial, sans-serif; font-size: 16px; color: #ffffff;\"><img src=\"http://3.223.185.12:8080/uploadOneFile/facebook-white.png\" alt=\"\" style=\"border: 0; line-height: 100%; outline: 0; -ms-interpolation-mode: bicubic; vertical-align: middle; margin-top: -2px;\" width=\"25\" height=\"25\">&nbsp;<span> Fanpage Facebook</span></a>\r\n" + 
	           		"                                                  </td>\r\n" + 
	           		"                                                </tr>\r\n" + 
	           		"                                              </tbody>\r\n" + 
	           		"                                            </table>\r\n" + 
	           		"                                          </td>\r\n" + 
	           		"                                        </tr>\r\n" + 
	           		"                                      </tbody>\r\n" + 
	           		"                                    </table>\r\n" + 
	           		"                                  </div>\r\n" + 
	           		"                                  <!--[if (gte mso 9)|(IE)]></td><td valign=\"top\"><![endif]-->\r\n" + 
	           		"                                  <div style=\"display: inline-block; vertical-align: top;\">\r\n" + 
	           		"                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\">\r\n" + 
	           		"                                      <tbody>\r\n" + 
	           		"                                        <tr>\r\n" + 
	           		"                                          <td style=\"padding: 8px;\" valign=\"top\">\r\n" + 
	           		"                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n" + 
	           		"                                              <tbody>\r\n" + 
	           		"                                                <tr>\r\n" + 
	           		"                                                  <td style=\"padding: 13px 17px; border-radius: 8px; background-color: #36B0FF;\" bgcolor=\"#36B0FF\" valign=\"top\" align=\"center\">\r\n" + 
	           		"                                                    <a class=\"pc-fb-font\" href=\"http://tamlyhochanhphuc.tk\" style=\"line-height: 1.5; text-decoration: none; word-break: break-word; font-weight: 500; display: block; font-family: 'Fira Sans', Helvetica, Arial, sans-serif; font-size: 16px; color: #ffffff;\"><img src=\"http://3.223.185.12:8080/uploadOneFile/logo-mjx.jpg\" alt=\"\" style=\"border: 0; line-height: 100%; outline: 0; -ms-interpolation-mode: bicubic; vertical-align: middle; margin-top: -2px;\" width=\"23\" height=\"23\">&nbsp;<span> Tâm lý học hạnh phúc</span></a>\r\n" + 
	           		"                                                  </td>\r\n" + 
	           		"                                                </tr>\r\n" + 
	           		"                                              </tbody>\r\n" + 
	           		"                                            </table>\r\n" + 
	           		"                                          </td>\r\n" + 
	           		"                                        </tr>\r\n" + 
	           		"                                      </tbody>\r\n" + 
	           		"                                    </table>\r\n" + 
	           		"                                  </div>\r\n" + 
	           		"                                  <!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]-->\r\n" + 
	           		"                                </td>\r\n" + 
	           		"                              </tr>\r\n" + 
	           		"                            </tbody>\r\n" + 
	           		"                          </table>\r\n" + 
	           		"                        </td>\r\n" + 
	           		"                      </tr>\r\n" + 
	           		"                    </tbody>\r\n" + 
	           		"                  </table>\r\n" + 
	           		"                  <!-- END MODULE: Call to Action 1 -->\r\n" + 
	           		"                  <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n" + 
	           		"                    <tbody>\r\n" + 
	           		"                      <tr>\r\n" + 
	           		"                        <td height=\"20\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\r\n" + 
	           		"                      </tr>\r\n" + 
	           		"                    </tbody>\r\n" + 
	           		"                  </table>\r\n" + 
	           		"                </td>\r\n" + 
	           		"              </tr>\r\n" + 
	           		"            </tbody>\r\n" + 
	           		"          </table>\r\n" + 
	           		"          <!--[if (gte mso 9)|(IE)]></td></tr></table><![endif]-->\r\n" + 
	           		"        </td>\r\n" + 
	           		"      </tr>\r\n" + 
	           		"    </tbody>\r\n" + 
	           		"  </table>\r\n" + 
	           		"  <!-- Fix for Gmail on iOS -->\r\n" + 
	           		"  <div class=\"pc-gmail-fix\" style=\"white-space: nowrap; font: 15px courier; line-height: 0;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </div>\r\n" + 
	           		"</body>\r\n" + 
	           		"</html>";        
	           System.out.println(htmlEmailTemplate);
	           ImageHtmlEmail email = new ImageHtmlEmail();
	           email.setHostName("smtp.googlemail.com");
	           email.setSmtpPort(465);
	           email.setAuthenticator(new DefaultAuthenticator(email_from, password));
	           email.setSSLOnConnect(true);
	           email.setFrom(email_from, "TamLyHocHanhPhuc");
	           email.addTo(sub.getEmail());
	           email.setSubject(String.format("Tâm Lý Học Hạnh Phúc có bài đăng mới trong chuyên mục %s", post.getCategory().getName()));
	           URL url = new URL("http://tamlyhochanhphuc.tk:8080");
	           email.setDataSourceResolver(new DataSourceUrlResolver(url) );
	           email.setHtmlMsg(htmlEmailTemplate);
	           email.setCharset("utf-8");
	           email.setTextMsg("Your email client does not support HTML messages");
	           email.send();
          }
	}

}
