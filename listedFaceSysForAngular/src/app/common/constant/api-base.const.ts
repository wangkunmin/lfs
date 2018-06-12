import { HttpHeaders } from '@angular/common/http';

/**
 * 接口地址管理
 * @type {{}}
 */
export const Base = {
  //公司风险级别
  comRiskLevelList :{
      risk1:{value: 1, text: "高风险"},
      risk2:{value: 2, text: "次高风险"},
      risk3:{value: 3, text: "关注"},
      risk4:{value: 4, text: "正常"},
    },
  comRiskLevels :[
    {key:1,value:'高风险'},
    {key:2,value:'次高风险'},
    {key:3,value:'关注'},
    {key:4,value:'正常'},
    ],

  cityName_SZ:'深圳市各区',

  cityUrlList : {
    SZ:{value:'SZ',text:'深圳市'},
    SZ_GMX:{value:'SZ_GMX',text:'光明新区'},
    SZ_BA:{value:'SZ_BA',text:'宝安区'},
    SZ_LG:{value:'SZ_LG',text:'龙岗区'},
    SZ_YT:{value:'SZ_YT',text:'盐田区'},
    SZ_PSX:{value:'SZ_PSX',text:'坪山新区'},
    SZ_DPX:{value:'SZ_DPX',text:'大鹏新区'},
    SZ_NS:{value:'SZ_NS',text:'南山区'},
    SZ_FT:{value:'SZ_FT',text:'福田区'},
    SZ_LHX:{value:'SZ_LHX',text:'龙华新区'},
    SZ_LH:{value:'SZ_LH',text:'罗湖区'},
  },

  cityListForSZ : [{"name":"龙岗区"},{"name":"龙华新区"},{"name":"坪山新区"},{"name":"盐田区"},{"name":"宝安区"},{"name":"福田区"},{"name":"南山区"},{"name":"大鹏新区"},{"name":"罗湖区"},{"name":"光明新区"}],

  riskTypeList:[
    { key:0,value:''},
    { key:2,value:'治理风险'},
    { key:3,value:'信息披露'},
    { key:1,value:'财务会计'},
    { key:4,value:'其他'},
  ],


  types :{
    1:'经营异常',
    2:'股东冻结',
    3:'行政处罚',
    4:'股权出质',
    5:'动产抵押',
    6:'监管处罚',
    7:'诚信信息',
    8:'数据异常',
    9:'失信被执行人',
    10:'失信被执行人',
    11:'被执行人',
    12: '裁判文书',
    13: '法院公告',
    21:'财务预警',
    22:'财务风险',
    23:'治理风险',
    24:'增信风险',
    25:'评级调整风险',
    26:'舆情公告',
    27:'债券违约',
    31 :'经营异常',
    32 :'股东冻结',
    33 :'行政处罚',
    34 :'股权出质',
    35 :'动产抵押',
    36:'对外担保',
    37 :'被执行人',
    38 :'失信被执行人',
    39 :'裁判文书',
    40 :'法院公告'
  },
};
