<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>getClassInfo</title>
    <script src="http://api.tianditu.gov.cn/api?v=4.0&tk=a99e1ab2bb48f23aa8ba4f56d6acc278"></script>

</head>
<body>
<div id="content" style="height: 500px;">
</div>
</body>
<script src="./test.js"></script>
<script>
    window.addEventListener("load", function (e) {
        document.getElementById('content').innerHTML = JSON.stringify(outJson);
    });
</script>
</html>