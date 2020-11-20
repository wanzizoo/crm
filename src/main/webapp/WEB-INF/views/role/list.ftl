<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
    <#include "../common/link.ftl"/>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl"/>
    <!--菜单回显-->
    <#assign currentMenu="role"/>
    <#include "../common/menu.ftl"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>角色管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/role/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="${result.currentPage}">
                    <a href="/role/input.do?currentPage=${result.currentPage}" class="btn btn-success btn-input" style="margin: 10px">
                        <span class="glyphicon glyphicon-plus"></span> 添加
                    </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>编号</th>
                            <th>角色名称</th>
                            <th>角色编号</th>
                            <th>操作</th>
                        </tr>
                        <#list result.data as entity>
                            <tr>
                                <td>${entity_index+1}</td>
                                <td>${(entity.name)!}</td>
                                <td>${(entity.sn)!}</td>
                                <td>
                                    <a class="btn btn-info btn-xs btn-input" href="/role/input.do?id=${(entity.id)!}&currentPage=${(result.currentPage)!}">
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="/role/delete.do?id=${(entity.id)!}&currentPage=${(result.currentPage)!}" class="btn btn-danger btn-xs btn-delete">
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
</body>
</html>
