<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<html>
<script type="text/javascript">
    function submitTheData() {
        console.log("Inside the Submit Data");
        var convertFrom = document.getElementById('sourceCurrency').value;
        var convertTo = document.getElementById('destinationCurrency').value;
        var amountToBeConverted = document.getElementById('amountToConvert').value;
        
        $.ajax({
            type: "POST",
            url: "/api/convert",
            data: "convertFrom=" + convertFrom + "&convertTo=" + convertTo + "&amountToBeConverted=" + amountToBeConverted,

        success: function(response){

            $('#result').html(response);

        },

        error: function(e){

            alert('Error: ' + e);

        }

    });

    }

</script>

<head>
    <meta charset="utf-8">
    <title>Currency Exchange</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
</head>


<body>

<div class="container">
    <div class="row">
        <div class="span8 offset2">
            <h1>Currency Exchange</h1>
            <div class="control-group">
                <label cssClass="control-label">Source Currency:</label>
                <div class="controls">
                    <select name="convertFrom" id="sourceCurrency">
                        <option value="NONE" label=" -- SELECT CURRENCY CODE --"/>
                        <c:forEach items="${currencies}" var="currency">
                            <option value="${currency}">
                                    ${currency}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label cssClass="control-label">Destination Currency:</label>
                <div class="controls">
                    <select name="convertTo" id="destinationCurrency">
                        <option value="NONE" label=" -- SELECT CURRENCY CODE --"/>
                        <c:forEach items="${currencies}" var="currency">
                            <option value="${currency}">
                                    ${currency}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label cssClass="control-label">Amount To Exchange:</label>
                <div class="controls">
                    <input name="amount" type="text" id="amountToConvert"></input>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input type="button" onclick="submitTheData()" value="Exchange Currency" class="btn"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input type="button" value="Back" class="btn" onclick="location.href='/welcome'"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <div id="result" style="color: green;"></div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>