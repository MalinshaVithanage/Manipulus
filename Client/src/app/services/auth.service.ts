import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest, LoginResponse } from '../models/login';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoggedUser } from '../models/logged-user-model';
import { Router } from '@angular/router';
import { USER_TYPES } from '../models/enums/user-type';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  jwtHelperService = new JwtHelperService();
  user = new BehaviorSubject<LoggedUser | null> (null);

  constructor(private httpClient: HttpClient, private router: Router) { }

  login(user: LoginRequest): Observable<LoginResponse> {
    const formData = new FormData();
    formData.append('username', user.username);
    formData.append('password', user.password);
    return this.httpClient.post<LoginResponse>(environment.baseurl + '/login', formData);
  }

  saveToken(jwtTokens: LoginResponse) {
    const decodedAccessToken = this.jwtHelperService.decodeToken(jwtTokens.accessToken);
    const loggedUser = new LoggedUser(decodedAccessToken.sub, decodedAccessToken.roles, jwtTokens.accessToken, this.getExpirationDate(decodedAccessToken.exp), undefined);
    localStorage.setItem('userData', JSON.stringify(loggedUser));
    this.user.next(loggedUser);
    this.redirectLoggedInUser(decodedAccessToken, jwtTokens.accessToken);
  }

  getExpirationDate(exp: number) {
    const date = new Date(0);
    date.setUTCSeconds(exp);
    return date;
  }

  redirectLoggedInUser(decodedToken: any, accessToken: string) {
    if(decodedToken.roles.includes(USER_TYPES.Admin)) {
      const loggedUser = new LoggedUser(decodedToken.sub, decodedToken.roles, accessToken, this.getExpirationDate(decodedToken.exp), "admin");
      localStorage.setItem('userData', JSON.stringify(loggedUser));
      this.router.navigateByUrl("/admin");
    } else if (decodedToken.roles.includes(USER_TYPES.User)) {
      const loggedUser = new LoggedUser(decodedToken.sub, decodedToken.roles, accessToken, this.getExpirationDate(decodedToken.exp), "user");
      localStorage.setItem('userData', JSON.stringify(loggedUser));
      this.router.navigateByUrl("/user");
    }
  }

  autoLogin() {
    const currentUser = localStorage.getItem('userData');
    const userData: {
      username: string,
      roles: string[],
      _token: string,
      _expiration: Date,
      userType: string | undefined
    } = currentUser !== null ? JSON.parse(currentUser) : undefined;

    if (!userData) return;
    const loadedUser = new LoggedUser(userData.username, userData.roles, userData._token, new Date(userData._expiration), userData.userType);
    if (loadedUser.token) {
      this.user.next(loadedUser);
    }
  }
}
