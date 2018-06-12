import {Component, Input, OnInit} from '@angular/core';
import { LocalStorageService } from "../../common/service/localStorage.service";
import {AtlasMapApiService} from "../../common/api/atlas-map-api.service";
import {ActivatedRoute} from "@angular/router";
import {HeaderParams} from "../../common/model/headerParams";
import {HeaderService} from "../../common/service/header.service";
import {SearchType} from "../../common/constant/header.const";
import { CommonUtil } from '../../common/utill/common-util';

@Component({
  selector: 'app-find-more-company',
  templateUrl: './find-more-company.component.html',
  styleUrls: ['./find-company-input.component.css']
})
export class FindMoreCompanyComponent implements OnInit{
  findDataList: any =[];
  searchSrc: string;
  userId: number;

  totalItems: number=0;
  currentPage: number=1;
  itemsPerPage: number=10;
  num: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private atlasMapApiService: AtlasMapApiService,
    private headerService :HeaderService,
    private utils : CommonUtil,
    private ls: LocalStorageService,
  ) {
    this.userId = this.ls.get('userInfo').userId;

    this.searchSrc = this.activatedRoute.snapshot.paramMap.get('searchSrc');
  }

  ngOnInit(){
    this.headerService
      .getData()
      .subscribe( headerParams=> {
        let hp: HeaderParams = headerParams;
        this.searchSrc = hp.searchSrc; //关键字赋值
        if(hp.searchType == SearchType.headerAtlasDelData){
          this.searchSrc = hp.searchSrc;
          this.getFindData(this.searchSrc);
        }
      });
    this.getFindData(this.searchSrc);
  }
  getValue(value){
    let ret = '';
    for(let src of value){
      ret = ret+ src+'\n';
    }
    return ret;
  }

  //点击公司名
  clickCompanyNm($event,companyId){
    $event.stopPropagation();
    window.open('#/lfs/company/id/'+companyId);
  }

  //打开公司图谱
 /* openCompanyChart(companyInfo){
    if(companyInfo.companyId!=undefined){ //有点击公司 查询数据
      window.open(`#/Atlas/chart/${companyInfo.companyId}/1`)
    }
  }*/

  //页码改变
  getPageChanged($event?){ //检测到下一页无count 页码自动跳为1 触发事件
    if ($event) {
      this.currentPage = $event.page;
      this.getFindData(this.searchSrc);
    }
  }

  //查询报表数据
  getFindData(searchSrc){
    if(this.utils.isSpecialStr(searchSrc.trim())){
      this.findDataList = [];
      return false;
    }
    this.atlasMapApiService.findCompanyDelData(this.userId,this.currentPage+'',searchSrc.trim())
      .subscribe(
        (data: any) => {
          this.totalItems = data.count;
          if(data.code == 0){
            this.num = (this.currentPage - 1) * this.itemsPerPage + 1;
            this.findDataList = data.data['warningDataList'];
          }else if(data.code == 1){
            this.findDataList = [];
          }
        },
        (error: any[]) => console.log('Error: ' + error)
      );
  }
}
