import { Customer } from "../customer/customer";

export class Job{
    id!: number;  
    job_type!: string;
    job_date!: Date;
    job_status!: String;
    jobCode!: String;
    customer!:Customer;
    customer_id!: number;
}