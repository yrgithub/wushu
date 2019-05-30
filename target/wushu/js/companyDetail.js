var strInfoId;
var username;
layui.use('layer',function () {
    var element = layui.layer;
});

layui.use('form',function () {
    var form = layui.form;
    form.render();
});
layui.use('upload', function() {
    var $ = layui.jquery,
        upload = layui.upload;

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#uploadPicture',
        url: '../../companyPicture/uploadPicture',
        before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#picture').attr('src', result); //图片链接（base64）
            });
        },
        done: function (res, index, upload) {
            //如果上传失败
            if (res.data == 1) {
                layer.msg('上传失败',{icon:2,time:1000});
                var demoText = $('#pictureText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
                return ;
            }
            //上传成功
            if (res.data != null && res.data != undefined){
                layer.msg('上传成功',{icon:1,time:1000});
                var pictureIdDemo = $('#hiddenPictureId');
                pictureIdDemo.val(res.data.pictureId);

                var pictureDemo = $('#picture');
                pictureDemo.attr('src','../../serverCompanyPicture/'+res.data.picturePath);
            }

        }
    });
});

$(function () {
    strInfoId = getParameter('strInfoId');
    username = getParameter('username');

    createSelected('companyArea','86');

    $.ajax({
        url:'../../company/getDetailData',
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

    if (data.companyName == null || data.companyName == undefined){
        data.companyName = '';
    }
    document.getElementById('companyName').value = data.companyName;


    if (data.companyAddress == null || data.companyAddress == undefined){
        data.companyAddress = '';
    }
    document.getElementById('companyAddress').value = data.companyAddress;

    if (data.companyArea == null || data.companyArea == undefined){
        data.companyArea = '';
    }
    document.getElementById('companyArea').value = data.companyArea;

    if (data.companyTel == null || data.companyTel == undefined){
        data.companyTel = '';
    }
    document.getElementById('companyTel').value = data.companyTel;

    if (data.companyCorporate == null || data.companyCorporate == undefined){
        data.companyCorporate = '';
    }
    document.getElementById('companyCorporate').value = data.companyCorporate;

    if (data.companyPictureId == null || data.companyPictureId == undefined){
        data.companyPictureId = '';
    }
    document.getElementById('hiddenPictureId').value = data.companyPictureId;

    var pictureUrlDemo = $('#picture');
    if (data.companyPictureUrl == null || data.companyPictureUrl == undefined || data.companyPictureUrl == ''){
        pictureUrlDemo.attr('src','../../images/companyDefault.png');
    }
    else {
        pictureUrlDemo.attr('src','../../serverCompanyPicture/'+data.companyPictureUrl);
    }

    if (data.companyDescribe == null || data.companyDescribe == undefined){
        data.companyDescribe = '';
    }
    document.getElementById('companyDescribe').value = data.companyDescribe;

    layui.use('form',function () {
        var form = layui.form;
        form.render('select');
    });
}

function save() {
    var detailData = {};
    var usernameCopy = username;
    var companyName = document.getElementById('companyName').value;
    if (companyName == null || companyName == undefined || companyName == ''){
        layer.msg('公司名称不能为空',{icon:2,time:1000});
        return;
    }
    var companyAddress = document.getElementById('companyAddress').value;
    var companyArea = document.getElementById('companyArea').value;
    var companyTel = document.getElementById('companyTel').value;
    if (companyTel != null && companyTel != undefined && companyTel != ''){
        if(!(/^1[34578]\d{9}$/.test(companyTel))){
            layer.msg('手机号码填写错误',{icon:2,time:1000});
            return;
        }
    }
    var companyPictureId = document.getElementById('hiddenPictureId').value;
    var companyDescribe = document.getElementById('companyDescribe').value;
    var companyCorporate = document.getElementById('companyCorporate').value;

    detailData.companyName = companyName;
    detailData.companyAddress = companyAddress;
    detailData.companyArea = companyArea;
    detailData.companyTel = companyTel;
    detailData.companyCorporate = companyCorporate;
    detailData.companyPictureId = companyPictureId;
    detailData.companyDescribe = companyDescribe;
    detailData.username = usernameCopy;
    $.ajax({
        url:'../../company/saveOrUpdate',
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