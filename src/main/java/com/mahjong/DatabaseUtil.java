package com.mahjong;  // パッケージ名を変更

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    // PostgreSQL接続URL（文字エンコーディングをUTF-8に指定）
    private static final String URL = "jdbc:postgresql://localhost:5432/Mahjong_Score?characterEncoding=UTF-8"; // 変更
    private static final String USER = "postgres"; // 使用するユーザー名
    private static final String PASSWORD = "admin"; // 使用するパスワード
    
    // データベースに接続してConnectionを返すメソッド
    public static Connection getConnection() throws SQLException {
        try {
            // JDBCドライバを明示的に読み込む（通常は不要ですが、場合によっては必要）
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        }
        
        // データベースに接続してConnectionを取得
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}