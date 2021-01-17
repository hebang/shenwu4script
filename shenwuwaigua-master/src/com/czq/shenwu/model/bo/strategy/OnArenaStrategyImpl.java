package com.czq.shenwu.model.bo.strategy;

import com.czq.shenwu.model.Point;
import com.czq.shenwu.model.bo.BGRCollection;
import com.czq.shenwu.model.bo.MouseOperation;
import com.czq.shenwu.model.bo.PointCollection;
import com.czq.shenwu.model.bo.RobotOperation;
import com.czq.shenwu.model.bo.strategy.onarena.strategy.FenYaoStartegy;
import com.czq.shenwu.model.pojo.IThemeBGRInfo;
import com.czq.shenwu.model.pojo.ScreenInfo;
import com.czq.shenwu.model.vo.CheckStateVO;
import com.czq.shenwu.ui.MainJFrame;
import com.czq.shenwu.utils.LogUtils;
import com.czq.shenwu.utils.ThreadUtil;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class OnArenaStrategyImpl implements IStrategy {
    private static final String TAG = "OnArenaStrategyImpl";
    //鼠标按键KeyEvent.BUTTON3_MASK = 4代表右键,KeyEvent.BUTTON1_MASK = 16代表左键
    protected int mouseSelect = KeyEvent.BUTTON3_MASK;
    //战斗状态三角的BGR
    protected int onArenaSanjiaoBGR = IThemeBGRInfo.SAN_JIAO_XING;
    //战斗状态battlePointBGR
    protected int battlePointBGR = BGRCollection.getInstance().getBattlePointBGR();
    //战斗操作状态菜单的BGR
    protected int onArenacaiDanRGB = BGRCollection.getInstance().getCaiDanBGR();
    protected CheckStateVO mCheckStateVO = new CheckStateVO();
    public BGRCollection mBGRCollection = BGRCollection.getInstance();
    public PointCollection mPointCollection = PointCollection.getInstance();

    public void onArena(BufferedImage bi) {
//        获取point处的BGR
        int battlePointRGB = mBGRCollection.getPointBGR(bi, mPointCollection.battlePoint);
        //判断是否进入战斗
        int caiDanRGB = mBGRCollection.getPointBGR(bi, mPointCollection.caiDanPoint);
//      battlePointBGR  -5337829
        //      this.battlePointBGR  -15842748
//        caiDanRGB  -12943730
//       mBGRCollection.getCaiDanBGR()  -16564657
        System.out.println(battlePointRGB);
        System.out.println(this.battlePointBGR);
        System.out.println(caiDanRGB);
        System.out.println(mBGRCollection.getCaiDanBGR());
        if (battlePointRGB != this.battlePointBGR || caiDanRGB == mBGRCollection.getCaiDanBGR()) {
            if (caiDanRGB != mBGRCollection.getCaiDanBGR()) {
                MouseOperation.mouseMoveAndDoubleMouse(new Point(mPointCollection.bossPoint.x + 40, 320), KeyEvent.BUTTON3_MASK);
                LogUtils.d(TAG, "进入战斗!");
                int masterCaiDanRGB1 = mBGRCollection.getPointBGR(bi, mPointCollection.masterCaidanPoint1);
                int masterCaiDanRGB2 = mBGRCollection.getPointBGR(bi, mPointCollection.masterCaidanPoint2);
                int petCaiDanRGB = mBGRCollection.getPointBGR(bi, mPointCollection.petCaidanPoint);
                LogUtils.d(TAG, "masterCaidan:" + masterCaiDanRGB1 + ";" + "petCaidan:" + petCaiDanRGB);
                if (masterCaiDanRGB1 != onArenacaiDanRGB || masterCaiDanRGB2 == onArenacaiDanRGB || petCaiDanRGB == onArenacaiDanRGB) {
                    LogUtils.d(TAG, "进入策略!");
//                    回血回魔
                    renewHpAndMp();
//                    准备
                    ready(bi);
                    execute(bi);
                    //双击
                    end();
                    MouseOperation.mouseMoveAndDoubleMouse(mPointCollection.windowMiddle, KeyEvent.BUTTON3_MASK);
                    LogUtils.d(TAG, "策略执行完毕!");
                }
            }
        } else {
            LogUtils.d(TAG, "结束战斗!");
            if (mCheckStateVO.isHandleDiolog()) {
                if (mBGRCollection.getDialogCenter() == mBGRCollection.getPointBGR(bi, mPointCollection.dialogCenter)) {
                    LogUtils.d(TAG, "判断是否继续副本");
                    MouseOperation.mouseMoveAndDoubleMouse(mPointCollection.dialogCenter, KeyEvent.BUTTON1_MASK);
                    RobotOperation.getInstance().mouseMove(mPointCollection.windowMiddle);
                } else if (mBGRCollection.getDialogCenter() == mBGRCollection.getPointBGR(bi, mPointCollection.dialogSingleButton)) {
                    LogUtils.d(TAG, "判断是否进入副本");
                    MouseOperation.mouseMoveAndDoubleMouse(mPointCollection.dialogSingleButton, KeyEvent.BUTTON1_MASK);
                    RobotOperation.getInstance().mouseMove(mPointCollection.windowMiddle);
                }
            }
        }
        ThreadUtil.sleep(3000);
    }

    @Override
    public void execute(BufferedImage bi) {
        Point target = confirmTarget(bi);
        LogUtils.d(TAG, "目标坐标:" + target);
        if (target != null) MouseOperation.mouseMoveAndDoubleMouse(target, mouseSelect);
        else end();
    }

    /**
     * 确定目标的Point
     *
     * @param bi
     * @return
     */
    protected abstract Point confirmTarget(BufferedImage bi);

    /**
     * 初始化回血回魔的策略
     */
    public abstract void initStrategyCheckStateVO();

    @Override
    public CheckStateVO getStrategyCheckState() {
        return mCheckStateVO;
    }

    /**
     * 判断npc是否存在或是否活着
     *
     * @return
     */
    public boolean npcIsAlive(BufferedImage bi, PointCollection.NpcName name) {
        return BGRCollection.NPC_BGR_ALIGIN == BGRCollection.getInstance().getPointBGR(bi, name.getPoint()) ? true : false;
    }

    /**
     * 回血回魔
     */
    protected void renewHpAndMp() {
        if (mCheckStateVO.isRenewHp()) {
            renewHp();
        }
        if (mCheckStateVO.isRenewMp()) {
            renewMp();
        }
        if (mCheckStateVO.isRenewBbHp()) {
            renewBbHp();
        }
        if (mCheckStateVO.isRenewBbMp()) {
            renewBbMp();
        }
    }

    /**
     * 恢复人物HP
     */
    private void renewHp() {
        RobotOperation.getInstance().mouseMove(PointCollection.getInstance().banHpZeroPoint);
        ThreadUtil.sleep(50L);
        RobotOperation.getInstance().mouseRelease(KeyEvent.BUTTON3_MASK);
        ThreadUtil.sleep(100L);
    }

    /**
     * 恢复人物MP
     */
    private void renewMp() {
        RobotOperation.getInstance().mouseMove(new Point(PointCollection.getInstance().banHpZeroPoint, 0, 14));
        ThreadUtil.sleep(50L);
        RobotOperation.getInstance().mouseRelease(KeyEvent.BUTTON3_MASK);
        ThreadUtil.sleep(50L);
    }

    /**
     * 恢复宠物HP
     */
    private void renewBbHp() {
        RobotOperation.getInstance().mouseMove(new Point(PointCollection.getInstance().banHpZeroPoint, -100, 0));
        ThreadUtil.sleep(50L);
        RobotOperation.getInstance().mouseRelease(KeyEvent.BUTTON3_MASK);
        ThreadUtil.sleep(50L);
    }

    /**
     * 恢复宠物MP
     */
    private void renewBbMp() {
        RobotOperation.getInstance().mouseMove(new Point(PointCollection.getInstance().banHpZeroPoint, -100, 14));
        ThreadUtil.sleep(50L);
        RobotOperation.getInstance().mouseRelease(KeyEvent.BUTTON3_MASK);
        ThreadUtil.sleep(50L);
    }

    /**
     * 判断是否有足够半血
     *
     * @param bi
     * @return
     */
    public boolean isEnoughHp(BufferedImage bi) {
        return BGRCollection.getInstance().getBanHpBGR() == BGRCollection.getInstance().getPointBGR(bi, PointCollection.getInstance().banHpZeroPoint) ? true : false;
    }

    public PointCollection.NpcName pingKanTaget(BufferedImage bi) throws InterruptedException {
        PointCollection.NpcName target;
        if (npcIsAlive(bi, PointCollection.NpcName.UNDER_FIFTH)) {
            target = PointCollection.NpcName.UNDER_FIFTH;
        } else if (npcIsAlive(bi, PointCollection.NpcName.UNDER_FOURTH)) {
            target = PointCollection.NpcName.UNDER_FOURTH;
        } else if (npcIsAlive(bi, PointCollection.NpcName.UNDER_THIRD)) {
            target = PointCollection.NpcName.UNDER_THIRD;
        } else if (npcIsAlive(bi, PointCollection.NpcName.UNDER_SECOND)) {
            target = PointCollection.NpcName.UNDER_SECOND;
        } else if (npcIsAlive(bi, PointCollection.NpcName.UNDER_FIRST)) {
            target = PointCollection.NpcName.UNDER_FIRST;
        } else {
            target = null;
        }
        return target;
    }
}
