package com.czq.shenwu.model.bo;

import com.czq.shenwu.model.pojo.ScreenInfo;
import com.czq.shenwu.utils.ThreadUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.czq.shenwu.model.Point;

public class RobotOperation {
    private  Robot mRobot;

    private RobotOperation(){
        try {
            mRobot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static RobotOperation getInstance() {
        return Single.SINGLE;
    }


    /**
     * 移动鼠标到point
     */
    public  void mouseMove(Point point) {
        int num = 1;
        do {
            ++num;
            mRobot.mouseMove(num, 1);
        } while(num != point.x && num <= point.x && num <= ScreenInfo.SCREEN_WIDTH -1);

        int nom = 1;

        do {
            ++nom;
            mRobot.mouseMove(num, nom);
        } while(nom != point.y && nom <= point.y && nom <= ScreenInfo.SCREEN_HEIGHT);
//        mRobot.mouseMove(point.x,point.y);

    }

    /**
     * 单击鼠标
     * @param mouse 4代表右键 16代表左
     * @throws InterruptedException
     */
    public  void mouseRelease( int mouse) {
        ThreadUtil.sleep(50L);
        mRobot.mousePress(mouse);
        ThreadUtil.sleep(50L);
        mRobot.mouseRelease(mouse);
    }

    /**
     * 按下键盘key键
     * @param key
     */
    public void keyPress(int key) {
        mRobot.keyPress(key);
    }

    /**
     * 释放键盘key键
     * @param key
     */
    public void keyRelease(int key) {
        mRobot.keyRelease(key);
    }

    /**
     * 抓取src领域的屏幕截图
     * @param src
     * @return
     */
    public BufferedImage createScreenCapture(Rectangle src ) {
        return mRobot.createScreenCapture(src);
    }

    private static class Single {
        private static RobotOperation SINGLE =  new RobotOperation();
    }
}
