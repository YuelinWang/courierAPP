
//表单通用配置 列表页 详情页都需要
layui.define(['jquery'],function(exports){
    var obj =
        {
            tableNameRest:"goods_file_rest",
            tableName:"goods_file",
            moduleName:"goods_file",//sys_module的moduleName
            formVerifyEditData:{//详情页提交时表单校验 使用方法参考layui官网 表单验证规则

            },
            form:{

                  fileSrc:{
                        renderConfig:{"auto":true,"acceptMime":"images","multiple":false,"inputType":"imgfile","drag":true,"exts":"jpg|png|gif|bmp|jpeg","accept":"images"}
                    }
            }
        }
    exports('goodsfile_config',obj);
})
