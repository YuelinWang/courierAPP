

layui.config({base : "assets/"}).extend({"orders_config":"admin/orders/orders-config","magicalcoderlist" : "magicalcoder/v001/rmp/magicalcoderlist"})
layui.use(["magicalcoderlist","orders_config"],function(){
    var config = $.extend(layui.orders_config,{
        childrenPage:[
            {
                tabTitle:"forders",
                url:"admin/page/forders/list",
                mcForeignName:"oid"
            },            {
                tabTitle:"logistics",
                url:"admin/page/logistics/list",
                mcForeignName:"oid"
            }        ],
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            number:'number',sender:'sender',saddress:'saddress',raddress:'raddress',sphone:'sphone',rphone:'rphone',state:'state'
        }
    });
    var  magicalcoderlist = layui.magicalcoderlist(config);
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},

            {field: 'number', title: '快递单号', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.number) +'" class="magicalcoder-table-text layui-input security_list_table_form_number" name="number" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="快递单号"/>'
                }
                , sort:true
            },

            {field: 'sender', title: '发货人', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.sender) +'" class="magicalcoder-table-text layui-input security_list_table_form_sender" name="sender" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="发货人"/>'
                }
                , sort:true
            },

            {field: 'saddress', title: '发货地址', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.saddress) +'" class="magicalcoder-table-text layui-input security_list_table_form_saddress" name="saddress" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="发货地址"/>'
                }
                , sort:true
            },

            {field: 'raddress', title: '收获地址', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.raddress) +'" class="magicalcoder-table-text layui-input security_list_table_form_raddress" name="raddress" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="收获地址"/>'
                }
                , sort:true
            },

            {field: 'sphone', title: '发货人手机号', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.sphone) +'" class="magicalcoder-table-text layui-input security_list_table_form_sphone" name="sphone" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="发货人手机号"/>'
                }
                , sort:true
            },

            {field: 'rphone', title: '收货人手机号', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.rphone) +'" class="magicalcoder-table-text layui-input security_list_table_form_rphone" name="rphone" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="收货人手机号"/>'
                }
                , sort:true
            },
            {field: 'state', title: '状态',minWidth:350, align:'center', templet:'#stateTemplate',sort:true},
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
