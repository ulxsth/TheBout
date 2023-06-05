package com.ulxsth.thebout.models;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GamePlayer {
    private static Set<GamePlayer> participants = new HashSet<>();

    private Player player;

    private Set<GamePlayer> protectTargets = new HashSet<>();
    private Set<GamePlayer> displayProtectTargets = new HashSet<>();
    private Set<GamePlayer> killTargets = new HashSet<>();
    private Set<GamePlayer> displayKillTargets = new HashSet<>();

    private GamePlayer(Player player) {
        this.player = player;
    }

    /**
     * プレイヤーを参加者に追加します
     * @param player: 参加者を示すPlayer
     */
    public static void addPlayer(Player player) {
        GamePlayer gamePlayer = new GamePlayer(player);
        participants.add(gamePlayer);
    }

    /**
     * 参加者から指定したプレイヤーを削除します
     * @param player: 削除するPlayer
     */
    public static void removePlayer(Player player) {
        if(!isExist(player)) {
            return;
        }

        for(GamePlayer gamePlayer: participants) {
            if(gamePlayer.getPlayer().equals(player)) {
                participants.remove(gamePlayer);
                break;
            }
        }
    }

    /**
     * 該当する参加者を検索します。
     * 存在しない場合はnullを返します。
     * @param player: 検索するプレイヤー
     * @return 該当する参加者
     */
    public static GamePlayer findByPlayer(Player player) {
        for(GamePlayer participant: participants) {
            if(participant.getPlayer().equals(player)) {
                return participant;
            }
        }

        return null;
    }

    /**
     * プレイヤーが参加者に存在するかを確認します
     * @param player: 確認するプレイヤー
     * @return 存在するかを示す真偽値
     */
    public static boolean isExist(Player player) {
        for(GamePlayer gamePlayer: participants) {
            if(!gamePlayer.getPlayer().equals(player)) {
                continue;
            }
            return true;
        }

        return false;
    }

    public static Set<GamePlayer> getParticipants() {
        return participants;
    }


    public Player getPlayer() {
        return player;
    }

    public Set<GamePlayer> getProtectTargets() {
        return protectTargets;
    }

    /**
     * 保護対象に参加者を追加します
     * @param player: 追加するプレイヤー
     * @param isDisplay: 表示リストに追加するか
     */
    public void addProtectTargets(Player player, boolean isDisplay) {
        GamePlayer gamePlayer = findByPlayer(player);
        this.protectTargets.add(gamePlayer);
        if(isDisplay) {
            displayProtectTargets.add(gamePlayer);
        }
    }

    /**
     * プレイヤーが保護対象に含まれているか確認します。
     * @param player: 調べるプレイヤー
     * @return 含まれるか
     */
    public boolean isExistOnProtectTarget(Player player) {
        for(GamePlayer gamePlayer: protectTargets) {
            if(gamePlayer.getPlayer().equals(player)) {
                return true;
            }
        }

        return false;
    }

    public Set<GamePlayer> getDisplayProtectTargets() {
        return displayProtectTargets;
    }

    /**
     * 保護対象の表示リストにプレイヤーを追加します
     * @param player: 追加するプレイヤー
     */
    public void addDisplayProtectTargets(Player player) {
        GamePlayer gamePlayer = findByPlayer(player);
        this.displayProtectTargets.add(gamePlayer);
    }

    /**
     * プレイヤーが保護対象の表示リストに含まれているか確認します。
     * @param player: 調べるプレイヤー
     * @return 含まれるか
     */
    public boolean isExistOnDisplayProtectTarget(Player player) {
        for(GamePlayer gamePlayer: displayProtectTargets) {
            if(gamePlayer.getPlayer().equals(player)) {
                return true;
            }
        }

        return false;
    }

    public Set<GamePlayer> getKillTargets() {
        return killTargets;
    }

    /**
     * 殺害対象リストにプレイヤーを追加します
     * @param player: 追加するプレイヤー
     * @param isDisplay: 表示リストに追加するか
     */
    public void addKillTargets(Player player, boolean isDisplay) {
        GamePlayer gamePlayer = findByPlayer(player);
        this.killTargets.add(gamePlayer);
        if(isDisplay) {
            displayKillTargets.add(gamePlayer);
        }
    }

    /**
     * プレイヤーが殺害対象に含まれているか確認します。
     * @param player: 調べるプレイヤー
     * @return 含まれるか
     */
    public boolean isExistOnKillTarget(Player player) {
        for(GamePlayer gamePlayer: killTargets) {
            if(gamePlayer.getPlayer().equals(player)) {
                return true;
            }
        }

        return false;
    }

    public Set<GamePlayer> getDisplayKillTargets() {
        return displayKillTargets;
    }

    /**
     * 殺害対象の表示リストにプレイヤーを追加します
     * @param player: 追加するプレイヤー
     */
    public void addDisplayKillTargets(Player player) {
        GamePlayer gamePlayer = findByPlayer(player);
        this.displayKillTargets.add(gamePlayer);
    }

    /**
     * プレイヤーが殺害対象に含まれているか確認します。
     * @param player: 調べるプレイヤー
     * @return 含まれるか
     */
    public boolean isExistOnDisplayKillTarget(Player player) {
        for(GamePlayer gamePlayer: displayKillTargets) {
            if(gamePlayer.getPlayer().equals(player)) {
                return true;
            }
        }

        return false;
    }
}
