package com.czq.shenwu.model.bo.strategy.onarena.strategy;

import com.czq.shenwu.constant.SizeConstant;
import com.czq.shenwu.model.Point;
import com.czq.shenwu.model.bo.BGRCollection;
import com.czq.shenwu.model.bo.MouseOperation;
import com.czq.shenwu.model.bo.PointCollection;
import com.czq.shenwu.model.bo.strategy.OnArenaStrategyImpl;
import com.czq.shenwu.model.vo.CheckStateVO;
import com.czq.shenwu.ui.MainJFrame;
import com.czq.shenwu.utils.LogUtils;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class FenYaoStartegy extends OnArenaStrategyImpl {
    private static final String TAG = "FenYaoStartegy";
    @Override
    public void ready(BufferedImage bi) {
        //判断是否半血以上
        mouseSelect = isEnoughHp(bi) ? KeyEvent.BUTTON3_MASK : KeyEvent.BUTTON1_MASK;
    }

    /**
     * 确定目标的Point
     * @param bi
     * @return
     */
    @Override
    protected Point confirmTarget(BufferedImage bi) {
        Point target = null;
        LogUtils.d(TAG,"npcBGR:" + BGRCollection.getInstance().getPointBGR(bi,PointCollection.NpcName.UNDER_FOURTH.getPoint()));
        if (npcIsAlive(bi,PointCollection.NpcName.UNDER_FIFTH)) {
            target = PointCollection.NpcName.UNDER_FIFTH.getPoint();
            mouseSelect = KeyEvent.BUTTON1_MASK;
        } else {
            target = new Point(PointCollection.getInstance().hpZeroPoint, SizeConstant.PU_TONG_ZHEN_BOSS_ZERO_HP_DX
                            , SizeConstant.PU_TONG_ZHEN_BOSS_ZERO_HP_DY);
        }
        return target;
    }

    @Override
    public void initStrategyCheckStateVO() {
        mCheckStateVO.setRenewHp(true);
        mCheckStateVO.setRenewBbMp(true);
    }


    @Override
    public void end() {
        MouseOperation.keyAltAndA();
        MouseOperation.keyAltAndA();
    }

}
