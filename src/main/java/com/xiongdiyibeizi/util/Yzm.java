package com.xiongdiyibeizi.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Yzm {

    public Color getRandColor(int fc,int bc){
        Random random = new Random();
        if (fc>255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc-fc);
        int g = fc + random.nextInt(bc-fc);
        int b = fc + random.nextInt(bc-fc);
        return new Color(r,g,b);
    }

    public int getRange(int from,int to)
    {
        Random random = new Random();
        int seed = (from+to)/2;
        int result = (from+to)/2;
        while(true){
            result = random.nextInt(seed);
            if (result<=to && result>=from){
                break;
            }
        }
        return result;
    }
    public static void main(String[] arg){

        Yzm yzm = new Yzm();

        int height = 33;
        int width = 88;

        //BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("D:\\projectsYR\\yzm\\9.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graphics g = img.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        g.setFont(new Font("微软雅黑",Font.PLAIN,18));

        Random r = new Random();
        for (int i = 0;i<15;i++){
            g.setColor(yzm.getRandColor(160,200));
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            int x1 = r.nextInt(12);
            int y1 = r.nextInt(12);
            g.drawLine(x,y,x1+x,y+y1);
        }

        for (int i=0;i<4;i++){
            int random = r.nextInt(9);
            System.out.println(random);
            g.setColor(yzm.getRandColor(0,255));
            int x= 8+i*22;
            int y = yzm.getRange(15,30);
            System.out.println(x+":    "+y);
            g.drawString(String.valueOf(random),x,y);
        }
        g.dispose();
        File file = new File("D:\\projectsYR\\yzm\\first.gif");
        try {
            ImageIO.write(img,"jpeg",file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
