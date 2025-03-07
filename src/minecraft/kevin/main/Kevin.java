package kevin.main;

import kevin.command.CommandManager;
import kevin.event.ClientShutdownEvent;
import kevin.event.EventManager;
import kevin.file.FileManager;
import kevin.module.ModuleManager;
import kevin.module.modules.render.ClickGui;
import kevin.module.modules.render.HUD;
import kevin.utils.FontManager;
import org.lwjgl.opengl.Display;

public enum Kevin {

    getInstance();

    public String name = "Kevin";
    public String version = "b1.0";

    public ModuleManager moduleManager;
    public FileManager fileManager;
    public EventManager eventManager;
    public CommandManager commandManager;
    public FontManager fontManager;
    public ClickGui.ClickGUI clickGUI;

    public String cStart = "§l§7[§l§9Kevin§l§7]";

    public void run() {
        moduleManager = new ModuleManager();
        fileManager = new FileManager();
        commandManager = new CommandManager();
        eventManager = new EventManager();
        fontManager = new FontManager();
        fontManager.loadFonts();
        Display.setTitle(name + " " +version +" | Minecraft 1.8.9");
        moduleManager.load();
        fileManager.load();
        commandManager.load();
        clickGUI = new ClickGui.ClickGUI();
    }

    public void stop() {
        this.eventManager.callEvent(new ClientShutdownEvent());
        fileManager.saveAllConfigs();
    }
}
