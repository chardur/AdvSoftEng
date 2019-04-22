import { Component } from '@angular/core';
import { LoginService } from './login-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  hide = true;
  username: string;

  constructor(private service: LoginService, private router: Router) {
  }

  login() {
    this.service.getUserInfo(this.username)
      .subscribe(user => {
        localStorage.clear();
        localStorage.setItem('userId', user.id.toString());
        this.service.getRoles(user.id)
          .subscribe(roles => {
            localStorage.setItem('roles', JSON.stringify(roles));
            this.router.navigate(['dashboard']);
          });
      });
  }
}
