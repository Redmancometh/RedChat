package RedChat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;
public class RedChat extends JavaPlugin
{
    public static Plugin pl;
    public static Essentials essentials;
    public void onEnable()
    {
	PluginManager pm = getServer().getPluginManager();
	getCommand("s").setExecutor(new StaffChat());
	getCommand("rank").setExecutor(new RankCommand());
	pm.registerEvents(new Chat(), this);
	essentials = (Essentials)Bukkit.getServer().getPluginManager().getPlugin("Essentials");
	pl = this;
    }
}
