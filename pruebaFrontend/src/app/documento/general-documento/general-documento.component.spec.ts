import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneralDocumentoComponent } from './general-documento.component';

describe('GeneralDocumentoComponent', () => {
  let component: GeneralDocumentoComponent;
  let fixture: ComponentFixture<GeneralDocumentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GeneralDocumentoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneralDocumentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
