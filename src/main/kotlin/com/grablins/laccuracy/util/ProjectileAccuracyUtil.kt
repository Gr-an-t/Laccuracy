package com.grablins.laccuracy.util

import com.grablins.laccuracy.Laccuracy
import org.bukkit.Bukkit
import org.bukkit.entity.Projectile
import org.bukkit.util.Vector

object ProjectileAccuracyUtil {
    fun apply(projectile: Projectile, accuracy: Double) {
        val randomOffset = Vector(
            (Math.random() - 0.5) * accuracy,
            (Math.random() - 0.5) * accuracy,
            (Math.random() - 0.5) * accuracy
        )

        Bukkit.getScheduler().runTaskLater(Laccuracy.instance, Runnable {
            projectile.velocity = projectile.velocity.add(randomOffset)
        }, 1L)
    }
}