import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PastRegistriesComponent } from './past-registries.component';

describe('PastRegistriesComponent', () => {
  let component: PastRegistriesComponent;
  let fixture: ComponentFixture<PastRegistriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PastRegistriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PastRegistriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
