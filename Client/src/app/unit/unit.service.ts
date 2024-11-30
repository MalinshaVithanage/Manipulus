import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Unit } from './unit';

@Injectable({
  providedIn: 'root'
})
export class UnitService {
  private baseUrl = environment.baseurl;

  constructor(private httpClient: HttpClient) { }

  public getUnitList(): Observable<Unit[]> {
    return this.httpClient.get<Unit[]>(`${this.baseUrl}/units`);
  }

  public addUnit(unit: Unit): Observable<Unit> {
    return this.httpClient.post<Unit>(`${this.baseUrl}/units`, unit);
  }

  public updateUnit(unitId: Number, unit: Unit): Observable<Unit> {
    return this.httpClient.put<Unit>(`${this.baseUrl}/units/${unitId}`, unit);
  }
  public deleteUnit(unitId: Number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/units/${unitId}`);
  }
}
