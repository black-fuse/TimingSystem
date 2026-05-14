package me.makkuusen.timing.system.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import me.makkuusen.timing.system.theme.Theme;
import me.makkuusen.timing.system.tuning.Attribute;
import me.makkuusen.timing.system.tuning.Part;
import me.makkuusen.timing.system.tuning.PartCategory;
import me.makkuusen.timing.system.tuning.PartManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Map;

@CommandAlias("parts")
public class CommandParts extends BaseCommand {
    @Subcommand("create OARS|HULL|RUDDER")
    @CommandCompletion("<name>")
    @CommandPermission("%permissiontimingsystem_part_create")
    public void create(CommandSender sender,PartCategory category, String name){
        Theme theme = Theme.getTheme(sender);

        Part lePart = new Part(name);
        lePart.setCategoryName(category);

        PartManager.addPart(lePart);
        sender.sendMessage(Component.text("part was added").color(theme.getSuccess()));
    }

    @Subcommand("list")
    @CommandPermission("%permissiontimingsystem_part_list")
    public void list(CommandSender sender){
        Theme theme = Theme.getTheme(sender);

        sender.sendMessage(theme.getRefreshButton().clickEvent(ClickEvent.runCommand("/parts list"))
                .append(theme.getTitleLine(Component.text("parts").color(theme.getSecondary())))
        );

        for (String name : PartManager.getPartNames()){
            sender.sendMessage(Component.text(name).color(theme.getSecondary()));
        }
    }

    @Subcommand("delete")
    @CommandCompletion("@parts")
    public void delete(CommandSender sender, String name){
        Theme theme = Theme.getTheme(sender);

        if (!PartManager.removePart(name)) {
            sender.sendMessage(Component.text("Part not found").color(theme.getError()));
            return;
        }
        sender.sendMessage(Component.text("Part deleted").color(theme.getSuccess()));
    }

    @Subcommand("manage")
    @CommandCompletion("@parts")
    @CommandPermission("%permissiontimingsystem_part_manage")
    public void manage(CommandSender sender, String name){
        Theme theme = Theme.getTheme(sender);
        Part workingPart = PartManager.getPartByName(name);

        if (workingPart == null){
            sender.sendMessage(Component.text("Part not found").color(theme.getError()));
            return;
        }

        Map<Attribute, Integer> attributes = workingPart.getAttributes();

        // Title
        sender.sendMessage(theme.getRefreshButton().clickEvent(ClickEvent.runCommand("/parts manage " + name))
                .append(theme.getTitleLine(Component.text(workingPart.getName()).color(theme.getSecondary())))
        );

        // Description
        sender.sendMessage(Component.text(workingPart.getDescription() == null ? "No Description" : workingPart.getDescription()).clickEvent(ClickEvent.suggestCommand("/parts set description \"" + name + "\" ")));

        //rating
        sender.sendMessage(Component.text(workingPart.getRating()).clickEvent(ClickEvent.suggestCommand("/parts set rating \"" + name + "\" ")));

        // attributes
        for (Attribute attribute : attributes.keySet()){
            sendTuningAttribute(sender, workingPart, attribute);
        }
    }


    @Subcommand("set description")
    @CommandCompletion("@parts")
    @CommandPermission("%permissiontimingsystem_part_manage")
    public void setDescription(CommandSender sender, String part,String... description){
        Theme theme = Theme.getTheme(sender);
        Part thePart = PartManager.getPartByName(part);

        thePart.setDescription(String.join(" ", description));
        sender.sendMessage(Component.text(thePart.getName() + "'s description was changed").color(theme.getSuccess()));
    }

    @Subcommand("set attribute")
    @CommandCompletion("@parts")
    @CommandPermission("%permissiontimingsystem_part_manage")
    public void setAttribute(CommandSender sender, String part, Attribute attribute, Integer value) {
        Theme theme = Theme.getTheme(sender);
        Part thePart = PartManager.getPartByName(part);

        thePart.removeAttribute(attribute); // ik its lazy but icba right now
        thePart.addAttribute(attribute, value);
        sender.sendMessage(Component.text(thePart.getName() + "'s " + attribute.toString() + " has been set to " + value.toString()).color(theme.getSuccess()));
    }

    @Subcommand("set rating")
    @CommandCompletion("@parts")
    @CommandPermission("%permissiontimingsystem_part_manage")
    public void setRating(CommandSender sender, String part, Integer value){
        Theme theme = Theme.getTheme(sender);
        Part thePart = PartManager.getPartByName(part);

        thePart.setRating(value);
        sender.sendMessage(Component.text(thePart.getName() + "'s rating was set to " + value.toString()).color(theme.getSuccess()));
    }


    private void sendTuningAttribute(CommandSender sender, Part part, Attribute attribute){
        Theme theme = Theme.getTheme(sender);
        Component toSend;

        toSend = Component.text(attribute + ": ")
                        .color(theme.getPrimary())
                                .append(
                                        theme.getBrackets(Component.text(part.getValue(attribute).toString())
                                                .clickEvent(ClickEvent.suggestCommand("/parts set attribute \"" + part.getName() + "\" " + attribute.name() + " ") ))
                                        );

        sender.sendMessage(toSend);
    }

}
