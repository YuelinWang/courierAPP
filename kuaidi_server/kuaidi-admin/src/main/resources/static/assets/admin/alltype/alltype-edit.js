
layui.config({
    base : "assets/magicalcoder/v001/rmp/"
}).extend({
    "magicalcoderedit" : "magicalcoderedit"
})
layui.use(["magicalcoderedit"],function(){
    var magicalcoderedit = layui.magicalcoderedit(
    {
        tableNameRest:"all_type_rest",
        tableName:"all_type",
        moduleName:"all_type"//sys_module的moduleName
        }
    );
    //在提交表单前执行回调方法
    var submitBeforeCallBack = function (postData) {
        return true;//则继续提交
    }
    magicalcoderedit.bindFormArea();
    magicalcoderedit.submitEvents(submitBeforeCallBack);
})
