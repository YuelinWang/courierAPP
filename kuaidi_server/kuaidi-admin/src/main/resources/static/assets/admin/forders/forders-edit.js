
layui.config({base : "assets/"}).extend({"forders_config":"admin/forders/forders-config","magicalcoderedit" : "magicalcoder/v001/rmp/magicalcoderedit"})
layui.use(["forders_config","magicalcoderedit"],function(){
    var magicalcoderedit = layui.magicalcoderedit(layui.forders_config);
    //在提交表单前执行回调方法
    var submitBeforeCallBack = function (postData) {
        return true;//则继续提交
    }
    magicalcoderedit.bindFormArea();
    magicalcoderedit.submitEvents(submitBeforeCallBack);
})
