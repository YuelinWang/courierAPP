package com.my.kuaidi.admin.rmp.dto;


import com.my.kuaidi.admin.rmp.model.SysRole;

import java.util.List;

public class SysRoleDto extends SysRole {
    private List<SysModuleCategoryDto> sysModuleCategoryDtoList;

    public List<SysModuleCategoryDto> getSysModuleCategoryDtoList() {
        return sysModuleCategoryDtoList;
    }

    public void setSysModuleCategoryDtoList(List<SysModuleCategoryDto> sysModuleCategoryDtoList) {
        this.sysModuleCategoryDtoList = sysModuleCategoryDtoList;
    }
}
