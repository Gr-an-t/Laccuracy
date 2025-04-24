package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.util.ArrowAccuracyUtil.apply
import org.bukkit.entity.Arrow
import org.bukkit.entity.Skeleton
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent


open class SkeletonShootListener(open var accuracy: Double) : Listener {
    @EventHandler
    open fun onSkeletonShoot(event: EntityShootBowEvent) {
        if (event.entity !is Skeleton) return
        val arrow = event.projectile as? Arrow ?: return
        apply(arrow, accuracy)
    }
}