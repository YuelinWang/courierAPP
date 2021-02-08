/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

layui.config({base : "assets/"}).extend({"logistics_config":"admin/logistics/logistics-config","magicalcoderlist" : "magicalcoder/v001/rmp/magicalcoderlist"})
layui.use(["magicalcoderlist","logistics_config"],function(){
    var config = $.extend(layui.logistics_config,{
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            message:'message',time:'time',oid:'oid'
        }
    });
    var  magicalcoderlist = layui.magicalcoderlist(config);
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
                {field: 'oid', title: '订单id', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.oid || d.oid==null) ? '' : d.oid
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_oid" lay-ignore="true"  name="oid" data-identify="'+d.id+'" data-value="" data-url="admin/orders_rest/search" data-id="id" data-text-fields="uid" lay-verify="magicalCoderVerify" magicalcoder-verify="" disabled="true" placeholder="订单id">'+option+'</select>'
                    },sort:true
                },

            {field: 'message', title: '物流信息', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.message) +'" class="magicalcoder-table-text layui-input security_list_table_form_message" name="message" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="物流信息"/>'
                }
                , sort:true
            },
            {field: 'time', title: '更新时间', align:'center', minWidth:250, templet :"#timeTemplate",sort:true},
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
