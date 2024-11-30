import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { LoggedUser } from '../models/logged-user-model';
import { USER_TYPES } from '../models/enums/user-type';
import { SideNavService } from './side-nav.service';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent implements OnInit, OnDestroy {

  userSubscription: Subscription;
  isAuthenticated = false;
  isAdmin = false;
  isUser = false;

  constructor(private authService: AuthService , private sideNavService : SideNavService) { }

  ngOnInit(): void {
    this.subscribeToUser();
  }
  public logout() {
    this.authService.logout();
  }

  public dashboard() {
    this.sideNavService.dashboard();
  }

  public navigation(navigation:string){
    this.sideNavService.navigation(navigation);
  }

  subscribeToUser() {
    this.userSubscription = this.authService.user.subscribe(user => {
      this.isAuthenticated = !!user;
      if(!this.isAuthenticated) {
        this.initializeState();
      }
      this.setRole(user);
    });
  }

  setRole(loggedUser: LoggedUser | null) {
    if(loggedUser?.roles.includes(USER_TYPES.Admin)) {
      this.isAdmin = true;
    } else if (loggedUser?.roles.includes(USER_TYPES.User)) {
      this.isUser = true;
    }
  }

  initializeState() {
    this.isAdmin = false;
    this.isUser = false;
  }

  ngOnDestroy(): void {
    this.userSubscription.unsubscribe();
  }
}
