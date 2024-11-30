import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer/customer.service';
import { Customer } from '../customer/customer';
import { HttpErrorResponse } from '@angular/common/http';
import { JobService } from '../job/job.service';
import { Job } from '../job/job';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css','.../../../../assets/Css/nucleo-icons.css',]
})
export class DashboardComponent implements OnInit {

  public customers: Customer[] = [];
  public customersList: Customer[] = [];
  public jobs : Job[] =[];
  constructor(private customerService: CustomerService , private jobService: JobService) {}
  public filteredJobs: Job[] = [];
  public customerId!: number;
 public jobstypes : String[]=["Inspection",
"Service",
"Installation",
"Breakdowns",
"Upgrades",
"Remove"
];
addForm: NgForm;
  ngOnInit(): void {
    this.getCustomers();
    this.getJobs();

  }



  public getCustomers(): void {
    {
      this.customerService.getCustomerList().subscribe(
        (response: Customer[]) => {
          this.customers = response;
          this.customersList = response.filter(customer => customer.active_status);
          console.log(this.customers);
        },
        (error: HttpErrorResponse) => alert(error.message)
      );
    }
  }

  public getJobs():void { {
    this.jobService.getJobList().subscribe(
      (response: Job[]) =>{
        this.jobs = response;

        console.log(this.jobs);
      },
      (error: HttpErrorResponse) =>
         alert(error.message)

      ); }}

      public filterJobs(event: Event): void {
        const jobType = (event.target as HTMLSelectElement).value;
        if (jobType === '') {
          this.filteredJobs = this.jobs;
        } else {
          this.filteredJobs = this.jobs.filter(job => job.job_type === jobType);
        }
      }



 public onAddCustomer(addForm: NgForm): void {
    this.customerService.addCustomer(addForm.value).subscribe(
      (response: Customer) => {
        console.log(response);
        this.getCustomers();


        addForm.reset();
        // window.location.reload();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  // public onAddJob(addForm: NgForm): void {
  //   this.getCustomers();
  //   const customerId: number = this.customerId;
  //   // const customerId: number = 5; // Set the desired customer ID here
  //   this.jobService.addJob(addForm.value, customerId).subscribe(
  //     (response: Job) => {
  //       console.log(response);
  //       this.getJobs();

  //       addForm.reset();
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   );
  // }

}
