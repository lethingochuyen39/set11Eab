
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>transfer</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">


    <div class="row">
        <div class="col">
            <h1>Transfer within FAI Bank</h1>
            <hr/>
            <h3>Hello, ${account.accountName}</h3>
            <h3>$.${account.balance}</h3>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <form action="bank" method="POST">
                <div class="form-group">
                    <label>
                        Account Source:
                    </label>
                    <input type="text" name="txtaccount" class="form-control" value="${account.accountNo}" readonly required/>
                </div>
                <div class="form-group">
                    <label>
                        Account to:
                    </label>
                    <input type="text" name="txtaccountto" class="form-control" value="" required/>
                </div>
                <div class="form-group">
                    <label>
                        Amount:
                    </label>
                    <input type="number" name="txtamount" class="form-control" value="" required/>
                </div>

                <div class="form-group">
                    <label>
                        Comment:
                    </label>
                    <input type="text" name="txtcomment" class="form-control" value="" required/>
                </div>
                <p style="color: green">${msg}</p>
                <p style="color: red">${error}</p>
                <input type="submit" name="action" value="transfer" class="btn btn-primary"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>
