package kevin.command.commands

import kevin.command.ICommand
import kevin.utils.ChatUtils

class HelpCommand : ICommand {
    override fun run(args: Array<out String>?) {
        ChatUtils().message("§b<Help>")
        ChatUtils().message("§a.t/.toggle <ModuleName> <on/off> §9Enable/Disable module.")
        ChatUtils().message("§a.h/.help §9Show this message.")
        ChatUtils().message("§a.binds §9Show binds.")
        ChatUtils().message("§a.bind <Module> <Key> §9Bind Module To a Key.")
        ChatUtils().message("§a.binds clear §9Clear binds.")
        ChatUtils().message("§a.login <Name> <Password?> §9Login.")
        ChatUtils().message("§a.say §9Say.")
        ChatUtils().message("§a.modulestate §9Show module state.")
        ChatUtils().message("§a.<ModuleName> <Option> <Value> §9Set module option value.")
    }
}