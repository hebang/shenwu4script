package com.czq.shenwu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MouseShenWu extends JPanel {
    public static final int HP_BGR = -1361342;
    public static int BAN_HP_BGR;
    public static final int KONG_HP_BGR = -7052137;
    public static final int TOW_BGR = -8330080;
    public static final int ONE_BGR = -4131656;
    public static final int ZERO_BGR = -2557736;
    public static final int SAN_JIAO_XING = -3810079;
    public static final int CAI_DAN_BGR = -16564657;
    private static int REMIND_X = 150;
    private static int REMIND_BGR = -6729191;
    private static int REMIND_Y = 1050;
    public static volatile boolean iStart = false;
    private int BOSS_BGR;
    private Integer hpx = 1;
    private Integer hpy = 20;
    private int INTEVAL = 28;
    public JButton startButton;
    public JButton stopButton;

    public MouseShenWu() {
        this.setLayout(new GridLayout(1, 2, 10, 30));
        this.setSize(300, 200);
        this.setVisible(true);
        this.startButton = new JButton("开始");
//        this.startButton.addActionListener(new StartAction(this));
        this.stopButton = new JButton("停止");
//        this.stopButton.addActionListener(new StopAction(this));
        this.add(this.stopButton, "East");
        this.add(this.startButton, "West");
    }

    public static void main(String[] str) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension di = tk.getScreenSize();
        JFrame jf = new JFrame();
        jf.setSize(600, 200);
        jf.setDefaultCloseOperation(3);
        jf.setVisible(true);
        MouseShenWu mouseShenWu = new MouseShenWu();
        jf.add(mouseShenWu);
    }

    public void shenWuGuaJi() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension di = tk.getScreenSize();
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);

        try {
            Robot myRobot = new Robot();
            BufferedImage bi = myRobot.createScreenCapture(rec);
            Integer[] hpPoint = this.getBgrHpXandHpY(myRobot, bi, -1361342, this.hpy);
            this.hpx = hpPoint[0];
            this.hpy = hpPoint[1];
            if (this.hpx == null) {
                System.out.print("找不到角色血条");
            } else {
                System.out.println("获取到角色血条");
                int bossX = this.hpx - 320;
                int bossY = this.hpy + 455;
                int ban_hpx = this.hpx + 33;
                int sanjiaoX = this.hpx - 188;
                int sanjiaoY = this.hpy;
                int caiDanX = this.hpx;
                int caiDanY = this.hpy + 120;
                int mouseSelect = 4;
                this.BOSS_BGR = bi.getRGB(bossX, bossY);
                BAN_HP_BGR = bi.getRGB(ban_hpx, this.hpy);
                boolean firstRow = false;
                boolean battle = false;
                boolean var17 = false;

                while(true) {
                    while(iStart) {
                        bi = myRobot.createScreenCapture(rec);
                        int caiDanRGB = bi.getRGB(caiDanX, caiDanY);
                        if (Math.abs(bi.getRGB(sanjiaoX, sanjiaoY) - -3810079) >= 500000 && caiDanRGB != -16564657) {
                            battle = false;
                            firstRow = false;
                            var17 = false;
                        } else {
                            battle = true;
                            if (caiDanRGB == -16564657) {
                                battle = true;
                                mouseSelect = 4;
                                if (bi.getRGB(ban_hpx, this.hpy) == BAN_HP_BGR) {
                                    mouseSelect = 4;
                                    this.fuben(myRobot, mouseSelect, rec, bossX, bossY);
                                } else {
                                    mouseSelect = 16;
                                    this.pingKan(myRobot, mouseSelect, bi, bossX, bossY);
                                }

                                this.mouseMove(myRobot, bossX + 40, 320);
                                Thread.sleep(500L);
                                this.doubleMouseRelease(myRobot, mouseSelect);
                            }
                        }
                    }

                    return;
                }
            }
        } catch (Exception var19) {
            var19.printStackTrace();
        }
    }

    public void fenYao(Robot myRobot, int mouseSelect, BufferedImage bi, int bossX, int bossY) throws InterruptedException {
        this.renewHp(myRobot, this.hpx, this.hpy);
        this.renewBbMp(myRobot, this.hpx, this.hpy);
        if (this.BOSS_BGR == bi.getRGB(bossX + this.INTEVAL + this.INTEVAL, bossY + this.INTEVAL)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX + this.INTEVAL + this.INTEVAL, bossY + this.INTEVAL, 16);
        } else {
            this.mouseMoveAndDoubleMouse(myRobot, this.hpx - 530, this.hpy + 150, mouseSelect);
            this.keyAltAndQ(myRobot);
        }

    }

    public void lingxu(Robot myRobot, int mouseSelect, BufferedImage bi, int bossX, int bossY) throws InterruptedException {
        if (this.BOSS_BGR == bi.getRGB(bossX + this.INTEVAL, bossY + this.INTEVAL)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX + this.INTEVAL, bossY + this.INTEVAL, mouseSelect);
        } else if (this.BOSS_BGR == bi.getRGB(bossX, bossY)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX, bossY, mouseSelect);
        } else {
            this.keyAltAndA(myRobot);
        }

    }

    public void fuben(Robot myRobot, int mouseSelect, Rectangle rec, int bossX, int bossY) throws InterruptedException {
        Thread.sleep(1000L);
        BufferedImage bi = myRobot.createScreenCapture(rec);
        if (this.BOSS_BGR == bi.getRGB(bossX + this.INTEVAL + this.INTEVAL, bossY + this.INTEVAL) && this.BOSS_BGR == bi.getRGB(bossX - this.INTEVAL - this.INTEVAL, bossY + this.INTEVAL)) {
            if (this.BOSS_BGR == bi.getRGB(bossX - this.INTEVAL - this.INTEVAL, bossY)) {
                this.mouseMoveAndDoubleMouse(myRobot, bossX - this.INTEVAL - this.INTEVAL, bossY, mouseSelect);
            } else if (this.BOSS_BGR == bi.getRGB(bossX - this.INTEVAL, bossY)) {
                this.mouseMoveAndDoubleMouse(myRobot, bossX - this.INTEVAL, bossY, mouseSelect);
            } else if (this.BOSS_BGR == bi.getRGB(bossX + this.INTEVAL, bossY)) {
                this.mouseMoveAndDoubleMouse(myRobot, bossX + this.INTEVAL, bossY, mouseSelect);
            } else {
                this.keyAltAndA(myRobot);
            }

            Thread.sleep(100L);
        } else if (this.BOSS_BGR == bi.getRGB(bossX + this.INTEVAL + this.INTEVAL, bossY + this.INTEVAL)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX + this.INTEVAL + this.INTEVAL, bossY + this.INTEVAL, mouseSelect);
        } else if (this.BOSS_BGR == bi.getRGB(bossX + this.INTEVAL, bossY + this.INTEVAL)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX + this.INTEVAL, bossY + this.INTEVAL, mouseSelect);
        } else if (this.BOSS_BGR == bi.getRGB(bossX, bossY)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX, bossY, mouseSelect);
        } else {
            this.keyAltAndA(myRobot);
        }

    }

    public void pingKan(Robot myRobot, int mouseSelect, BufferedImage bi, int bossX, int bossY) throws InterruptedException {
        if (this.BOSS_BGR == bi.getRGB(bossX + this.INTEVAL + this.INTEVAL, bossY + this.INTEVAL)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX + this.INTEVAL + this.INTEVAL, bossY + this.INTEVAL, mouseSelect);
        } else if (this.BOSS_BGR == bi.getRGB(bossX + this.INTEVAL, bossY + this.INTEVAL)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX + this.INTEVAL, bossY + this.INTEVAL, mouseSelect);
        } else if (this.BOSS_BGR == bi.getRGB(bossX, bossY + this.INTEVAL)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX, bossY + this.INTEVAL, mouseSelect);
        } else if (this.BOSS_BGR == bi.getRGB(bossX - this.INTEVAL, bossY + this.INTEVAL)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX - this.INTEVAL, bossY + this.INTEVAL, mouseSelect);
        } else if (this.BOSS_BGR == bi.getRGB(bossX - this.INTEVAL - this.INTEVAL, bossY + this.INTEVAL)) {
            this.mouseMoveAndDoubleMouse(myRobot, bossX - this.INTEVAL - this.INTEVAL, bossY + this.INTEVAL, mouseSelect);
        } else {
            this.keyAltAndA(myRobot);
        }
    }

    public void keyAltAndA(Robot myRobot) throws InterruptedException {
        myRobot.keyPress(18);
        myRobot.keyPress(65);
        Thread.sleep(50L);
        myRobot.keyRelease(18);
        myRobot.keyRelease(65);
    }

    public void keyAltAndQ(Robot myRobot) throws InterruptedException {
        myRobot.keyPress(18);
        myRobot.keyPress(81);
        Thread.sleep(50L);
        myRobot.keyRelease(18);
        myRobot.keyRelease(81);
    }

    public void renewMp(Robot myRobot, int hpx, int hpy) throws InterruptedException {
        this.mouseMove(myRobot, hpx + 30, hpy + 14);
        Thread.sleep(50L);
        this.mouseRelease(myRobot, 4);
    }

    public void renewHp(Robot myRobot, int hpx, int hpy) throws InterruptedException {
        this.mouseMove(myRobot, hpx + 30, hpy);
        Thread.sleep(50L);
        this.mouseRelease(myRobot, 4);
        Thread.sleep(50L);
    }

    public void renewBbMp(Robot myRobot, int hpx, int hpy) throws InterruptedException {
        this.mouseMove(myRobot, hpx - 100, hpy + 14);
        Thread.sleep(50L);
        this.mouseRelease(myRobot, 4);
    }

    public void printRGB(int rgb) {
        rgb += 16777216;

        for(int i = 0; i < 3; ++i) {
            int b = rgb % 256;
            if (b > 0) {
                System.out.print(":" + b);
            } else {
                System.out.print(":" + b);
            }

            rgb /= 256;
        }

    }

    public Integer[] getBgrHpXandHpY(Robot myRobot, BufferedImage bi, int bgr, int y) {
        int x;
        int findy = y;
        Integer[] hpPoint = new Integer[2];

        while(true) {
            x = 1;

            while(true) {
                boolean var8 = false;

                label32: {
                    int pixelColor;
                    try {
                        pixelColor = bi.getRGB(x, findy);
                    } catch (Exception var10) {
                        break label32;
                    }

                    if (pixelColor == bgr) {
                        hpPoint[0] = x;
                        hpPoint[1] = findy;
                        return hpPoint;
                    }

                    ++x;
                    if (x < 1919) {
                        continue;
                    }
                }

                ++findy;
                if (y >= 1000) {
                    return null;
                }
                break;
            }
        }
    }

    public void mouseRelease(Robot myRobot, int mouse) throws InterruptedException {
        Thread.sleep(50L);
        myRobot.mousePress(mouse);
        Thread.sleep(50L);
        myRobot.mouseRelease(mouse);
    }

    public void doubleMouseRelease(Robot myRobot, int mouse) throws InterruptedException {
        this.mouseRelease(myRobot, mouse);
        Thread.sleep(50L);
        this.mouseRelease(myRobot, mouse);
        Thread.sleep(50L);
    }

    public boolean isFinaly(BufferedImage bi, int bossX, int bossY) {
        return this.BOSS_BGR == bi.getRGB(bossX + 28, bossY + 28) && this.BOSS_BGR == bi.getRGB(bossX + 28, bossY) && this.BOSS_BGR == bi.getRGB(bossX, bossY) && this.BOSS_BGR == bi.getRGB(bossX - 28 - 28, bossY) && this.BOSS_BGR == bi.getRGB(bossX - 28, bossY) && this.BOSS_BGR == bi.getRGB(bossX + 28 + 28, bossY) && this.BOSS_BGR == bi.getRGB(bossX - 28, bossY + 28) && this.BOSS_BGR == bi.getRGB(bossX, bossY + 28) && this.BOSS_BGR == bi.getRGB(bossX + 28 + 28, bossY + 28) && this.BOSS_BGR == bi.getRGB(bossX - 28 - 28, bossY + 28);
    }

    public void mouseMoveAndDoubleMouse(Robot myRobot, int x, int y, int mouse) throws InterruptedException {
        this.mouseMove(myRobot, x, y);
        Thread.sleep(100L);
        this.doubleMouseRelease(myRobot, mouse);
    }

    public void mouseMove(Robot myRobot, int x, int y) {
        int num = 1;

        do {
            ++num;
            myRobot.mouseMove(num, 1);
        } while(num != x && num <= x && num <= 1919);

        int nom = 1;

        do {
            ++nom;
            myRobot.mouseMove(num, nom);
        } while(nom != y && nom <= y && nom <= 1080);

    }
}

