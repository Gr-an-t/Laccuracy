package com.grablins.laccuracy.moblistener

import com.grablins.laccuracy.Laccuracy
import org.bukkit.Bukkit
import org.bukkit.entity.Arrow
import org.bukkit.entity.Pillager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.util.Vector

class PillagerShootListener(var accuracy: Double) : Listener {
    @EventHandler
    fun onPillagerShoot(event: EntityShootBowEvent) {
        if (event.entity !is Pillager) return

        val arrow = event.projectile as? Arrow ?: return
        val velocity = arrow.velocity

        val randomOffset = Vector(
            (Math.random() - 0.5) * accuracy,
            (Math.random() - 0.5) * accuracy,
            (Math.random() - 0.5) * accuracy
        )

        Bukkit.getScheduler().runTaskLater(Laccuracy.instance, Runnable {
            arrow.velocity = arrow.velocity.add(randomOffset)
        }, 1L)
    }
}