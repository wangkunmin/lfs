export class User {
  userId: number;            //用户ID
  userNm: string;      //昵称
  accountNm: string;        //账户名
  cellPhone: string;       //手机
  phone: string;       //固定电话
  accountPw: any;        //用户密码
  accountId: number;//账户ID
  type: number; // 激活状态
  mail: string;        //邮箱
  position: string;        //职务
  companyId: number;     //公司ID
  companyNm: string;       //公司名称
  address: string;     //公司地址
  reAccountPw?: any;
}
