package com.czq.shenwu.controller.controllerImpl;


import com.czq.shenwu.constant.Strategy;
import com.czq.shenwu.controller.IController;
import com.czq.shenwu.model.Point;
import com.czq.shenwu.model.bo.BGRCollection;
import com.czq.shenwu.model.bo.PointCollection;
import com.czq.shenwu.model.bo.RobotOperation;
import com.czq.shenwu.model.bo.strategy.OnArenaStrategyImpl;
import com.czq.shenwu.model.bo.strategy.onarena.strategy.FenYaoStartegy;
import com.czq.shenwu.model.bo.strategy.onarena.strategy.FubenStartegy;
import com.czq.shenwu.model.bo.strategy.onarena.strategy.MiaoXiFenYaoStrategyImpl;
import com.czq.shenwu.model.bo.strategy.onarena.strategy.MiaoXiStartegy;
import com.czq.shenwu.model.pojo.IThemeBGRInfo;
import com.czq.shenwu.model.pojo.ScreenInfo;
import com.czq.shenwu.model.pojo.themebgrinfoImpl.BlueThemeBGRImpl;
import com.czq.shenwu.model.pojo.themebgrinfoImpl.GreenJDThemeBGRImpl;
import com.czq.shenwu.model.vo.CheckStateVO;
import com.czq.shenwu.ui.view.IView;
import com.czq.shenwu.utils.LogUtils;
import com.czq.shenwu.utils.ThreadUtil;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Controller implements IController {
    private static final String TAG = "Controller!";
    OnArenaStrategyImpl mStrategy = new FenYaoStartegy();

    IView mView;


    PointCollection mPointCollection = PointCollection.getInstance();

    BGRCollection mBGRCollection = BGRCollection.getInstance();

    ExecutorService mThreadPool = Executors.newCachedThreadPool();

    public Controller() {
    }


    @Override
    public void ready() {
        LogUtils.d(TAG, "Controller.ready start!");
        if (mView != null) {
            mStrategy.initStrategyCheckStateVO();
            mView.setCheckButtonState(mStrategy.getStrategyCheckState());
        }
        LogUtils.d(TAG, "Controller.ready end!");
    }

    @Override
    public void onBattle() {
        if (mView == null) {
            return;
        }

        Runnable run = new Runnable() {
            @Override
            public void run() {
                BufferedImage bi = getBufferedImage();
                mPointCollection.obtainAllPoint(bi);
                while (mView.isStart()) {
                    LogUtils.d(TAG, "进入循环");
                    bi = getBufferedImage();
                    if (!mPointCollection.hpZeroPointIsVaild(bi)) {
                        LogUtils.d(TAG, "hpzero异常!");
                        mPointCollection.obtainAllPoint(bi);
                    }

                    if (mStrategy != null) {
                        mStrategy.onArena(bi);
                    }
                    bi = null;
                }
            }
        };
        mThreadPool.execute(run);

    }


    @Override
    public void bindView(IView view) {
        mView = view;
    }

    @Override
    public void unBindView() {

    }

    @Override
    public void startegyChange(String startegyName) {
        switch (Strategy.getStrategy(startegyName)) {
            case FENYAO:
                mStrategy = new FenYaoStartegy();
                break;
            case FUBEN:
                mStrategy = new FubenStartegy();
                break;
            case MIAOXIFENYAO:
                mStrategy = new MiaoXiFenYaoStrategyImpl();
                break;
            case MIAOXIHUODONG:
                mStrategy = new MiaoXiStartegy();
                break;
        }


        mStrategy.initStrategyCheckStateVO();
        System.out.println(mStrategy.getStrategyCheckState());
        mView.setCheckButtonState(mStrategy.getStrategyCheckState());
    }

    @Override
    public void themeChange(String themeName) {
        if (IThemeBGRInfo.THEME_TYPES[0].equals(themeName)) {
            BGRCollection.getInstance().setThemeBGRInfo(new BlueThemeBGRImpl());
        } else if (IThemeBGRInfo.THEME_TYPES[1].equals(themeName)) {
            mBGRCollection.setThemeBGRInfo(new GreenJDThemeBGRImpl());
        }
    }

    @Override
    public void checkButtonStateChange(CheckStateVO checkStateVO) {
        LogUtils.d(TAG, "checkButtonStateChange:" + checkStateVO);
        mStrategy.getStrategyCheckState().setRenewHp(checkStateVO.isRenewHp());
        mStrategy.getStrategyCheckState().setRenewMp(checkStateVO.isRenewMp());
        mStrategy.getStrategyCheckState().setRenewBbHp(checkStateVO.isRenewBbHp());
        mStrategy.getStrategyCheckState().setRenewBbMp(checkStateVO.isRenewBbMp());
        mStrategy.getStrategyCheckState().setHandleDiolog(checkStateVO.isHandleDiolog());
    }


    /**
     * 获取3张屏幕实时图片
     *
     * @return List
     */
    protected List<BufferedImage> listBufferedImage() {
        List<BufferedImage> list = new ArrayList<>();
        list.add(getBufferedImage());
        ThreadUtil.sleep(50l);
        list.add(getBufferedImage());
        ThreadUtil.sleep(50l);
        list.add(getBufferedImage());
        return list;
    }

    /**
     * 获取屏幕实时图片
     *
     * @return BufferedImage
     */
    protected BufferedImage getBufferedImage() {
        return RobotOperation.getInstance().createScreenCapture(ScreenInfo.rec);
    }
}
