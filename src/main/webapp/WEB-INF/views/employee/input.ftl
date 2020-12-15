<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <#include "../common/link.ftl" />

    <script>
        function moveAll(srcSelect, distSelect) {
            $('.' + srcSelect + " option").appendTo($('.' + distSelect));
        }

        function moveSelected(srcSelect, distSelect) {
            $('.' + srcSelect + " option:selected").appendTo($('.' + distSelect));
        }

        //点击提交，先执行选中方法，再发送请求
        $(function () {
            $("#editForm").submit(function () {
                $(".selfRoles option").prop("selected", true);
            });
        })

        //页面加载完成，将右边框中存在多角色从左边删除
        $(function () {
            var ids = [];
            $.each($(".selfRoles option"), function (index, option) {
                ids.push(option.value);
            })
            console.log(ids);
            //获取右边框多所有角色的ID
            $.each($(".allRoles option"), function (index, option) {
                //判断左边所有的元素的id是否包含右边的角色id，有则删除
                if ($.inArray(option.value, ids) != -1) {
                    $(option).remove();
                }
            })
        })

        $(function () {
            var role;
            //修改超级管理员复选框的选中状态时，进入值改变事件方法
            $("#admin").change(function () {
                if (this.checked) {
                    //detach删除会保留元素的事件
                    role = $("#role").detach();
                } else {
                    $(this).closest(".form-group").after(role);
                }
            })

            //当编辑时
            <#if e??>
            //如果当前员工为超级管理员，删除下面的复选框
            if (${e.admin?c}) {
                //detach删除会保留元素的事件
                role = $("#role").detach();
            }
            </#if>

        })
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl" />
    <!--菜单回显-->
    <#assign currentMenu="employee" />
    <#include "../common/menu.ftl" />
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工编辑</h1>
        </section>
        <section class="content" style="align-content: space-between">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(e.id)!}" name="id">
                    <input type="hidden" value="${(currentPage)!}" name="currentPage">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" value="${(e.name)!}"
                                   placeholder="请输入用户名">
                        </div>
                    </div>

                    <#if !e?? >
                        <div class="form-group" style="margin-top: 10px;">
                            <label for="password" class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 10px;">
                            <label for="repassword" class="col-sm-2 control-label">确认密码：</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" id="repassword" name="repassword"
                                       placeholder="请输入验证密码">
                            </div>
                        </div>
                    </#if>


                    <div class="form-group" style="margin-top: 10px;">
                        <label for="email" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-4">
                            <input type="email" class="form-control" id="email" name="email" value="${(e.email)!}"
                                   placeholder="请输入电子邮箱">
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 10px;">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="age" name="age" value="${(e.age)!}"
                                   placeholder="请输入年龄">
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 10px;">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dept.id" id="dept">
                                <option value="0">请选择所属部门</option>
                                <#if depts??>
                                    <#list depts as d>
                                        <option value="${(d.id)!}">${(d.name)!}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <script>
                        $("select[name='dept.id']").val(${(e.dept.id)!});
                    </script>

                    <div class="form-group" style="margin-top: 10px;">
                        <label for="admin" class="col-sm-2 control-label">　超级管理员：</label>
                        <div class="col-sm-4">
                            <input type="checkbox" id="admin" name="admin">
                        </div>
                    </div>
                    <script>
                        <#if e??>
                        $("input[name=admin]").prop("checked", (${(e.admin?c)!}));
                        </#if>
                    </script>

                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" size="15">
                                    <#if roles??>
                                        <#list roles as r>
                                            <option value="${(r.id)!}">${(r.name)!}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" size="15" name="roleIds">
                                    <#if e?? && e.roles??>
                                        <#list e.roles as r>
                                            <option value="${(r.id)!}">${(r.name)!}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-6">
                            <button id="submitBtn" type="submit" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-danger">重置</button>
                        </div>
                    </div>

                </form>

            </div>

        </section>
    </div>
    <#include "../common/footer.ftl" />
</div>


</body>
</html>
