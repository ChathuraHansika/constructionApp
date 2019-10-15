import {Component, OnInit} from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-material-manager',
  templateUrl: './material-manager.component.html',
  styleUrls: ['./material-manager.component.scss']
})
export class MaterialManagerComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

  Modal() {
    $('.ui.tiny.modal.test')
      .modal({
        blurring: true
      })
      .modal('show')
    ;
    // this.modalService.open(id);
  }
  addMatieralModal() {
    $('.ui.small.test.modal.matie')
      .modal({
        blurring: true
      })
      .modal('show')
    ;
  }

}
