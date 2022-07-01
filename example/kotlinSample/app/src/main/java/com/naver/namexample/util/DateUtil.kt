package com.naver.namexample.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {
    private val SDF = SimpleDateFormat("HH:mm:ss.SSS", Locale.KOREA)
    val CURR_TIME_STR: String
        get() = SDF.format(Date())
}
