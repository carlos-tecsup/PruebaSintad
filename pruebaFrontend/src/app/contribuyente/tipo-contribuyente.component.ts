import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TipoContribuyenteService } from 'src/app/services/tipo-contribuyente.service';

@Component({
  selector: 'app-tipo-contribuyente',
  templateUrl: './tipo-contribuyente.component.html',
  styleUrls: ['./tipo-contribuyente.component.scss']
})
export class TipoContribuyenteComponent implements OnInit {
  tipos:any;
  tipo = [];
  value = '';
  get:any;
  constructor(
    private tipoContribuyenteService: TipoContribuyenteService,
    private router: Router,
  ) { }

  ngOnInit( ): void {
    this.getAll();
  }

  add(){
    this.router.navigate(['/home/generalContribuyente']);
  }

  update(id: number){
    this.router.navigate(['/home/generalContribuyente',id]);
  }

  getAll(){
    this.tipoContribuyenteService.getTipoContribuyentes().subscribe(response => {this.tipos = response})
  }

  delete(id:number){
    this.tipoContribuyenteService.deleteTipoContribuyente(id).subscribe(() => {
      this.getAll();
    })
  }
}
