import {Component, EventEmitter, Input, Output} from '@angular/core';
import { Follow } from '../follow/follow';
import {ParamsService} from "../../common/service/params.service";

@Component({
  selector: 'app-confirm-message',
  templateUrl: './confirm-message.component.html',
  styleUrls: ['./confirm-message.component.css']
})
export class ConfirmMessageComponent {
  @Input() message: string;
  @Input() follow: Follow;
  @Output() confirmType = new EventEmitter<number>();

  constructor(
  ) { }

  confirm(): void {
    this.confirmType.emit(0);
  }

  decline(): void {
    this.confirmType.emit(1);
  }

}
