package com.czq.shenwu.model.bo.strategy.onarena.strategy;

import com.czq.shenwu.model.Point;
import com.czq.shenwu.model.bo.*;
import com.czq.shenwu.model.bo.strategy.OnArenaStrategyImpl;
import com.czq.shenwu.model.vo.CheckStateVO;
import com.czq.shenwu.utils.ThreadUtil;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class FubenStartegy extends OnArenaStrategyImpl {
    @Override
    public void ready(BufferedImage bi) {
        //判断是否半血以上
        mouseSelect = isEnoughHp(bi) ? KeyEvent.BUTTON3_MASK : KeyEvent.BUTTON1_MASK;
    }




    @Override
    public void end() {
        MouseOperation.keyAltAndA();
        MouseOperation.keyAltAndA();
    }


    @Override
    protected Point confirmTarget(BufferedImage bi) {
        Point target = null;
        //判断是否为110副本最终战
        if (npcIsAlive(bi, PointCollection.NpcName.UNDER_FIRST) && npcIsAlive(bi, PointCollection.NpcName.UNDER_FIFTH)) {
            if (npcIsAlive(bi,PointCollection.NpcName.FIRST)) {
                //击杀沙唔净
                target = PointCollection.NpcName.FIRST.getPoint();
            } else if (npcIsAlive(bi, PointCollection.NpcName.SECOND)) {
                //击杀孙悟空
                target = PointCollection.NpcName.SECOND.getPoint();
            } else if (npcIsAlive(bi, PointCollection.NpcName.FOURTH)) {
                //击杀唐僧
                target = PointCollection.NpcName.FOURTH.getPoint();
            } else {
                target = null;
            }
        } else if (npcIsAlive(bi,PointCollection.NpcName.UNDER_FOURTH)) {
            //击杀下排第四个
            target = PointCollection.NpcName.UNDER_FOURTH.getPoint();
        } else if (npcIsAlive(bi,PointCollection.NpcName.UNDER_FIFTH)) {
            //击杀下排第五个
            target = PointCollection.NpcName.UNDER_FIFTH.getPoint();
        }  else if (npcIsAlive(bi, PointCollection.NpcName.BOSS) && mouseSelect == KeyEvent.BUTTON3_MASK) {
            //击杀BOSS
            target = PointCollection.NpcName.BOSS.getPoint();
        } else if (npcIsAlive(bi,PointCollection.NpcName.UNDER_THIRD)) {
            //击杀下排第三个
            target = PointCollection.NpcName.UNDER_THIRD.getPoint();
        } else if (npcIsAlive(bi,PointCollection.NpcName.UNDER_SECOND)) {
            //击杀下排第二个
            target = PointCollection.NpcName.UNDER_SECOND.getPoint();
        } else if (npcIsAlive(bi,PointCollection.NpcName.UNDER_FIRST)) {
            //击杀下排第一个
            target = PointCollection.NpcName.UNDER_FIRST.getPoint();
        }else {
            target = null;
        }
        return target;
    }

    @Override
    public void initStrategyCheckStateVO() {
        mCheckStateVO.setHandleDiolog(true);
    }


}
