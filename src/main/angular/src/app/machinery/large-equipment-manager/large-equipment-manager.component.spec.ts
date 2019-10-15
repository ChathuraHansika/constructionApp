import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LargeEquipmentManagerComponent } from './large-equipment-manager.component';

describe('LargeEquipmentManagerComponent', () => {
  let component: LargeEquipmentManagerComponent;
  let fixture: ComponentFixture<LargeEquipmentManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LargeEquipmentManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LargeEquipmentManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
