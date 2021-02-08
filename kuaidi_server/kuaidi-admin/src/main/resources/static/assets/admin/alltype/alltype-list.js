

layui.config({
    base : "assets/magicalcoder/v001/rmp/"
}).extend({
    "magicalcoderlist" : "magicalcoderlist"
})
layui.use(['magicalcoderlist'],function(){
    var  magicalcoderlist = layui.magicalcoderlist(
    {
        tableNameRest:"all_type_rest",
        tableName:"all_type",
        moduleName:"all_type",//sys_module的moduleName
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{

        }
    });
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
        {title: '操作', minWidth:250, templet:'#newsListOperateTemplate',fixed:"right",align:"center"}
    ]];
    //表格中的一些事件
    var tableEvent = {
        "tool":function (obj,roleId) {//操作部分的按钮
            return true;//不阻止事件继续执行封装的事件
        },
        "sort":function (obj,safeOrderBy) {//排序
            return true;//不阻止事件继续执行封装的事件
        }
    }
    magicalcoderlist.bindTableArea(cols,tableEvent)
    magicalcoderlist.bindAreaEvents();
})
