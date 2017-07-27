package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by 326944 on 2017/7/24.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        String qrcPic = "D:\\326944\\Downloads\\QRCode\\new.png";
        String logoPic = "D:\\0.jpg";

        /**
         * 读取二维码图片，并构建绘图对象
         */
        BufferedImage image = ImageIO.read(new File(qrcPic));
        Graphics2D g = image.createGraphics();
        /**
         * 读取Logo图片
         */
        BufferedImage logo = ImageIO.read(new File(logoPic));
        /**
         * 设置logo的大小,本人设置为二维码图片的20%,因为过大会盖掉二维码
         */
        int widthLogo = logo.getWidth(null)>image.getWidth()*2/10?(image.getWidth()*2/10):logo.getWidth(null),
                heightLogo = logo.getHeight(null)>image.getHeight()*2/10?(image.getHeight()*2/10):logo.getHeight(null);

        // 计算图片放置位置
        /**
         * logo放在中心
         */
        int x = (image.getWidth() - widthLogo) / 2;
        int y = (image.getHeight() - heightLogo) / 2;
        /**
         * logo放在右下角
         */
/*        int x = (image.getWidth() - widthLogo);
        int y = (image.getHeight() - heightLogo);*/

        LogoConfig logoConfig = new LogoConfig();
        //开始绘制图片
        g.drawImage(logo, x, y, widthLogo, heightLogo, null);
        g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
        g.setStroke(new BasicStroke(logoConfig.getBorder()));
        g.setColor(logoConfig.getBorderColor());
        g.drawRect(x, y, widthLogo, heightLogo);

        g.dispose();
        logo.flush();
        image.flush();

        ImageIO.write(image, "png", new File("D:/test.png"));

}
}
