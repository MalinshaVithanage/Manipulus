import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  errorMessage: string;
  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void { }

  navigateToResetPassword() {
    this.router.navigate(['/forget-password']);
  };
  dismissAlert() {
    this.errorMessage = '';
  }
  login(loginForm: NgForm) {
    console.log(loginForm.value);
    this.authService.login(loginForm.value).subscribe({
      next: res => {
        this.authService.saveToken(res);
        console.log(res);
      },
      error: err => {
        console.log(err);
        this.errorMessage = "Error occurred"
      }
    });
  }
}

