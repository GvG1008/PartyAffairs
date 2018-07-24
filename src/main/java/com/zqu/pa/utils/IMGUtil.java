package com.zqu.pa.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class IMGUtil {

	/**
	 * 压缩图片，指定长宽
	 * @param file 文件
	 * @param newWidth 宽
	 * @param newHeight	长
	 * @param FullPath	存储全路径
	 * @return
	 */
	public static boolean compressPic(File file, int newWidth , int newHeight , String FullPath){
		try{
			//获得文件源
			if(!file.exists()){
				return false;
			}
			//imageIO.read();图片解析,可传入文件,文件输入流等
			Image img = ImageIO.read(file);
			if(img.getWidth(null) == -1){
				return false;
			}else{
				/**
				 *Image.SCALE_SMOOTH的缩略算法生成缩略图片的平滑度的优先级比速度高,生成的图片质量好但是速度慢
				 *根据具体要求取舍时间或是质量
				 */
				BufferedImage tag = new BufferedImage((int)newWidth,(int)newHeight,BufferedImage.TYPE_INT_RGB);
				/* @param    img    the specified image to be drawn. 
				 * @param    x      x坐标.
				 * @param    y      y坐标.
				 * @param    width  the width of the rectangle.
				 * @param    height the height of the rectangle.
				 * @param    observer   像素是否被改变			
				 */
				tag.getGraphics().drawImage(img,0,0,newWidth,newHeight,null);
				FileOutputStream out = new FileOutputStream(FullPath);
				//JPEGImageEncoder可适用于其他图片的类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag); 
				encoder.encode(tag);
				out.close();
			}
			
		}catch (Exception e) {
			System.out.println("压缩图片失败");
			e.printStackTrace();
		}
		System.out.println("压缩图片成功");
		return true;
	  }
}
