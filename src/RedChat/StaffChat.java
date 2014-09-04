package RedChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class StaffChat implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
	Player sent = (Player) sender;
	if (cmd.getName().equalsIgnoreCase("s"))
	{
	    if (sent.hasPermission("rc.staff.write"))
	    {
		@SuppressWarnings("deprecation")
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		String message = "";
		for(String s : args)
		{
		    message+=s+" ";
		}
		for (Player p : players)
		{
		    PermissionUser pexUser = PermissionsEx.getUser((Player) sender);
		    if (p.hasPermission("rc.staff.read"))
		    {
			p.sendMessage(ChatColor.GREEN + "[S] " + ChatColor.RESET + Chat.colorize(pexUser.getPrefix()) + " " + sent.getDisplayName() + ": " + ChatColor.GREEN + Chat.colorize(message));
		    }
		}
	    }
	    else
	    {
		sent.sendMessage(ChatColor.DARK_RED+"You do not have permission to talk in this chat!");
	    }
	}
	return true;
    }

}
