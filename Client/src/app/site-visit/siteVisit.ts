import { scheduled } from "rxjs";
import { Vehicle } from "../vehicle/vehicle";
import { Job } from "../job/job";
import { Customer } from "../customer/customer";

export interface SiteVisit{
   
    //vehicle: import("c:/Manipulus 7.0/Manipulus/Client/src/app/vehicle/vehicle").Vehicle;
    siteVisitName: string;
    siteVisitId: number;
    scheduledDate: Date;
    tMemberOne: string;
    // tMember2:string;
    // tMember3:string;
    // tMember4:string;
    // tMember5:string;
    // tMember1Id:number;
    // tMember2Id:number;
    // tMember3Id:number;
    // tMember4Id:number;
    // tMember5Id:number;
    assignedVehicle: string;
    startSiteVisit: boolean;
    dateRange: Date;
    state:string;
    vehicles: Vehicle[];
    job:Job[];
    assignedJob: number;
    memberOne: string;
    memberTwo: string;
    memberThree: string;
    memberFour: string;
    memberFive: string;
    memberIdOne:number;
    memberIdTwo:number;
    memberIdThree:number;
    memberIdFour:number;
    memberIdFive:number;

    

}

export { Vehicle };
