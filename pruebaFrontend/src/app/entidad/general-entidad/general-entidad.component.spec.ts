import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneralEntidadComponent } from './general-entidad.component';

describe('GeneralEntidadComponent', () => {
  let component: GeneralEntidadComponent;
  let fixture: ComponentFixture<GeneralEntidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GeneralEntidadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneralEntidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
