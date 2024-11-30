import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { SiteVisitService } from './site-visit.service';
import { SiteVisit } from './siteVisit';
import { NgForm } from '@angular/forms';
import { Vehicle } from '../vehicle/vehicle';
import { VehicleService } from '../vehicle/vehicle.service';
import { ActivatedRoute } from '@angular/router';
import { scheduled } from 'rxjs';
import { saveAs } from 'file-saver';
import { Job } from '../job/job';
import { FormGroup, FormControl } from '@angular/forms';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-site-visit',
  templateUrl: './site-visit.component.html',
  styleUrls: ['./site-visit.component.css']
})
export class SiteVisitComponent implements OnInit {
  title = 'CreatePDF';
  
  public siteVisits: SiteVisit[]=[] ;
  public editSiteVisits: SiteVisit |undefined;
  public deleteSiteVisits: any;
  public completedSiteVisitsCount: number = 0;
  public incompleteSiteVisitsCount: number = 0;
  public totalSiteVisitsCount: number = 0;
  availableVehicles: Vehicle[] = [];
  availableJobs: Job[]=[];
  selectedVehicle: Vehicle | null = null;
  public state: string | undefined;
  public printGatePasses: any;
  public printJobCards:any
  

  //siteVisit: any;
  constructor(private siteVisitService : SiteVisitService,private route: ActivatedRoute) {
    this.siteVisits=[];
    
   }

  ngOnInit(): void {
    this.getSiteVisit();
    this.getAvailableVehicles();
    this.getAvailableJobs();
  }

  getAvailableVehicles() {
    this.siteVisitService.getAvailableVehicles().subscribe((vehicles: Vehicle[]) => {
      this.availableVehicles = vehicles;
    });
  }

  getAvailableJobs() {
    this.siteVisitService.getAvailableJobs().subscribe((jobs: Job[]) => {
      this.availableJobs = jobs;
    });
  }
  getSiteVisit(): void {
    const siteVisitId = this.route.snapshot.paramMap.get('siteVisitId');
    this.siteVisitService.getSiteVisits().subscribe(siteVisits => { // fetches all site visits
      this.siteVisits = siteVisits;
      this.completedSiteVisitsCount = this.countCompletedSiteVisits(siteVisits);
      this.incompleteSiteVisitsCount = this.countIncompleteSiteVisits(siteVisits);
      this.totalSiteVisitsCount = siteVisits.length;
    });
    
  }

  //Assign Vehicle
  assignVehicle(): void {
    if (this.selectedVehicle) {
      this.siteVisits[0].vehicles.push(this.selectedVehicle);

      this.selectedVehicle = null;
    }
  }

//count completed site Visits
  countCompletedSiteVisits(siteVisits: SiteVisit[]): number {
    let completedSiteVisitsCount = 0;
      for (let siteVisit of siteVisits) {
        if (siteVisit.state === 'Completed') {
          completedSiteVisitsCount++;
        }
      }
    return completedSiteVisitsCount;
  }

  //count incompleted site Visits
  countIncompleteSiteVisits(siteVisits: SiteVisit[]): number {
    let incompleteSiteVisitsCount = 0;
      for (let siteVisit of siteVisits) {
        if (siteVisit.state !== 'Completed') {
          incompleteSiteVisitsCount++;
        }
      }
    return incompleteSiteVisitsCount;
  }
  
