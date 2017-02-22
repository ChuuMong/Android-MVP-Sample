package io.chuumong.cleancodejava.data.api.util;

/**
 * Created by JongHunLee on 2017-02-22.
 */

public interface Scheduler {

    rx.Scheduler main();

    rx.Scheduler background();
}
