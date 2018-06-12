import {AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, Renderer2} from '@angular/core';
import {Observable} from "rxjs/Rx";

@Component({
  selector: 'app-right-bar',
  templateUrl: './right-bar.component.html',
  styleUrls: ['./right-bar.component.css', '../atlas-map.component.css']
})
export class RightBarComponent implements OnInit, AfterViewInit {
  @Input() riskSelF: any;
  @Input() cy: any;
  @Input() sels: any;
  @Input() sel: any;
  @Input() rClientWidth: number;
  @Output() printType = new EventEmitter<any>();

  rightBarCtrl: any;

  constructor(
    private el: ElementRef,
    private renderer2: Renderer2,
  ) { }

  ngAfterViewInit() {
    let width = this.rClientWidth - 100;
    const rightBars = this.el.nativeElement.querySelector(`.right-bars`);
    this.renderer2.setStyle(rightBars, 'left', `${width}px`);
  }

  ngOnInit() {
    Observable.fromEvent(window, 'resize')
      .subscribe((event) => {
        let width = this.rClientWidth - 100;
        const rightBars = this.el.nativeElement.querySelector(`.right-bars`);
        this.renderer2.setStyle(rightBars, 'left', `${width}px`);
      });
    this.rightBarCtrl = {
      isColorful: true,     // 是否炫彩模式
      minZoomLevel: 0.2,    // 最小缩放比例
      maxZoomLevel: 5,      // 最大缩放比例
      zoomLevel: 1,         // 原始缩放比例
      isFullScreen: false,  // 是否全屏

      /**
       * 切换为炫彩模式
       */
      switchToColorfulStyle: () => {
        if (!this.rightBarCtrl.isColorful) {
          this.cy.style()
            .selector("node[label='1']")
            .style({
              "border-color": "#9EBCF7",
              "color": "#000"
            })
            .selector("node[label='2']")
            .style({
              "border-color": "#97CC85",
              "color": "#000"
            })
            .selector("node[label='3']")
            .style({
              "border-color": "#FE8572",
              "color": "#000"
            })
            .selector("node[label='4']")
            .style({
              "background-color": "#006",
              "color": "#fff"
            })
            .selector("node[label='5']")
            .style({
              "background-color": "#999",
              "color": "#fff"
            })
            .selector("node.clicked")
            .style({
              "background-color": "#fff",
              "color": "#000"
            })
            .selector("edge[type='1']")
            .style({
              "line-color": "#89C3EB",
              "color": "#666",
              "source-arrow-color": "#89C3EB",
              "target-arrow-color": "#89C3EB"
            })
            .selector("edge[type='2']")
            .style({
              "line-color": "#97CC85",
              "color": "#666",
              "source-arrow-color": "#97CC85",
              "target-arrow-color": "#97CC85"
            })
            .selector("edge[type='3']")
            .style({
              "line-color": "#32A1AF",
              "color": "#666"
            })
            .selector("edge[type='4']")
            .style({
              "line-color": "#3366CC",
              "color": "#666",
              "source-arrow-color": "#3366CC",
              "target-arrow-color": "#3366CC"
            })
            .selector("edge[type='5']")
            .style({
              "line-color": "#FACD37",
              "color": "#666",
              "source-arrow-color": "#FACD37",
              "target-arrow-color": "#FACD37"
            })
            .selector("edge[type='6']")
            .style({
              "line-color": "#4206A9",
              "color": "#666",
              "source-arrow-color": "#4206A9",
              "target-arrow-color": "#4206A9"
            })
            .selector("edge[type='7']")
            .style({
              "line-color": "#E56765",
              "color": "#666"
            })
            .selector("edge[type='8']")
            .style({
              "line-color": "#FACD37",
              "color": "#666"
            })
            .selector("edge[type='9']")
            .style({
              "line-color": "#FACD37",
              "color": "#666"
            })
            .selector("edge[type='10']")
            .style({
              "line-color": "#FACD37",
              "color": "#666"
            })
            .selector("edge[type='11']")
            .style({
              "line-color": "#FACD37",
              "color": "#666"
            })
            .selector("edge[type='12']")
            .style({
              "line-color": "#FACD37",
              "color": "#666"
            })
            .update();

          this.rightBarCtrl.isColorful = true;
        }
      },

      /**
       * 切换为灰白模式
       */
      switchToGreyStyle: () => {
        if (this.rightBarCtrl.isColorful) {
          this.cy.style()
            .selector("node")
            .style({
              "border-color": "#333",
              "color": "#000"
            })
            .selector("node.clicked")
            .style({
              "background-color": "#182132",
              "background-blacken": "-0.25",
              "color": "#FFF"
            })
            .selector("edge")
            .style({
              "line-color": "#666",
              "color": "#000",
              "source-arrow-color": "#666",
              "target-arrow-color": "#666"
            })
            .update();

          this.rightBarCtrl.isColorful = false;
        }
      },

      /**
       * 保存
       */
      save: () => {
        let pngName = this.sels[this.sel].name;
        let tem=[];
        //图谱插件bug，当ele的css为display: none;时，导出图片出错，等待插件更新解决此问题
        this.cy.elements().forEach(function (n) {
          if(n.hasClass('hidden')){
            tem.push(this.cy.remove(n));
          }
        });

        let pngData = this.cy.png({bg: "#fff", full: true, scale: 1});
        //图谱插件bug，当ele的css为display: none;时，导出图片出错，等待插件更新解决此问题
        tem.forEach(function (n) {
          n.restore();
        });

        if ("ActiveXObject" in window || navigator.userAgent.indexOf("Edge") > -1) { // IE, Edge
          let pngDataArr = pngData.split(","),
            mime = pngDataArr[0].match(/:(.*?);/)[1],
            bytes = atob(pngDataArr[1]),
            len = bytes.length,
            uia = new Uint8Array(len);

          while (len--) {
            uia[len] = bytes.charCodeAt(len);
          }

          navigator.msSaveBlob(new Blob([uia], {type: mime}), pngName + '关系图谱.png');
        } else { // Chrome, FireFox, Opera等
          let aLink = document.createElement("a");
          aLink.setAttribute("href", pngData);
          aLink.setAttribute("download", pngName + "关系图谱.png");

          let event = document.createEvent("HTMLEvents");
          event.initEvent("click", false, false);
          aLink.click();
        }
      },

      /**
       * 打印
       */
      print: () => {
        this.printType.emit(1);
      },

      /**
       * 放大
       */
      zoomIn: () => {
        if (this.rightBarCtrl.zoomLevel < 5) {
          this.rightBarCtrl.zoomLevel += 0.1;
          this.cy.zoom(this.rightBarCtrl.zoomLevel);
        }
      },

      /**
       * 缩小
       */
      zoomOut: () => {
        if (this.rightBarCtrl.zoomLevel > 0.2) {
          this.rightBarCtrl.zoomLevel -= 0.1;
          this.cy.zoom(this.rightBarCtrl.zoomLevel);
        }
      },

      /**
       * 还原
       */
      zoomReset: () => {
        this.cy.animation({
          fit: {
            eles: this.cy.elements(),
            padding: 10
          },
          duration: 550,
          easing:  'linear'
        }).play();
      }
    }
  }


  riskSel() {
    this.printType.emit({'type': 2});
  }


}
