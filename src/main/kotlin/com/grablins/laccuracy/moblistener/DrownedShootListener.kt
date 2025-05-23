package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.util.ProjectileAccuracyUtil
import org.bukkit.entity.Drowned
import org.bukkit.entity.Trident
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent

class DrownedShootListener(var accuracy: Double) : Listener{
    @EventHandler
    fun onDrownedShoot(event: ProjectileLaunchEvent) {
        val projectile = event.entity
        if (projectile !is Trident) return

        val shooter = projectile.shooter
        if (shooter !is Drowned) return

        ProjectileAccuracyUtil.apply(projectile, accuracy)
    }
}