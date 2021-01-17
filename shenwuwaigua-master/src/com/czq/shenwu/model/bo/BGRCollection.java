package com.czq.shenwu.model.bo;

import com.czq.shenwu.model.Point;
import com.czq.shenwu.model.pojo.IThemeBGRInfo;
import com.czq.shenwu.model.pojo.themebgrinfoImpl.BlueThemeBGRImpl;



import java.awt.image.BufferedImage;

/**
 * 检测Bgr
 */
public class BGRCollection implements IThemeBGRInfo{

    private  IThemeBGRInfo themeBGRInfo = new BlueThemeBGRImpl();

    public static int NPC_BGR_ALIGIN = IThemeBGRInfo.NPC_BGR;

    private BGRCollection() {

    }

    public static BGRCollection getInstance() {
        return Single.INSTANCE;
    }


    public void setThemeBGRInfo(IThemeBGRInfo themeBGRInfo) {
        this.themeBGRInfo = themeBGRInfo;
    }

    /**'
     * 获取point处的BGR
     * @param bi
     * @param point
     * @return
     */
    public int getPointBGR(BufferedImage bi,Point point) {
        if (point.isVaild())
            return bi.getRGB(point.x,point.y);
        return 0;
    }

    @Override
    public int getHpZeroBGR() {
        return themeBGRInfo.getHpZeroBGR();
    }

    @Override
    public int getCaiDanBGR() {
        return themeBGRInfo.getCaiDanBGR();
    }
    @Override
    public int getBanHpBGR() {
        return themeBGRInfo.getBanHpBGR();
    }

    @Override
    public int getBattlePointBGR() {
        return themeBGRInfo.getBattlePointBGR();
    }

    @Override
    public int getDialogCenter() {
        return themeBGRInfo.getDialogCenter();
    }

    //单例内部类
    private static class Single {
        private static final  BGRCollection INSTANCE = new BGRCollection();
    }


}
