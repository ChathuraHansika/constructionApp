import {Component, Input, OnInit} from '@angular/core';
import {PaysheetData} from '../../../dto/hrm/PaysheetData';

@Component({
  selector: 'app-pay-sheet-pdf',
  templateUrl: './pay-sheet-pdf.component.html',
  styleUrls: ['./pay-sheet-pdf.component.scss']
})

export class PaySheetPdfComponent implements OnInit {
  @Input()
  public data: PaysheetData;

  constructor() {
  }

  ngOnInit() {
  }

}
