import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { User } from "./user";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = environment.baseurl;

  constructor(private httpClient: HttpClient) { }

  public getUserList(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.baseUrl}/users`);
  }

  public addUser(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.baseUrl}/users`, user);
  }

  public updateUser(userId: Number, user: User): Observable<User> {
    return this.httpClient.put<User>(`${this.baseUrl}/users/${userId}`, user);
  }
  public deleteUser(userId: Number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/users/${userId}`);
  }
}
