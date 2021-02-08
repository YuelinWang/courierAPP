
layui.config({base : "assets/"}).extend({"goodscategory_config":"admin/goodscategory/goodscategory-config","magicalcoderedit" : "magicalcoder/v001/rmp/magicalcoderedit"})
layui.use(["goodscategory_config","magicalcoderedit"],function(){
    var magicalcoderedit = layui.magicalcoderedit(layui.goodscategory_config);
    //在提交表单前执行回调方法
    var submitBeforeCallBack = function (postData) {
        return true;//则继续提交
    }
    magicalcoderedit.bindFormArea();
    magicalcoderedit.submitEvents(submitBeforeCallBack);
})
