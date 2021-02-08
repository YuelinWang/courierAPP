
layui.config({base : "assets/"}).extend({"goods_config":"admin/goods/goods-config","magicalcoderedit" : "magicalcoder/v001/rmp/magicalcoderedit"})
layui.use(["goods_config","magicalcoderedit"],function(){
    var magicalcoderedit = layui.magicalcoderedit(layui.goods_config);
    //在提交表单前执行回调方法
    var submitBeforeCallBack = function (postData) {
        return true;//则继续提交
    }
    magicalcoderedit.bindFormArea();
    magicalcoderedit.submitEvents(submitBeforeCallBack);
})
