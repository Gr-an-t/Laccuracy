package com.grablins.laccuracy.moblistener
import com.grablins.laccuracy.util.ProjectileAccuracyUtil
import org.bukkit.entity.ThrownPotion
import org.bukkit.entity.Witch
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent

class WitchShootListener(var accuracy: Double) : Listener {
    @EventHandler
    fun onWitchShoot(event: ProjectileLaunchEvent) {
        val projectile = event.entity
        if (projectile !is ThrownPotion) return

        val shooter = projectile.shooter
        if (shooter !is Witch) return
        ProjectileAccuracyUtil.apply(projectile, accuracy)
    }
}