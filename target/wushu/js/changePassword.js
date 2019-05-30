layui.use('layer',function () {
    var element = layui.layer;
});

layui.use('form',function () {
    var element = layui.form;
});

$(function () {
    var username = getParameter('username');
    setData(username);
});

function setData(data) {

    if (data == null || data == undefined){
        document.getElementById('username').value = '';
    }
    document.getElementById('username').value = decodeURI(data);
}

function save() {
    var detailData = {};
    var username = document.getElementById('username').value;
    var oldPassword = document.getElementById('oldPassword').value;
    var newPassword = document.getElementById('newPassword').value;
    var newPasswordSure = document.getElementById('newPasswordSure').value;
    if (newPassword != newPasswordSure){
        layer.msg('输入的新密码不正确',{icon:2,time:1000});
        return;
    }
    detailData.username = username;
    detailData.oldPassword = oldPassword;
    detailData.newPassword = newPassword;
    detailData.newPasswordSure = newPasswordSure;
    $.ajax({
        url:'../../user/changePassword',
        type: 'get',
        data:{
            singleData: encodeURI(JSON.stringify(detailData))
        },
        dataType:'json',
        success:function (json) {
            if (json == null || json == undefined){
                layer.msg('系统错误',{icon:2,time:1000});
            }
            else {
                if (json.data == 1) {
                    layer.msg('保存成功', {icon: 1, time: 1000});
                    cancle();
                    window.parent.location.reload();
                }
                else {
                    layer.msg('原密码错误', {icon: 4, time: 1000});
                }
            }
        }
    });
}


function cancle() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}