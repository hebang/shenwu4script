package com.czq.shenwu.model.bo;

import com.czq.shenwu.constant.SizeConstant;
import com.czq.shenwu.model.Point;
import com.czq.shenwu.model.pojo.ScreenInfo;
import com.czq.shenwu.utils.LogUtils;

import java.awt.image.BufferedImage;

public class PointCollection {
    private static final String TAG = "PointCollection";
    /**
     * 零点Hp的位置
     */
    public Point hpZeroPoint = new Point();//
    /**
     * 半血Hp的位置
     */
    public Point banHpZeroPoint = new Point();//
    /**
     * boss的位置
     */
    public Point bossPoint = new Point();//

    public Point sanjiaoPoint = new Point();
    /**
     * 菜单位置
     */
    public Point caiDanPoint = new Point();

    public Point masterCaidanPoint1 = new Point();

    public Point masterCaidanPoint2 = new Point();

    public Point petCaidanPoint = new Point();

    public Point battlePoint = new Point();

    public Point dialogCenter = new Point();

    public Point dialogSingleButton = new Point();

    public Point windowMiddle = new Point();

    private PointCollection() { }

    public static PointCollection getInstance() {
        return Single.INSTANCE;
    }


    public boolean obtainAllPoint(BufferedImage bi) {
        obtainBgrHpZeroXY(bi);
        if (hpZeroPointIsVaild(bi)) {
            LogUtils.d(TAG,"获取到角色血条:" + hpZeroPoint);
        } else {
            LogUtils.d(TAG,"获取到角色血条失败:" + hpZeroPoint);
            return false;
        }
        banHpZeroPoint = new Point(hpZeroPoint, 33,0);

        caiDanPoint = new Point(hpZeroPoint,0,120);
        masterCaidanPoint1 = new Point(caiDanPoint,0,280);
        masterCaidanPoint2 = new Point(caiDanPoint,0,310);
        petCaidanPoint = new Point(caiDanPoint,0,190);

        battlePoint = new Point(hpZeroPoint,60,490);

        sanjiaoPoint = new Point(hpZeroPoint,-190,0);
        //获得BOSS的坐标
        obtainBossPoint(bi); //
        //对齐NPC
        NpcName.alignAllPoint();

        dialogCenter = new Point(hpZeroPoint,-445,320);
        dialogSingleButton = new Point(hpZeroPoint,-291,319);
        windowMiddle = new Point(hpZeroPoint,-285,290);
        return true;
    }
    /**
     * 获取零点Hp位置的坐标
     * @param bi
     * @return
     */
    public void obtainBgrHpZeroXY(BufferedImage bi) {

        Point hpZeroCheckPoint = checkBgrPoint(bi, BGRCollection.getInstance().getHpZeroBGR());
        if (hpZeroCheckPoint.isVaild()) {
            this.hpZeroPoint = hpZeroCheckPoint;
        }

    }

    /**
     * 获得boss处的坐标
     * @param bi
     * @return
     */
    public void obtainBossPoint(BufferedImage bi) {
        if (!firstAndFifthNpcHave(bi)) {
            LogUtils.d(TAG,"抓取到boss坐标");
            bossPoint = new Point(hpZeroPoint.x - 325, hpZeroPoint.y + 458);
        } else {
            bossPoint = new Point(hpZeroPoint,- 325,+ 458);
        }
        BGRCollection.NPC_BGR_ALIGIN = BGRCollection.getInstance().getPointBGR(bi,bossPoint);
    }


    public boolean hpZeroPointIsVaild(BufferedImage bi) {
        if (hpZeroPoint.isVaild() &&
                BGRCollection.getInstance().getHpZeroBGR() == BGRCollection.getInstance().getPointBGR(bi,hpZeroPoint)) {
            return true;
        }
        return false;
    }



    public Point getNpcPoint(NpcName name) {
        switch (name) {
            case FIRST:
                return new Point(bossPoint.x - SizeConstant.INTEVAL*2,bossPoint.y);
            case SECOND:
                return new Point(bossPoint.x - SizeConstant.INTEVAL,bossPoint.y);
            case BOSS:
                return bossPoint;
            case FOURTH:
                return new Point(bossPoint.x + SizeConstant.INTEVAL,bossPoint.y);
            case FIFTH:
                return new Point(bossPoint.x + SizeConstant.INTEVAL*2,bossPoint.y);
            case UNDER_FIRST:
                return new Point(bossPoint.x - SizeConstant.INTEVAL*2,bossPoint.y + SizeConstant.INTEVAL);
            case UNDER_SECOND:
                return new Point(bossPoint.x - SizeConstant.INTEVAL,bossPoint.y + SizeConstant.INTEVAL);
            case UNDER_THIRD:
                return new Point(bossPoint.x ,bossPoint.y + SizeConstant.INTEVAL);
            case UNDER_FOURTH:
                return new Point(bossPoint.x + SizeConstant.INTEVAL,bossPoint.y + SizeConstant.INTEVAL);
            case UNDER_FIFTH:
                return new Point(bossPoint.x + SizeConstant.INTEVAL*2,bossPoint.y + SizeConstant.INTEVAL);
            default:
                return new Point();

        }
    }
    /**
     * 根据bgr扫描获取point
     * @param bi
     * @param bgr
     * @return
     */
    public Point checkBgrPoint(BufferedImage bi,int bgr) {
        int x = 1;
        int y = 1;
        Point point = new Point();

        m:while(true) {
            while(x < ScreenInfo.SCREEN_WIDTH -1) {
                int pixelColor = bi.getRGB(x, y);

                if (pixelColor == bgr) {
                    point.x = x;
                    point.y = y;
                    break m;
                }
                x++;
            }
            x = 1;
            y++;
            if (y >= ScreenInfo.SCREEN_HEIGHT - 1) {
                break ;
            }
        }
        return point;
    }

    /**
     * 判断第一个NPC和第五个NPC是否存在,存在就计算BOSS NPC的位置
     * @param bi
     * @return
     */
    private boolean firstAndFifthNpcHave(BufferedImage bi) {
        Point point1 = checkBgrPoint(bi,BGRCollection.NPC_BGR);
        Point point5 = new Point(point1, SizeConstant.INTEVAL * 4,0);

        if (BGRCollection.getInstance().getPointBGR(bi,point5) == BGRCollection.NPC_BGR) {
            bossPoint = new Point(point1.x + SizeConstant.INTEVAL * 2 , point1.y);
            return true;
        }
        return false;
    }

    private static class Single {
        private static PointCollection INSTANCE = new PointCollection();
    }
    public enum NpcName {
        FIRST,
        SECOND,
        BOSS,
        FOURTH,
        FIFTH,
        UNDER_FIRST,
        UNDER_SECOND,
        UNDER_THIRD,
        UNDER_FOURTH,
        UNDER_FIFTH;
        private Point mPoint;
        NpcName() {

        }

        /**
         * 对齐npc
         */
        public static void alignAllPoint() {
            NpcName[] npcs = NpcName.values();
            for (int i = 0; i < npcs.length; i++) {
                npcs[i].mPoint = PointCollection.getInstance().getNpcPoint(npcs[i]);
            }
        }

        public Point getPoint() {
            return mPoint;
        }
    }
}
