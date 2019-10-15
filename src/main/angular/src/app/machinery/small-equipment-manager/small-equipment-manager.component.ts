import {Component, OnInit} from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-small-equipment-manager',
  templateUrl: './small-equipment-manager.component.html',
  styleUrls: ['./small-equipment-manager.component.scss']
})
export class SmallEquipmentManagerComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

  addNameDecQtyModal() {
    $('.ui.large.test.modal.addName')
      .modal({
        blurring: true
      })
      .modal('show')
    ;
  }
}
