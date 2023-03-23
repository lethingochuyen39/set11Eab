
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>login</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col">
      <h2>Welcome to FAI Bank</h2>
      <br>
      <h4>System login</h4>
      <hr/>
    </div>
  </div>
  <div class="row">
    <div class="col">
      <form action="bank" method="POST">
        <div class="form-group">
          <label>
            Account No
          </label>
          <input type="text" name="txtName" class="form-control" value="" required/>
        </div>
        <div class="form-group">
          <label>
            Pin Code
          </label>
          <input type="password" name="txtPass" class="form-control" value="" required/>
        </div>
        <p style="color: red">${error}</p>
        <input type="submit" name="action"  class="btn btn-primary" value="login"/>
      </form>
    </div>
  </div>
</div>
</body>
</html>
