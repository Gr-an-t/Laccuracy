package com.grablins.laccuracy

import com.grablins.laccuracy.commands.GetAccuracyCommand
import com.grablins.laccuracy.commands.SetAccuracyCommand
import com.grablins.laccuracy.moblistener.BlazeShootListener
import com.grablins.laccuracy.moblistener.BoggedShootListener
import com.grablins.laccuracy.moblistener.BreezeShootListener
import com.grablins.laccuracy.moblistener.DrownedShootListener
import com.grablins.laccuracy.moblistener.EndDragonShootListener
import com.grablins.laccuracy.moblistener.GhastShootListener
import com.grablins.laccuracy.moblistener.PiglinShootListener
import com.grablins.laccuracy.moblistener.PillagerShootListener
import com.grablins.laccuracy.moblistener.SkeletonShootListener
import com.grablins.laccuracy.moblistener.StrayShootListener
import com.grablins.laccuracy.moblistener.WitchShootListener
import com.grablins.laccuracy.moblistener.WitherSkeletonShootListener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Laccuracy : JavaPlugin() {

    companion object {
        lateinit var instance: Laccuracy
            private set
    }

    // Listener Instances Go Here
    lateinit var skeletonListener: SkeletonShootListener
    lateinit var ghastListener: GhastShootListener
    lateinit var pillagerListener: PillagerShootListener
    lateinit var blazeListener: BlazeShootListener
    lateinit var drownedListener: DrownedShootListener
    lateinit var boggedListener: BoggedShootListener
    lateinit var breezeListener: BreezeShootListener
    lateinit var endDragonListener: EndDragonShootListener
    lateinit var piglinListener: PiglinShootListener
    lateinit var strayListener: StrayShootListener
    lateinit var witchListener: WitchShootListener
    lateinit var witherSkeletonListener: WitherSkeletonShootListener

    override fun onEnable() {
        instance = this

        val configFile = File(dataFolder, "config.yml")
        if (!configFile.exists()) {
            configFile.parentFile.mkdirs()
            val resource = getResource("config.yml")
            resource?.use { input ->
                configFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            } ?: logger.warning("Could not find config.yml in resources!")
        }

        saveDefaultConfig()
        reloadConfig()

        // Get Mob Accuracy Here
        val skeletonAccuracy = config.getDouble("accuracy.skeleton", 0.2)
        val ghastAccuracy = config.getDouble("accuracy.ghast", 0.2)
        val pillagerAccuracy = config.getDouble("accuracy.pillager", 0.2)
        val blazeAccuracy = config.getDouble("accuracy.blaze", 0.2)
        val drownedAccuracy = config.getDouble("accuracy.drowned", 0.2)
        val boggedAccuracy = config.getDouble("accuracy.bogged", 0.2)
        val breezeAccuracy = config.getDouble("accuracy.breeze", 0.2)
        val endDragonAccuracy = config.getDouble("accuracy.end_dragon", 0.2)
        val piglinAccuracy = config.getDouble("accuracy.piglin", 0.2)
        val strayAccuracy = config.getDouble("accuracy.stray", 0.2)
        val witchAccuracy = config.getDouble("accuracy.witch", 0.2)
        val witherSkeletonAccuracy = config.getDouble("accuracy.wither_skeleton", 0.2)


        // Assign Accuracy to Listeners Here
        skeletonListener = SkeletonShootListener(skeletonAccuracy)
        ghastListener = GhastShootListener(ghastAccuracy)
        pillagerListener = PillagerShootListener(pillagerAccuracy)
        blazeListener = BlazeShootListener(blazeAccuracy)
        drownedListener = DrownedShootListener(drownedAccuracy)
        boggedListener = BoggedShootListener(boggedAccuracy)
        breezeListener = BreezeShootListener(breezeAccuracy)
        endDragonListener = EndDragonShootListener(endDragonAccuracy)
        piglinListener = PiglinShootListener(piglinAccuracy)
        strayListener = StrayShootListener(strayAccuracy)
        witchListener = WitchShootListener(witchAccuracy)
        witherSkeletonListener = WitherSkeletonShootListener(witherSkeletonAccuracy)

        // Set up Event Registers Here
        server.pluginManager.registerEvents(skeletonListener, this)
        server.pluginManager.registerEvents(ghastListener, this)
        server.pluginManager.registerEvents(pillagerListener, this)
        server.pluginManager.registerEvents(blazeListener, this)
        server.pluginManager.registerEvents(drownedListener, this)
        server.pluginManager.registerEvents(boggedListener, this)
        server.pluginManager.registerEvents(breezeListener, this)
        server.pluginManager.registerEvents(endDragonListener, this)
        server.pluginManager.registerEvents(piglinListener, this)
        server.pluginManager.registerEvents(strayListener, this)
        server.pluginManager.registerEvents(witchListener, this)
        server.pluginManager.registerEvents(witherSkeletonListener, this)



        // Set up Commands Here
        getCommand("setaccuracy")?.setExecutor(SetAccuracyCommand())
        getCommand("setaccuracy")?.tabCompleter = SetAccuracyCommand()
        getCommand("getaccuracy")?.setExecutor(GetAccuracyCommand())
        getCommand("getaccuracy")?.tabCompleter = GetAccuracyCommand()
    }

    fun updateAccuracyForMob(mob: String, newAccuracy: Double) {
        config.set("accuracy.$mob", newAccuracy)
        saveConfig()

        when (mob.lowercase()) {
            "skeleton" -> skeletonListener.accuracy = newAccuracy
            "ghast" -> ghastListener.accuracy = newAccuracy
            "pillager" -> pillagerListener.accuracy = newAccuracy
            "blaze" -> blazeListener.accuracy = newAccuracy
            "drowned" -> drownedListener.accuracy = newAccuracy
            "bogged" -> boggedListener.accuracy = newAccuracy
            "breeze" -> breezeListener.accuracy = newAccuracy
            "end_dragon" -> endDragonListener.accuracy = newAccuracy
            "piglin" -> piglinListener.accuracy = newAccuracy
            "stray" -> strayListener.accuracy = newAccuracy
            "witch" -> witchListener.accuracy = newAccuracy
            "wither_skeleton" -> witherSkeletonListener.accuracy = newAccuracy
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
