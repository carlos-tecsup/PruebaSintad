import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneralContribuyenteComponent } from './general-contribuyente.component';

describe('GeneralContribuyenteComponent', () => {
  let component: GeneralContribuyenteComponent;
  let fixture: ComponentFixture<GeneralContribuyenteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GeneralContribuyenteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneralContribuyenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
