package com.ulxsth.thebout.models;

import org.bukkit.entity.Player;

import java.sql.Array;
import java.util.*;


public class GamePlayer {
    private static final List<GamePlayer> participants = new ArrayList<>();

    private final Player player;

    private final List<GamePlayer> protectTargets = new ArrayList<>();
    private final List<GamePlayer> displayProtectTargets = new ArrayList<>();
    private final List<GamePlayer> killTargets = new ArrayList<>();
    private final List<GamePlayer> displayKillTargets = new ArrayList<>();

    private GamePlayer(Player player) {
        this.player = player;
    }

    public boolean equals(Player player) {
        return this.getPlayer().getUniqueId().equals(player.getUniqueId());
    }

    /**
     * プレイヤーを参加者に追加します
     * @param player: 参加者を示すPlayer
     */
    public static void addPlayer(Player player) {
        if (isExist(player)) {
            return;
        }
        GamePlayer gamePlayer = new GamePlayer(player);
        participants.add(gamePlayer);
    }

    /**
     * 参加者から指定したプレイヤーを削除します
     * @param player: 削除するPlayer
     */
    public static void removePlayer(Player player) {
        for(GamePlayer gamePlayer: participants) {
            if(gamePlayer.getPlayer().equals(player)) {
                participants.remove(gamePlayer);
                break;
            }
        }
    }

    /**
     * 参加者のカードをシャッフルします。
     */
    public static void assignAllPlayer() {
        // TODO: 役職ごとに配布カードの処理をする

        Random rand = new Random();

        GamePlayer newGamePlayer;
        List<GamePlayer> remainKillTarget = participants;
        List<GamePlayer> remainProtectTarget = participants;

        for(GamePlayer gamePlayer: participants) {
            newGamePlayer = new GamePlayer(gamePlayer.getPlayer());

            List<GamePlayer> remainKillTargetCopy = new ArrayList<>(remainKillTarget);
            remainKillTargetCopy.remove(findByPlayer(gamePlayer.getPlayer()));
            GamePlayer killTarget = (GamePlayer) remainKillTargetCopy.get(rand.nextInt(remainKillTarget.size()));

            List<GamePlayer> remainProtectTargetCopy = new ArrayList<>(remainProtectTarget);
            remainProtectTargetCopy.remove(findByPlayer(gamePlayer.getPlayer()));
            remainProtectTargetCopy.remove(findByPlayer(killTarget.getPlayer()));
            GamePlayer protectTarget = (GamePlayer) remainProtectTargetCopy.get(rand.nextInt(remainProtectTarget.size()));

            newGamePlayer.addKillTarget(killTarget.getPlayer(), true);
            newGamePlayer.addProtectTarget(protectTarget.getPlayer(), true);
            remainKillTarget.remove(killTarget);
            remainProtectTargetCopy.remove(protectTarget);
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
            if(!gamePlayer.equals(player)) {
                continue;
            }
            return true;
        }

        return false;
    }

    public static List<GamePlayer> getParticipants() {
        return participants;
    }


    public Player getPlayer() {
        return player;
    }

    public List<GamePlayer> getProtectTargets() {
        return protectTargets;
    }

    /**
     * 保護対象に参加者を追加します
     * @param player: 追加するプレイヤー
     * @param isDisplay: 表示リストに追加するか
     */
    public void addProtectTarget(Player player, boolean isDisplay) {
        if (isExist(player)) {
            return;
        }

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

    public List<GamePlayer> getDisplayProtectTargets() {
        return displayProtectTargets;
    }

    /**
     * 保護対象の表示リストにプレイヤーを追加します
     * @param player: 追加するプレイヤー
     */
    public void addDisplayProtectTarget(Player player) {
        if (isExist(player)) {
            return;
        }

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

    public List<GamePlayer> getKillTargets() {
        return killTargets;
    }

    /**
     * 殺害対象リストにプレイヤーを追加します
     * @param player: 追加するプレイヤー
     * @param isDisplay: 表示リストに追加するか
     */
    public void addKillTarget(Player player, boolean isDisplay) {
        if (isExist(player)) {
            return;
        }

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

    public List<GamePlayer> getDisplayKillTargets() {
        return displayKillTargets;
    }

    /**
     * 殺害対象の表示リストにプレイヤーを追加します
     * @param player: 追加するプレイヤー
     */
    public void addDisplayKillTarget(Player player) {
        if (isExist(player)) {
            return;
        }

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
