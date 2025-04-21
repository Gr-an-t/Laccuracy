package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.Laccuracy
import org.bukkit.Bukkit
import org.bukkit.entity.Fireball
import org.bukkit.entity.Ghast
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.util.Vector

class GhastShootListener(var accuracy: Double) : Listener {
    @EventHandler
    fun onGhastShoot(event: ProjectileLaunchEvent) {
        val projectile = event.entity
        if (projectile !is Fireball) return

        val shooter = projectile.shooter
        if (shooter !is Ghast) return

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