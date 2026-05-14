package me.makkuusen.timing.system.gui;

import me.makkuusen.timing.system.ItemBuilder;
import me.makkuusen.timing.system.tplayer.TPlayer;
import me.makkuusen.timing.system.tuning.Part;
import me.makkuusen.timing.system.tuning.PartCategory;
import me.makkuusen.timing.system.tuning.PartManager;
import net.kyori.adventure.text.Component;


public class PartSelectGui extends BaseGui{
    public PartSelectGui(TPlayer tPlayer, PartCategory category){
        super(Component.text(category.toString()), 3);
    }

    public GuiButton getCategoryButton(TPlayer tPlayer, PartCategory category){
        var button = new GuiButton(new ItemBuilder(category.getMaterial()).setName(category.toString()).build());
        return button;
    }

    public GuiButton getPartButton(TPlayer tPlayer, Part part){
        var button = new GuiButton(part.getItem(tPlayer));
        return button;
    }

    private void setButtons(TPlayer tPlayer, PartCategory category){
        setItem(getCategoryButton(tPlayer, category), 0);

        int x = 10;

        for (Part part : PartManager.getParts()){
            if (part.getCategoryName() == category){
                setItem(getPartButton(tPlayer, part), x);
                x++;
            }
        }
    }
}
