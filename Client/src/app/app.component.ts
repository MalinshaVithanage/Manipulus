import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})

export class AppComponent {
  title = 'Manipulus';
  isTokenAvailable: string;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.autoLogin();
  }
}



