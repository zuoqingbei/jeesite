<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
result
<script>

    var url = document.location.toString();
    var arrUrl = url.split("?");
    var para = arrUrl[1].split("=")[1];
    //alert(para);
    console.log(para);

</script>
</body>
</html>