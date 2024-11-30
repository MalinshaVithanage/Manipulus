import { TestBed } from '@angular/core/testing';

import { UnitService } from './unit.service';

describe('Service.TsService', () => {
  let service: UnitService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UnitService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
