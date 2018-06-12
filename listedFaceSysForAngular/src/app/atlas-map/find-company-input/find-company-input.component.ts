import { Component, OnInit } from '@angular/core';
import {AtlasMapApiService} from "../../common/api/atlas-map-api.service";
import {HeaderParams} from "../../common/model/headerParams";
import {HeaderService} from "../../common/service/header.service";
import {SearchType} from "../../common/constant/header.const";
import { Subject } from 'rxjs/Subject';
import {Observable} from "rxjs/Observable";
import {ObserveOnMessage} from "rxjs/operators/observeOn";
import {ParamsService} from "../../common/service/params.service";
import {LocalStorageService} from "../../common/service/localStorage.service";
import { CommonUtil } from '../../common/utill/common-util';

@Component({
  selector: 'app-find-company-input',
  templateUrl: './find-company-input.component.html',
  styleUrls: ['./find-company-input.component.css']
})
export class FindCompanyInputComponent implements OnInit {

  findDataList = [
    {
      companyCd : "000000000513847",
      companyId : "513847",
      companyNm : "万科企业股份有限公司",
      oldname : null,
      type : "公司名称",
    }
  ];//查询结果集

  relationCompanyList = [];//选中的关系公司

  inputData = '';  //检索输入框
  oldInputData = '';  //保存修改前检索输入框

  selectDivFlag = false; // 查询结果模态框

  constructor(
    private headerService :HeaderService,
    private atlasMapApiService: AtlasMapApiService,
    private paramsService:ParamsService,
    private utils:CommonUtil,
    private lg:LocalStorageService
  ) { }

  ngOnInit() {
    this.findCompanyData('');//初始化为空
    this.documentOnKeyUp();
    this.documentClick();
  }

  //选中 1 图谱或者 2关系
  activeClassType = 1;

  //点击图谱或者关系
  clickTab(type){
    this.activeClassType = type;
    this.inputData = '';
    if(type == 1){
      // this.findCompanyData(this.inputData.trim());
    }else if(type==2){
      // this.getRelationship(this.inputData.trim())
    }
  }

  // 监听页面回车
  documentOnKeyUp(){
    let subject = new Subject<string>(); //创建观察者
    document.onkeyup=(ev => { //点击回车事件
      if(ev.keyCode == 13){
        if (this.lg.get("isHeadrInputEntr")){
          this.lg.set("isHeadrInputEntr",false);
          return;
        }
        subject.next('')  //发送读取文件结束后内容
      }
    });
    subject.asObservable().subscribe( resultList=> {
      if(this.activeClassType == 2){  //只有关系回车可以进 查询关系
        this.openCompanyShip(this.relationCompanyList);
      }
    });
  }

  exportFileState = 0;//0 导入完成状态 1 正在导入
  exportFileMessage = '';
  //
  //批量导入文件读取
  exportFile(ev){
    this.exportFileState = 1;
    this.selectDivFlag = true;
    this.exportFileMessage = '文件导入中....';

    let files = ev.target.files;
    let subject = new Subject<string>(); //创建观察者
    if(files.length >0){
      let file = files[0];
      let readerFile = new FileReader();
      readerFile.readAsText(file,'gb2312');
      readerFile.onload = (e=>{
        let target :any= e.target;
        let resultList = target.result;
            subject.next(resultList)  //发送读取文件结束后内容
      })
    }
    subject.asObservable().subscribe( resultList=> {
      this.exportFun(resultList); //接收读取文件结束后内容
      ev.target.value = '' //清除文件内容
    });
  }

  //点击公司
  clickCompany(companyInfo,activeClassType,relationCompanyList){
    if(activeClassType == 1){ //图谱
      this.openCompanyChart(companyInfo);
    }else if(activeClassType == 2){ //关系
      if(relationCompanyList.length>=5){ //五个以上点击不增加
        return ;
      }else{
        this.inputData = '';
        this.relationCompanyList.push(companyInfo);
        let element = document.getElementById("seleItem");
        element.focus();
      }
    }

  }

  //删除选中关系公司
  delCompany(selectCompany){
    for(let i = 0; i<this.relationCompanyList.length;i++){
      if(selectCompany.companyId == this.relationCompanyList[i].companyId){
        this.relationCompanyList.splice(i,1);//删除指定位置元素
      }
    }
  }

