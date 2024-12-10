package com.mahjong;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GameRecordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameType = request.getParameter("gameType");
        String rank = request.getParameter("rank");
        String profit = request.getParameter("profit");
        String tip = request.getParameter("tip");  // チップ金額の追加

        // ゲームタイプに応じた着順チェック
        try {
            int gameTypeInt = Integer.parseInt(gameType);
            int rankInt = Integer.parseInt(rank);

            // gameTypeが3の場合、rankは1から3の範囲、gameTypeが4の場合、rankは1から4の範囲
            if ((gameTypeInt == 3 && (rankInt < 1 || rankInt > 3)) || (gameTypeInt == 4 && (rankInt < 1 || rankInt > 4))) {
                request.setAttribute("errorMessage", "不正な入力です。ゲーム種類に応じた順位を選んでください。");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            // profitは100円単位
            int profitInt = Integer.parseInt(profit);
            if (profitInt % 100 != 0) {
                request.setAttribute("errorMessage", "収支は100円単位で入力してください。");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            // チップ金額のチェック（任意なので、デフォルトは0）
            BigDecimal tipBigDecimal = BigDecimal.ZERO; // チップ金額が入力されていない場合、0として扱う
            if (tip != null && !tip.isEmpty()) {
                try {
                    tipBigDecimal = new BigDecimal(tip); // チップ金額をBigDecimalに変換
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "不正なチップ金額が入力されました。");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                }
            }

            // データベースに接続してデータを保存
            try (Connection connection = DatabaseUtil.getConnection()) {
                // game_records テーブルに挿入するSQL文
                String sql = "INSERT INTO game_records (game_type, rank, profit, tip) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1, gameTypeInt);
                    stmt.setInt(2, rankInt);
                    stmt.setBigDecimal(3, new BigDecimal(profit)); // profitもBigDecimalで挿入
                    stmt.setBigDecimal(4, tipBigDecimal); // tipもBigDecimalで挿入
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "データベースへの保存に失敗しました");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            response.sendRedirect("index.jsp");

        } catch (NumberFormatException e) {
            // パラメータの解析エラー
            request.setAttribute("errorMessage", "入力された値に不正な形式があります。");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
