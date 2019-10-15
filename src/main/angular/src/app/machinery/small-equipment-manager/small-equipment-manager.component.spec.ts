import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmallEquipmentManagerComponent } from './small-equipment-manager.component';

describe('SmallEquipmentManagerComponent', () => {
  let component: SmallEquipmentManagerComponent;
  let fixture: ComponentFixture<SmallEquipmentManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmallEquipmentManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmallEquipmentManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
