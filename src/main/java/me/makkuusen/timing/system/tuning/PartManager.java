package me.makkuusen.timing.system.tuning;

import java.util.*;

public class PartManager {
    Map<String, Part> parts = new HashMap<>();

    public PartManager(){
        parts.put("1", new Part("Stock Oars"));
        parts.put("2", new Part("Stock Oars"));
        parts.put("3", new Part("Stock Oars"));
    }

    public boolean addPart(Part part){
        if (partExists(part.getName())) return false;

        parts.put(part.getId(), part);
        return true;
    }

    public boolean removePart(String id){
        return parts.remove(id) != null;
    }

    public List<String> getPartNames() {
        List<String> names = new ArrayList<>();

        for (Part part : parts.values()) {
            names.add(part.getName());
        }

        return names;
    }

    public Collection<Part> getParts() {
        return parts.values();
    }

    public Part getPart(String id){
        return parts.get(id);
    }

    public boolean partExists(String name) {
        return getPartByName(name) != null;
    }

    public Part getPartByName(String name) {
        for (Part part : parts.values()) {
            if (part.getName().equalsIgnoreCase(name)) {
                return part;
            }
        }
        return null;
    }
}
