package me.makkuusen.timing.system.tuning;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class Part {
    @Getter
    private String name;
    @Getter
    private String id;
    @Getter
    public PartCatagory CatagoryName;
    @Getter
    @Setter
    public Integer rating;
    @Getter
    @Setter
    private String description;
    private Map<Attribute, Integer> attributes = new HashMap<>();

    public Part(String TheName){
        this.name = TheName;

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

    public Map getAttributes(){
        return attributes;
    }

    public Integer getValue(Attribute attribute){
        return attributes.get(attribute);
    }
}
