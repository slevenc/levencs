<html>
<head>
    <title>发布文章</title>
    <script src="http://127.0.0.1/res/dojo/dojo/dojo.js" djConfig="parseOnLoad: true"></script>
    <link ref="styleSheet" type="text/css" href="http://127.0.0.1/res/dojo/dijit/themes/claro/claro.css"/>
</head>

<body>
<#if _rootPath??>
    <div>root:${_rootPath}</div>
</#if>

<div>
    <form action="" method="post">
        <div>
            <span>标题</span><span><input type="text" name="title"/></span>
        </div>
        <div>
            <span>正文</span>
        </div>

        <div>
            <textarea dojoType="dijit.form.Textarea" name="content"></textarea>
        </div>

        <div>
            <input type="submit"/>
        </div>

    </form>
</div>
</body>
<script>

    dojo.require("dojo.parser");
    dojo.require("dijit.form.TextArea");


</script>
</html>
