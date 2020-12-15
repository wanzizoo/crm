<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <#include "../common/link.ftl" />

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl" />
    <!--菜单回显-->
    <#assign currentMenu="employee"/>
    <#include "../common/menu.ftl" />
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/employee/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="${(pageInfo.pageNum)!}">
                    <div class="form-group">
                        <label for="keyword">&nbsp;&nbsp;&nbsp;关键字：</label>
                        <input type="text" name="keyword" id="keyword" value="${(qo.keyword)!}"
                               placeholder="请输入姓名/邮箱" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="dept">部门：</label>
                        <select class="form-control" id="dept" name="deptId" aria-selected="${(qo.deptId)!}">
                            <option value="-1">全部</option>
                            <#list depts as entity>
                                <option value="${(entity.id)!}">${(entity.name)!}</option>
                            </#list>
                        </select>
                        <script>
                            $("#dept option[value='${(qo.deptId)!}']").prop("selected", true);
                        </script>
                    </div>
                    &nbsp;&nbsp;
                    <button id="btn_query" class="btn btn-primary">
                        <span class="glyphicon glyphicon-search"></span>查询
                    </button>
                    <a href="/employee/input.do?currentPage=${(pageInfo.pageNum)!}" class="btn btn-success btn-input"
                       style="margin: 10px">
                        <span class="glyphicon glyphicon-plus"></span> 添加
                    </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <th>email</th>
                            <th>年龄</th>
                            <th>部门</th>
                            <th>操作</th>
                        </tr>

                        <#list  pageInfo.list as entity>
                            <tr>
                                <td>${entity_index+1}</td>
                                <td>${(entity.name)!}</td>
                                <td>${(entity.email)!}</td>
                                <td>${(entity.age)!}</td>
                                <td>${(entity.dept.name)!}</td>
                                <td>
                                    <a class="btn btn-info btn-xs btn-input"
                                       href="/employee/input.do?id=${(entity.id)!}&currentPage=${(pageInfo.pageNum)!}">
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="javascript:;" data-url="/employee/delete.do?id=${(entity.id)!}&currentPage=${(pageInfo.pageNum)!}"
                                       class="btn btn-danger btn-xs btn-delete">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <!--分页-->
                    <#include "../common/page.ftl" />
                </div>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl" />
</div>
</body>
</html>
