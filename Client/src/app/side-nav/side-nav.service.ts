import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest, LoginResponse } from '../models/login';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class SideNavService {

  private BaseUrl: string = 'http://localhost:4200';

  constructor(private httpClient: HttpClient, private router: Router) { }

  login(user: LoginRequest): Observable<LoginResponse> {
    const formData = new FormData();
    formData.append('username', user.username);
    formData.append('password', user.password);
    return this.httpClient.post<LoginResponse>(environment.baseurl + '/login', formData);
  }

  dashboard() {
    this.router.navigateByUrl('/dashboard');
  }

  navigation(navigate: string) {
    this.router.navigateByUrl(`${navigate}`)
  }
}
