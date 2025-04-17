package com.grablins.laccuracy

import org.bukkit.entity.Arrow
import org.bukkit.entity.Skeleton
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.util.Vector

class SkeletonShootListener(private val inaccuracy: Double) : Listener {
    @EventHandler
    fun onSkeletonShoot(event: EntityShootBowEvent) {
        if (event.entity !is Skeleton) return

        val arrow = event.projectile as? Arrow ?: return
        val velocity = arrow.velocity

        val randomOffset = Vector(
            (Math.random() - 0.5) * inaccuracy,
            (Math.random() - 0.5) * inaccuracy,
            (Math.random() - 0.5) * inaccuracy
        )
    }
}