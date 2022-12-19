import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EntidadService } from 'src/app/services/entidad.service';

@Component({
  selector: 'app-entidad',
  templateUrl: './entidad.component.html',
  styleUrls: ['./entidad.component.scss']
})
export class EntidadComponent implements OnInit {
  entidades:any;

  constructor(
    private entidadService: EntidadService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAll();
  }

  add(){
    this.router.navigate(['/home/generalEntidad']);
  }

  update(id: number){
    this.router.navigate(['/home/generalEntidad',id]);
  }

  private getAll(){
    this.entidadService.getEntidades().subscribe(response => {this.entidades = response})
  }

  delete(id:number){
    this.entidadService.deleteEntidad(id).subscribe(() => {
      this.getAll();
    })
  }
}
