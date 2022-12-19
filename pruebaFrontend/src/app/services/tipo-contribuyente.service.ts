import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { TipoContribuyente } from '../models/TipoContribuyente';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TipoContribuyenteService {

  constructor(private http: HttpClient) { }

  getTipoContribuyenteById(id: number): Observable<any>{
    return this.http.get(`${environment.apiURL}/tipoContribuyente/`+ id);
  }

  getTipoContribuyentes(){
    return this.http.get(`${environment.apiURL}/tipoContribuyente`);
  }

  saveTipoContribuyente(data: TipoContribuyente){
    return this.http.post(`${environment.apiURL}/tipoContribuyente`, data);
  }

  updateTipoContribuyente(id: number, data: TipoContribuyente){
    return this.http.put(`${environment.apiURL}/tipoContribuyente/`+ id, data);
  }

  deleteTipoContribuyente(id: number){
    return this.http.delete(`${environment.apiURL}/tipoContribuyente/`+ id);
  }
}
