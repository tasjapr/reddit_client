package com.example.redditclient.utils

import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.LinkedHashMap


object DateFormatter {

    val times: MutableMap<String, Long> = LinkedHashMap()

    init {
        times["year"] = TimeUnit.DAYS.toMillis(365)
        times["month"] = TimeUnit.DAYS.toMillis(30)
        times["week"] = TimeUnit.DAYS.toMillis(7)
        times["day"] = TimeUnit.DAYS.toMillis(1)
        times["hour"] = TimeUnit.HOURS.toMillis(1)
        times["minute"] = TimeUnit.MINUTES.toMillis(1)
        times["second"] = TimeUnit.SECONDS.toMillis(1)
    }

    @JvmOverloads
    fun toRelative(duration: Long, maxLevel: Int = times.size): String {
        var duration = duration
        val res = StringBuilder()
        var level = 0
        for ((key, value) in times) {
            val timeDelta = duration / value
            if (timeDelta > 0) {
                res.append(timeDelta)
                    .append(" ")
                    .append(key)
                    .append(if (timeDelta > 1) "s" else "")
                    .append(", ")
                duration -= value * timeDelta
                level++
            }
            if (level == maxLevel) {
                break
            }
        }
        if ("" == res.toString()) {
            return "0 seconds timeAgo"
        } else {
            res.setLength(res.length - 2)
            res.append(" timeAgo")
            return res.toString()
        }
    }

    fun toRelative(start: Date, end: Date): String {
        assert(start.after(end))
        return toRelative(end.time - start.time)
    }

    fun toRelative(start: Date, end: Date, level: Int): String {
        assert(start.after(end))
        return toRelative(end.time - start.time, level)
    }
}