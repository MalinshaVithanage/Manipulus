import { TestBed } from '@angular/core/testing';

import { SiteVisitService } from './site-visit.service';

describe('SiteVisitService', () => {
  let service: SiteVisitService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SiteVisitService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
