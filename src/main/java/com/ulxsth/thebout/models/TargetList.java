package com.ulxsth.thebout.models;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TargetList {
    private final ArrayList<Player> targets = new ArrayList<>();
    private final ArrayList<Player> displayTargets = new ArrayList<>();

    public void addTarget(Player target, boolean isDisplay) {
        if(!isExistOnTarget(target)) {
            targets.add(target);
        }

        if(isDisplay) {
            addDisplayTarget(target);
        }
    }

    public void addDisplayTarget(Player target) {
        if(!isExistOnDisplayTarget(target)) {
            displayTargets.add(target);
        }
    }


    public void removeTarget(Player target, boolean isDisplay) {
        targets.remove(target);
        if(isDisplay) {
            removeDisplayTarget(target);
        }
    }
    public void removeDisplayTarget(Player target) {
        displayTargets.remove(target);
    }


    public boolean isExistOnTarget(Player target) {
        return targets.contains(target);
    }
    public boolean isExistOnDisplayTarget(Player target) {
        return displayTargets.contains(target);
    }
}
