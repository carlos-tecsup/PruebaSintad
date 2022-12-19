import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { GeneralContribuyenteComponent } from './contribuyente/general-contribuyente/general-contribuyente.component';
import { TipoContribuyenteComponent } from './contribuyente/tipo-contribuyente.component';
import { GeneralDocumentoComponent } from './documento/general-documento/general-documento.component';
import { TipoDocumentoComponent } from './documento/tipo-documento.component';
import { EntidadComponent } from './entidad/entidad.component';
import { GeneralEntidadComponent } from './entidad/general-entidad/general-entidad.component';
import { HomeComponent } from './home/home.component';
import { InterceptorService } from './jwt/interceptor.service';
import { HeaderComponent } from './shared/header/header.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    HeaderComponent,
    SidebarComponent,
    TipoContribuyenteComponent,
    GeneralContribuyenteComponent,
    EntidadComponent,
    TipoDocumentoComponent,
    TipoContribuyenteComponent,
    GeneralDocumentoComponent,
    GeneralContribuyenteComponent,
    GeneralEntidadComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
