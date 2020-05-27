package com.gxhy.springCloud;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        System.out.println(TestService.imgBinarization("F:/桂平图像识别/test/2020-05-21.jpg"));
    }
}
