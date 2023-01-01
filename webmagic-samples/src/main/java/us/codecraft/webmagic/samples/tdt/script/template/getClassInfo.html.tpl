<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>getClassInfo</title>
</head>
<body>
<div id="content" style="height: 500px;">
</div>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    $.getScript('http://api.tianditu.gov.cn/api?v=4.0&tk=a99e1ab2bb48f23aa8ba4f56d6acc278', function () {
        setTimeout(function(){
            let classes = ${content};

            let notExist = {};
            let superClass = {};
            let extendInfos = {};
            let classesLen = classes.length;
            let outJson = {};

            for (let i = 0; i < classesLen; i++) {
                let curClass = classes[i];
                let curObj = eval(curClass.obj);
                for (let j = 0; j < classesLen; j++) {
                    if (i === j) {
                        continue;
                    }

                    let compareClass = classes[j];
                    let compareObj = eval(compareClass.obj);

                    if (curObj && compareObj) {
                        if (curObj.prototype instanceof compareObj) {
                            if (!(compareClass.name in superClass)) {
                                superClass[compareClass.name] = [];
                            }

                            superClass[compareClass.name].push(curClass.name);
                            extendInfos[curClass.name] = compareClass.name;
                        }
                    } else {
                        if (!curObj) {
                            notExist[curClass.name] = 1;
                        }
                    }
                }
            }

            outJson['super']= superClass;
            outJson['child']= extendInfos;
            outJson['notExist']= notExist;

            document.getElementById('content').innerHTML = JSON.stringify(outJson);
        },1000);
    });
</script>
</html>