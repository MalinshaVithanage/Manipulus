import { Component, NgModule, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {
  constructor(private http: HttpClient, private authService: AuthService) {}

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    const email = form.value.email;
    this.authService.sendResetPasswordRequest(email).subscribe(data => {
      console.log(data);

    });
  }

}
