import {Component, OnInit} from '@angular/core';
import {LocalStorageService} from "../../common/service/localStorage.service";
import {Router} from '@angular/router';


@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.css']
})
export class ManageComponent implements OnInit {
  constructor(
    private route: Router,
    private ls: LocalStorageService
  ) { }

  ngOnInit() {

  }
}
