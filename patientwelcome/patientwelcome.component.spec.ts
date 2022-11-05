import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientwelcomeComponent } from './patientwelcome.component';

describe('PatientwelcomeComponent', () => {
  let component: PatientwelcomeComponent;
  let fixture: ComponentFixture<PatientwelcomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientwelcomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientwelcomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
