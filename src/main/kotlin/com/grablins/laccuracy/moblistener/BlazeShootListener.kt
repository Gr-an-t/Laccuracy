package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.Laccuracy
import org.bukkit.Bukkit.getScheduler
import org.bukkit.entity.Blaze
import org.bukkit.entity.Fireball
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.util.Vector

class BlazeShootListener(var accuracy: Double) : Listener {
    @EventHandler
    fun onBlazeShoot(event: ProjectileLaunchEvent) {
        val projectile = event.entity
        if (projectile !is Fireball) return

        val shooter = projectile.shooter
        if (shooter !is Blaze) return

        val randomOffset = Vector(
            (Math.random() - 0.5) * accuracy,
            (Math.random() - 0.5) * accuracy,
            (Math.random() - 0.5) * accuracy
        )

        getScheduler().runTaskLater(Laccuracy.instance, Runnable {
            projectile.velocity = projectile.velocity.add(randomOffset)
        }, 1L)
    }
}