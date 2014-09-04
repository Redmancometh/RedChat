package RedChat;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import com.earth2me.essentials.User;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

@SuppressWarnings("deprecation")
public class Chat implements Listener
{
    @EventHandler
    public void globalChat(PlayerChatEvent e)
    {
	Player p = e.getPlayer();
	boolean color = false;
	boolean format = false;
	PermissionUser pexUser = PermissionsEx.getUser(p);
	if (p.hasPermission("rc.chatcolor"))
	{
	    color = true;
	}
	if (p.hasPermission("rc.chatformat"))
	{
	    format = true;
	}
	e.setCancelled(true);
	User u = RedChat.essentials.getUser(p);
	if (!u.isMuted())
	{
	    if (e.getMessage().startsWith("!"))
	    {

		String message = e.getMessage().replaceFirst("!", "");
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		if (p.hasPermission("rc.global.write"))
		{
		    for (Player receiver : players)
		    {
			if (receiver.hasPermission("rc.global.read"))
			{
			    if (!format)
			    {
				message = deFormat(message);
			    }
			    String messageColored = colorize(message);
			    if (color)
			    {
				receiver.sendMessage(ChatColor.YELLOW + "[G]" + ChatColor.RESET + colorize(pexUser.getPrefix()) + " " + p.getDisplayName() + ": " + ChatColor.YELLOW + messageColored);
			    }
			    else
			    {
				receiver.sendMessage(ChatColor.YELLOW + "[G]" + ChatColor.RESET + colorize(pexUser.getPrefix()) + " " + p.getDisplayName() + ": " + ChatColor.YELLOW + message);
			    }
			}
		    }
		}
		else
		{
		    p.sendMessage(ChatColor.DARK_RED + "You do not have permission to talk in this chat!");
		}
	    }
	    else
	    {
		String message = ChatColor.DARK_GREEN + "[L]" + ChatColor.RESET + colorize(pexUser.getPrefix()) + " " + p.getDisplayName() + ": " + ChatColor.RESET + e.getMessage();
		if (!format)
		{
		    message = deFormat(message);
		}
		String messageColored = colorize(message);
		if (color)
		{
		    p.sendMessage(messageColored);
		}
		else
		{
		    p.sendMessage(message);
		}
		List<Entity> eList = p.getNearbyEntities(100, 100, 100);
		boolean peopleThere = false;
		for (Entity entity : eList)
		{
		    if (entity instanceof Player)
		    {
			peopleThere = true;
			Player receiver = (Player) entity;
			if (color)
			{
			    receiver.sendMessage(messageColored);
			}
			else
			{
			    receiver.sendMessage(message);
			}
		    }
		}
		if (!peopleThere)
		{
		    p.sendMessage(ChatColor.BLUE + "There isn't anybody there! Use !message to talk in global chat.");
		}
	    }
	}
	else{ p.sendMessage(ChatColor.DARK_RED + "You are muted!");}
    }

    public static String colorize(String msg)
    {
	String coloredMsg = "";
	for (int i = 0; i < msg.length(); i++)
	{
	    if (msg.charAt(i) == '&')
		coloredMsg += '§';
	    else
		coloredMsg += msg.charAt(i);
	}
	return coloredMsg;
    }

    public static String deFormat(String msg)
    {
	String[] replace =
	{ "&k", "&l", "&m", "&n", "&o", "&r" };
	for (String s : replace)
	{
	    msg = msg.replace(s, "");
	}
	return msg;
    }
}
