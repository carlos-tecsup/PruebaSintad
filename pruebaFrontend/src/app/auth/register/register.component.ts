import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  loading = false;
  submit = false;
  registerForm: FormGroup;

  constructor(
    private router: Router,
    private builder: FormBuilder,
    private userService: UserService,
  ) { 
    this.registerForm = this.builder.group({
      email: ['', Validators.compose([Validators.email, Validators.required])],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      username: ['', Validators.required],
    })
  }

  ngOnInit(): void {  }

  register(values: any){
    this.submit = true;
    this.loading = true;

    let newUser = {
      username: values.username,
      email: values.email,
      password: values. password,
    };

    this.userService.register(
      newUser
    )
      .pipe(first())
      .subscribe({
        next:() => {
         
          this.router.navigate(['/']);
        },
        error: error => {
          this.loading = false;
        }
      })
  }

  goToLogin(){
    this.router.navigate(['/']);
  }
}
