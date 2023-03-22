package com.renatusnetwork.managers;

import com.renatusnetwork.objects.MenuObject;

import java.util.HashMap;
import java.util.UUID;

public class MenuManager {
    private HashMap<String, MenuObject> menus = new HashMap<>();
    private HashMap<UUID, MenuObject> inMenu = new HashMap<>();

    public MenuManager () {}

    public HashMap<String, MenuObject> getMenus() {
        return menus;
    }

    public MenuObject getMenu(String menuName) { return menus.get(menuName); }
}
