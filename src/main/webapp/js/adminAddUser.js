
layui.use('layer',function () {
    var layer = layui.layer;
});
function save() {
    var userType = getParameter('userType');
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var passwordSure = document.getElementById('passwordSure').value;
    if (username == null || username == undefined || username == ''){
        layer.msg('用户名不能为空',{icon:2,time:1000});
        return;
    }
    if (password == null || password == undefined || password == ''){
        layer.msg('密码不能为空',{icon:2,time:1000});
        return;
    }
    //确认密码
    if(password !== passwordSure){
        return layer.msg('两次密码输入不一致',{icon:2,time:500});
    }
    $.ajax({
        url:'../../regist/registUser',
        type:'get',
        data:{
            username:username,
            password:password,
            userType:userType
        },
        dataType:'json',
        success:function (json) {
            if (json.data == 1){
                layer.msg('用户名已存在',{icon:2,time:500});
                return;
            }
            else {
                if (json.data == 0){
                    layer.msg('添加成功',{icon:1,time:500},function () {
                        cancle();
                        window.parent.location.reload();
                    });
                }
                else {
                    layer.msg('添加成功',{icon:1,time:500});
                    return;
                }
            }
        }
        
    });
}

function cancle() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}