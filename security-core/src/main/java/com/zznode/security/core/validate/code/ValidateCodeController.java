package com.zznode.security.core.validate.code;

import com.zznode.security.core.properties.SecurityProperties;
import com.zznode.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 20:59 2018/3/22
 */
@RestController
public class ValidateCodeController {
    public static final String SESSION_KEY = "SESSION_KEY_CODE";
    public static final String SESSION_KEY_SMS = "SESSION_KEY_CODE_SMS";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;
    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;
    @Autowired
    private SmsCodeSender smsCodeSender;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.createCode(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    @GetMapping("/code/smsCode")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        ValidateCode smsCode = smsCodeGenerator.createCode(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_SMS, smsCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsCodeSender.sendCode(mobile, smsCode.getCode());
    }
}
