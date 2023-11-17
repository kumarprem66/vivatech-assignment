import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisComponent } from './regis.component';

describe('RegisComponent', () => {
  let component: RegisComponent;
  let fixture: ComponentFixture<RegisComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegisComponent]
    });
    fixture = TestBed.createComponent(RegisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
