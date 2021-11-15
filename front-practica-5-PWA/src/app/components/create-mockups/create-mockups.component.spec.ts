import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMockupsComponent } from './create-mockups.component';

describe('CreateMockupsComponent', () => {
  let component: CreateMockupsComponent;
  let fixture: ComponentFixture<CreateMockupsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateMockupsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMockupsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
