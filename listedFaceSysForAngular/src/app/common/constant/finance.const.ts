export const RptTimeTypeCd = [
  {'PARAMCHNAME': '年度报告', 'PARAMCODE': '1'},
  {'PARAMCHNAME': '半年度报告', 'PARAMCODE': '2'},
  {'PARAMCHNAME': '一季度报告', 'PARAMCODE': '3'},
  {'PARAMCHNAME': '三季度报告', 'PARAMCODE': '4'},
  {'PARAMCHNAME': '其他报告', 'PARAMCODE': '5'},
  {'PARAMCHNAME': '7-9月季报', 'PARAMCODE': '6'},
  {'PARAMCHNAME': '第四季度报告', 'PARAMCODE': '7'},
  {'PARAMCHNAME': '第二季度报告', 'PARAMCODE': '8'},
  {'PARAMCHNAME': '第三季度报告', 'PARAMCODE': '9'}
];

// 财务报表不显示的对象
export const NotShowColumn = [
  'lkpFinansubjectdispSid',
  'subjectCd',
  'parentSubjectCd',
  'subjectLevel',
  'isLabel',
  'isBold',
  'isFormular'
];

export const MarketType = {
  1: '上市后',
  0: '上市前'
};

// 公司类型
export const CompanyList = {
  0: '通用',
  1: '银行',
  2: '证券',
  3: '保险',
  4: '未知'
};

// 报告期
export const ReportDate = {
  1: '年度报告',
  2: '半年度报告',
  3: '一季度报告',
  4: '三季度报告',
  5: '其他报告',
  6: '7-9月季报',
  7: '第四季度报告',
  8: '第二季度报告',
  9: '第三季度报告'
};

// 报表类型
export const ReportType = {
  1: '合并',
  2: '母公司'
};

// 数据来源
export const DataForm = {
  2995: '临时公告',
  3025: '年度报告',
  3089: '募集说明书',
  3377: '未知',
  3257: '一季度业绩公告',
  3258: '半年度业绩公告',
  3259: '三季度业绩公告',
  3260: '年度业绩公告',
  2600: '一季度报告',
  2601: '半年度报告',
  2637: '招股说明书',
  2638: '上市公告书',
  2639: '转让说明书',
  2691: '定期报告',
  2837: '三季度报告',
  2920: '招股意向书',
  2973: '招股说明书（申报稿）'
};

export const NotCompare = [
  '报表日期',
  '报告期',
  '报表类型',
  '公司类型',
  '上市前/上市后'
];
