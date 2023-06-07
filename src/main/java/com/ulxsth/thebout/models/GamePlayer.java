package com.ulxsth.thebout.models;

import org.bukkit.entity.Player;

import java.util.*;


public class GamePlayer {
    private static final List<Player> participants = new ArrayList<>();

    private final Player player;

    private final List<Player> protectTargets = new ArrayList<>();
    private final List<Player> displayProtectTargets = new ArrayList<>();
    private final List<Player> killTargets = new ArrayList<>();
    private final List<Player> displayKillTargets = new ArrayList<>();

    private GamePlayer(Player player) {
        this.player = player;
    }

    /**
     * プレイヤーを参加者に追加します
     * @param target: 参加者を示すPlayer
     */
    public static void addPlayer(Player target) {
        if (isExistOnParticipants(target)) {
            return;
        }

        participants.add(target);
    }

    /**
     * 参加者から指定したプレイヤーを削除します
     * @param target: 削除するPlayer
     */
    public static void removePlayer(Player target) {
        for(Player player: participants) {
            if(player.getUniqueId().equals(target.getUniqueId())) {
                participants.remove(player);
                return;
            }
        }
    }

    /**
     * 参加者のカードをシャッフルします。
     */
    public static void assignAllPlayer() {
        // TODO: 役職ごとに配布カードの処理をする

        Random rand = new Random();

        GamePlayer newPlayer;
        List<Player> remainKillTarget = participants;
        List<Player> remainProtectTarget = participants;

        for(Player player: participants) {
            newPlayer = new GamePlayer(player.getPlayer());

            List<Player> remainKillTargetCopy = new ArrayList<>(remainKillTarget);
            remainKillTargetCopy.remove(player);
            Player killTarget = remainKillTargetCopy.get(rand.nextInt(remainKillTarget.size()));

            List<Player> remainProtectTargetCopy = new ArrayList<>(remainProtectTarget);
            remainProtectTargetCopy.remove(player);
            remainProtectTargetCopy.remove(killTarget);
            Player protectTarget = remainProtectTargetCopy.get(rand.nextInt(remainProtectTarget.size()));

            newPlayer.addKillTarget(killTarget, true);
            newPlayer.addProtectTarget(protectTarget, true);
            remainKillTarget.remove(killTarget);
            remainProtectTargetCopy.remove(protectTarget);
        }
    }

    /**
     * プレイヤーが参加者に存在するかを確認します
     * @param target: 確認するプレイヤー
     * @return 存在するかを示す真偽値
     */
    public static boolean isExistOnParticipants(Player target) {
        for(Player player: participants) {
            if(!player.getUniqueId().equals(target.getUniqueId())) {
                continue;
            }
            return true;
        }

        return false;
    }

    public static List<Player> getParticipants() {
        return participants;
    }

    public static void clear() {
        participants.clear();
    }

    public Player getPlayer() {
        return player;
    }

    public List<Player> getProtectTargets() {
        return protectTargets;
    }

    /**
     * 保護対象に参加者を追加します
     * @param target: 追加するプレイヤー
     * @param isDisplay: 表示リストに追加するか
     */
    public void addProtectTarget(Player target, boolean isDisplay) {
        if (isExistOnParticipants(target)) {
            return;
        }

        this.protectTargets.add(target);
        if(isDisplay) {
            displayProtectTargets.add(target);
        }
    }

    /**
     * プレイヤーが保護対象に含まれているか確認します。
     * @param target: 調べるプレイヤー
     * @return 含まれるか
     */
    public boolean isExistOnProtectTarget(Player target) {
        for(Player player: protectTargets) {
            if(player.getUniqueId().equals(target.getUniqueId())) {
                return true;
            }
        }

        return false;
    }

    public List<Player> getDisplayProtectTargets() {
        return displayProtectTargets;
    }

    /**
     * 保護対象の表示リストにプレイヤーを追加します
     * @param target: 追加するプレイヤー
     */
    public void addDisplayProtectTarget(Player target) {
        if (isExistOnParticipants(target)) {
            return;
        }

        this.displayProtectTargets.add(target);
    }

    /**
     * プレイヤーが保護対象の表示リストに含まれているか確認します。
     * @param target: 調べるプレイヤー
     * @return 含まれるか
     */
    public boolean isExistOnDisplayProtectTarget(Player target) {
        for(Player player: displayProtectTargets) {
            if(player.getUniqueId().equals(target.getUniqueId())) {
                return true;
            }
        }

        return false;
    }

    public List<Player> getKillTargets() {
        return killTargets;
    }

    /**
     * 殺害対象リストにプレイヤーを追加します
     * @param target: 追加するプレイヤー
     * @param isDisplay: 表示リストに追加するか
     */
    public void addKillTarget(Player target, boolean isDisplay) {
        if (isExistOnParticipants(target)) {
            return;
        }

        this.killTargets.add(target);
        if(isDisplay) {
            displayKillTargets.add(target);
        }
    }

    /**
     * プレイヤーが殺害対象に含まれているか確認します。
     * @param target: 調べるプレイヤー
     * @return 含まれるか
     */
    public boolean isExistOnKillTarget(Player target) {
        for(Player player: killTargets) {
            if(player.getUniqueId().equals(target.getUniqueId())) {
                return true;
            }
        }

        return false;
    }

    public List<Player> getDisplayKillTargets() {
        return displayKillTargets;
    }

    /**
     * 殺害対象の表示リストにプレイヤーを追加します
     * @param target: 追加するプレイヤー
     */
    public void addDisplayKillTarget(Player target) {
        if (isExistOnParticipants(target)) {
            return;
        }

        this.displayKillTargets.add(target);
    }

    /**
     * プレイヤーが殺害対象に含まれているか確認します。
     * @param target: 調べるプレイヤー
     * @return 含まれるか
     */
    public boolean isExistOnDisplayKillTarget(Player target) {
        for(Player player: displayKillTargets) {
            if(player.getUniqueId().equals(target.getUniqueId())) {
                return true;
            }
        }

        return false;
    }
}
