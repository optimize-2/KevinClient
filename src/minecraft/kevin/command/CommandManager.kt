package kevin.command

import kevin.command.commands.*
import kevin.main.Kevin
import kevin.utils.ChatUtils

class CommandManager {
    val commands = HashMap<Array<String>,ICommand>()

    private val prefix = "."

    fun load(){
        commands[arrayOf("t","toggle")] = ToggleCommand()

        commands[arrayOf("h","help")] = HelpCommand()

        commands[arrayOf("bind")] = BindCommand()

        commands[arrayOf("binds")] = BindsCommand()

        val modulesCommand = arrayListOf<String>()
        for (m in Kevin.getInstance.moduleManager.getModules()) modulesCommand.add(m.getName())
        commands[modulesCommand.toTypedArray()] = ValueCommand()

        commands[arrayOf("say")] = SayCommand()

        commands[arrayOf("login")] = LoginCommand()

        commands[arrayOf("modulestate")] = StateCommand()
    }

    fun execCommand(message: String): Boolean{
        if (!message.startsWith(prefix)) return false
        val run = message.split(prefix).size > 1
        if (run) {
            val second = message.removePrefix(prefix).split(" ")
            val list = ArrayList<String>()
            list.addAll(second)
            val key = list[0]
            val command = getCommand(key)
            if (command != null) {
                if (command !is ValueCommand) list.remove(key)
                command.run(list.toTypedArray())
            } else {
                ChatUtils().message("${Kevin.getInstance.cStart} §l§4Command Not Found! Use .help for help")
            }
        }else {
            ChatUtils().message("${Kevin.getInstance.cStart} §l§4Command Not Found! Use .help for help")
        }
        return true
    }

    fun getCommand(key: String): ICommand? {
        for (entry in commands.entries){
            for (s in entry.key){
                if (s.equals(key,ignoreCase = true)) return entry.value
            }
        }
        return null
    }

}