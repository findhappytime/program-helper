package com.findhappytime.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by zhangshu on 2017/9/14.
 */
@Component
public class MailUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtils.class);

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送带附件的邮件
     *
     * @param toEmail
     * @param title
     * @param path
     */
    public void sendAttachmentsEmail(String fromEmail, String toEmail, String title, String content, String path) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            // 邮件抄送
            String[] emailCc = {"xxxx@163.com "};
            helper.setCc(emailCc);
            helper.setSubject(title);
            helper.setText(content);

            //1、获取附件文件对象
            if (StringUtils.isNotEmpty(path)) {
                FileSystemResource file = new FileSystemResource(new File(path));
                helper.addAttachment(title, file);
            }

            mailSender.send(mimeMessage);

        } catch (Exception e) {
            LOGGER.error("发送邮件异常 - error: " + e.getMessage());
        }

    }


}
