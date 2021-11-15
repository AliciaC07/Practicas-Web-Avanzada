import { TestBed } from '@angular/core/testing';

import { CreateMockupsService } from './create-mockups.service';

describe('CreateMockupsService', () => {
  let service: CreateMockupsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateMockupsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
