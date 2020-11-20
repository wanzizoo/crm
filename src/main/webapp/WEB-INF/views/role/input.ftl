<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
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
                $(".selfPermissions option").prop("selected", true);
            });
        })

        //页面加载完成，将右边框中存在多角色从左边删除
        $(function () {
            var ids = [];
            $(".selfPermissions option").each(function (index, option) {
                ids.push(option.value);
            })
            //获取右边框多所有角色的ID
            $(".allPermissions option").each(function (index, option) {
                //判断左边所有的元素的id是否包含右边的角色id，有则删除
                if ($.inArray(option.value, ids) != -1) {
                    $(option).remove();
                }
            })
        })
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl" />
    <!--菜单回显-->
    <#assign currentMenu="role"/>
    <#include "../common/menu.ftl" />
    <div class="content-wrapper">
        <section class="content-header">
            <h1>角色编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/role/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(r.id)!}" name="id">
                    <input type="hidden" value="${(currentPage)!}" name="currentPage">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">名称：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" value="${(r.name)!}"
                                   placeholder="请输入角色名">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-2 control-label">编码：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="sn" name="sn" value="${(r.sn)!}"
                                   placeholder="请输入角色编码">
                        </div>
                    </div>

                    <div class="form-group " id="permission">
                        <label for="permission" class="col-sm-2 control-label">分配权限：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allPermissions" size="15">
                                    <#list permissions as p>
                                        <option value="${(p.id)!}">${(p.name)!}</option>
                                    </#list>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfPermissions" size="15" name="permissionIds">
                                    <#list r.permissions as rp>
                                        <option value="${(rp.id)!}">${(rp.name)!}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
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
