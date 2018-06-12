import { HttpHeaders } from '@angular/common/http';

/**
 * 接口地址管理
 * @type {{}}
 */
export const ApiUrl = {
  api_url: 'http://localhost:4200/',
  api_uri: 'http://localhost:8080/',
  // api_uri: 'http://121.13.219.106:8000/listedfacesys/',  //测试环境外部访问地址
  //api_uri: 'http://10.100.54.16:8000/listedfacesys/',    //测试环境内部访问地址

  // api_uri: 'http://10.100.24.26:8080/listedfacesys/', //aut应用服务器内部访问地址
  // api_uri: 'http://121.13.219.105:8080/listedfacesys/', //aut应用服务器外网访问地址

  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),

  user_login:'ifcUser/login',
  user_register:'ifcUser/register',

  regionRisk_warningTop: 'regionRisk/warningTop',
  regionRisk_lastingBondViolation: 'regionRisk/lastingBondViolation',
  regionRisk_newsContent: 'regionRisk/newsContent/',
  regionRisk_newsCharts: 'regionRisk/newsChart',
  regionRisk_newsChartByDate: 'regionRisk/newsChartByDate',
  regionRisk_geographyAllCompanyData: 'regionRisk/companyMap',

  regionRisk_trendChartSelectBase: 'assets/dataJson/selectBase.json',

  regionRisk_trendWarningChartSingle: 'regionRisk/warningChartSingle',
  regionRisk_trendWarningChart:'regionRisk/warningChart',


  regionRisk_geographySZCityData:'regionRisk/companyMapRatio',  //深圳市 所有区公司数据

  regionRisk_riskDistributeMap:'regionRisk/riskDistributeMap',  //深圳市 所有区公司评级数据
  regionRisk_negativeDistributeMap:'regionRisk/negativeDistributeMap',  //深圳市 所有区公司负面信息数据
  regionRisk_negativeDistributeRecord:'regionRisk/negativeDistributeRecord',  //深圳市 所有区公司负面信息数据
  regionRisk_riskDimensionMap:'regionRisk/riskDimensionMap',  //深圳市 所有区公司风险维度数据
  regionRisk_riskCompanyInfo:'regionRisk/riskCompanyInfo',  //深圳市 所有区公司风险维度数据


  groupRisk_newsCharts: 'groupRisk/consensusChart',
  groupRisk_newsChartByDate: 'groupRisk/consensusChartSingle',

  groupRisk_followCompany: 'groupRisk/userWarningTop',
  groupRisk_getShortCutWarningTop: 'groupRisk/getShortCutWarningTop',
  groupRisk_newsConsensus:'groupRisk/newsConsensus',//新闻舆情风险分页查询接口


  groupRisk_eventRisk: 'groupRisk/eventRisk',
  groupRisk_singleEventRisk:'groupRisk/singleEventRisk',//获取单个公司全部公告
  groupRisk_eventRiskDetail:'groupRisk/eventRiskDetail',//获取公告详情

  userAttention_findAllFollowedCompyInfo:'userAttention/findAllFollowedCompyInfo',
  userAttention_findNotFollowCompyInfo:'userAttention/findNotFollowCompyInfo',

  ifcUser_addAttention: 'userAttention/add',
  ifcUser_deleteAttention: 'userAttention/delete',

  companyBaseInfo_company: 'companyBase/company/',//公司基本信息
  companyRisk_controller: 'companyBase/managelevel/',//管理层信息

  companyRisk_companyAnnounce: 'companyRisk/companyAnnounce/',//事件公告风险
  companyRisk_announceDetail: 'companyRisk/announceDetail/',//事件公告风险详情 infoCd

  companyRisk_newsConsensus: 'companyRisk/newsConsensus',//公司展台舆情事件

  companyRisk_newsEvent: 'companyRisk/newsEvent/',//新闻事件趋势图
  companyRisk_newsEventByDate: 'companyRisk/newsEventByDate/',//新闻事件趋势单天

  companyRisk_singleCompanyWR: 'companyRisk/singleCompanyWR/',//get风险预警信息
  companyRisk_singleCompanyWRCount: 'companyRisk/getRiskWarningCountByEvent/',//get风险预警信息count数

  companyRisk_wordCloud: 'wordCloud//FindNewKeyWords/FindKeyWordsByCompanyId/',//获取词云数据

  financeInfo_finance: 'financeInfo/finance/',
  financeInfo_cashflow: 'financeInfo/cashflow',

  companyRisk_shareholder: 'companyRisk/shareholder/',//股东及投资信息

  findFollowedCompyInfoById: 'userAttention/findFollowedCompyInfoById/',//查询用户是否已关注某公司接口

  chart_company: 'atlasMap/pfcompany/',
  chart_iscompany: 'atlasMap/iscompany/',
  chart_ship: 'atlasMap/FindRelation/GetAllNodesAndRels',
  chart_thumbnail: 'atlasMap/findThumbnail/thumbnail/company_id/',


  atlas_companySearch: 'companyBase/companySearch/', //根据关键字，查询公司基本信息
  atlas_relationship: 'companyBase/relationship/', //根据关键字，查找关系
  atlas_batchCompanySearch: 'companyBase/batchCompanySearch/', //根据批量导入的关键字字符串，查询公司基本信息

  atlas_warningSearch: 'atlasMap/warningSearch/', //根据关键字，查询公司详情信息

  userManagement_userMaintain: 'userManagement/userMaintain',
  userManagement_editUserMaintain: 'userManagement/editUserMaintain',
  userManagement_deleteUser: 'userManagement/deleteUser/',
  userSupervise_findCompySuperviseInfo: 'userSupervise/findCompySuperviseInfo',
  userSupervise_operationSupervise: 'userSupervise/operationSupervise',

  setting_updatePassword: 'ifcUser/updatePassword', //更新密码
  setting_updateUserInfo: 'ifcUser/updateUserInfo', //更新用户基本信息
  setting_findUserInfo: 'ifcUser/findUserInfo/', //查询用户基本信息
  setting_findUserName: 'ifcUser/findUserName/', //验证昵称
  setting_findAccountName: 'ifcUser/findAccountName/', //验证用户名
  setting_batchAdd: 'userAttention/batchAdd', //批量关注公司

  setting_queryCompanyInfo: 'userAttention/queryCompanyInfo/', //查询公司信息
  setting_queryNotAttentCompyInfo: 'userAttention/queryNotAttentCompyInfo/', //查询用户未关注公司列表

  setting_findFollowedCompanyInfo: 'userAttention/findFollowedCompyInfo', //查询用户基本信息

  manage_riskRateManage: 'userAttention/batchAdd', //批量关注公司

  manage_findAllRiskRateInfo: 'riskRateManage/findAllRiskRateInfo', //查询深圳所有上市公司风险评级信息  /findAllRiskRateInfo/{page}/{pageSize}/{companyNm}
  manage_findRiskRateInfo: 'riskRateManage/findRiskRateInfo', //查询单个公司风险评级信息 /findRiskRateInfo/{companyId}
  manage_editRiskRateInfo: 'riskRateManage/editRiskRateInfo', //编辑风险评级信息

  manage_findAllRiskInfo: 'riskRateManage/findAllRiskInfo',  //查询深圳所有上市公司风险类型信息  /findAllRiskInfo/{page}/{pageSize}/{companyNm}
  manage_findRiskRateInfoById: 'riskRateManage/findRiskRateInfoById', //查询单个公司风险类型信息 /findRiskRateInfoById/{companyRiskId}
  manage_editRiskInfo: 'riskRateManage/editRiskInfo', //编辑风险类型信息
  manage_addRiskInfo: 'riskRateManage/addRiskInfo',//添加风险类型信息
  manage_removeRiskInfo: 'riskRateManage/removeRiskInfo',//删除风险类型信息  /removeRiskInfo/{compyRiskId}

  manage_findAllCompanyBaseInfo: 'riskRateManage/findAllCompanyBaseInfo',  //查询深圳所有上市公司基本信息  /findAllCompanyBaseInfo/{page}/{pageSize}/{companyNm}
  manage_findCompanyBaseInfoById: 'riskRateManage/findCompanyBaseInfoById', //查询单个公司基本信息 /findCompanyBaseInfoById/{companyId}
  manage_editCompanyBaseInfo: 'riskRateManage/editCompanyBaseInfo', //编辑公司基本信息


  regionRisk_geographyChartsMap: 'assets/mapJson/newSZCity.json',
};

export const HttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
