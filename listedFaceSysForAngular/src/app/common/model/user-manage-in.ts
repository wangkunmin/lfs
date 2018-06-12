export class UserManageIn {
  userId?: number;        //用户ID
  userNm?: string;      //昵称
  accountId?: number;     //账号ID
  accountNm?: string;   //账户名
  accountPw?: any;   //用户密码
  roleId?: any;        //权限ID
  roleNm?: string;      //角色名称
  email?: string;       //邮箱
  companyNm?: string;   //公司名称
  dateStart?: string;   //开始时间（YYYYMMDD）
  dateEnd?: string;     //结束时间（YYYYMMDD）
  page?: number;           //页码
  pageSize?: number;       //单页数据量
  type?: number;
}
