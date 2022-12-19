import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loading = false;
  submit = false;
  loginForm: FormGroup;

  constructor(
    private router: Router,
    private builder: FormBuilder,
    private userService: UserService,
  ) { 
    if(this.userService.userValue){
      this.router.navigate(['/home']);
    }

    this.loginForm = this.builder.group({
      email: ['', Validators.compose([Validators.email, Validators.required])],
      password: ['', Validators.required]
    })
  }

  ngOnInit(): void { }

  get f() { return this.loginForm.controls; }

  loginHome(values: any){
    this.submit = true;
    this.loading = true;
    this.userService.login(values.email, values.password)
      .pipe(first())
      .subscribe({
        next:() => {
        
          this.router.navigate(['/home/contribuyente']);
        },
        error: error => {
          
          console.log(error);
          this.loading = false;
        }
      })
  }

  goToRegister(){
    this.router.navigate(['/register'])
  }
}
