function changePage(url)
{
	$('#mainFrm').attr('src',url);
}

function getParameter(paraName) {
    var url = document.location.toString();
    var arrObj = url.split('?');

    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split('&');
        var arr;

        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split('=');

            if (arr != null && arr[0] == paraName) {
                return arr[1];
            }
        }
        return '';
    }
    else {
        return '';
    }
}

function createSelected(cmpl,dictType) {
    $.ajax({
        url:'../../dict/getDictByType',
        type:'get',
        data:{
            dictType:dictType
        },
        dataType:'json',
        success:function (json) {
            if (json.data == 1){
                layer.msg('系统错误',{icon:2,time:500});
            }
            else {
                var data = json.data;
                for (var i=0;i<data.length;i++){
                    $('#'+cmpl).append('<option value="'+data[i].contentId+'">'+data[i].contentName+'</option>');
                }
            }

        }
    });
}