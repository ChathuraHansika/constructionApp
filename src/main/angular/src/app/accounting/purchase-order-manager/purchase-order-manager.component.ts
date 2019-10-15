import {Component, OnInit} from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-purchase-order-manager',
  templateUrl: './purchase-order-manager.component.html',
  styleUrls: ['./purchase-order-manager.component.scss']
})
export class PurchaseOrderManagerComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
    $('.button')
      .popup({
        boundary: '.segment'
      })
    ;
  }

}
