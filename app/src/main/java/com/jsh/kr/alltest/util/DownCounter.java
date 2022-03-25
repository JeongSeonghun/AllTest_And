package com.jsh.kr.alltest.util;

import java.util.concurrent.TimeUnit;

public class DownCounter extends Thread{
    private final static String TAG = DownCounter.class.getSimpleName();
    private final int sleepCnt = 200;

    private int startCnt = 0;
    private int step = 1;
    private long cnt = 0;

    private OnDownCounterListener downCountListener;

    private boolean isRunning = false;

    public DownCounter(int startCnt) {
        this.startCnt = startCnt;
        this.cnt = startCnt;
    }

    public void setDownCountListener(OnDownCounterListener downCountListener) {
        this.downCountListener = downCountListener;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void stopCount() {
        isRunning = false;
    }

    @Override
    public void run() {
        super.run();
        long startTime = System.currentTimeMillis();
        long currentSeconds = 0;
        isRunning = true;

        LogUtil.d(TAG, "down count start : "+cnt);
        if (downCountListener != null) {
            downCountListener.onTick(cnt);
        }

        while (isRunning && cnt > 0) {
            long current = System.currentTimeMillis();
            long afterSeconds = TimeUnit.MILLISECONDS.toSeconds(current - startTime);

            if (afterSeconds > currentSeconds) {
                currentSeconds = afterSeconds;
                cnt = startCnt - (currentSeconds * step);
                LogUtil.d(TAG, "down count : "+cnt);

                if (downCountListener != null) {
                    downCountListener.onTick(cnt);
                }
            }

            try {
                sleep(sleepCnt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
