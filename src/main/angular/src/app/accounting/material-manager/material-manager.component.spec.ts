import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialManagerComponent } from './material-manager.component';

describe('MaterialManagerComponent', () => {
  let component: MaterialManagerComponent;
  let fixture: ComponentFixture<MaterialManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaterialManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