  //键盘输入结束后事件
  keyUpFun(ev,src){
    if(ev.keyCode == 8){ //点删除键
      if((src+'').trim() == ''){
        this.selectDivFlag = false;
        return ;
      }
    }
    if(src.trim() == ''){
      return ;
    }
    /*if(ev.keyCode == 13){ //点击回车键
      if(this.activeClassType == 1){ //图谱查询
        this.sendDataForHeader(src);
      }else if(this.activeClassType == 2){ //关系查询
        //查询关系已做全局监听
        return;
      }
    }else*/
    // if(ev.keyCode == 32){ //点击空格键 开始查询
      this.selectDivFlag = true;
      //if((src+'').trim() != (''+this.oldInputData).trim()){   //参数改变 调接口
        if(this.activeClassType == 1){ //图谱查询
          this.findCompanyData(src);
        }else if(this.activeClassType == 2){ //关系查询
          this.getRelationship(src)
        }
        this.oldInputData = (''+src).trim();
      /*}else{  //参数不改变  不调接口

        return ;
      }*/
    // }
  }

  //输入框点击事件 展开查询结果数据
  clickFun(src,activeClassType){
    if(src.length >0 ){
      this.selectDivFlag = true;
    }else{
      this.selectDivFlag = false;
      return;
    }
    if(activeClassType == 1){
       this.findCompanyData(this.inputData.trim());
    }else if(activeClassType==2){
       this.getRelationship(this.inputData.trim())
    }
  }
  //向头部发送搜索条件
  private sendDataForHeader(searchSrc){
    let headerParams:HeaderParams = new HeaderParams();
    headerParams.searchType = SearchType.atlasForHeader;
    headerParams.searchSrc = searchSrc.trim();
    this.headerService.sendData(headerParams);
  }

  //打开公司图谱
  private openCompanyChart(companyInfo){
    if(companyInfo.companyId!=undefined){ //有点击公司 查询数据
      window.open(`#/Atlas/chart/${companyInfo.companyId}/1`)
    }
  }

  //打开公司关系
  private openCompanyShip(relationCompanyList){
    let keyList = '';
    let nameList = '';
    if(relationCompanyList.length >0){  //有选中关系参数 查询数据
      for(let companyInfo of  this.relationCompanyList){
        if(companyInfo.companyId == null){
          keyList = keyList +'P'+ companyInfo.companyNm+',';
          nameList = nameList + companyInfo.companyNm+',';
        }else{
          keyList = keyList + 'C'+ companyInfo.companyId+ ',';
          nameList = nameList + companyInfo.companyNm+ ',';
        }
      }
      let url = keyList + '/'+nameList;
      url = url.replace(/（/g,'+');
      url = url.replace(/\(/g,'+');
      url = url.replace(/\)/g,'-');
      url = url.replace(/）/g,'-');
      console.log(url)
        window.open('#/Atlas/ship/'+ url);
    }
  }

  //批量导入文件处理
  private exportFun(src:string){
    this.batchCompanySearch(src);
  }
  selectState = 0; //查询中状态控制

  //查询公司图谱数据
  private findCompanyData(keyWord) {
    if((keyWord+'').trim() == ''|| keyWord == undefined || keyWord  == null){
      this.findDataList = [];
      return ;
    }
    this.selectState = 1;
    if(this.utils.isSpecialStr((keyWord+'').trim())){
      this.selectState = 2;
      this.findDataList = null;
      return;
    }

    this.atlasMapApiService.findCompanyData((keyWord+'').trim())
      .subscribe(
        (data: any) => {
          this.selectState = 2;
          if(data.code == 0){
            this.findDataList =  data.data['searchOutObj'].list;
          }else if(data.code == 1){
            this.findDataList = [];
          }
        },
        (error: any[]) => console.log('Error: ' + error)
      );
  }

