import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit, OnDestroy {

  userSubscription: Subscription;
  isAuthenticated = false;

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    this.subscribeToLoggedInUser();
  }

  public logout() {
    this.authService.logout();
  }

  subscribeToLoggedInUser() {
    this.userSubscription = this.authService.user.subscribe(loggedUser => {
      this.isAuthenticated = !!loggedUser;
    });
  }

  ngOnDestroy(): void {
    this.userSubscription.unsubscribe();
  }
}
