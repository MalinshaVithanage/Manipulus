import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer/customer.component';
import { CalendarComponent } from './calendar/calendar.component';
import { GatePassComponent } from './gate-pass/gate-pass.component';
import { ItemComponent } from './item/item.component';
import { UserComponent } from './user/user.component';
import { JobComponent } from './job/job.component';
import { JobCardComponent } from './job-card/job-card.component';
import { LocationComponent } from './location/location.component';
import { RepairPartsComponent } from './repair-parts/repair-parts.component';
import { ServiceAgreementComponent } from './service-agreement/service-agreement.component';



import { SiteVisitComponent } from './site-visit/site-visit.component';
import { SubLocationComponent } from './sub-location/sub-location.component';
import { UnitComponent } from './unit/unit.component';
import { UserGroupComponent } from './user-group/user-group.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { ForgetPasswordComponent } from './user-login/forget-password/forget-password.component';

const routes: Routes = [
  { path: 'calendar', component: CalendarComponent },
  { path: 'customer', component: CustomerComponent },
  { path: 'gate-pass', component: GatePassComponent },
  { path: 'item', component: ItemComponent },
  { path: 'job', component: JobComponent },
  { path: 'job-card', component: JobCardComponent },
  { path: 'location', component: LocationComponent },
  { path: 'repair-parts', component: RepairPartsComponent },
  { path: 'service-agreement', component: ServiceAgreementComponent },

  

  { path: 'site-visit', component: SiteVisitComponent },
  { path: 'sub-location', component: SubLocationComponent },
  {
    path: 'unit',
    component: UnitComponent,
    canActivate: [],
    data: { roles: ['User'] },
  },
  { path: 'user', component: UserComponent },
  { path: 'user-group', component: UserGroupComponent },
  { path: 'vehicle', component: VehicleComponent },
  { path: '', redirectTo: '/user-login', pathMatch: 'full' },
  { path: '*', component: PageNotFoundComponent },
  { path: 'home', component: HomeComponent },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [],
    data: { roles: ['Admin'] },
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [],
    data: { roles: ['User'] },
  },
  { path: 'user-login', component: UserLoginComponent },
  { path: 'forbidden', component: ForbiddenComponent },
  { path: 'forget-password', component: ForgetPasswordComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
