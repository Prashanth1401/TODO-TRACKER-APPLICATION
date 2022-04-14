import { TestBed } from '@angular/core/testing';

import { ImpService } from './imp.service';

describe('ImpService', () => {
  let service: ImpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
