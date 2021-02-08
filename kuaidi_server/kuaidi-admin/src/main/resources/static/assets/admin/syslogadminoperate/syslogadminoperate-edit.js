
layui.config({
    base : "assets/"
}).extend({
    "magicalcoderedit" : "magicalcoder/v001/rmp/magicalcoderedit"
})
layui.use(["magicalcoderedit"],function(){
    var magicalcoderedit = layui.magicalcoderedit(
    {
        tableNameRest:"sys_log_admin_operate_rest",
        tableName:"sys_log_admin_operate",
        moduleName:"sys_log_admin_operate"//sys_module的moduleName
        }
    );
    //在提交表单前执行回调方法
    var submitBeforeCallBack = function (postData) {
        return true;//则继续提交
    }
    magicalcoderedit.bindFormArea();
    magicalcoderedit.submitEvents(submitBeforeCallBack);
})
