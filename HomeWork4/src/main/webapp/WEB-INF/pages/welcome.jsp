<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ikram
  Date: 12/13/2017
--%>
<html>
<head>
    <title>Currency Exchange Application</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="/webapp/css/main.css" rel="stylesheet" />
</head>
<body>
<div id="wrapper">
    <div id="column1">
        <h2 style =" text-align : center"> Add Currency  </h2>
        <div class="col_div">
        </div>
        <div style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align:center;">
            <input class="btn_click" type="button"  onclick="location.href='/api/currency'" value="Add Currency" >
        </div>
    </div>
    <div id="column2">
        <h2 style =" text-align : center"> Add Exchange Rate </h2>
        <div class="col_div">

        </div>
        <div style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align:center;">
            <input class="btn_click" type="button"  onclick="location.href='/api/rate'" value="Add Rate" >
        </div>
    </div>
</div>
</body>
</html>
