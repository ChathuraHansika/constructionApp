import {Component, OnInit} from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-large-equipment-manager',
  templateUrl: './large-equipment-manager.component.html',
  styleUrls: ['./large-equipment-manager.component.scss']
})
export class LargeEquipmentManagerComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

  addModal() {
    $('.ui.small.test.modal.add')
      .modal({
        blurring: true
      })
      .modal('show')
    ;
  }

}
