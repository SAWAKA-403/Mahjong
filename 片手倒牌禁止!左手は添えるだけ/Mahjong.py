import sqlite3
import time
import random
import csv
from datetime import datetime  # 日付操作用

# SQLiteデータベース接続設定
DB_PATH = "players.db"

# データベース接続
conn = sqlite3.connect(DB_PATH)

def create_table_if_not_exists():
    """テーブルが存在しない場合に作成する"""
    cursor = conn.cursor()
    try:
        cursor.execute("""
        CREATE TABLE IF NOT EXISTS players (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            answer_time REAL,
            date TEXT
        )
        """)
        conn.commit()
        print("テーブルを確認しました（存在しない場合は作成されました）。")
    except Exception as e:
        print("テーブル作成エラー:", e)
    finally:
        cursor.close()

def save_result(name, answer_time):
    """名前、解答タイム、日付をデータベースに保存する"""
    cursor = conn.cursor()
    try:
        # 小数点以下第2位に丸める
        rounded_time = round(answer_time, 2)
        
        # 現在の日付を取得（YYYY-MM-DD形式）
        current_date = datetime.now().strftime("%Y-%m-%d")
        
        cursor.execute("INSERT INTO players (name, answer_time, date) VALUES (?, ?, ?)", 
                       (name, rounded_time, current_date))
        conn.commit()
        print(f"データを保存しました。解答時間: {rounded_time}秒, 日付: {current_date}")
    except Exception as e:
        print("エラー:", e)
    finally:
        cursor.close()

def export_to_csv():
    """データベースの内容をCSVファイルに出力する"""
    cursor = conn.cursor()
    try:
        # データベースから全てのプレイヤーのデータを取得
        cursor.execute("SELECT id, name, answer_time, date FROM players")
        rows = cursor.fetchall()

        # CSVファイルを開く（上書きモードで保存）
        with open('players_data.csv', 'w', newline='') as csvfile:
            csv_writer = csv.writer(csvfile)
            
            # CSVにヘッダーを書き込む
            csv_writer.writerow(['id', 'name', 'answer_time', 'date'])
            
            # データを書き込む
            csv_writer.writerows(rows)
        
        print("CSVファイルにデータを出力しました。")
    except Exception as e:
        print("エラー:", e)
    finally:
        cursor.close()

def play_game():
    """ゲームをプレイする"""
    print("多面待ち問題集アプリへようこそ！")
    
    # プレイヤーの名前を取得
    player_name = input("あなたの名前を入力してください: ")

    # 19問の問題を用意
    problems = [
        {"hand": ["2", "3", "4", "5", "6", "白", "白"], "waits": ["1", "4", "7"]},
        {"hand": ["2", "3", "4", "5", "6", "7", "8"], "waits": ["2", "5", "8"]},
        {"hand": ["2", "3", "4", "5", "5", "6", "7"], "waits": ["2", "5", "8"]},
        {"hand": ["2", "3", "4", "4", "4", "白", "白"], "waits": ["1", "4", "白"]},
        {"hand": ["2", "3", "3", "3", "4", "5", "6"], "waits": ["1", "2", "4", "7"]},
        {"hand": ["3", "3", "3", "4", "4", "5", "6"], "waits": ["2", "4", "5", "7"]},
        {"hand": ["3", "3", "3", "4", "5", "5", "6"], "waits": ["4", "5", "7"]},
        {"hand": ["3", "3", "3", "4", "5", "6", "7"], "waits": ["2", "4", "5", "7", "8"]},
        {"hand": ["3", "3", "3", "4", "5", "6", "8"], "waits": ["7", "8"]},
        {"hand": ["3", "3", "3", "5", "5", "6", "7"], "waits": ["4", "5", "8"]},
        {"hand": ["3", "3", "3", "5", "6", "7", "8"], "waits": ["4", "5", "8"]},
        {"hand": ["3", "3", "3", "4", "4", "5", "5"], "waits": ["3", "4", "5", "6"]},
        {"hand": ["3", "3", "3", "4", "5", "5", "5"], "waits": ["2", "3", "4", "5", "6"]},
        {"hand": ["3", "3", "3", "4", "4", "4", "5"], "waits": ["3", "4", "5", "6"]},
        {"hand": ["3", "3", "3", "5", "7", "7", "7"], "waits": ["4", "5", "6"]},
        {"hand": ["3", "3", "4", "5", "5", "5", "5"], "waits": ["3", "4", "6"]},
        {"hand": ["3", "3", "4", "4", "4", "4", "5"], "waits": ["2", "3", "5", "6"]},
        {"hand": ["3", "3", "4", "4", "4", "5", "5"], "waits": ["3", "4", "5"]},
        {"hand": ["3", "4", "4", "4", "4", "5", "6"], "waits": ["2", "3", "5", "6"]} 
    ]

    # ランダムに3問を選ぶ
    selected_problems = random.sample(problems, 3)

    # 解答タイム計測の開始
    start_time = time.time()

    correct_count = 0  # 正解カウント

    # 問題を解く
    for problem in selected_problems:
        print("手牌:", " ".join(problem["hand"]))
        
        # 解答を入力
        answer = input("待ち牌をスペース区切りで入力してください: ").split()
        
        # 判定
        if set(answer) == set(problem["waits"]):
            print("〇")
            correct_count += 1
        else:
            print("✕")
        
    # 解答タイム計測の終了
    end_time = time.time()
    total_time = end_time - start_time  # 合計解答タイム

    print(f"3問を解くのにかかった合計時間: {total_time:.2f}秒\n")

    # 3問全て正解した場合のみ保存
    if correct_count == 3:
        save_result(player_name, total_time)
    else:
        print("全て正解しなかったため、結果は保存されませんでした。")

    print("お疲れ様でした！")

def main():
    """ゲーム開始前にCSV出力かゲームプレイを選ぶ"""
    print("ゲームをプレイしますか？それともCSV出力しますか？")
    choice = input("選択肢: 1 - CSV出力, 2 - ゲームプレイ: ")

    if choice == '1':
        # CSV出力
        export_to_csv()
    elif choice == '2':
        # ゲームプレイ
        play_game()
    else:
        print("無効な選択です。プログラムを終了します。")

# アプリ終了時にデータベース接続を閉じる
if __name__ == "__main__":
    create_table_if_not_exists()  # 必ずテーブルを作成
    main()
    conn.close()

