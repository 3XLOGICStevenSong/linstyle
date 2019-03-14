// 角色
export class Role {
    // 角色id
    public roleId: number;
    // 角色名称
    public roleName: string;
    // 选中状态(0.未被选中  1.被选中)
    public checkStatus: number;
    // 角色资源
    public resourceList: Array<Resource>;
}

// 权限资源
export class Resource {
    // id
    public resourceId: number;
    // 资源名称
    public resourceName: string;
    // 资源描述，只有三级资源有memo
    public memo: string;
    // 一级资源和二级资源，都只有子资源。三级资源没有
    public  sysResourceList: Array<Resource>;
    // 选中状态(0.未被选中  1.被选中)
    public checkStatus: number;
} 

// 角色描述
export class RoleDescription {
    // 资源Id
    public resourceId: number;
         
    // 资源名称
    public resourceName: string;
        // 功能描述
    public memo: string;
}

// 角色拥有者
export class RoleOwner {
    // 员工号
    public  cardNo: string;
         
    // 员工姓名
    public name: string;
    
    // 部门名称
    public depName: string;
}

// 只有角色id(更新用户的角色时列表中的对象)
export class RoleIdInfo {
    // 角色id
    public roleId: string;
}

// 更新用户的角色时用到的对象
export class UpdateRoleOwnerClient {
    // 员工号
    public  cardNo: string;
    // 角色id列表
    public roleList: Array<RoleIdInfo>;
}


// 只有资源id(更新角色时列表中的对象)
export class ResourceIdInfo {
    // 资源id
    public resourceId: number;
}

// 更新角色时用到的对象
export class UpdateRoleClient {
    // 角色id
    public roleId: number;
     // 角色名称
    public roleName: string;
    // 角色id列表
    public sysResourceList: Array<ResourceIdInfo>;
}

// 添加角色时用到的对象
export class AddRoleClient {
    // 角色名称
    public roleName: string;
    // 角色id列表
    public sysResourceList: Array<ResourceIdInfo>;
}

// 树形结构用到的对象
export class TreeNode {
    // 树形结构中的id
    public id: number;
    // 父亲id
    public pId: number;
    // 名称
    public name: string;
    // 资源id
    public resourceId: number;
    // 是否选中
    public checked: boolean;
    // 是否展开
    public open: boolean;
    // 是否是action
    public isAction: boolean;
}
