var table;
layui.use('table', function(){
    table = layui.table;

    var role = getParameter('role');
    var companyName = document.getElementById('companyName').value;
    //第一个实例
    table.render({
        id: 'tableContent',
        elem: '#tableId',
        height: 'full-200',
        url: '../../company/getBasicData', //数据接口
        where:{
            companyName:companyName,
            role:role
        },
        page: true, //开启分页
        cols: [[ //表头
            {field: 'username', title: '用户名',align:'center',width:100},
            {field: 'typeNameZh', title: '用户类型',align:'center',width:100},
            {field: 'companyName', title: '公司名称',align:'center', width:200},
            {field: 'contentName', title: '所属区域',align:'center', width:200},
            {field: 'companyAddress', title: '公司地址',align:'center', width:200},
            {field: 'companyTel', title: '公司电话', align:'center',width:120},
            {field: 'companyCorporate', title: '公司法人',align:'center', width: 200},

            {field: 'companyPictureId', title: '公司图片',align:'center', width: 300,templet:function (data) {
                if (data.companyPictureUrl == null || data.companyPictureUrl == undefined || data.companyPictureUrl == '') {
                    return '<div><img class="layui-upload-img" src="../../images/companyDefault.png"></div>';
                }else {
                    return '<div><img class="layui-upload-img" src="'+'../../serverCompanyPicture/'+data.companyPictureUrl+'"></div>';
                }
                }},
            {field: 'companyDescribe', title: '公司描述',align:'center', width: 200},
            {fixed: 'right', title:'操作',align:'center', toolbar: '#operation', width:300}
        ]]
    });

    table.on('tool(tableFilter)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var event = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if(event === 'del'){ //删除
            var result = false;
            layer.confirm('真的删除吗？', function(index){
                result = deleteById(data.companyInfoId,data.username);
                if (result){
                    obj.del(); //删除页面上的数据，后台并没有删除
                    layer.msg('删除成功',{icon:1,time:500},function () {
                        reload();
                    });
                } else {
                    layer.msg('删除失败',{icon:2,time:1000});
                }
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(event === 'edit'){ //编辑
            var strInfoId = data.companyInfoId;
            if (data.companyInfoId == null || data.companyInfoId == undefined){
                strInfoId = '';
            }
            layer.open({
                type:2,
                shade:0.2,
                title:'编辑公司信息',
                skin:'layui-layer-lan',
                area:['800px','700px'],
                content:'../../pages/backend/companyDetail.html?strInfoId='+strInfoId+'&username='+data.username
            });
        }else {
            if (event == 'modify') {
                layer.open({
                    type:2,
                    shade:0.2,
                    title:'修改公司密码',
                    skin:'layui-layer-lan',
                    area:['800px','700px'],
                    content:'../../pages/backend/changePassword.html?username='+data.username
                });
            }
        }
    });
});

function deleteById(strInfoId,username) {
    var result = false;
    $.ajax({
        url:'../../company/deleteById',
        type: 'get',
        data:{
            strInfoId:strInfoId,
            username:username
        },
        dataType:'json',
        async:false,
        success:function (json) {
            if (json == null || json == undefined){
                result = false;
            }
            else {
                result = true;
            }
        }
    });
    return result;
}

function search(){
    var companyName = document.getElementById('companyName').value;

    table.reload('tableContent',{
        where: { //设定异步数据接口的额外参数，任意设
            companyName: companyName
        },
        page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}

function reload() {
    document.getElementById('companyName').value = '';
    search();
}