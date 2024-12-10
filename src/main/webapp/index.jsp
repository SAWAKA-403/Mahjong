<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>麻雀成績管理</title>
</head>
<body>
	
	<div class="fview">
    <h1>麻雀成績管理</h1>&nbsp;
   <a href="GameStatsServlet" class="button">成績確認</a>
   </div>
    
    <h3>役満も平和も確率は同じ・・・和了れるか、和了れないか！！２分の１だ！！</h3>
    
    <div class="mainview">
    
     <!-- エラーメッセージ表示 -->
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p style="color: red;"><%= errorMessage %></p>
    <%
        }
    %>
    <!-- フォームの送信先を GameRecordServlet に指定 -->
    <form action="GameRecordServlet" method="post">
        <!-- ゲームタイプ選択 (ラジオボタン) -->
        <label for="gameType">ゲーム種類</label><br>
        <input type="radio" id="threePlayer" name="gameType" value="3" required>
        <label for="threePlayer">三人麻雀</label><br>

        <input type="radio" id="fourPlayer" name="gameType" value="4" required>
        <label for="fourPlayer">四人麻雀</label><br>

        <!-- 着順 -->
        <label for="rank">順位</label>
        <input type="number" id="rank" name="rank" size="8" min="1" max="4" required>&nbsp;位<br>
    
        <!-- 収支金額 (100円単位) -->
        <label for="profit">収支</label>
        <input type="number" step="100" id="profit" name="profit" size="10" required placeholder="100円単位で入力">&nbsp;円<br>

        <!-- チップ金額 (100円単位、マイナスも許容) -->
        <label for="tip">チップ（任意）</label>
        <input type="number" step="100" id="tip" name="tip" size="10" placeholder="100円単位で入力">&nbsp;円<br>
 
        <!-- 送信ボタン -->
        <input type="submit" value="成績を保存">
        
    </form>
    	
   
    </div>
    
    <h1>瞬間記憶能力ゲーム</h1>
    <a href="game.jsp" class="button">遊ぶ</a>
    
</body>
</html>
