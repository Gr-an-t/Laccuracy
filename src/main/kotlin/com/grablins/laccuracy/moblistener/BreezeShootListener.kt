package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.util.ProjectileAccuracyUtil
import org.bukkit.entity.Breeze
import org.bukkit.entity.Fireball
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent

class BreezeShootListener(var accuracy: Double) : Listener{
    @EventHandler
    fun onBreezeShoot(event: ProjectileLaunchEvent) {
        val projectile = event.entity
        if (projectile !is Fireball) return
        val shooter = projectile.shooter
        if (shooter !is Breeze) return

        ProjectileAccuracyUtil.apply(projectile, accuracy)
    }
}