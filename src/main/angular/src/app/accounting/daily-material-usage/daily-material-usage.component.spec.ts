import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DailyMaterialUsageComponent } from './daily-material-usage.component';

describe('DailyMaterialUsageComponent', () => {
  let component: DailyMaterialUsageComponent;
  let fixture: ComponentFixture<DailyMaterialUsageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DailyMaterialUsageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DailyMaterialUsageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
