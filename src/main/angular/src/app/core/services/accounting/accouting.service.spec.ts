import { TestBed } from '@angular/core/testing';

import { AccoutingService } from './accouting.service';

describe('AccoutingService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccoutingService = TestBed.get(AccoutingService);
    expect(service).toBeTruthy();
  });
});
