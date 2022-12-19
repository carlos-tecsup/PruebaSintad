import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Entidad } from '../models/Entidad';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EntidadService {

  constructor(private http: HttpClient) { }

  getEntidadById(id: number): Observable<any>{
    return this.http.get(`${environment.apiURL}/entidad/`+ id);
  }

  getEntidades(){
    return this.http.get(`${environment.apiURL}/entidad`);
  }

  saveEntidad(data: Entidad){
    return this.http.post(`${environment.apiURL}/entidad`, data);
  }

  updateEntidad(id: number, data: Entidad){
    return this.http.put(`${environment.apiURL}/entidad/`+ id, data);
  }

  deleteEntidad(id: number){
    return this.http.delete(`${environment.apiURL}/entidad/`+ id);
  }
}
