package me.makkuusen.timing.system.tuning;

import java.util.*;

public class PartManager {
    private static Map<String, Part> parts = new HashMap<>();

    public PartManager(){
        Part StockOars = new Part("Stock Oars");
        Part StockRudder = new Part("Stock Rudder");
        Part StockHull = new Part("Stock Hull");

        StockOars.setCategoryName(PartCategory.OARS);
        StockRudder.setCategoryName(PartCategory.RUDDER);
        StockHull.setCategoryName(PartCategory.HULL);

        parts.put("1", StockOars);
        parts.put("2", StockRudder);
        parts.put("3", StockHull);
    }

    public static boolean addPart(Part part){
        if (partExists(part.getName())) return false;

        parts.put(part.getId(), part);
        return true;
    }

    public static boolean removePart(String id){
        return parts.remove(id) != null;
    }

    public static List<String> getPartNames() {
        List<String> names = new ArrayList<>();

        for (Part part : parts.values()) {
            names.add(part.getName());
        }

        return names;
    }

    public static Collection<Part> getParts() {
        return parts.values();
    }

    public static Part getPart(String id){
        return parts.get(id);
    }

    public static boolean partExists(String name) {
        return getPartByName(name) != null;
    }

    public static Part getPartByName(String name) {
        for (Part part : parts.values()) {
            if (part.getName().equalsIgnoreCase(name)) {
                return part;
            }
        }
        return null;
    }
}
