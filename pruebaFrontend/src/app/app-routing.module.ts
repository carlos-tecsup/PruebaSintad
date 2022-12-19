import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//Guard
import { AuthGuard } from './jwt/auth.guard';

//Components
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { HomeComponent } from './home/home.component';
import { TipoContribuyenteComponent } from './contribuyente/tipo-contribuyente.component';
import { GeneralContribuyenteComponent } from './contribuyente/general-contribuyente/general-contribuyente.component';
import { EntidadComponent } from './entidad/entidad.component';
import { GeneralEntidadComponent } from './entidad/general-entidad/general-entidad.component';
import { TipoDocumentoComponent } from './documento/tipo-documento.component';
import { GeneralDocumentoComponent } from './documento/general-documento/general-documento.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'contribuyente',
        component: TipoContribuyenteComponent,
      },
      {
        path: 'generalContribuyente',
        component: GeneralContribuyenteComponent
      },
      {
        path: 'generalContribuyente/:id',
        component: GeneralContribuyenteComponent
      },
      {
        path: 'entidad',
        component: EntidadComponent,
      },
      {
        path: 'generalEntidad',
        component: GeneralEntidadComponent
      },
      {
        path: 'generalEntidad/:id',
        component: GeneralEntidadComponent
      },
      {
        path: 'documento',
        component: TipoDocumentoComponent,
      },
      {
        path: 'generalDoc',
        component: GeneralDocumentoComponent
      },
      {
        path: 'generalDoc/:id',
        component: GeneralDocumentoComponent
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }