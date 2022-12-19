import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { TipoDocumentoService } from 'src/app/services/tipo-documento.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-general-documento',
  templateUrl: './general-documento.component.html',
  styleUrls: ['./general-documento.component.scss']
})
export class GeneralDocumentoComponent implements OnInit {
  loading = false;
  submit = false;
  id:number = 0;
  value:string = 'Crear';
  tipoForm: FormGroup;
  constructor(
    private router: Router,
    private route:ActivatedRoute,
    private tipoDocumentoService: TipoDocumentoService,
    private builder: FormBuilder,
    ) { 
     
      this.tipoForm = this.builder.group({
        codigo: ['', Validators.compose([Validators.email, Validators.required])],
        nombre: ['', Validators.required],
        descripcion: [''],
      });
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    if(this.id == 0 || this.id === undefined){
      this.value = 'Crear';
    }else{
      this.value = 'Actualizar';
      this.tipoDocumentoService.getTipoDocumentoById(this.id).subscribe(data => {
        this.tipoForm.patchValue({
          codigo: data.codigo,
          descripcion: data.descripcion,
          nombre: data.nombre
        })
      })
    }
  }

  get f() { return this.tipoForm.controls; }

  handleSubmit(values:any){
    this.submit = true;
    this.loading = true;
    if(this.id == 0 || this.id === undefined){
      this.tipoDocumentoService.saveTipoDocumento(values)
      .pipe(first())
      .subscribe({
        next:() => {
          //Agregar notify y settimeout
          this.router.navigate(['/home/documento']);
        },
        error: error => {
          //Agregar notify
          console.log(error);
          this.loading = false;
        }
      })
    }else{
      this.tipoDocumentoService.updateTipoDocumento(this.id,values)
      .pipe(first())
      .subscribe({
        next:() => {
          //Agregar notify y settimeout
          this.router.navigate(['/home/documento']);
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
