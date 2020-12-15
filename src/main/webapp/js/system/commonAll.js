//公共组件
//数组传值时，不添加[]
$.ajaxSettings.traditional = true;


//查询
$(function () {
    $("#btn_query").click(function () {
        $("#currentPage").val(1);
        $("#searchForm").submit();
    });
});
//删除
$(function () {

    $.messager.model = {
        ok: {text: "确认"},
        cancel: {text: "取消"}
    };

    $(".btn-delete").click(function () {
        var url = $(this).data("url");
        console.log(url);
        $.messager.confirm("温馨提示", "是否确认删除此数据？", function () {
            $.get(url, function (data) {
                handleMessage(data);
            });

        })
    })

})


function handleMessage(data) {
    //data后台响应的数据
    if (data.success) {
        $.messager.alert("温馨提示", "执行成功，2秒后自动刷新");
        //2秒后自动刷新页面
        setTimeout(function () {
            window.location.reload();
        }, 2000)

    } else {
        $.messager.alert("温馨提示", data.errorMsg);
    }
}

//跳转
$(function () {
    //编辑跳转
    $(function () {
        $(".btn_redirect").click(function () {
            var url = $(this).data("url");
            window.location.href = url;
        })
    })
})
