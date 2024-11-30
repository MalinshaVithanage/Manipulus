import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SiteVisit } from './siteVisit';
import { environment } from 'src/environments/environment';
import { Vehicle } from "../vehicle/vehicle";
import { saveAs } from 'file-saver';
import { Job } from '../job/job';

@Injectable({
  providedIn: 'root'
})
export class SiteVisitService {
  downloadJobCard(siteVisitId: number) {
    const url = `${this.apiServerUrl}/job-card/printJobCard/${siteVisitId}`;
    return this.http.get(url, { responseType: 'blob' });;
  }
  siteVisitService: any;
 

  private apiServerUrl = environment.baseurl;
  //printSiteVisit: any;
  constructor(private http: HttpClient) { }
  getCompletedSiteVisits() {
    throw new Error('Method not implemented.');
  }
  
  getAvailableVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(`${this.apiServerUrl}/vehicle/all`);
  }
  getAvailableJobs(): Observable<Job[]> {
    return this.http.get<Job[]>(`${this.apiServerUrl}/job/all`);
  }
  public updateVehicles(vehicle: Vehicle):Observable<Vehicle> {
    return this.http.put<Vehicle>(`${this.apiServerUrl}/vehicle/update`,vehicle);

}
  public getSiteVisits():Observable<SiteVisit[]> {
    return this.http.get<SiteVisit[]>(`${this.apiServerUrl}/siteVisit/all`);

}
public addSiteVisit(siteVisit: SiteVisit):Observable<SiteVisit> {
    return this.http.post<SiteVisit>(`${this.apiServerUrl}/siteVisit/add`,siteVisit);

}


public updateSiteVisit(siteVisit: SiteVisit):Observable<SiteVisit> {
    return this.http.put<SiteVisit>(`${this.apiServerUrl}/siteVisit/update`,siteVisit);

}
public deleteSiteVisit(siteVisitId: number):Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/siteVisit/delete/${siteVisitId}`);

}

public downloadPDF(siteVisitId: number): Observable<Blob> {
  const url = `${this.apiServerUrl}/gate-pass/printGatePass/${siteVisitId}`;
  return this.http.get(url, { responseType: 'blob' });
}


}

