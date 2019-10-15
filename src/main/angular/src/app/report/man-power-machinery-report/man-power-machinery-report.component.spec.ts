import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManPowerMachineryReportComponent } from './man-power-machinery-report.component';

describe('ManPowerMachineryReportComponent', () => {
  let component: ManPowerMachineryReportComponent;
  let fixture: ComponentFixture<ManPowerMachineryReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManPowerMachineryReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManPowerMachineryReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
