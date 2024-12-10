package com.mahjong;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class GameStats {
    private int gameType;        // ゲームタイプ（3: 三人麻雀, 4: 四人麻雀）
    private int rank;            // 順位
    private BigDecimal profit;   // 収支金額（BigDecimal型）
    private BigDecimal tip;      // チップ金額（BigDecimal型）
    private Timestamp createdAt; // 作成日時

    // コンストラクタ
    public GameStats(int gameType, int rank, BigDecimal profit, BigDecimal tip) {
        this.gameType = gameType;
        this.rank = rank;
        this.profit = profit;
        this.tip = tip;
        this.createdAt = new Timestamp(System.currentTimeMillis()); // 現在の日時を設定
    }

    // ゲッターとセッター
    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getTip() {
        return tip;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // toString メソッド（デバッグ用）
    @Override
    public String toString() {
        return "GameStats{" +
                "gameType=" + gameType +
                ", rank=" + rank +
                ", profit=" + profit +
                ", tip=" + tip +
                ", createdAt=" + createdAt +
                '}';
    }
}
