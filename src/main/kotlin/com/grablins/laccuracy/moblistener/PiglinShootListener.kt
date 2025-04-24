package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.util.ArrowAccuracyUtil
import org.bukkit.entity.Arrow
import org.bukkit.entity.Piglin
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent

class PiglinShootListener(var accuracy: Double) : Listener {
    @EventHandler
    fun onPiglinShoot(event: EntityShootBowEvent) {
        if (event.entity !is Piglin) return
        val arrow = event.projectile as? Arrow ?: return
        ArrowAccuracyUtil.apply(arrow, accuracy)
    }
}