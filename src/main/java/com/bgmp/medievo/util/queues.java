package com.bgmp.medievo.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class queues {

    public static HashMap<String, String> invitequeue;
    public static HashMap<String, Boolean> confirmqueue;
    public static BiMap<String, String> replyqueue;
    public static HashMap<String, Player> tpaqueue;


    public static void loadqueues() {
         invitequeue = new HashMap<>();
         confirmqueue = new HashMap<>();
         replyqueue = HashBiMap.create();
         tpaqueue = new HashMap<>();
    }
}
