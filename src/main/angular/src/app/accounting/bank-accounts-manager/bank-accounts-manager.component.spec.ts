import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BankAccountsManagerComponent } from './bank-accounts-manager.component';

describe('BankAccountsManagerComponent', () => {
  let component: BankAccountsManagerComponent;
  let fixture: ComponentFixture<BankAccountsManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BankAccountsManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BankAccountsManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
