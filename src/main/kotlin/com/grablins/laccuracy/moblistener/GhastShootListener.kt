package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.util.ProjectileAccuracyUtil
import org.bukkit.entity.Fireball
import org.bukkit.entity.Ghast
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent

class GhastShootListener(var accuracy: Double) : Listener {
    @EventHandler
    fun onGhastShoot(event: ProjectileLaunchEvent) {
        val projectile = event.entity
        if (projectile !is Fireball) return

        val shooter = projectile.shooter
        if (shooter !is Ghast) return

        ProjectileAccuracyUtil.apply(projectile, accuracy)
    }
}