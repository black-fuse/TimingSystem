package me.makkuusen.timing.system.tuning;

import lombok.Getter;
import lombok.Setter;
import me.makkuusen.timing.system.ItemBuilder;
import me.makkuusen.timing.system.tplayer.TPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Part {
    @Getter
    @Setter
    private String name;
    @Getter
    private String id;
    @Getter
    @Setter
    public PartCategory CategoryName;
    @Getter
    @Setter
    public Integer rating;
    @Getter
    @Setter
    private String description;
    @Setter
    private Material item;
    private Map<Attribute, Integer> attributes = new HashMap<>();

    public Part(){
        this.id = UUID.randomUUID().toString();
        this.rating = 0;

        addAttribute(Attribute.FORWARD_ACCEL, 5);
        addAttribute(Attribute.YAW_ACCEL, 5);
        addAttribute(Attribute.DEFAULT_SLIPPERINESS, 5);
        addAttribute(Attribute.PACKED_ICE_SLIPPERINESS, 5);
        addAttribute(Attribute.BLUE_ICE_SLIPPERINESS, 5);
        addAttribute(Attribute.TURNING_FORWARD_ACCEL, 5);
        addAttribute(Attribute.BACKWARD_ACCEL, 5);
    }

    public void addAttribute(Attribute name, Integer value){
        attributes.put(name, value);
    }

    public void removeAttribute(Attribute name){
        attributes.remove(name);
    }

    public Map<Attribute, Integer> getAttributes(){
        return attributes;
    }

    public Integer getValue(Attribute attribute){
        return attributes.get(attribute);
    }

    public ItemStack getItem(TPlayer tPlayer){
        if (this.item == null){
            return new ItemBuilder(Material.PUFFERFISH).setName(this.getName()).build();
        }
        ItemStack Item = new ItemBuilder(this.item).setName(this.getName()).build();

        List<Component> loreToSet = new ArrayList<>();

        loreToSet.add(Component.text(this.getDescription()));
        loreToSet.add(Component.text("rating: " + getRating()));

        for (Attribute thing : this.attributes.keySet()){
            loreToSet.add(Component.text(thing.toString() +": [" + attributes.get(thing) + "]"));
        }

        ItemMeta im = Item.getItemMeta();

        if (im != null) {
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            im.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
            im.addItemFlags(ItemFlag.HIDE_DYE);
            im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            im.displayName(Component.text(this.getName()).color(tPlayer.getTheme().getSecondary()));
            im.lore(loreToSet);
            Item.setItemMeta(im);
        }

        return Item;
    }
}
