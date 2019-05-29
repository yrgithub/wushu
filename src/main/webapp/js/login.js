/*function submit() {
    var username=document.getElementById('LAY-user-login-username').value;
    if (username == undefined || username == null) {
        alert('用户名不能为空!');
        return;
    }
    var password=document.getElementById('LAY-user-login-password').value;
    if (password == undefined || password == null){
        alert('密码不能为空!');
        return;
    }
    $.ajax({
        type:'get',
        url:'./shiro/login',
        data:
            {
                username:username,
                password:password
            },
        dataType:'json',
        success:function (json) {
            if(json)
            {
                window.location.href = './pages/backend/main.html';
            }
            else {
                alert('系统错误！');
            }
        }
    });
}

function regist() {
    window.location.href = './pages/backend/regist.html';
}*/


layui.config(
    {
        base: '../../admin/'//静态资源所在路径
    }).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'user'], function(){
    var $ = layui.$,
        setter = layui.setter,
        admin = layui.admin,
        form = layui.form,
        router = layui.router(),
        search = router.search;

    form.render();

    //提交
    form.on('submit(LAY-user-login-submit)', function(obj){

        //请求登入接口
        admin.req({
            url: '../../shiro/login', //服务端真实接口
            data: obj.field,
            done: function(res){
                var data = res.data;
                if (data == 1){
                    //登入失败的提示与跳转
                    layer.msg('登入失败,重新登入', {
                        offset: '15px',
                        icon: 1,
                        time: 1000
                    }, function(){
                        location.href = '../../pages/backend/login.html'; //后台主页
                    });
                }
                else {
                    //登入成功的提示与跳转
                    layer.msg(
                        '登入成功',
                        {offset: '15px', icon: 1, time: 1000},
                        function(){
                        var role = data.role;
                        var url = '';
                        switch (role) {
                            case 1:
                                url = '../../pages/backend/main.html'; //管理员主页
                                break;
                            case 2:
                                url = '../../pages/main.html'; //个人(包括教练和学员)登录主页
                                break;
                            case 3:
                                url = '../../pages/main.html'; //公司登录主页
                                break;
                        }
                        location.href = url;
                    });
                }
            }
        });

    });
});
function regist() {
    location.href = '../../pages/backend/regist.html';
}