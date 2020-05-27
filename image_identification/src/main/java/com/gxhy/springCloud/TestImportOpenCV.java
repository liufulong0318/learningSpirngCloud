package com.gxhy.springCloud;


import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class TestImportOpenCV {
    public static void main(String [] args){
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        Mat srcImage = Imgcodecs.imread("2020-05-21.jpg");
//        Mat srcImage = Imgcodecs.imread("2020-05-21-2.jpg");
//        Mat srcImage = Imgcodecs.imread("3333.jpg");
//        Mat srcImage = Imgcodecs.imread("4444.jpg");
//        Mat srcImage = Imgcodecs.imread("5555.jpg");
//        Mat srcImage = Imgcodecs.imread("6666.jpg");

        Mat dstImage = new Mat();
        //1 图片灰度化
        Imgproc.cvtColor(srcImage,dstImage,Imgproc.COLOR_BGR2GRAY);
        Imgcodecs.imwrite("2020-05-21-cvtColor.jpg", dstImage);
        Mat image = new Mat();
        //2 直方图均衡化
        Imgproc.equalizeHist(dstImage,image);
        Imgcodecs.imwrite("2020-05-21-equalizeHist.jpg", image);
        //3 中值滤波
        Mat image2 = new Mat();
        Imgproc.medianBlur(image,image2,3);
        Imgcodecs.imwrite("2020-05-21-medianBlur.jpg", image2);
        //4 二值化
        Mat target = new Mat();
        Imgproc.threshold(image2,target,0,255,Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Imgcodecs.imwrite("2020-05-21-threshold.jpg", target);
        Mat target2 = new Mat();
        Imgproc.adaptiveThreshold(image2, target2, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 5, 0);
        Imgcodecs.imwrite("2020-05-21-threshold2.jpg", target2);
        //5 腐蚀.
        Mat erode = new Mat();
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
        Imgproc.erode(target, erode, element, new Point(-1, -1), 1);
        Imgcodecs.imwrite("2020-05-21-erode.jpg", erode);
        //6 边缘检测
        Mat gray = new Mat();
        Imgproc.Canny(target, gray, 3, 6, 3, false);
        Imgcodecs.imwrite("2020-05-21-gray.jpg", gray);


        //模板匹配
        //1 获取匹配模板
//        Mat demo=Imgcodecs.imread("xxx.jpg");
//        int width=srcImage.cols()-demo.cols()+1;
//        int height=srcImage.rows()-demo.rows()+1;
//        // 3 创建32位模板匹配结果Mat
//        Mat result=new Mat(width,height,CvType.CV_32FC1);
//        // 4 调用 模板匹配函数
//        Imgproc.matchTemplate(srcImage, demo, result, Imgproc.TM_SQDIFF_NORMED);
//        // 5 归一化
//        Core.normalize(result, result,0, 1, Core.NORM_MINMAX, -1, new Mat());
//        // 6 获取模板匹配结果
//        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
//        // 7 绘制匹配到的结果
//        double x,y;
//        if (Imgproc.TM_SQDIFF_NORMED==Imgproc.TM_SQDIFF_NORMED || Imgproc.TM_SQDIFF_NORMED==Imgproc.TM_SQDIFF) {
//            x = mmr.minLoc.x;
//            y = mmr.minLoc.y;
//        } else {
//            x = mmr.maxLoc.x;
//            y = mmr.maxLoc.y;
//        }
//        Imgproc.rectangle(srcImage,new Point(x,y),new Point(x+demo.cols(),y+demo.rows()),new Scalar( 0, 0, 255),2,Imgproc.LINE_AA);
//        Imgproc.putText(srcImage,"Math Success",new Point(x,y),Imgproc.FONT_HERSHEY_SCRIPT_COMPLEX, 1.0, new Scalar(0, 255, 0),1,Imgproc.LINE_AA);
//        Imgcodecs.imwrite("2020-05-21-templete.jpg", srcImage);
    }
}
