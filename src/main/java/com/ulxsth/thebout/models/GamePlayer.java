package com.ulxsth.thebout.models;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class GamePlayer {
    private static List<GamePlayer> participants = new ArrayList<>();

    private Player player;

    private List<GamePlayer> protectTargets;
    private List<GamePlayer> displayProtectTargets;
    private List<GamePlayer> killTargets;
    private List<GamePlayer> displayKillTargets;

    private GamePlayer(Player player) {
        this(player, new ArrayList<GamePlayer>(), new ArrayList<GamePlayer>(), new ArrayList<GamePlayer>(), new ArrayList<GamePlayer>());
    }

    private GamePlayer(Player player, List<GamePlayer> protectTargets, List<GamePlayer> displayProtectTargets, List<GamePlayer> killTargets, List<GamePlayer> displayKillTargets) {
        if(player.isOnline()) {
            throw new IllegalArgumentException("対象のプレイヤーは存在しないか、オンラインではありません");
        }

        this.player = player;
        this.protectTargets = protectTargets;
        this.displayProtectTargets = displayProtectTargets;
        this.killTargets = killTargets;
        this.displayKillTargets = displayKillTargets;
    }

    /**
     * プレイヤーを参加者に追加します
     * @param player: 参加者を示すPlayer
     * @throws Exception: プレイヤーがすでに参加している場合
     */
    public static void addPlayer(Player player) throws Exception {
        if(isExist(player)) {
            throw new Exception("プレイヤーは既に参加しています");
        }

        // TODO: ターゲットの抽選処理

        GamePlayer gamePlayer = new GamePlayer(player);
        participants.add(gamePlayer);
    }

    /**
     * 参加者から指定したプレイヤーを削除します
     * @param player: 削除するPlayer
     * @throws Exception: プレイヤーが参加者にいない場合
     */
    public static void removePlayer(Player player) throws Exception {
        if(!isExist(player)) {
            throw new Exception("プレイヤーが参加状態ではないか、存在しません");
        }

        for(int i=0; i<participants.size(); i++) {
            GamePlayer gamePlayer = participants.get(i);
            if(gamePlayer.getPlayer().equals(player)) {
                participants.remove(i);
                break;
            }
        }
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

    public static List<GamePlayer> getParticipants() {
        return participants;
    }


    public Player getPlayer() {
        return player;
    }

    public List<GamePlayer> getProtectTargets() {
        return protectTargets;
    }

    public List<GamePlayer> getDisplayProtectTargets() {
        return displayProtectTargets;
    }

    public List<GamePlayer> getKillTargets() {
        return killTargets;
    }

    public List<GamePlayer> getDisplayKillTargets() {
        return displayKillTargets;
    }
}
