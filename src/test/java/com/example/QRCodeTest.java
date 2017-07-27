package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 326944 on 2017/7/24.
 */
public class QRCodeTest {
    public static void main(String []args)throws Exception{
        Map map = new HashMap();
        map.put("ssid","Deppon-Guest");
        map.put("password","12345678");
        String jsonString = JSON.toJSONString(map);
        int width = 200;
        int height = 200;
        String format = "png";
        Map hints= new HashMap();
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //生成矩阵
        BitMatrix bitMatrix = new MultiFormatWriter().encode(jsonString, BarcodeFormat.QR_CODE, width, height,hints);
        File outputFile = new File("new.png");
        //输出图片
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        System.out.println(outputFile.getAbsolutePath());
    }

    /**
     * 解析二维码
     */
    @Test
    public void testDecode() {
        //图片路径
        String filePath = "D:\\326944\\Downloads\\QRCode\\new.png";
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
            JSONObject content = JSONObject.parseObject(result.getText());
            System.out.println("图片中内容：  ");
            System.out.println("ssid： " + content.getString("ssid"));
            System.out.println("password：  " + content.getString("password"));
            System.out.println("图片中格式：  ");
            System.out.println("encode： " + result.getBarcodeFormat());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
