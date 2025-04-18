package com.grablins.laccuracy.commands

import com.grablins.laccuracy.Laccuracy
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class SetAccuracyCommand : CommandExecutor, TabCompleter {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.size != 2) {
            sender.sendMessage("§eUsage: /setaccuracy <mob> <value>")
            return true
        }

        val mob = args[0].lowercase()
        val value = args[1].toDoubleOrNull()

        if (value == null) {
            sender.sendMessage("§cInvalid number.")
            return true
        }
        val config = Laccuracy.instance.config

        if (!config.contains("accuracy.$mob")) {
            sender.sendMessage("§cUnknown mob: §e$mob§c. Please check your config.")
            return true
        }

        Laccuracy.instance.updateAccuracyForMob(mob, value)
        sender.sendMessage("§aUpdated accuracy for §e$mob§a to §b$value")
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): List<String> {
        val config = Laccuracy.instance.config

        return when (args.size) {
            1 -> config.getConfigurationSection("accuracy")
                ?.getKeys(false)
                ?.toList()
                ?: emptyList()

            2 -> listOf("0.0", "0.2", "0.5", "1.0")
            else -> emptyList()
        }
    }
}