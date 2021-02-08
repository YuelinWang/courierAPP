/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

layui.config({base : "assets/"}).extend({"user_config":"admin/user/user-config","magicalcoderlist" : "magicalcoder/v001/rmp/magicalcoderlist"})
layui.use(["magicalcoderlist","user_config"],function(){
    var config = $.extend(layui.user_config,{
        childrenPage:[
            {
                tabTitle:"orders",
                url:"admin/page/orders/list",
                mcForeignName:"uid"
            },            {
                tabTitle:"friend",
                url:"admin/page/friend/list",
                mcForeignName:"uid"
            },            {
                tabTitle:"forders",
                url:"admin/page/forders/list",
                mcForeignName:"uid"
            },            {
                tabTitle:"moments",
                url:"admin/page/moments/list",
                mcForeignName:"uid"
            }        ],
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            id:'id',username:'username',password:'password',realname:'realname',phone:'phone'
        }
    });
    var  magicalcoderlist = layui.magicalcoderlist(config);
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
                {field: 'id', title: 'id', minWidth:100, align:"center",sort:true},

            {field: 'username', title: '用户名，手机号', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.username) +'" class="magicalcoder-table-text layui-input security_list_table_form_username" name="username" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="用户名，手机号"/>'
                }
                , sort:true
            },

            {field: 'password', title: '密码', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.password) +'" class="magicalcoder-table-text layui-input security_list_table_form_password" name="password" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="密码"/>'
                }
                , sort:true
            },

            {field: 'realname', title: '昵称', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.realname) +'" class="magicalcoder-table-text layui-input security_list_table_form_realname" name="realname" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入昵称"/>'
                }
                , sort:true
            },

            {field: 'phone', title: '电话号', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.phone) +'" class="magicalcoder-table-text layui-input security_list_table_form_phone" name="phone" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="电话号"/>'
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
