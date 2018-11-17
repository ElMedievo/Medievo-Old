package com.bgmp.medievo.util;

import java.util.HashMap;

public class queues {

    public static HashMap<String, String> invitequeue;
    public static HashMap<String, Boolean> confirmqueue;

    public static void loadqueues() {
         invitequeue = new HashMap<String, String>();
         confirmqueue = new HashMap<String, Boolean>();
    }
}
