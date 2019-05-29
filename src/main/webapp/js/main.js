//使用layui
layui.use('element',function () {
    var element = layui.element;
});
layui.use('layer',function () {
    var layer = layui.layer;
});
$(function(){
    $('#main_left li a').on('click',function(){
        var address =$(this).attr('data-src');
        $('iframe').attr('src',address);
    });

    /*var username = getParameter('username');

    document.getElementById('username').innerText ='欢迎光临:  ' + decodeURI(username);*/
});

function addUser(type) {
    var url = '';
    var title = '';
    switch (type) {
        case 2:
            url = '../../pages/backend/adminAddUser.html?userType=2';
            title = '添加教练用户';
            break;
        case 3:
            url = '../../pages/backend/adminAddUser.html?userType=3';
            title = '添加个人用户';
            break;
        case 4:
            url = '../../pages/backend/adminAddUser.html?userType=4';
            title = '添加公司用户';
            break;
    }
    layer.open({
        type:2,
        shade:0.2,
        title:title,
        skin:'layui-layer-lan',
        area:['500px','400px'],
        content:url
    });
}

