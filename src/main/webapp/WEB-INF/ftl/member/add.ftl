<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>用户列表 —个人中心</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="https://open.itboy.net/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="https://open.itboy.net/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="http://open.itboy.net/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/shiro.demo.js"></script>
    <script>
        <#--function saveUser() {-->
            <#--$.post('${basePath}/member/save.shtml',-->
                    <#--{'nickname':'wangkuan'}, function (result) {-->

                <#--if (result && result.status != 200) {-->
                    <#--return layer.msg(result.message, so.default), !0;-->
                <#--} else {-->
                    <#--layer.msg('删除成功');-->
                    <#--setTimeout(function () {-->
                        <#--$('#formId').submit();-->
                    <#--}, 1000);-->
                <#--}-->
            <#--}, 'json');-->
        <#--}-->
    </script>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 2/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.member 1/>
        <div class="col-md-10">
            <h2>添加用户</h2>
            <hr>
            <form method="post" action="${basePath}/member/add.shtml" id="formId" class="form-inline">
                <div clss="well">
                    <span class=""> <#--pull-right -->
                        <button class="btn btn-success" type="submit">添加</button>
				         </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>昵称</th>
                        <td><input type="text" name="nickname"></td>
                    </tr>

                    <tr>
                        <th>邮箱</th>
                        <td><input type="text" name="email"></td>
                    </tr>
                    <tr>
                        <th>密码</th>
                        <td><input type="text" name="pswd"></td>
                    </tr>


            </form>
        </div>
    </div><#--/row-->
</div>

</body>
</html>