  //get all the site Visits
  public getSiteVisits():void{
    this.siteVisitService.getSiteVisits().subscribe(
      (response: SiteVisit[])=>{
        this.siteVisits=response;
        console.log(this.siteVisits)
      },
      (
        error: HttpErrorResponse)=>{
          alert(error.message);
        }
        );
  }

  
  //add a new site visit
  public onAddSiteVisit(addForm: NgForm): void {
    document.getElementById('add-siteVisit-form')?.click();
    
    const assignedVehicle = addForm.value.assignedVehicle;
    const state = addForm.value.state;
  
    // Check if the vehicle number already exists in the site visits
    const existingVehicle = this.availableVehicles.find(sv => sv.vehicle_number === assignedVehicle);
    if (existingVehicle) {
      // Update the active_state of the existing vehicle
      const vehicleToUpdate = existingVehicle;
      
      if (vehicleToUpdate) {
        const assignedStatus = state !== 'Completed' ? 'Assigned' : 'Available';
        let activeState = assignedStatus;
        let preActiveState = activeState;
        if (assignedStatus === 'Assigned') {
            // const previousAssignedDates = vehicleToUpdate.active_state.match(/\(FROM: (.*?), TO: (.*?)\)/g);
            // const currentAssignedDate = `(FROM: ${addForm.value.scheduledDate}, TO: ${addForm.value.dateRange})`;
            const previousAssignedDates = vehicleToUpdate.active_state && vehicleToUpdate.active_state.match(/\(FROM: (.*?), TO: (.*?)\)/g);
            const currentAssignedDate = `(FROM: ${addForm.value.scheduledDate}, TO: ${addForm.value.dateRange})`;
            
            if (previousAssignedDates) {
                activeState += previousAssignedDates.join(' ');
                activeState += ' ' + currentAssignedDate;
            } else {
                activeState += ' ' + currentAssignedDate;
            }
    
            vehicleToUpdate.active_state = activeState;
        } else {
          // const previousAssignedDates = vehicleToUpdate.active_state.match(/\(FROM: (.*?), TO: (.*?)\)/g);
          const previousAssignedDates = vehicleToUpdate.active_state && vehicleToUpdate.active_state.match(/\(FROM: (.*?), TO: (.*?)\)/g);
          const currentAssignedDate = `(FROM: ${addForm.value.scheduledDate}, TO: ${addForm.value.dateRange})`;
  
          if (previousAssignedDates) {  
            // const datePattern = /\(FROM: (.*?), TO: (.*?)\)/g;
            // activeState = vehicleToUpdate.active_state.replace(datePattern, '');
            //  activeState = vehicleToUpdate.active_state.replace(/\(FROM:.*?, TO:.*?\)/g, '');
            activeState += ' ' + previousAssignedDates.filter(date => !date.includes(`FROM: ${addForm.value.scheduledDate}, TO: ${addForm.value.dateRange}`)).join(' ');
            vehicleToUpdate.active_state = activeState;
            
        } else {
           
          vehicleToUpdate.active_state = vehicleToUpdate.active_state;
        }

       
        }
    }
      this.siteVisitService.updateVehicles(existingVehicle).subscribe(
        (response: Vehicle) => {
          console.log(response);
          this.getSiteVisits();
          addForm.reset();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
          addForm.reset();
        }
      );

      const siteVisit: SiteVisit = {
        scheduledDate: addForm.value.scheduledDate,
        tMemberOne: addForm.value.tMemberOne,
        assignedVehicle: assignedVehicle,
        startSiteVisit: false,
        dateRange: addForm.value.dateRange,
        state: state,
        vehicles: [],
        siteVisitName: '',
        siteVisitId: 0,
        job: [],
        assignedJob: addForm.value.assignedJob,
        memberOne: addForm.value.memberOne,
        memberTwo: addForm.value.memberTwo,
        memberThree: addForm.value.memberThree,
        memberFour: addForm.value.memberFour,
        memberFive: addForm.value.memberFive,
        memberIdOne: addForm.value.memberIdOne,
        memberIdTwo: addForm.value.memberIdTwo,
        memberIdThree: addForm.value.memberIdThree,
        memberIdFour: addForm.value.memberIdFour,
        memberIdFive: addForm.value.memberIdFive
      };
      
      this.siteVisitService.addSiteVisit(siteVisit).subscribe(
        (response: SiteVisit) => {
          console.log(response);
          this.getSiteVisits();
          addForm.reset();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
          addForm.reset();
        }
      );
    } else {
      alert('Vehicle number already exists in a site visit. Please select a different vehicle number.');
      return;
      };
      
  }
  
