package com.dw.application;

/**
 * @function 时间接口，注意实例化用TimeGet.getInstance()
 */
public class TimeGet {

    private static int time = 1;

    private TimeGet() {
    }

    /**
     * @function 实例化方法
     */
    public static TimeGet getInstance() {
        return new TimeGet();
    }

    /**
     * @return [hour,minute]
     */
    public int[] getTime() {
        int[] var = new int[2];
        var[0] = time / 60;
        var[1] = time % 60;
        return var;
    }

    /**
     * @param minutes 设定时间，分钟单位
     */
    public static void setTime(int minutes) {
        time = minutes;
    }

}
