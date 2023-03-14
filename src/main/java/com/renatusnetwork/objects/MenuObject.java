package com.renatusnetwork.objects;

import org.bukkit.inventory.Inventory;

import java.util.List;

public class MenuObject {
    private String title;
    private List<Inventory> pages;
    private int slots;
    private int pageSize; // Rows per page
    private int totalPages; // Total pages in menu
    private boolean isOpen;


}
