import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { environment } from "src/environments/environment";
import { Vehicle } from './vehicle';



@Injectable({
    providedIn: 'root'  
})
export class VehicleService{
    private baseUrl=environment.baseurl;
    constructor( private httpClient : HttpClient){}

     public getVehicleList(): Observable<Vehicle[]>{
        return this.httpClient.get<Vehicle[]>(`${this.baseUrl}/vehicle/all`)
     }
    
     public addVehicle(vehicle: Vehicle): Observable<Vehicle>{
        return this.httpClient.post<Vehicle>(`${this.baseUrl}/vehicle/add`, vehicle);
     }

     public updateVehicle(vehicle: Vehicle): Observable<Vehicle>{
        return this.httpClient.put<Vehicle>(`${this.baseUrl}/vehicle/update`, vehicle);
    }
    
     public deleteVehicle(vehicleId: number): Observable<void>{
        return this.httpClient.delete<void>(`${this.baseUrl}/vehicle/delete/${vehicleId}`);
    }

}