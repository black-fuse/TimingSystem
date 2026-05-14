package me.makkuusen.timing.system.gui;

import me.makkuusen.timing.system.ItemBuilder;
import me.makkuusen.timing.system.team.TeamManager;
import me.makkuusen.timing.system.theme.Text;
import me.makkuusen.timing.system.theme.messages.Gui;
import me.makkuusen.timing.system.tplayer.TPlayer;
import me.makkuusen.timing.system.tuning.PartCategory;
import org.bukkit.Material;

public class BoatSetupGui extends BaseGui{

    public BoatSetupGui(TPlayer tPlayer){
        super(Text.getGuiComponent(tPlayer.getPlayer(), Gui.SETTINGS_TITLE), 3);
        setButtons(tPlayer);
    }

    public GuiButton boatDisplay(TPlayer tPlayer){
        var button = new GuiButton(new ItemBuilder(Material.OAK_BOAT).setName(TeamManager.getPlayerTeams(tPlayer).toString() + " tuning" ).build());
        return button;
    }

    public GuiButton getCategoryButton(TPlayer tPlayer, PartCategory category){
        var button = new GuiButton(new ItemBuilder(category.getMaterial()).setName(category.toString()).build());
        return button;
    }

    private void setButtons(TPlayer tPlayer){
        setItem(boatDisplay(tPlayer), 14);

        int x = 20;

        for (PartCategory category : PartCategory.values()){
            setItem(getCategoryButton(tPlayer, category), x);
            x++;
        }
    }
}
