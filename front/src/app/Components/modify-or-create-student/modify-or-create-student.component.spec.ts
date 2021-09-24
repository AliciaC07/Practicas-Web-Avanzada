import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyOrCreateStudentComponent } from './modify-or-create-student.component';

describe('ModifyOrCreateStudentComponent', () => {
  let component: ModifyOrCreateStudentComponent;
  let fixture: ComponentFixture<ModifyOrCreateStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifyOrCreateStudentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyOrCreateStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
