var strInfoId;
var username;
layui.use('layer',function () {
    var element = layui.layer;
});

layui.use('form',function () {
    var element = layui.form;
});

layui.use('laydate',function () {
    var laydate = layui.laydate;

    laydate.render({
        elem: '#personBerthDay',
        type: 'datetime',
        format: 'yyyy-MM-dd HH:mm:ss'
    });

    laydate.render({
        elem: '#personLevelDay',
        type: 'datetime',
        format: 'yyyy-MM-dd HH:mm:ss'
    });
});
$(function () {
    strInfoId = getParameter('strInfoId');
    username = getParameter('username');

    $.ajax({
        url:'../../person/getDetailData',
        type: 'get',
        data:{
          username:username
        },
        dataType:'json',
        success:function (json) {
            if (json == null || json == undefined){
                layer.msg('系统错误',{icon:2,time:1000});
            }
            else {
                setData(json.data);
            }
        }
    });


});

function setData(data) {

    if (data == null || data == undefined) {
        layer.msg('数据为空',{icon:2,time:1000});
        return;
    }

    username = data.username;

    if (data.personName == null || data.personName == undefined){
        data.personName = '';
    }
    document.getElementById('personName').value = data.personName;

    if (data.personAddress == null || data.personAddress == undefined){
        data.personAddress = '';
    }
    document.getElementById('personAddress').value = data.personAddress;


    if (data.personPhone == null || data.personPhone == undefined){
        data.personPhone = '';
    }
    document.getElementById('personPhone').value = data.personPhone;

    if (data.personSex == null || data.personSex == undefined){
        data.personSex = '';
    }
    document.getElementById('personSex').value = data.personSex;
/**************************************************************************/
    if (data.personBerthDay == null || data.personBerthDay == undefined){
        data.personBerthDay = '';
    }
    document.getElementById('personBerthDay').value = data.personBerthDay;

    if (data.personCertId == null || data.personCertId == undefined){
        data.personCertId = '';
    }
    document.getElementById('personCertId').value = data.personCertId;

    if (data.personLevel == null || data.personLevel == undefined){
        data.personLevel = '';
    }
    document.getElementById('personLevel').value = data.personLevel;

    if (data.personLevelDay == null || data.personLevelDay == undefined){
        data.personLevelDay = '';
    }
    document.getElementById('personLevelDay').value = data.personLevelDay;

    if (data.personExaminer == null || data.personExaminer == undefined){
        data.personExaminer = '';
    }
    document.getElementById('personExaminer').value = data.personExaminer;

    layui.use('form',function () {
        var element = layui.form;
        element.render('select');
    });
}

function save() {
    var detailData = {};
    var usernameCopy = username;
    var personName = document.getElementById('personName').value;
    if (personName == null || personName == undefined || personName == '')
    {
        layer.msg('性名不能为空',{icon:2,time:1000});
        return;
    }
    var personCertId = document.getElementById('personCertId').value;
    if (personCertId == null || personCertId == undefined || personCertId == ''){
        layer.msg('会员编号不能为空',{icon:2,time:1000});
        return;
    }
    var personAddress = document.getElementById('personAddress').value;
    var personPhone = document.getElementById('personPhone').value;
    if (personPhone != null && personPhone != undefined && personPhone != ''){
        if(!(/^1[34578]\d{9}$/.test(personPhone))){
            layer.msg('手机号码填写错误',{icon:2,time:1000});
            return;
        }
    }
    var personSex = document.getElementById('personSex').value;

    var personBerthDay = document.getElementById('personBerthDay').value;
    var personLevel = document.getElementById('personLevel').value;
    var personLevelDay = document.getElementById('personLevelDay').value;
    var personExaminer = document.getElementById('personExaminer').value;


    detailData.personName = personName;
    detailData.personAddress = personAddress;
    detailData.personPhone = personPhone;
    detailData.personSex = personSex;
    /**************************************************/
    detailData.personBerthDay = personBerthDay;
    detailData.personCertId = personCertId;
    detailData.personLevel = personLevel;
    detailData.personLevelDay = personLevelDay;
    detailData.personExaminer = personExaminer;
    /**************************************************/
    detailData.username = usernameCopy;
    $.ajax({
        url:'../../person/saveOrUpdate',
        type: 'get',
        data:{
            strInfoId:strInfoId,
            singleData: JSON.stringify(detailData)
        },
        dataType:'json',
        success:function (json) {
            if (json == null || json == undefined){
                layer.msg('系统错误',{icon:2,time:1000});
            }
            else {
                layer.msg('保存成功',{icon:1,time:500},
                    function () {
                        cancle();
                        window.parent.reload();
                    });
            }
        }
    });
}


function cancle() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}