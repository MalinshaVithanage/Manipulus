import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SideNavComponent } from './side-nav/side-nav.component';
import { CustomerComponent } from './customer/customer.component';
import { JobComponent } from './job/job.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { LocationComponent } from './location/location.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserComponent } from './user/user.component';
import { UnitComponent } from './unit/unit.component';
import { SubLocationComponent } from './sub-location/sub-location.component';
import { CalendarComponent } from './calendar/calendar.component';
import { ItemComponent } from './item/item.component';
import { ServiceAgreementComponent } from './service-agreement/service-agreement.component';
import { UserGroupComponent } from './user-group/user-group.component';
import { RepairPartsComponent } from './repair-parts/repair-parts.component';
import { SiteVisitComponent } from './site-visit/site-visit.component';
import { GatePassComponent } from './gate-pass/gate-pass.component';
import { JobCardComponent } from './job-card/job-card.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { ForgetPasswordComponent } from './user-login/forget-password/forget-password.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { HeaderComponent } from './header/header.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { RouterModule } from '@angular/router';
import { AuthInterceptorService } from './services/auth.interceptor.service';
import { DataTablesModule } from "angular-datatables";


@NgModule({
  declarations: [
    AppComponent,
    SideNavComponent,
    CustomerComponent,
    JobComponent,
    VehicleComponent,
    LocationComponent,
    UserLoginComponent,
    UserComponent,
    UnitComponent,
    SubLocationComponent,
    CalendarComponent,
    ItemComponent,
    ServiceAgreementComponent,
    UserGroupComponent,
    RepairPartsComponent,
    SiteVisitComponent,
    GatePassComponent,
    JobCardComponent,
    PageNotFoundComponent,
    DashboardComponent,
    ForgetPasswordComponent,
    HomeComponent,
    AdminComponent,
    HeaderComponent,
    ForbiddenComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule,
    DataTablesModule,
  ],
  providers: [
    ReactiveFormsModule,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
