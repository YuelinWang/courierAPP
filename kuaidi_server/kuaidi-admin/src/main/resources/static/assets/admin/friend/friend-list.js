

layui.config({base : "assets/"}).extend({"friend_config":"admin/friend/friend-config","magicalcoderlist" : "magicalcoder/v001/rmp/magicalcoderlist"})
layui.use(["magicalcoderlist","friend_config"],function(){
    var config = $.extend(layui.friend_config,{
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            id:'id',uid:'uid',fid:'fid',time:'time'
        }
    });
    var  magicalcoderlist = layui.magicalcoderlist(config);
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
                {field: 'id', title: 'id', minWidth:100, align:"center",sort:true},
                {field: 'uid', title: '用户id', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.uid || d.uid==null) ? '' : d.uid
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_uid" lay-ignore="true"  name="uid" data-identify="'+d.id+'" data-value="" data-url="admin/user_rest/search" data-id="id" data-text-fields="realname" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="用户id">'+option+'</select>'
                    },sort:true
                },
                {field: 'fid', title: '好友id', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.fid || d.fid==null) ? '' : d.fid
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_fid" lay-ignore="true"  name="fid" data-identify="'+d.id+'" data-value="" data-url="admin/user_rest/search" data-id="id" data-text-fields="realname" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="好友id">'+option+'</select>'
                    },sort:true
                },

            {field: 'time', title: '添加好友时间', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.time) +'" class="magicalcoder-table-text layui-input security_list_table_form_time" name="time" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="添加好友时间"/>'
                }
                , sort:true
            },
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
