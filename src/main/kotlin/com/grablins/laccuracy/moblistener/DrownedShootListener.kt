package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.Laccuracy
import org.bukkit.Bukkit
import org.bukkit.entity.Drowned
import org.bukkit.entity.Trident
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.util.Vector

class DrownedShootListener(var accuracy: Double) : Listener{
    @EventHandler
    fun onDrownedShoot(event: ProjectileLaunchEvent) {
        val projectile = event.entity
        if (projectile !is Trident) return

        val shooter = projectile.shooter
        if (shooter !is Drowned) return

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