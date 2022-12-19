import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TipoDocumentoService } from 'src/app/services/tipo-documento.service';

@Component({
  selector: 'app-tipo-documento',
  templateUrl: './tipo-documento.component.html',
  styleUrls: ['./tipo-documento.component.scss']
})
export class TipoDocumentoComponent implements OnInit {
  tipos:any;

  constructor(
    private router: Router,
    private tipoDocumentoService: TipoDocumentoService,
  ) { }

  ngOnInit(): void {
    this.getAll();
  }

  add(){
    this.router.navigate(['/home/generalDoc']);
  }

  update(id: number){
    this.router.navigate(['/home/generalDoc',id]);
  }

  private getAll(){
    this.tipoDocumentoService.getTipoDocumentos().subscribe(response => {this.tipos = response})
  }

  delete(id:number){
    this.tipoDocumentoService.deleteTipoDocumento(id).subscribe(() => {
      this.getAll();
    })
  }
}
