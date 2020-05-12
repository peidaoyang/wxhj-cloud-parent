package com.wxhj.cloud.core.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



/** 
* @ClassName: LoginVerification 
* @author cya
*/
public class LoginVerificationUtil {
	static Random random = new Random();
    //设置验证码的文本  
    static int width=120;
	static int height=40;       
    static BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);      
    static Graphics g;     
    
   private static final Color DarkBlue = new Color(0,0,139);
   private static BASE64Encoder encoder = new BASE64Encoder();

   //设置指定的字体颜色
   public static Color getColor() {
	   List<Color> colorList = new ArrayList<>();
	   colorList.add(Color.BLACK);
	   colorList.add(Color.RED);
	   colorList.add(Color.BLUE);
	   colorList.add(Color.GREEN);
	   colorList.add(Color.ORANGE);
	   colorList.add(Color.GRAY);
	   colorList.add(DarkBlue);
	   
	   // 取得集合的长度，for循环使用  
       int size = colorList.size();  
       
       //生成随机数
       int ran = (int)(Math.random()*size);
       //获取颜色
	   return colorList.get(ran);
   }
   
   public static String verificationImage(String letterAndNumStr){         
       //设置字体的大小     
       Font mFont = new Font("Times New Roman",Font.PLAIN,28);            
      
       g = image.getGraphics();   
       g.setColor(new Color(255,255,255));//背景颜色   白色
       g.fillRect(0, 0, width, height); //绘制图片大小   
       g.setColor(new Color(255,255,255)); //边框颜色 白色
       g.drawRect(0, 0, width-1, height-1); 
       
       g.setFont(mFont);       
       
       //设置颜色   
       g.setColor(getColor());  
       for (int i=0;i<2;i++)       
       {       
           int x = random.nextInt(width - 1);       
           int y = random.nextInt(height - 1);       
           int xl = random.nextInt(60) + 10;       
           int yl = random.nextInt(12) + 10;       
           g.drawLine(x,y,x + xl,y + yl);        //划线
       }       
       for (int i = 0;i < 2;i++)       
       {       
           int x = random.nextInt(width - 1);       
           int y = random.nextInt(height - 1);       
           int xl = random.nextInt(60) + 10;       
           int yl = random.nextInt(6) + 10;       
           g.drawLine(x,y,x - xl,y - yl);        //划线
       }     
       //设置验证码的类型  
       String[] sRand  = letterAndNumStr.split("");  
       for(int i = 0; i < 4; i++) {
    	   g.setColor(getColor());     //字体颜色
           g.drawString(sRand[i],25 * i + 20, 30);
       }
       
       g.dispose();   
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       try {
    	   ImageIO.write(image, "JPEG", baos);
       } catch (IOException e) {
    	   e.printStackTrace();
       }
       return encoder.encodeBuffer(baos.toByteArray()).trim();
   }
   
   // 英文字符加数字的验证码    
   public static String letterAndNum(){  
        String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",    
                "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        String sRand = "";
        for (int i = 0; i < 4; i++) {    
            String tempRand = "";    
            if (random.nextBoolean()) {    
                tempRand = String.valueOf(random.nextInt(10));    
            } else {    
                tempRand = letter[random.nextInt(25)];    

            }    
            sRand += tempRand; 
        }   
        return sRand;
   }  
 
   
   static BASE64Decoder decoder = new BASE64Decoder();
   
   public static void base64StringToImage(String base64String,String path) {
       try {
           byte[] bytes1 = decoder.decodeBuffer(base64String);
           ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
           BufferedImage bi1 = ImageIO.read(bais);
           File f1 = new File(path);
           ImageIO.write(bi1, "jpeg", f1);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
