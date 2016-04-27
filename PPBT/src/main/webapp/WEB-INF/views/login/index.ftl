<@content for="title">Login</@content>

<#if (flasher.message) ??>
<div class="alert alert-danger" role="alert"><@flash name="message"/></div>
</#if>

<style type="text/css" scoped>
    html,body{min-height:100%}

    body {
        background: url("/Public/imgs/login-bg.jpg") no-repeat center;
        background-size: 100% 100%;
    }

    .login-panel {
        width: 300px;
        position: absolute;
        border-radius: 2px;
        top: 12%;
        left: 50%;
        margin-left: -150px;
        padding: 30px 20px 20px;
        background-color: rgba(100, 100, 100, 0.2);
    }
</style>

<div class="login-panel">
    <form class="form-horizontal" method="post" action="/login/login">
        <div class="form-group">
            <div class="col-sm-12">
                <input type="text" class="form-control" name="username" placeholder="username">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-12">
                <input type="password" class="form-control" name="password" placeholder="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-6">
                <button type="submit" name="action" value="login" class="btn btn-warning" style="width: 100%;box-sizing: border-box;">Log in</button>
            </div>
            <div class="col-sm-6">
                <button type="submit" name="action" value="register" class="btn btn-success" style="width: 100%;box-sizing: border-box;">Register
                </button>
            </div>
        </div>
    </form>
</div>