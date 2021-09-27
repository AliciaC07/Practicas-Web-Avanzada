import { TestBed } from '@angular/core/testing';

import { ModifyOrCreateStudentService } from './modify-or-create-student.service';

describe('ModifyOrCreateStudentService', () => {
  let service: ModifyOrCreateStudentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModifyOrCreateStudentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
