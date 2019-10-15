import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeManagerComponent } from './income-manager.component';

describe('IncomeManagerComponent', () => {
  let component: IncomeManagerComponent;
  let fixture: ComponentFixture<IncomeManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncomeManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncomeManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
