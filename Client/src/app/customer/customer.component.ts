import { Component, OnInit } from '@angular/core';
import { Customer } from './customer';
import { CustomerService } from './customer.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
})
export class CustomerComponent implements OnInit {
onChange(arg0: any) {
throw new Error('Method not implemented.');
}
  // Define class properties

  currentDate = new Date();
  public customers: Customer[] = [];
  public editCustomer!: Customer;
  public deleteCustomer!: Customer;
  dtoptions: DataTables.Settings = {};
  dtTriger: Subject<any> = new Subject<any>();



  public activeStatus: boolean = true;

  // Inject customer service
  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    // Define DataTables options

    this.dtoptions = {
      pagingType: 'full_numbers',
      destroy: true,
      // columns: [
      //   // Define your columns here...
      //   { data: 'name', orderable: true },
      //   { data: 'address', orderable: false },
      //   { data: 'contactNumber', orderable: false },
      //   { data: 'nic_number', orderable: true },
      //   { data: 'customer.address', orderable: true },
      //   { data: 'contactPersonName', orderable: false },
      //   { data: 'designation', orderable: false },
      //   { data: 'email', orderable: false },
      //   { data: 'customercode', orderable: false },
      //   // Disable sorting for this column
      // ],
      // order: [],
    };
    // Call getCustomers method to retrieve customer data
    this.getCustomers();
    this.dtoptions = {
      retrieve: true,
    };


  }

  // Retrieve customer data using customer service
  public getCustomers(): void {
    {
      this.customerService.getCustomerList().subscribe(
        (response: Customer[]) => {
          this.customers = response;
          this.dtTriger.next(null);
          console.log(this.customers);
        },
        (error: HttpErrorResponse) => alert(error.message)
      );
    }
  }

  // Add new customer using customer service and reset form
  public onAddCustomer(addForm: NgForm): void {
    this.customerService.addCustomer(addForm.value).subscribe(
      (response: Customer) => {
        console.log(response);
        this.getCustomers();
        this.dtoptions = {
          retrieve: true,
        };

        addForm.reset();
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  // Delete customer using customer service
  public onDeleteCustomer(customerId: number): void {
    this.customerService.deleteCustomer(customerId).subscribe(
      (response: void) => {
        console.log(response);
        this.getCustomers();
        this.dtoptions = {
          retrieve: true,
        };
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }



  // Update customer using customer service
  public onUpdateCustomer(Customer: Customer): void {
    Customer.active_status = this.activeStatus; // Set the updated active_status value
    this.customerService.updateCustomer(Customer).subscribe(
      (response: Customer) => {
        console.log(response);
        this.getCustomers();
        this.dtoptions = {
          retrieve: true,
        };
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }



  // public toggleActiveStatus(){
  //   this.editCustomer.active_status = !this.editCustomer.active_status;
  // }


  // Search customers by name, email, or contact person name
  public searchCustomer(key: string): void {
    console.log(key);
    const results: Customer[] = [];
    for (const customer of this.customers) {
      if (
        customer.name.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
        customer.email.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
        customer.contactPersonName.toLowerCase().indexOf(key.toLowerCase()) !==
          -1
      ) {
        results.push(customer);
      }
    }
    this.customers = results;
    if (results.length === 0 || !key) {
      this.getCustomers();
    }
  }

  public news (){
    console.log("delete clicked")
  }

  // Function to handle opening modal dialog for editing or deleting a customer
  public onOpenModal(customer: Customer, mode: string): void {
    console.log(" open modal called");
    const container = document.getElementById(
      'main-container'
    ) as HTMLInputElement;

    // Create a hidden button element
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-bs-toggle', 'modal');

    // If mode is 'edit', set data-bs-target attribute to edit modal and assign customer to editCustomer property
    if (mode === 'edit') {
      button.setAttribute('data-bs-target', '#exampleModal2');
      this.editCustomer = customer;
    }

    // If mode is 'delete', set data-bs-target attribute to delete modal and assign customer to deleteCustomer property
    if (mode === 'delete') {
      button.setAttribute('data-bs-target', '#exampleModal3');
      this.deleteCustomer = customer;
    }

    // Append button to main container element and trigger a click event
    container.appendChild(button);
    button.click();
  }
}
