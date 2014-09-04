package RedChat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class RankCommand implements CommandExecutor
{
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
	if(cmd.getName().equalsIgnoreCase("rank")&&sender.hasPermission("rc.rank")&&args.length==1)
	{
	    Player p = (Player)sender;
	    Player getHim = Bukkit.getServer().getPlayer(args[0]);
	    if(getHim!=null)
	    {
		PermissionUser pexUser = PermissionsEx.getUser(getHim);
		for(String s : pexUser.getGroupsNames())
		{
		    p.sendMessage(Chat.colorize(getHim.getName())+" "+s);
		}
	    }
	}
	return true;
    }
}
