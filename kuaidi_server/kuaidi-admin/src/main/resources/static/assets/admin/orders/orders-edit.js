
layui.config({base : "assets/"}).extend({"orders_config":"admin/orders/orders-config","magicalcoderedit" : "magicalcoder/v001/rmp/magicalcoderedit"})
layui.use(["orders_config","magicalcoderedit"],function(){
    var magicalcoderedit = layui.magicalcoderedit(layui.orders_config);
    //在提交表单前执行回调方法
    var submitBeforeCallBack = function (postData) {
        return true;//则继续提交
    }
    magicalcoderedit.bindFormArea();
    magicalcoderedit.submitEvents(submitBeforeCallBack);
})
