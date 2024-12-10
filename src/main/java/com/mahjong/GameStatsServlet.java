package com.mahjong;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GameStatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            // 三人麻雀の統計データを計算
            String threePlayerQuery = "SELECT rank, COUNT(*) AS count FROM game_records WHERE game_type = 3 GROUP BY rank ORDER BY rank";
            PreparedStatement ps = conn.prepareStatement(threePlayerQuery);
            ResultSet rs = ps.executeQuery();

            // 三人麻雀の集計
            int threePlayerFirstPlaceCount = 0;
            int threePlayerSecondPlaceCount = 0;
            int threePlayerThirdPlaceCount = 0;
            int threePlayerTotalCount = 0;
            double threePlayerTotalRank = 0;

            while (rs.next()) {
                int rank = rs.getInt("rank");
                int count = rs.getInt("count");

                if (rank == 1) {
                    threePlayerFirstPlaceCount = count;
                } else if (rank == 2) {
                    threePlayerSecondPlaceCount = count;
                } else if (rank == 3) {
                    threePlayerThirdPlaceCount = count;
                }

                threePlayerTotalCount += count;
                threePlayerTotalRank += rank * count;
            }

            double threePlayerFirstRate = (threePlayerFirstPlaceCount * 100.0) / threePlayerTotalCount;
            double threePlayerSecondRate = (threePlayerSecondPlaceCount * 100.0) / threePlayerTotalCount;
            double threePlayerThirdRate = (threePlayerThirdPlaceCount * 100.0) / threePlayerTotalCount;
            double threePlayerAverageRank = threePlayerTotalRank / threePlayerTotalCount;

            // 三人麻雀の収支金額合計を計算
            String threePlayerProfitQuery = "SELECT SUM(profit) AS total_profit FROM game_records WHERE game_type = 3";
            PreparedStatement profitStmt = conn.prepareStatement(threePlayerProfitQuery);
            ResultSet profitRs = profitStmt.executeQuery();

            double threePlayerTotalProfit = 0;
            if (profitRs.next()) {
                threePlayerTotalProfit = profitRs.getDouble("total_profit");
            }

            // 三人麻雀のチップ金額合計を計算
            String threePlayerTipQuery = "SELECT SUM(tip) AS total_tip FROM game_records WHERE game_type = 3";
            PreparedStatement tipStmt = conn.prepareStatement(threePlayerTipQuery);
            ResultSet tipRs = tipStmt.executeQuery();

            double threePlayerTotalTip = 0;
            if (tipRs.next()) {
                threePlayerTotalTip = tipRs.getDouble("total_tip");
            }

            // 三人麻雀 収支金額合計 + チップ金額合計を計算
            double threePlayerTotal = threePlayerTotalProfit + threePlayerTotalTip;

            // 結果をリクエスト属性にセット
            request.setAttribute("threePlayerFirstPlaceCount", threePlayerFirstPlaceCount);
            request.setAttribute("threePlayerFirstRate", threePlayerFirstRate);
            request.setAttribute("threePlayerSecondPlaceCount", threePlayerSecondPlaceCount);
            request.setAttribute("threePlayerSecondPlaceRate", threePlayerSecondRate);
            request.setAttribute("threePlayerThirdPlaceCount", threePlayerThirdPlaceCount);
            request.setAttribute("threePlayerThirdPlaceRate", threePlayerThirdRate);
            request.setAttribute("threePlayerAverageRank", threePlayerAverageRank);
            request.setAttribute("threePlayerTotalProfit", threePlayerTotalProfit);
            request.setAttribute("threePlayerTotalTip", threePlayerTotalTip);
            request.setAttribute("threePlayerTotal", threePlayerTotal);  // 新しく追加

            // 四人麻雀の統計データを計算
            String fourPlayerQuery = "SELECT rank, COUNT(*) AS count FROM game_records WHERE game_type = 4 GROUP BY rank ORDER BY rank";
            ps = conn.prepareStatement(fourPlayerQuery);
            rs = ps.executeQuery();

            int fourPlayerFirstPlaceCount = 0;
            int fourPlayerSecondPlaceCount = 0;
            int fourPlayerThirdPlaceCount = 0;
            int fourPlayerFourthPlaceCount = 0;
            int fourPlayerTotalCount = 0;
            double fourPlayerTotalRank = 0;

            while (rs.next()) {
                int rank = rs.getInt("rank");
                int count = rs.getInt("count");

                if (rank == 1) {
                    fourPlayerFirstPlaceCount = count;
                } else if (rank == 2) {
                    fourPlayerSecondPlaceCount = count;
                } else if (rank == 3) {
                    fourPlayerThirdPlaceCount = count;
                } else if (rank == 4) {
                    fourPlayerFourthPlaceCount = count;
                }

                fourPlayerTotalCount += count;
                fourPlayerTotalRank += rank * count;
            }

            double fourPlayerFirstRate = (fourPlayerFirstPlaceCount * 100.0) / fourPlayerTotalCount;
            double fourPlayerSecondRate = (fourPlayerSecondPlaceCount * 100.0) / fourPlayerTotalCount;
            double fourPlayerThirdRate = (fourPlayerThirdPlaceCount * 100.0) / fourPlayerTotalCount;
            double fourPlayerFourthRate = (fourPlayerFourthPlaceCount * 100.0) / fourPlayerTotalCount;
            double fourPlayerAverageRank = fourPlayerTotalRank / fourPlayerTotalCount;

            // 四人麻雀の収支金額合計を計算
            String fourPlayerProfitQuery = "SELECT SUM(profit) AS total_profit FROM game_records WHERE game_type = 4";
            PreparedStatement profitStmtFourPlayer = conn.prepareStatement(fourPlayerProfitQuery);
            ResultSet profitRsFourPlayer = profitStmtFourPlayer.executeQuery();

            double fourPlayerTotalProfit = 0;
            if (profitRsFourPlayer.next()) {
                fourPlayerTotalProfit = profitRsFourPlayer.getDouble("total_profit");
            }

            // 四人麻雀のチップ金額合計を計算
            String fourPlayerTipQuery = "SELECT SUM(tip) AS total_tip FROM game_records WHERE game_type = 4";
            PreparedStatement fourPlayerTipStmt = conn.prepareStatement(fourPlayerTipQuery);
            ResultSet fourPlayerTipRs = fourPlayerTipStmt.executeQuery();

            double fourPlayerTotalTip = 0;
            if (fourPlayerTipRs.next()) {
                fourPlayerTotalTip = fourPlayerTipRs.getDouble("total_tip");
            }

            // 四人麻雀 収支金額合計 + チップ金額合計を計算
            double fourPlayerTotal = fourPlayerTotalProfit + fourPlayerTotalTip;

            // 結果をリクエスト属性にセット
            request.setAttribute("fourPlayerFirstPlaceCount", fourPlayerFirstPlaceCount);
            request.setAttribute("fourPlayerFirstRate", fourPlayerFirstRate);
            request.setAttribute("fourPlayerSecondPlaceCount", fourPlayerSecondPlaceCount);
            request.setAttribute("fourPlayerSecondPlaceRate", fourPlayerSecondRate);
            request.setAttribute("fourPlayerThirdPlaceCount", fourPlayerThirdPlaceCount);
            request.setAttribute("fourPlayerThirdPlaceRate", fourPlayerThirdRate);
            request.setAttribute("fourPlayerFourthPlaceCount", fourPlayerFourthPlaceCount);
            request.setAttribute("fourPlayerFourthPlaceRate", fourPlayerFourthRate);
            request.setAttribute("fourPlayerAverageRank", fourPlayerAverageRank);
            request.setAttribute("fourPlayerTotalProfit", fourPlayerTotalProfit);
            request.setAttribute("fourPlayerTotalTip", fourPlayerTotalTip);
            request.setAttribute("fourPlayerTotal", fourPlayerTotal);

            // JSPへフォワード
            request.getRequestDispatcher("/gameStats.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データベースエラー");
        }
    }
}
