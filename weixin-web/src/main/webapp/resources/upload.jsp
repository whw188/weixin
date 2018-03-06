<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>文件上传</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/file/upload" enctype="multipart/form-data" method="post">
    用户id：<input type="text" name="uid"><br/>
    上传标题：<input type="text" name="title"><br/>
    所属类别：<input type="text" name="categoryId"><br/>
    文件类型：<input type="text" name="type"><br/>
    上传文件1：<input type="file" name="file1"><br/>
    上传文件2：<input type="file" name="file2"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>