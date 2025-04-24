package com.grablins.laccuracy.util

import com.grablins.laccuracy.Laccuracy
import org.bukkit.Bukkit
import org.bukkit.entity.Arrow
import org.bukkit.util.Vector

object ArrowAccuracyUtil {
    fun apply(arrow: Arrow, accuracy: Double) {
        val randomOffset = Vector(
            (Math.random() - 0.5) * accuracy,
            (Math.random() - 0.5) * accuracy,
            (Math.random() - 0.5) * accuracy
        )

        Bukkit.getScheduler().runTaskLater(Laccuracy.instance, Runnable {
            arrow.velocity = arrow.velocity.add(randomOffset)
        }, 1L)
    }
}