   //Edit asite visit
  public onUpdateSiteVisit(siteVisit: SiteVisit): void {
   

      const assignedVehicle = siteVisit.assignedVehicle;
      const state = siteVisit.state;
    
      const existingVehicle = this.availableVehicles.find(sv => sv.vehicle_number === assignedVehicle);
      if (existingVehicle) {
        const vehicleToUpdate = existingVehicle;
        if (vehicleToUpdate) {
          const assignedStatus = state !== 'Completed' ? 'Assigned' : 'Available';
          let activeState = assignedStatus;
          let preActiveState = activeState;
          if (assignedStatus === 'Assigned') {
            const previousAssignedDates = vehicleToUpdate.active_state && vehicleToUpdate.active_state.match(/\(FROM: (.*?), TO: (.*?)\)/g);
            const currentAssignedDate = `(FROM: ${siteVisit.scheduledDate}, TO: ${siteVisit.dateRange})`;
            
            if (previousAssignedDates) {
              
              activeState += previousAssignedDates.join(' ');
              activeState += ' ' + currentAssignedDate;
            } else {
              activeState += ' ' + currentAssignedDate;
            }
    
            vehicleToUpdate.active_state = activeState;
          } else {
            const previousAssignedDates = vehicleToUpdate.active_state && vehicleToUpdate.active_state.match(/\(FROM: (.*?), TO: (.*?)\)/g);
            const currentAssignedDate = `(FROM: ${siteVisit.scheduledDate}, TO: ${siteVisit.dateRange})`;
    
            if (previousAssignedDates) {  
              activeState += ' ' + previousAssignedDates.filter(date => !date.includes(`FROM: ${siteVisit.scheduledDate}, TO: ${siteVisit.dateRange}`)).join(' ');
              vehicleToUpdate.active_state = activeState;
            } else {
              vehicleToUpdate.active_state = vehicleToUpdate.active_state;
            }
          }
        }
      
    
      this.siteVisitService.updateVehicles(existingVehicle).subscribe(
        (response: Vehicle) => {
          console.log(response);
          this.getSiteVisits();
         
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        
        }
      );
    }
    
    this.siteVisitService.updateSiteVisit(siteVisit).subscribe(
      (response: SiteVisit) => {
        console.log(response);
        this.getSiteVisits();
     
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
// }

   //Delete a site visit
  public onDeleteSiteVisit(siteVisitId: number): void {
    this.siteVisitService.deleteSiteVisit(siteVisitId).subscribe(
      (response: void) => {
        console.log(response);
        this.getSiteVisits();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

   //Search a site visit
  public searchSiteVisits(key: string): void {
    console.log(key);
    const results: SiteVisit[] = [];
    for (const siteVisit of this.siteVisits) {
      if (siteVisit &&  siteVisit.siteVisitId && siteVisit.siteVisitId.toString().toLowerCase().indexOf(key.toLowerCase()) !== -1
      ) {
        results.push(siteVisit);
      }
    }
    this.siteVisits = results;
    if (results.length === 0 || !key) {
      this.getSiteVisits();
    }
  }
  
  downloadPDF(siteVisitId: number) {
    this.siteVisitService.downloadPDF(siteVisitId).subscribe((response: Blob) => {
      const fileURL = URL.createObjectURL(response);
      window.open(fileURL);
    });
  }
  downloadJobCard(siteVisitId: number) {
    this.siteVisitService.downloadJobCard(siteVisitId).subscribe((response: Blob) => {
      const fileURL = URL.createObjectURL(response);
      window.open(fileURL);
    });
  }
  
public onOpenModal(siteVisit: SiteVisit,mode:string): void{ //takes a SiteVisit object and a mode as parameters. It opens a modal window based on the mode passed in as a parameter.
  const container=document.getElementById(
    'main-container'
    )as HTMLInputElement;
    const button = document.createElement('button');
    button.type='button';
    button.style.display='none';
    button.setAttribute('data-bs-toggle','modal');
    // if(mode ==='add'){
   
    //   button.setAttribute('data-bs-target','#addSiteVisitModal');
      
    // }
    if(mode ==='edit'){
      
      this.editSiteVisits=siteVisit;
      button.setAttribute('data-bs-target','#updateSiteVisitModal');
    }
    if(mode ==='delete'){
      this.deleteSiteVisits = siteVisit;
     
      button.setAttribute('data-bs-target','#deleteSiteVisitModal');
    }
    if(mode ==='print'){
      this.printGatePasses = siteVisit;
     
      button.setAttribute('data-bs-target','#printGatePassModal');
    }
    if(mode ==='downLoad'){
      this.printJobCards = siteVisit;
     
      button.setAttribute('data-bs-target','#printJobCardModal');
    }
    // if (mode === 'print') {
    //   this.printGatePasses = siteVisit;
    //   button.setAttribute('data-target', '#printGatePassModal');
    //  }
     
    container.appendChild(button);
    button.click();

  }
  
}


