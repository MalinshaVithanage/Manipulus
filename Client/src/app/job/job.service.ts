import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Job } from './job';
import { Customer } from '../customer/customer';

@Injectable({
  providedIn: 'root',
})
export class JobService {
  // Initialize the base URL from the environment variable
  private baseUrl = environment.baseurl;

  constructor(private httpClient: HttpClient) { }

  // Retrieves a list of all jobs
  public getJobList(): Observable<Job[]> {
    return this.httpClient.get<Job[]>(`${this.baseUrl}/job/all`);
  }

  // Adds a new job
  public addJob(job: Job, id: number): Observable<Job> {
    return this.httpClient.post<Job>(`${this.baseUrl}/job/add?id=${id}`, job);
  }
  
  // Updates an existing job
  public updateJob(job: Job, id: number): Observable<Job> {
    return this.httpClient.put<Job>(`${this.baseUrl}/job/update?id=${id}`, job);
  }

  // Deletes an existing job
  public deleteJob(jobId: Number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/job/delete/${jobId}`);
  }

  public findCustomerById(customerId: Number): Observable<Customer>{
    return this.httpClient.get<Customer>(`${this.baseUrl}/customer/find/${customerId}`);
  }

  public getCustomerList(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(`${this.baseUrl}/customer/all`); // Get a list of all customers from the server
  }


}