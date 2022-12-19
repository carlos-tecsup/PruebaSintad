import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { EntidadService } from 'src/app/services/entidad.service';
import { TipoContribuyenteService } from 'src/app/services/tipo-contribuyente.service';
import { TipoDocumentoService } from 'src/app/services/tipo-documento.service';

@Component({
  selector: 'app-general-entidad',
  templateUrl: './general-entidad.component.html',
  styleUrls: ['./general-entidad.component.scss']
})
export class GeneralEntidadComponent implements OnInit {
  loading = false;
  submit = false;
  id:number = 0;
  value:string = 'Crear';
  documentos:any; 
  contribuyentes:any;
  entidadForm: FormGroup;
  constructor(
    private router: Router,
    private route:ActivatedRoute,
    private entidadService: EntidadService,
    private tipoDocumentoService: TipoDocumentoService,
    private tipoContribuyenteService: TipoContribuyenteService,
    private builder: FormBuilder,
    ) { 
      this.entidadForm = this.builder.group({
        nroDocumento: ['', Validators.required],
        telefono: [''],
        nombreComercial: [''],
        razonSocial: ['', Validators.required],
        direccion: [''],
        idTipoDocumento: ['', Validators.required],
        idTipoContribuyente: ['']
      });
  }

  ngOnInit(): void {
    this.getContribuyentes();
    this.getDocumentos();
    this.id = this.route.snapshot.params['id'];
    if(this.id == 0 || this.id === undefined){
      this.value = 'Crear';
    }else{
      this.value = 'Actualizar';
      this.entidadService.getEntidadById(this.id).subscribe(data => {
        this.entidadForm.patchValue({
          nroDocumento: data.nroDocumento,
          telefono: data.telefono || '',
          nombreComercial: data.nombreComercial || '',
          razonSocial: data.razonSocial,
          direccion: data.direccion || '',
          idTipoDocumento: data.idTipoDocumento,
          idTipoContribuyente: data.idTipoContribuyente || ''
        })
      })
    }
  }

  get f() { return this.entidadForm.controls; }

  private getDocumentos(){
    this.tipoDocumentoService.getTipoDocumentos().subscribe(response => {this.documentos = response})
  }

  private getContribuyentes(){
    this.tipoContribuyenteService.getTipoContribuyentes().subscribe(response => {this.contribuyentes = response})
  }

  handleSubmit(values:any){
    this.submit = true;
    this.loading = true;
    if(this.id == 0 || this.id === undefined){
      this.entidadService.saveEntidad(values)
      .pipe(first())
      .subscribe({
        next:() => {
          //Agregar notify y settimeout
          this.router.navigate(['/home/entidad']);
        },
        error: error => {
          //Agregar notify
          console.log(error);
          this.loading = false;
        }
      })
    }else{
      this.entidadService.updateEntidad(this.id,values)
      .pipe(first())
      .subscribe({
        next:() => {
          //Agregar notify y settimeout
          this.router.navigate(['/home/entidad']);
        },
        error: error => {
          //Agregar notify
          console.log(error);
          this.loading = false;
        }
      })
    }
  }
}
