import { Component, OnInit, ElementRef, Renderer2 } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { RegionRiskApiService } from '../../common/api/region-risk-api.service';
import { NewsWarning } from '../../common/model/news-warning';
import {LoginService} from "../../common/service/login.service";

@Component({
  selector: 'app-news-detail',
  templateUrl: './news-detail.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class NewsDetailComponent implements OnInit {
  newsWarning: NewsWarning = new NewsWarning();
  score: number;
  max: number = 5;
  rate: number = 0;
  checkModel: Array<boolean>;
  colorArr: Array<string>;
  backgroundArr: Array<string>;

  constructor(
    private renderer2: Renderer2,
    private el: ElementRef,
    private activatedRoute: ActivatedRoute,
    private regionRiskApiService: RegionRiskApiService
  ) {
    this.checkModel = [false, false, false, false];
    this.colorArr = ['#F85F43', '#FCC718', '#7ABF6D', '#6681C5'];
    this.backgroundArr = ['#FFF2F1', '#FEF6D1', '#F1FBEF', '#E0E6F3'];
  }

  ngOnInit() {
    this.getNewsDetail();
  }

  // 获取新闻详情
  getNewsDetail() {
    const companyId = +this.activatedRoute.snapshot.paramMap.get('companyId');
    const newsCode = +this.activatedRoute.snapshot.paramMap.get('newsCode');
    this.regionRiskApiService.getNewsDetail(companyId, newsCode.toString())
      .subscribe(
        data => {
          if (data.code === '0') {
            this.newsWarning = data.data['content'];
            this.score = Number(this.newsWarning.score);
            this.setRate(Number(this.newsWarning.relevance), Number(this.newsWarning.importance));
            return;
          }
          this.newsWarning = null;
        },
        error => {
          console.log(error);
        }
      );
  }

  // 设置比率
  setRate(relevance: number, importance: number) {
    if ((relevance > 0.01 && relevance <= 0.1) || (relevance <= 0.01 && importance > 0)) {
      this.rate = 1;
    } else if (relevance > 0.1 && relevance <= 0.3) {
      this.rate = 2;
    } else if (relevance > 0.3 && relevance <= 0.8) {
      this.rate = 3;
    } else if(relevance > 0.8 && relevance <= 0.999) {
      this.rate = 4;
    } else if(relevance > 0.999) {
      this.rate = 5;
    } else {
      this.rate = 0;
    }
  }

  // 设置高亮
  highLight(i: number, value: string) {
    if (this.checkModel[i]) {
      this.setColor(i, value);
    } else {
      this.resetColor(value);
    }
  }

  // 设置高亮色
  setColor(index: number, value: string) {
    const classArr = value.split(',');
    const len = classArr.length;

    for (let i = 0; i < len; i++) {
      const newsClass = this.el.nativeElement.querySelector(`.news-${classArr[i]}`);
      if (newsClass !== null) {
        this.renderer2.setStyle(newsClass, 'color', this.colorArr[index]);
        this.renderer2.setStyle(newsClass, 'background-color', this.backgroundArr[index]);
      }
    }
  };

  // 取消高亮色
  resetColor(value: string) {
    const classArr = value.split(',');
    const len = classArr.length;

    for (let i = 0; i < len; i++) {
      const newsClass = this.el.nativeElement.querySelector(`.news-${classArr[i]}`);
      if (newsClass !== null) {
        this.renderer2.removeStyle(newsClass, 'color');
        this.renderer2.removeStyle(newsClass, 'background-color');
      }
    }
  };

}
