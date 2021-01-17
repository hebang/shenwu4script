package com.czq.shenwu.ui;

public interface IViewObserver {
    /**
     * 开启
     */
    void onStart();

    /**
     * 进入战斗
     */
    void onArena();

    /**
     * 结束战斗
     */
    void outArena();

    /**
     * 结束
     */
    void onStop();
}
