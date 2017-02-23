package io.chuumong.cleancodekotlin.data.api.util

/**
 * Created by JongHunLee on 2017-02-23.
 */
interface Scheduler {
    fun background(): rx.Scheduler

    fun main(): rx.Scheduler
}