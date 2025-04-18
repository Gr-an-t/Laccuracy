package com.grablins.laccuracy

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Laccuracy : JavaPlugin() {
    override fun onEnable() {
        val configFile = File(dataFolder, "config.yml")

        if (!configFile.exists()) {
            // Ensure the parent folder exists
            configFile.parentFile.mkdirs()

            // Copy the default config from resources
            val resource = getResource("config.yml")
            resource?.use { input ->
                configFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            } ?: logger.warning("Could not find config.yml in resources!")
        }

        val config = YamlConfiguration.loadConfiguration(configFile)
        val accuracy = config.getDouble("inaccuracy", 0.2)

        server.pluginManager.registerEvents(SkeletonShootListener(accuracy), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
