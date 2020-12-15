<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门管理</title>
    <#include "../common/link.ftl" />

    <script>
        //等待页面加载完成
        $(function () {
            //模态框点击事件，点击弹出模态框
            $(".btn-input").click(function () {
                //刷新页面
                $("input[name=id]").val("");
                $("input[name=name]").val("");
                $("input[name=sn]").val("");
                //编辑页面的回显
                var json = $(this).data("json");
                if (json) {
                    $("input[name=id]").val(json.id);
                    $("input[name=name]").val(json.name);
                    $("input[name=sn]").val(json.sn);
                }

                $("#myModal").modal("show");
            })


            //保存按钮点击事件，点击提交表单
            $(".btn-submit").click(function () {
                $("#editForm").ajaxSubmit(function (data) {
                    handleMessage(data);
                });
            })

        })

    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl"/>
    <!--菜单回显-->
    <#assign currentMenu="department" />
    <#include "../common/menu.ftl"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>部门管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/department/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="${(pageInfo.pageNum)!}">
                    <a href="javascript:;" class="btn btn-success btn-input" style="margin: 10px">
                        <span class="glyphicon glyphicon-plus"></span> 添加
                    </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>编号</th>
                            <th>部门名称</th>
                            <th>部门编号</th>
                            <th>操作</th>
                        </tr>
                        <#list pageInfo.list as entity>
                            <tr>
                                <td>${entity_index+1}</td>
                                <td>${(entity.name)!}</td>
                                <td>${(entity.sn)!}</td>
                                <td>
                                    <a class="btn btn-info btn-xs btn-input" href="#" data-json='${(entity.json)!}'>
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="javascript:;" data-url="/department/delete.do?id=${(entity.id)!}"
                                       class="btn btn-danger btn-xs btn-delete">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>

                    </table>
                    <!--分页-->
                    <#include "../common/page.ftl"/>
                </div>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl"/>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">部门编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/department/saveOrUpdate.do" method="post" id="editForm">
                    <!-- freemarker取值为空或没有就报错-->
                    <input type="hidden" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">名称：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入部门名"
                                   style="width: 300px">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-2 control-label">编码：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="sn" name="sn" placeholder="请输入部门编码"
                                   style="width: 300px">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary btn-submit">提交</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
