package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.util.ArrowAccuracyUtil
import org.bukkit.entity.Arrow
import org.bukkit.entity.WitherSkeleton
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent

class WitherSkeletonShootListener(var accuracy: Double) : Listener {
    @EventHandler
    fun onWitherSkeletonShoot(event: EntityShootBowEvent) {
        if (event.entity !is WitherSkeleton) return
        val arrow = event.projectile as? Arrow ?: return
        ArrowAccuracyUtil.apply(arrow, accuracy)
    }
}