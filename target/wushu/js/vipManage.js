var table;
var role;
layui.use('table', function(){
    table = layui.table;
    role = getParameter('role');
    var firstValue = document.getElementById('firstValue').value;
    //第一个实例
    table.render({
        id: 'tableContent',
        elem: '#tableId',
        height: 'full-200',
        url: '../../person/getBasicData', //数据接口
        where:{
            firstValue:firstValue,
            role:role
        },
        page: true, //开启分页
        cols: [[ //表头
            {field: 'username', title: '用户名',align:'center',width:100},
            {field: 'typeNameZh', title: '用户类型',align:'center',width:100},
            {field: 'personCertId', title: '会员编号',align:'center',  width: 200},
            {field: 'personName', title: '性名',align:'center', width:80},
            {field: 'personSex', title: '性别',align:'center',  width:80,templet:function (data) {
                    if (data.personSex == 1) {
                        return '男';
                    }
                    if (data.personSex == 2) {
                        return '女';
                    }
                    if (data.personSex == null || data.personSex == undefined || data.personSex == '')
                    {
                        return '';
                    }
                }},
            {field: 'personPhone', title: '个人电话',align:'center',  width:120},
            {field: 'personAddress', title: '家庭住址',align:'center',  width: 300},

            {field: 'personBerthDay', title: '出生日期',align:'center',  width: 180},
            {field: 'personLevel', title: '级别',align:'center',  width: 100},
            {field: 'personLevelDay', title: '晋级日期',align:'center',  width: 180},
            {field: 'personExaminer', title: '考官',align:'center',  width: 100},
            {fixed: 'right', title:'操作', toolbar: '#operation',align:'center',  width:250}
        ]]
    });

    table.on('tool(tableFilter)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var event = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if(event === 'del'){ //删除
            var result = false;
            layer.confirm('真的删除吗？', function(index){
                result = deleteById(data.personInfoId,data.username);
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
            var strInfoId = data.personInfoId;
            if (data.personInfoId == null || data.personInfoId == undefined){
                strInfoId = '';
            }
            layer.open({
                type:2,
                shade:0.2,
                title:'编辑个人信息',
                skin:'layui-layer-lan',
                area:['800px','700px'],
                content:'../../pages/backend/vipDetail.html?strInfoId='+strInfoId+'&username='+data.username
            });
        }
        else {
            if (event == 'modify') {
                layer.open({
                    type:2,
                    shade:0.2,
                    title:'修改个人密码',
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
        url:'../../person/deleteById',
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
    var firstValue = document.getElementById('firstValue').value;

    table.reload('tableContent',{
        where: { //设定异步数据接口的额外参数，任意设
            firstValue: firstValue,
            role:role
        },
        page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}

function reload() {
    document.getElementById('firstValue').value = '';
    search();
}