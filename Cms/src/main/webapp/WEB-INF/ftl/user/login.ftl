<html>

<head>
    <title>登录</title>
    <script src="http://127.0.0.1/res/dojo/dojo/dojo.js" djConfig="parseOnLoad: true"></script>
    <style type="text/css">
        @import "http://127.0.0.1/res/dojo/dijit/themes/claro/claro.css";
    </style>
    <!--<link ref="styleSheet" type="text/css" href="http://127.0.0.1/res/dojo/dijit/themes/claro/claro.css"/>-->
    <!--<link ref="styleSheet" type="text/css" href="http://127.0.0.1/res/dojo/dojo/resources/dojo.css"/>-->
    <style>
        label {
        width:150px;
        float:left;
        }
    </style>
</head>
<body class="claro">


<div dojoType="dijit.layout.ContentPane" title="登录">
    <form id="loginForm" action="" method="post">
    <label for="user_name_input">用户名</label>
    <input id="user_name_input" name="username" type="text" size="64"
           dojoType="dijit.form.ValidationTextBox" trim="true" required="true"/>
    <br/>
    <label for="password_input">密码</label>
    <input id="password_input" name="password" type="password" size="64"
           dojoType="dijit.form.ValidationTextBox" trim="true" required="true"/>
    <br/>

    <div id="submitButton" dojoType="dijit.form.Button">登录</div>
    </form>
</div>


</body>

<script>
    dojo.require("dojo.parser");
    dojo.require("dijit.layout.ContentPane");
    dojo.require("dijit.layout.TabContainer");
    dojo.require("dijit.form.ValidationTextBox");
    dojo.require("dijit.form.Button");
    function connect(){
    dojo.connect(dojo.byId("submitButton"),"click",al) ;
    }
    dojo.addOnLoad(connect);

    function al(eventObj){
    dojo.byId('loginForm').submit();
    }


</script>


</html>