  //查找关系
  private getRelationship(keyWord) {
    if((keyWord+'').trim() == ''|| keyWord == undefined || keyWord  == null){
      this.findDataList = [];
      return ;
    }
    this.selectState = 1;
    if(this.utils.isSpecialStr((keyWord+'').trim())){
      this.selectState = 2;
      this.findDataList = null;
      return ;
    }

    this.atlasMapApiService.getRelationship((keyWord+'').trim())
      .subscribe(
        (data: any) => {
          this.selectState = 2;
          if(data.code == 0){
            let dataList = data.data['content'].list;
            if(this.relationCompanyList.length >0){  //将已选过的公司，从查询结果集移除
              for(let companyInfo of  this.relationCompanyList){
                for(let i = 0; i<dataList.length;i++){
                  if(dataList[i].companyId == companyInfo.companyId){
                    dataList.splice(i,1);
                    break;
                  }
                }
              }
            }
            this.findDataList = dataList;
            //如果当接口响应返回前，用户已将输入框的值改为特殊字符，把结果集置null
            if(this.utils.isSpecialStr(this.inputData.trim())){
              this.selectState = 2;
              this.findDataList = null;
              return ;
            }

          }else if(data.code == 1){
            this.findDataList = [];
          }
        },
        (error: any[]) => console.log('Error: ' + error)
      );
  }
  //根据批量导入的关键字字符串，查询公司基本信息
  private batchCompanySearch(keyWord) {
    if((keyWord+'').trim() == ''|| keyWord == undefined || keyWord  == null){
      this.relationCompanyList = [];
      this.exportFileMessage = '文件内容为空!';
      this.setExportFileState(0,3000);
      return ;
    }
    this.atlasMapApiService.batchCompanySearch((keyWord+'').trim())
      .subscribe(
        (data: any) => {

          if(data.code == 0){
            this.relationCompanyList =  data.data['content'].list;
            let len = 0;
            if(this.relationCompanyList!=undefined&&this.relationCompanyList!=null){
              len =  this.relationCompanyList.length;
            }
            this.exportFileMessage = '成功导入 '+len+' 条信息!';
            this.setExportFileState(0,3000);
            return ;
          }else if(data.code == 1){
            this.relationCompanyList = [];
            this.exportFileMessage = '文件导入失败!';
            this.setExportFileState(0,3000);
            return ;
          }else{
            this.relationCompanyList = [];
            this.exportFileMessage = '文件导入失败!';
            this.setExportFileState(0,3000);
            return ;
          }
        },
        (error: any[]) => {
          console.log('Error: ' + error);
          this.exportFileMessage = '文件导入失败!';
          this.setExportFileState(0,3000)
        }
      );
  }

  //定时关闭文件导入消息
  private setExportFileState(type:number,time?:number){
    if(time == undefined){
      if(type == 0){
        this.selectDivFlag = false; //关闭消息div
        this.exportFileState = type;//导入完成
      }else {
        this.selectDivFlag = true; //打开消息div
        this.exportFileState = type;//导入完成
      }
    }else{
      let subject = new Subject<number>(); //创建观察者
      let tempTime = setTimeout(function(){
        subject.next(0)  //发送读取文件结束后内容
      },time);
      subject.asObservable().subscribe( resultList=> {
        if(type == 0){
          this.selectDivFlag = false; //关闭消息div
          this.exportFileState = type;//导入完成
        }else {
          this.selectDivFlag = true; //打开消息div
          this.exportFileState = type;//导入完成
        }
      });
    }
  }

  // 监听页面回车
  documentClick(){
    document.onclick=(ev => { //点击回车事件
      let target:any = ev.target;
      if(target ==null){
        this.paramsService.sendHeaderSubject('0');
        this.paramsService.sendFindCompanySubject('0');
        return;
      }
      if(target.offsetParent ==null){
        this.paramsService.sendHeaderSubject('0');
        this.paramsService.sendFindCompanySubject('0');
        return;
      }
      if(target.offsetParent.id ==null){
        // subject.next('0'); //发送读取文件结束后内容
        this.paramsService.sendHeaderSubject('0');
        this.paramsService.sendFindCompanySubject('0');
        return;
      }
      if(target.offsetParent.id == 'searchMapInput' ){
        this.paramsService.sendFindCompanySubject('1');
      }else{
        this.paramsService.sendFindCompanySubject(0);
      }
      if(target.offsetParent.id == 'headerSearchInput' ){
        this.paramsService.sendHeaderSubject('1');
      }else{
        this.paramsService.sendHeaderSubject('0');
      }
    });

    this.paramsService.getFindCompanySubject().subscribe(resultList => {
      if(+resultList == 0){
        this.selectDivFlag = false;
      }else{
        this.selectDivFlag = true;
      }
    });
  }

}
