import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email: string = "";
  password: string = "";
  errorMessage: string = "";

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.loginService.login(this.email, this.password).subscribe({
      next: user => {
        this.router.navigate(['/users/'+ user.id + '/home']);
      },
      error: error => {
        if (error.status == 404) {
          this.errorMessage = "Email o contraseña incorrectos";
        }
        else {
          this.errorMessage = "Error al iniciar sesión";
          console.log(error);
        }
      }
    });
  }

}
