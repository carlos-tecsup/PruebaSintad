import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { TipoContribuyenteService } from 'src/app/services/tipo-contribuyente.service';

@Component({
  selector: 'app-general-contribuyente',
  templateUrl: './general-contribuyente.component.html',
  styleUrls: ['./general-contribuyente.component.scss']
})
export class GeneralContribuyenteComponent implements OnInit {
  loading = false;
  submit = false;
  id:number = 0;
  value:string = 'Crear';
  tipoForm: FormGroup;
  constructor(
    private router: Router,
    private route:ActivatedRoute,
    private tipoContribuyenteService: TipoContribuyenteService,
    private builder: FormBuilder,
    ) { 
     
      this.tipoForm = this.builder.group({
        nombre: ['', Validators.required],
      });
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    if(this.id == 0 || this.id === undefined){
      this.value = 'Crear';
    }else{
      this.value = 'Actualizar';
      this.tipoContribuyenteService.getTipoContribuyenteById(this.id).subscribe(data => {
        this.tipoForm.patchValue({
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
      this.tipoContribuyenteService.saveTipoContribuyente(values)
      .pipe(first())
      .subscribe({
        next:() => {
          //Agregar notify y settimeout
          this.router.navigate(['/home/contribuyente']);
        },
        error: error => {
          //Agregar notify
          console.log(error);
          this.loading = false;
        }
      })
    }else{
      this.tipoContribuyenteService.updateTipoContribuyente(this.id,values)
      .pipe(first())
      .subscribe({
        next:() => {
          //Agregar notify y settimeout
          this.router.navigate(['/home/contribuyente']);
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