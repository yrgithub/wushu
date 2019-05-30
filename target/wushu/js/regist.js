layui.config({
    base: '../../admin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'user'], function(){
    var $ = layui.$,
        setter = layui.setter,
        admin = layui.admin,
        form = layui.form,
        router = layui.router();

    form.render();

    //提交
    form.on('submit(LAY-user-reg-submit)', function(obj){
        var field = obj.field;

        //确认密码
        if(field.password !== field.repass){
            return layer.msg('两次密码输入不一致');
        }

        //是否同意用户协议
        if(!field.agreement){
            return layer.msg('你必须同意用户协议才能注册');
        }

        //请求接口
        admin.req({
            url: '../../regist/registUser', //实际使用请改成服务端真实接口
            data: field,
            done: function(res){

                if (res.data == 0){
                    layer.msg('注册成功', {
                        offset: '15px',
                        icon: 1,
                        time: 1000
                    }, function(){
                        location.href = '../../pages/backend/login.html'; //跳转到登入页
                    });
                }
                else {
                    if (res.data == 1){
                        layer.msg('用户已存在', {
                            offset: '15px',
                            icon: 3,
                            time: 1000
                        }, function(){
                            location.href = '../../pages/backend/regist.html'; //跳转到注册页
                        });
                    }
                    else {
                        if (res.data == 2)
                        {
                            layer.msg('系统错误', {
                                offset: '15px',
                                icon: 2,
                                time: 1000
                            }, function(){
                                location.href = '../../pages/backend/regist.html'; //跳转到注册页
                            });
                        }
                    }
                }
            }
        });

        return false;
    });
});

function backToLogin() {
    location.href = '../../pages/backend/login.html';
}