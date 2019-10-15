import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseRequestManagerComponent } from './purchase-request-manager.component';

describe('PurchaseRequestManagerComponent', () => {
  let component: PurchaseRequestManagerComponent;
  let fixture: ComponentFixture<PurchaseRequestManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PurchaseRequestManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseRequestManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
