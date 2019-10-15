import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalRequestsManagerComponent } from './approval-requests-manager.component';

describe('ApprovalRequestsManagerComponent', () => {
  let component: ApprovalRequestsManagerComponent;
  let fixture: ComponentFixture<ApprovalRequestsManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApprovalRequestsManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalRequestsManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
