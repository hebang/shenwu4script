package com.czq.shenwu.model.pojo;

import com.czq.shenwu.model.bo.RobotOperation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenInfo {
    public static final Rectangle rec;

    public static final int SCREEN_WIDTH;

    public static final int SCREEN_HEIGHT;

    static {
        //获取默认工具包
        Toolkit tk = Toolkit.getDefaultToolkit();
//        获取屏幕的大小
        Dimension di = tk.getScreenSize();
        //屏幕宽
        SCREEN_WIDTH = di.width;
        //屏幕高
        SCREEN_HEIGHT = di.height;
        //构造矩形
        rec = new Rectangle(0, 0, di.width, di.height);
    }

    /**
     * 截取屏幕图片
     * @return
     */
    public static BufferedImage createScreenCapture() {
        return RobotOperation.getInstance().createScreenCapture(rec);
    }

}
