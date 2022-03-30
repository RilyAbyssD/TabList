package rilyabyss.tabstatus;

import org.bukkit.plugin.java.JavaPlugin;
import rilyabyss.tabstatus.TabList.TabManager;

public final class TabStatus extends JavaPlugin {

    TabManager tabManager;

    @Override
    public void onEnable() {

        tabManager.showTab();

    }

    @Override
    public void onDisable() {

    }
}
