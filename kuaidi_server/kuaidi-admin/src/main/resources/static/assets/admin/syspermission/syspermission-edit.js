
layui.config({
    base : "assets/"
}).extend({
    "magicalcoderedit" : "magicalcoder/v001/rmp/magicalcoderedit"
})
layui.use(["magicalcoderedit"],function(){
    var magicalcoderedit = layui.magicalcoderedit(
    {
        tableNameRest:"sys_permission_rest",
        tableName:"sys_permission",
        moduleName:"sys_permission"//sys_module的moduleName
        }
    );
    //在提交表单前执行回调方法
    var submitBeforeCallBack = function (postData) {
        return true;//则继续提交
    }
    magicalcoderedit.bindFormArea();
    magicalcoderedit.submitEvents(submitBeforeCallBack);
})
