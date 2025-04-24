package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.util.ArrowAccuracyUtil
import org.bukkit.entity.Arrow
import org.bukkit.entity.Stray
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent

class StrayShootListener(var accuracy: Double) : Listener {
    @EventHandler
    fun onStrayShoot(event: EntityShootBowEvent) {
        if (event.entity !is Stray) return
        val arrow = event.projectile as? Arrow ?: return
        ArrowAccuracyUtil.apply(arrow, accuracy)
    }
}