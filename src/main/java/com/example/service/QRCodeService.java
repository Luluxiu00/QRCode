package com.example.service;

import com.google.zxing.WriterException;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by 326944 on 2017/7/24.
 */
public interface QRCodeService {

    /**
     * 生成二维码
     * @param url
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public BufferedImage createQRCode(final String url) throws WriterException, IOException;
}
