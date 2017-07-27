package com.example.controller;

import com.example.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 326944 on 2017/7/24.
 */
@Controller
public class QRController {

    @Autowired
    QRCodeService qrCodeService;

    /**
     * 生成网址的二维码
     * @param url
     * @param response
     */
    @RequestMapping("/qrcode")
    public void getQRCode(String url,HttpServletResponse response) throws IOException {
        //二维码图片输出流
        OutputStream out = null;
        try{
            //设置页面输出的文档MIME类型
            response.setContentType("image/jpeg;charset=UTF-8");
            //生成二维码
            BufferedImage image = qrCodeService.createQRCode(url);
            //实例化输出流对象
            out = response.getOutputStream();
            //画图
            ImageIO.write(image, "png", response.getOutputStream());
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭连接
            try{
                if (null != response.getOutputStream()) {
                    response.getOutputStream().close();
                }
                if (null != out) {
                    out.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}
