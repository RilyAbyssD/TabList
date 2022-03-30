package rilyabyss.tabstatus.TabList;

import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.scheduler.BukkitRunnable;
import rilyabyss.tabstatus.TabStatus;

import java.lang.reflect.Field;

public class TabManager implements Listener {

    TabStatus plugin;


    public void showTab() {

        IChatBaseComponent headerComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + "§aようこそ！サーバーへ！" + "\"}");
        IChatBaseComponent footerComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + "Minecraft.net" + "\"}");

        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter((PacketDataSerializer) headerComponent);

        new BukkitRunnable() {
            @Override
            public void run() {

                try {

                    Field a = packet.getClass().getDeclaredField("header");
                    Field b = packet.getClass().getDeclaredField("footer");
                    a.setAccessible(true);
                    b.setAccessible(true);

                    Field fieldA = packet.getClass().getDeclaredField("a");
                    fieldA.setAccessible(true);
                    fieldA.set(packet, headerComponent);
                    Field fieldB = packet.getClass().getDeclaredField("b");
                    fieldB.setAccessible(true);
                    fieldB.set(packet, footerComponent);
                    if (Bukkit.getOnlinePlayers().size() == 0)
                        return;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer) player).getHandle().b.sendPacket(packet);
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }

        }.runTaskTimer(plugin, 0, 20);

    }


}
