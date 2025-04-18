package com.grablins.laccuracy.commands

import com.grablins.laccuracy.Laccuracy
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class GetAccuracyCommand : CommandExecutor, TabCompleter{
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.size != 1) {
            sender.sendMessage("§eUsage: /getaccuracy <mob>")
            return true
        }

        val mob = args[0].lowercase()
        val config = Laccuracy.instance.config


        if (!config.contains("accuracy.$mob")) {
            sender.sendMessage("§cUnknown mob: §e$mob§c. Please check your config.")
            return true
        }

        val currentAccuracy = config.getDouble("accuracy.$mob", 0.2)
        sender.sendMessage("§aThe current accuracy for §e$mob§a is §b$currentAccuracy")
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String?>? {
        val config = Laccuracy.instance.config

        return when (args.size) {
            1 -> config.getConfigurationSection("accuracy")
                ?.getKeys(false)
                ?.toList()
                ?: emptyList()

            else -> emptyList()
        }
    }

}