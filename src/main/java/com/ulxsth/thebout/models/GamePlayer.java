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
     * 該当する参加者を検索します。
     * 存在しない場合はnullを返します。
     * @param player: 検索するプレイヤー
     * @return 該当する参加者
     */
    public GamePlayer findByPlayer(Player player) {
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
     * @throws Exception: プレイヤーがすでに存在する場合
     */
    public void addProtectTargets(Player player, boolean isDisplay) throws Exception {
        // TODO: Setで置き換える（重複を許さない）
        if(isExist(player)) {
            throw new Exception("そのプレイヤーは既に存在します");
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
     * @throws Exception: プレイヤーが既に存在する場合
     */
    public void addDisplayProtectTargets(Player player) throws Exception {
        // TODO: Setで置き換える（重複を許さない）
        if(isExist(player)) {
            throw new Exception("そのプレイヤーは既に存在します");
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
     * @throws Exception: プレイヤーが既に存在する場合
     */
    public void addKillTargets(Player player, boolean isDisplay) throws Exception {
        // TODO: Setで置き換える（重複を許さない）
        if(isExist(player)) {
            throw new Exception("そのプレイヤーは既に存在します");
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
     * @throws Exception: 表示リストに追加するか
     */
    public void addDisplayKillTargets(Player player) throws Exception {
        // TODO: Setで置き換える（重複を許さない）
        if(isExist(player)) {
            throw new Exception("そのプレイヤーは既に存在します");
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
