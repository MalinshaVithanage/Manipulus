import { HttpClient } from '@angular/common/http';
import { Component, OnInit, } from '@angular/core';
import { Data } from '@angular/router';
import { Subject } from 'rxjs';
import Swal from 'sweetalert2';







@Component({
  selector: 'app-service-agreement',
  templateUrl: './service-agreement.component.html',
  styleUrls: ['./service-agreement.component.css']
})


export class ServiceAgreementComponent implements OnInit{

  dtoptions: DataTables.Settings = {};
  dtTriger: Subject<any> = new Subject<any>();
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
    this.getAllAgreement();
    this.dtoptions = {
      retrieve: true,
    };

    
  }


  showMe: boolean = false
  
  toogleTag() {
    this.showMe = !this.showMe
  }


  showMeForm: boolean = false
  ngOnInitForm() {

  }
  toogleTagForm() {
    this.showMeForm = !this.showMeForm
  }









//...............................................
  // showMeTitle2: boolean = true
  // ngOnInitTitle2() {

  // }
  // toogleTagTitle2() {
  //   this.showMeTitle2 = !this.showMeTitle2
  // }
//




  DontshowMeTable: boolean = true
  ngOnInitTable() {

  }
  toogleTagTable() {
    this.DontshowMeTable = !this.DontshowMeTable
  }





  AgreementArray: any[] = [];
  CustomerArray: any[] = [];
  isResultLoaded = false;
  isUpdateFormActive = false;


  unitid: number = 0;
  equipment: string = "";
  price_per_service: number = 0;
  emergency_service_rate: number = 0;
  type_of_the_service: string = "";
  initiated_date: string ;
  expired_date: string;
  nic: string = "";

 

  currentAgreementID = "";



  constructor(private http: HttpClient) {
    this.getAllAgreement();
    this.getAllNIC();

  }




  getAllAgreement() {

    this.http.get("http://localhost:8080/api/v1/agreement/getAllAgreement")

      .subscribe((resultData: any) => {
        this.isResultLoaded = true;
        console.log(resultData);
        this.AgreementArray = resultData;
        this.dtTriger.next(null);
      });
  }

  register() {

    let bodyData = {

      "unitid": this.unitid,
      "buttoId": this.unitid,
      "equipment": this.equipment,
      "price_per_service": this.price_per_service,
      "emergency_service_rate": this.emergency_service_rate,
      "type_of_the_service": this.type_of_the_service,
      "initiated_date": this.initiated_date,
      "expired_date": this.expired_date,
      "nic": this.nic,
    };


    if (this.unitid == 0 && this.equipment == '' && this.price_per_service <= 0 && this.emergency_service_rate <= 0 && this.type_of_the_service == '' && this.initiated_date == '' && this.expired_date == '' && this.nic == '') {

      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'All Details Required !',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',


        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });

    }
    else if (this.unitid == 0) {
      // alert("Please insert an existing UnitId!");



      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please insert an existing UnitId!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',


        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });





    } else if (this.equipment == '') {
      // alert("Please insert the Equipment!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please insert the Equipment!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });

    } else if (this.price_per_service <= 0) {
      //alert("Please insert the Service Price correctly!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please insert the Service Price correctly!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',


        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });

    } else if (this.emergency_service_rate <= 0) {
      // alert("Please insert the Emergency Service Price correctly!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please insert the Emergency Service Price correctly!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });
    } else if (this.type_of_the_service == '') {
      // alert("Please Choose a Service Type!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please Choose a Service Type!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });
    } else if (this.initiated_date == '') {
      //alert("Please enter the initial date!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please enter the initial date!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });
    }
    else if (this.expired_date == '') {
      // alert("please enter the expire date!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'please enter the expire date!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });
    } else if (this.nic == '') {
      // alert("Please insert an existing NIC number!")
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please insert an existing NIC number!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });
    }

    else {

      this.http.post("http://localhost:8080/api/v1/agreement/save", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
        console.log(resultData);
        // alert("Service Agreement Created Successfully");
        Swal.fire({
          title: 'Service Agreement Created Successfully',
          //  text: 'Please insert an existing NIC number!',
          icon: 'success',
          position: 'top',
          width: '500px',
          imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
          imageHeight: '100px',
          imageWidth: '100px',
          confirmButtonColor: '#3085d6',
          customClass: {
            confirmButton: 'btn btn-primary',
            cancelButton: 'btn btn-secondary',
          },
        });
        this.getAllAgreement();

        this.unitid = 0;
        this.equipment = '';
        this.price_per_service = 0;
        this.emergency_service_rate = 0;
        this.type_of_the_service = '';
        this.initiated_date = '';
        this.expired_date = '';
        this.nic = '';

      });
      this.showMeForm = !this.showMeForm
      this.DontshowMeTable = !this.DontshowMeTable



    }
  }



  setUpdate(data: any) {

    this.unitid = data.unitid;
    this.equipment = data.equipment;
    this.price_per_service = data.price_per_service;
    this.emergency_service_rate = data.emergency_service_rate;
    this.type_of_the_service = data.type_of_the_service;
    this.initiated_date = data.initiated_date;
    this.expired_date = data.expired_date;
    this.nic = data.nic;
    this.currentAgreementID = data.agreementid;


  }

  UpdateRecords() {
    let bodyData = {

      "agreementid": this.currentAgreementID,
      "unitid": this.unitid,
      "buttoId": this.unitid,
      "equipment": this.equipment,
      "price_per_service": this.price_per_service,
      "emergency_service_rate": this.emergency_service_rate,
      "type_of_the_service": this.type_of_the_service,
      "initiated_date": this.initiated_date,
      "expired_date": this.expired_date,
      "nic": this.nic,

    };


    if (this.unitid == 0 && this.equipment == '' && this.price_per_service <= 0 && this.emergency_service_rate <= 0 && this.type_of_the_service == '' && this.initiated_date == '' && this.expired_date == '' && this.nic == '') {

      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'All Details Required !',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',


        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });

    }
    else if (this.unitid == 0) {
      // alert("Please insert an existing UnitId!");



      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please insert an existing UnitId!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',


        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });





    } else if (this.equipment == '') {
      // alert("Please insert the Equipment!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please insert the Equipment!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });

    } else if (this.price_per_service <= 0) {
      //alert("Please insert the Service Price correctly!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please insert the Service Price correctly!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',


        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });

    } else if (this.emergency_service_rate <= 0) {
      // alert("Please insert the Emergency Service Price correctly!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please insert the Emergency Service Price correctly!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });
    } else if (this.type_of_the_service == '') {
      // alert("Please Choose a Service Type!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please Choose a Service Type!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });
    } else if (this.initiated_date == '') {
      //alert("Please enter the initial date!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please enter the initial date!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });
    }
    else if (this.expired_date == '') {
      // alert("please enter the expire date!");
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'please enter the expire date!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });
    } else if (this.nic == '') {
      // alert("Please insert an existing NIC number!")
      Swal.fire({
        title: 'Fill The Form Correctly',
        text: 'Please insert an existing NIC number!',
        icon: 'error',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',
        },
      });
    } else {

      this.http.put("http://localhost:8080/api/v1/agreement/update", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
        console.log(resultData);
        // alert("Service Agreement is Updated")
        Swal.fire({
          title: 'Service Agreement is Updated Successfully',
          //  text: 'Please insert an existing NIC number!',
          icon: 'success',
          position: 'top',
          width: '500px',
          imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
          imageHeight: '100px',
          imageWidth: '100px',
          confirmButtonColor: '#3085d6',
          customClass: {
            confirmButton: 'btn btn-primary',
            cancelButton: 'btn btn-secondary',
          },
        });

        setTimeout(() => {
          window.location.reload();
        }, 1500);



        this.getAllAgreement();
        this.unitid = 0;
        this.equipment = '';
        this.price_per_service = 0;
        this.emergency_service_rate = 0;
        this.type_of_the_service = '';
        this.initiated_date = '';
        this.expired_date = '';
        this.nic = '';


      });
    }
  }


  save() {


    if (this.currentAgreementID == '') {
      this.register();
    }
    else {
      this.UpdateRecords();
    }

    // setTimeout(() => {
    //   window.location.reload();
    // }, 30);

  }






  passIdForGeneratePdf(data: any) {


    this.currentAgreementID = data.agreementid;

    let bodyData = {

      "agreementid": this.currentAgreementID,


    };

    this.http.put("http://localhost:8080/api/v1/agreement/passId", bodyData, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      // alert("PDF is generated")
      Swal.fire({
        title: 'PDF is generated Successfully',
        //  text: 'Please insert an existing NIC number!',
        icon: 'success',
        position: 'top',
        width: '500px',
        imageUrl: '../../assets/Icon/303292717_570886498156142_5541375326204770233_n.jpg',
        imageHeight: '100px',
        imageWidth: '100px',
        confirmButtonColor: '#3085d6',
        customClass: {
          confirmButton: 'btn btn-primary',
          cancelButton: 'btn btn-secondary',

        },
      });

      setTimeout(() => {
        window.location.reload();
      }, 1500);



    });
  }

  //

  setDelete(data: any) {






    this.http.delete("http://localhost:8080/api/v1/agreement/deleteagreement" + "/" + data.agreementid, { responseType: 'text' }).subscribe((resultData: any) => {
      console.log(resultData);
      // alert("Service Agreement Deleted")

      this.getAllAgreement();

      this.unitid = 0;
      this.equipment = '';
      this.price_per_service = 0;
      this.emergency_service_rate = 0;
      this.type_of_the_service = '';
      this.initiated_date = '';
      this.expired_date = '';
      this.nic = '';
    });

  }

  public onOpenModal(mode: string): void {
    const container = (document.getElementById('main-container') as HTMLInputElement);
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-bs-toggle', 'modal');

    container.appendChild(button);
    button.click();
  }


  apiurl = "http://localhost:8080/api/v1/agreement/passId";

  postId(inputdata: any) {
    return this.http.post(this.apiurl, inputdata)
  }

  navigateToUrl(url: string): void {
    window.location.href = url;

    // setTimeout(() => {
    //   window.location.reload();
    // }, 500);


  }



  getAllNIC() {

    this.http.get("http://localhost:8080/customer/all")

      .subscribe((resultData: any) => {
        this.isResultLoaded = true;
        console.log(resultData);
        this.CustomerArray = resultData;
      });
  }

}
