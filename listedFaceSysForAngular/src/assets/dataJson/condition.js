/**
 * Created by Dwyane-Wade on 2017/3/20.
 */
'use strict';
angular.module('cs_portal.conditionCtrl', [])
    .controller('conditionCtrl', ['$scope', '$http', '$uibModal','$timeout', function ($scope, $http, $uibModal,$timeout,$routeParams) {
        $scope.init = function () {
            initCondition();
        };

        // 财务情况
        var initCondition = function () {
            $http.get('#urlcompanyInfo/ratingHistResult/' + $scope.companyId).success(function (data) {
            	if(data.level){
                    $scope.ratingInfoFlag = true;
                    $http.get('#urlcompanyInfo/financeManageScatter/' + $scope.companyId).success(function (resultData) {
                        var arr1 = [];

                        var tem=function(obj){
                            var arr=[];
                            arr.push(obj.financeValue);
                            arr.push(obj.operationValue);
                            arr.push(obj.companyName);
                            arr.push(obj.level);
                            return arr;
                        };

                        for (var i = 0; i < resultData.length; i++) {
                            var t = resultData[i];
                            arr1.push(tem(t));
                            if (t.companyId == $scope.companyId) {
                                var ii = [];
                                ii.push(t.financeValue, t.operationValue);
                            }
                        }
                        changeConditionRs(arr1, ii, data.exposure);
                    });
                } else {
                    $scope.ratingInfoFlag = false;
				}
            });
                                                       
            // 主要财务数据
            $http.get('#urlcompanyInfo/finance/' + $scope.companyId).success(function (data) {
                $scope.mainReport = data.compyFinanceOutData;
                $scope.condata = finData(data.compyFinanceOutData);
            });
        };
		function isEmptyArry (e) {
			if (e.length == 0) {
				return false;
			}else{
				return true;
			}
		}
        function isEmptyObject(e) {
            var t;
            for (t in e)
                return true;
            return false;
        }

        // 整合数据
        var finData = function (digital) {
            var data = [];
            for (var i = 0; i < 12; i++) {
                data.push(onlyOneData(digital, i));
            }
            return [data, finflag(0, data)];
        };

        var finflag = function (num, data) {
            if (data[num][1][0] && num <= 12) {
                finflag(num++, data);
            } else {
                return false;
            }
            return true;
        };

        // table数据分类
        var onlyOneData = function (digital, num) {
            var data = [];
            switch (num) {
                case 0:
                    data = ['资产总计', financeData(digital, 'sumAsset')];
                    break;
                case 1:
                    data = ['负债合计', financeData(digital, 'sumLiab')];
                    break;
                case 2:
                    data = ['流动负债合计', financeData(digital, 'sumLliab')];
                    break;
                case 3:
                    data = ['营业收入', financeData(digital, 'operateReve')];
                    break;
                case 4:
                    data = ['营业成本', financeData(digital, 'operateExp')];
                    break;
                case 5:
                    data = ['营业利润', financeData(digital, 'operateProfit')];
                    break;
                case 6:
                    data = ['利润总额', financeData(digital, 'sumProfit')];
                    break;
                case 7:
                    data = ['净利润', financeData(digital, 'netProfit')];
                    break;
                case 8:
                    data = ['经营活动产生的现金流量净额', financeData(digital, 'netOperateCashflow')];
                    break;
                case 9:
                    data = ['投资活动产生的现金流量净额', financeData(digital, 'netInvCashflow')];
                    break;
                case 10:
                    data = ['筹资活动产生的现金流量净额', financeData(digital, 'netFinaCashflow')];
                    break;
                case 11:
                    data = ['现金及现金等价物净增加额', financeData(digital, 'nicashEqui')];
                    break;
            }
            return data;
        };

        // table数据
        var financeData = function (digital, type) {
            var flag = false;
            var data = [];
            for (var i = 0; i < digital.length; i++) {
                var j = createTableData(digital[i][type]);
                if (null == j || undefined == j || 0 == j || '' == j) {
                    flag = true;
                } else {
                    data.push(j);
                }
            }
            return [flag, data];
        };

        // table数据保留两位有效数字并每3个以逗号分开
        var createTableData = function (num1) {
            if (num1) {
                var num = ((parseFloat(num1) / 10000).toFixed(2)).toString(),
                    result = '';
                var mums;
                if (num.indexOf('-') < 0) {
                    mums = num.split('.');
                }
                if (num.indexOf('-') >= 0) {
                    mums = num.substr(1).split('.');
                }
                while (mums[0].length > 3) {
                    result = ',' + mums[0].slice(-3) + result;
                    mums[0] = mums[0].slice(0, mums[0].length - 3);
                }
                if (mums[0]) {
                    result = mums[0] + result;
                }
                if (mums[1]) {
                    result = result + '.' + mums[1];
                }
                if (num1) {
                    if (num.indexOf('-') < 0) {
                        return result;
                    }
                    if (num.indexOf('-') >= 0) {
                        return '-' + result;
                    }
                }
            } else {
                return num1;
            }
        };

        // 散点图
        var changeConditionRs = function (data,ii,exposure) {
            try {
				var item = {};
				item.data = data;
				item.coode = ii;
				item.name='上市发债企业  ' + exposure +'敞口  风险分布';
				var quan = echarts.init(document.getElementById('quan'));
				quan.setOption(condition(item));
            } catch (e) {
                $scope.ratingLength = 0;
            }
        };

        // 向下取整并保留两位有效数字
        var qScore = function (data) {        	
            return Math.floor(qParse(data));
        };
        // 保留两位有效数字
        var qParse = function (data) {
            return parseFloat(data).toFixed(2);
        };
        // 判断哪个分区
        var qFlag = function (max, min) {
            var flag = 0;
            if (((max - min) / 5 + '').split('.')[1]) {
                flag = ((max - min) / 5 + '').split('.')[1].length;
            }
            return flag;
        };

	    var condition = function  (item) {
	    	var i = item.data;
	    	var coode = item.coode;
	    	var name = item.name;
	    	var option = {
                title : {
                    text: name,
                    x:'center'
                },
                grid: {
                    left: '2%',
                    right: '8%',
                    // bottom: '3%',
                    bottom: 0,
                    containLabel: true
                },
                tooltip : {
                    showDelay : 0,
                    formatter : function (i) {
                        return i.value[2] + ' <br/>' + '经营风险: '+ i.value[1] + '&#x3000; 财务风险: '+ i.value[0];
                    },
                    axisPointer:{
                        show: true,
                        type : 'cross',
                        lineStyle: {
                            type : 'solid',
                            width : '1'
                        }
                    }
                },
                xAxis : [{
                    type : 'value',
                    scale:true,
                    name:'财务风险',
                    splitLine: {
                        show: true,
                        lineStyle:{
                            type:'dashed',
                            width:'.5',
                            opacity:'.8'
                        }
                    },
                    axisLine:{
                        show:false,
                        onZero:false
                    },
                    axisLabel:{
                        show:false
                    },
                    axisTick:{
                        show:false
                    }
                }],
                yAxis : [{
                    type : 'value',
                    scale:true,
                    name:'经营风险',
                    splitLine: {
                        show: true,
                        lineStyle:{
                            type:'dashed',
                            width:'.5',
                            opacity:'.8'
                        }
                    },
                    axisLine:{
                        show:false,
                        onZero:false
                    },
                    splitNumber:5,
                    axisLabel:{
                        show:false,
                        onZero:false
                    },
                    axisTick:{
                        show:false
                    }
                }],
                series : [{
                    type:'scatter',
                    data: i,
                    symbol:'circle',
                    symbolSize:function (i) {
                        var coodeArr = [];
                        coodeArr.push(i[0],i[1]);
                        if (coodeArr[0] == coode[0] && coodeArr[1] == coode[1]) {
                            return '15';
                        } else{
                            return '10';
                        }
                    },
                    markPoint : {
                        label:{
                            normal:{
                                show:false,
                                formatter:function  (param) {
                                },
                                position:'insideLeft'
                            }
                        },
                        data : [{
                            type : '某个坐标',
                            symbolSize:'50',
                            coord:coode,
                            symbol:'pin',
                            itemStyle: {
                                normal: {
                                    color: 'red'
                                }
                            }
                        }]
                    },
                    itemStyle:{
                        normal:{
                            color:function  (i) {
                                var colorList = ['rgb(166,223,184)','rgb(166,223,184)','rgb(171,221,175)','rgb(173,220,171)','rgb(174,219,168)',
                                'rgb(172,220,172)','rgb(178,218,159)','rgb(178,218,160)','rgb(213,229,160)','rgb(223,227,161)','rgb(224,227,161)',
                                'rgb(230,226,162)','rgb(235,225,163)','rgb(242,224,164)','rgb(231,179,147)','rgb(229,172,146)','rgb(228,166,146)',
                                'rgb(226,161,146)','rgb(224,156,146)','rgb(222,148,145)'];

                                var colorNum = parseInt(i.value[3]);
                                return colorList[colorNum];
                            }
                        },
                        emphasis:{
                            color:'pink'
                        }
                    }
                }]
	    	};
	        return option;
	    };
                
        // 打开财务报表
        $scope.openFinanceReportModel = function () {
            $uibModal.open({
                templateUrl: 'financeReport/financeReport.html',
                backdrop: 'static',
                keyboard: false,
                controller: "financeReportCtrl",
                scope: $scope,
                size: 'lg-xx'
            });
        };
    }]);
