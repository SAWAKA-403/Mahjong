<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mahjong.GameStats" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.math.BigDecimal" %>

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
	
    <h1>個人総合成績</h1>

	<div class="mainview">
    <div class="scview">
        <div class="">
            <!-- 三人麻雀 1位, 2位, 3位の回数と率 -->
            <h2>三人麻雀</h2>
        
            <p>1位: 
                <%= (request.getAttribute("threePlayerFirstPlaceCount") != null ? request.getAttribute("threePlayerFirstPlaceCount") : 0) %> 回
                <%= String.format("%.2f", (request.getAttribute("threePlayerFirstRate") != null ? (double) request.getAttribute("threePlayerFirstRate") : 0.0)) %> %
            </p>
            <p>2位: 
                <%= (request.getAttribute("threePlayerSecondPlaceCount") != null ? request.getAttribute("threePlayerSecondPlaceCount") : 0) %> 回
                <%= String.format("%.2f", (request.getAttribute("threePlayerSecondPlaceRate") != null ? (double) request.getAttribute("threePlayerSecondPlaceRate") : 0.0)) %> %
            </p>
            <p>3位: 
                <%= (request.getAttribute("threePlayerThirdPlaceCount") != null ? request.getAttribute("threePlayerThirdPlaceCount") : 0) %> 回
                <%= String.format("%.2f", (request.getAttribute("threePlayerThirdPlaceRate") != null ? (double) request.getAttribute("threePlayerThirdPlaceRate") : 0.0)) %> %
            </p>

            <!-- 三人麻雀の平均着順 -->
            <p>平均着順: 
                <%= String.format("%.2f", (request.getAttribute("threePlayerAverageRank") != null ? (double) request.getAttribute("threePlayerAverageRank") : 0.0)) %>
            </p>
			
			
            <!-- 三人麻雀の収支金額合計 -->
            <!-- 三人麻雀 チップ金額合計 -->
            <h2>三人麻雀 収支合計</h2>
            <%
                // 収支金額のフォーマット
                DecimalFormat decimalFormat = new DecimalFormat("#,###"); // カンマ区切りフォーマット
                String formattedThreePlayerProfit = (request.getAttribute("threePlayerTotalProfit") != null) ? 
                    decimalFormat.format((Double) request.getAttribute("threePlayerTotalProfit")) + "" : "0";
            %>
            <p>麻雀収支: <%= formattedThreePlayerProfit %>&nbsp;
            チップ収支: <%= (request.getAttribute("threePlayerTotalTip") != null) ? String.format("%,.0f", request.getAttribute("threePlayerTotalTip")) : "0" %>
            </p>
            
            <!-- 三人麻雀 収支金額合計 + チップ金額合計 -->
		<p>合計: 
		    <%= (request.getAttribute("threePlayerTotal") != null) ? String.format("%,.0f", request.getAttribute("threePlayerTotal")) : "0" %>
		</p>
            
        </div>

        <div class="">
            <!-- 四人麻雀 1位, 2位, 3位, 4位の回数と率 -->
            <h2>四人麻雀</h2>
            <p>1位: 
                <%= (request.getAttribute("fourPlayerFirstPlaceCount") != null ? request.getAttribute("fourPlayerFirstPlaceCount") : 0) %> 回
                <%= String.format("%.2f", (request.getAttribute("fourPlayerFirstRate") != null ? (double) request.getAttribute("fourPlayerFirstRate") : 0.0)) %> %
            </p>
            <p>2位: 
                <%= (request.getAttribute("fourPlayerSecondPlaceCount") != null ? request.getAttribute("fourPlayerSecondPlaceCount") : 0) %> 回
                <%= String.format("%.2f", (request.getAttribute("fourPlayerSecondPlaceRate") != null ? (double) request.getAttribute("fourPlayerSecondPlaceRate") : 0.0)) %> %
            </p>
            <p>3位: 
                <%= (request.getAttribute("fourPlayerThirdPlaceCount") != null ? request.getAttribute("fourPlayerThirdPlaceCount") : 0) %> 回
                <%= String.format("%.2f", (request.getAttribute("fourPlayerThirdPlaceRate") != null ? (double) request.getAttribute("fourPlayerThirdPlaceRate") : 0.0)) %> %
            </p>
            <p>4位: 
                <%= (request.getAttribute("fourPlayerFourthPlaceCount") != null ? request.getAttribute("fourPlayerFourthPlaceCount") : 0) %> 回
                <%= String.format("%.2f", (request.getAttribute("fourPlayerFourthPlaceRate") != null ? (double) request.getAttribute("fourPlayerFourthPlaceRate") : 0.0)) %> %
            </p>

            <!-- 四人麻雀の平均着順 -->
            <p>平均着順: 
                <%= String.format("%.2f", (request.getAttribute("fourPlayerAverageRank") != null ? (double) request.getAttribute("fourPlayerAverageRank") : 0.0)) %>
            </p>

            <!-- 四人麻雀の収支金額合計 -->
            <!-- 四人麻雀 チップ金額合計 -->
            <h2>四人麻雀 収支合計</h2>
            <%
                // 収支金額のフォーマット
                String formattedFourPlayerProfit = (request.getAttribute("fourPlayerTotalProfit") != null) ? 
                    decimalFormat.format((Double) request.getAttribute("fourPlayerTotalProfit")) + "" : "0";
            %>
            <p>麻雀収支: <%= formattedFourPlayerProfit %>&nbsp;
            チップ収支: <%= (request.getAttribute("fourPlayerTotalTip") != null) ? String.format("%,.0f", request.getAttribute("fourPlayerTotalTip")) : "0" %>
            </p>

            <!-- 四人麻雀 収支金額合計 + チップ金額合計 -->
            <p>合計: 
		    <%= (request.getAttribute("fourPlayerTotal") != null) ? String.format("%,.0f", request.getAttribute("fourPlayerTotal")) : "0" %>
			</p>
            
        </div>
    </div>
    </div><br>
    <a href="index.jsp" class="button">HOMEへ</a>

    

</body>
</html>



