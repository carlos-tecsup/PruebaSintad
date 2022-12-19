import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { TipoDocumento } from '../models/TipoDocumento';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TipoDocumentoService {

  constructor(private http: HttpClient) { }

  getTipoDocumentoById(id: number): Observable<any>{
    return this.http.get(`${environment.apiURL}/tipoDocumento/`+ id);
  }

  getTipoDocumentos(){
    return this.http.get(`${environment.apiURL}/tipoDocumento`);
  }

  saveTipoDocumento(data: TipoDocumento){
    return this.http.post(`${environment.apiURL}/tipoDocumento`, data);
  }

  updateTipoDocumento(id: number, data: TipoDocumento){
    return this.http.put(`${environment.apiURL}/tipoDocumento/`+ id, data);
  }

  deleteTipoDocumento(id: number){
    return this.http.delete(`${environment.apiURL}/tipoDocumento/`+ id);
  }
}
