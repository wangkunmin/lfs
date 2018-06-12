import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/Rx";

@Component({
  selector: 'app-atlas-map',
  templateUrl: './atlas-map.component.html',
  styleUrls: ['./atlas-map.component.css']
})
export class AtlasMapComponent implements OnInit {
  width: string;
  height: string;

  constructor() { }

  ngOnInit() {
    let body = document.body;
    this.width = `${body.clientWidth + 6}px`;
    this.height = `${body.clientHeight - 64}px`;
    Observable.fromEvent(window, 'resize')
      .subscribe((event) => {
        this.width = `${body.clientWidth + 6}px`;
        this.height = `${body.clientHeight - 64}px`;
      });
  }

}
