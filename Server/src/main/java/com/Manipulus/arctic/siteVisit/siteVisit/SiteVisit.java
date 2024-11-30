package com.Manipulus.arctic.siteVisit.siteVisit;

import com.Manipulus.arctic.job.model.Job;
import com.Manipulus.arctic.vehicle.model.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@ToString
@Entity
@Table(name = "site_visit")
public class SiteVisit implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false,updatable = false)
        private Long siteVisitId;
    @Column(name = "scheduledDate" )
        private Date scheduledDate;

    @Column(name = "assignedVehicle" )
        private String assignedVehicle;
    @Column(name = "assignedJob" )
    private Long assignedJob;
    @Column(name = "memberOne" )
    private String memberOne;
    @Column(name = "memberTwo" )
    private String memberTwo;
    @Column(name = "memberThree" )
    private String memberThree;
    @Column(name = "memberFour" )
    private String memberFour;
    @Column(name = "memberFive" )
    private String memberFive;
    @Column(name = "memberIdOne" )
    private int memberIdOne;
    @Column(name = "memberIdTwo" )
    private int memberIdTwo;
    @Column(name = "memberIdThree" )
    private int memberIdThree;
    @Column(name = "memberIdFour" )
    private int memberIdFour;
    @Column(name = "memberIdFive" )
    private int memberIdFive;

    @Column(name = "startSiteVisit" )
        private boolean startSiteVisit;
    @Column(name = "dateRange" )
        private Date dateRange;
    @Column(name = "state" )
        private String state;

    @Column(nullable = false, updatable = false)
    private String siteVisitCode;

  /* @OneToMany(targetEntity = Vehicle.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "sv_fk",referencedColumnName = "siteVisitId")
    private List<Vehicle>vehicles;
*/
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "siteVisit")
  private List<Vehicle> vehicles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "siteVisit")
    private List<Job> jobs;

public SiteVisit(){
    super();
}
   public String getSiteVisitCode() {
        return siteVisitCode;
    }

    public void setSiteVisitCode(String siteVisitCode) {
        this.siteVisitCode = siteVisitCode;
    }



        public SiteVisit(Date scheduledDate, String assignedVehicle,Long assignedJob,
                         String memberOne,String memberTwo,String memberThree,String memberFour,String memberFive,
                         int memberIdOne,int memberIdTwo,int memberIdThree,int memberIdFour,int memberIdFive,
                         List<Job> jobs, List<Vehicle> vehicles,boolean startSiteVisit, Date dateRange,
                         String state, String siteVisitCode){
            this.scheduledDate=scheduledDate;
//            this.assignedTeamId=assignedTeamId;
            this.startSiteVisit=startSiteVisit;
            this.dateRange=dateRange;
            this.state=state;
           this.assignedVehicle=assignedVehicle;
            this.siteVisitCode=siteVisitCode;
            this.vehicles = vehicles;
            this.jobs=jobs;
            this.assignedJob=assignedJob;
            this.memberOne=memberOne;
            this.memberTwo=memberTwo;
            this.memberThree=memberThree;
            this.memberFour=memberFour;
            this.memberFive=memberFive;
            this.memberIdOne=memberIdOne;
            this.memberIdTwo=memberIdTwo;
            this.memberIdThree=memberIdThree;
            this.memberIdFour=memberIdFour;
            this.memberIdFive=memberIdFive;


        }

        public Long getSiteVisitId(){
            return siteVisitId;
        }
        public  void setSiteVisitId(long siteVisitId){
            this.siteVisitId=siteVisitId;
        }

       public String getAssignedVehicle(){
            return  assignedVehicle;
        }
        public void setAssignedVehicle(String assignedVehicle){
            this.assignedVehicle=assignedVehicle;
        }
    public Long getAssignedJob(){
        return  assignedJob;
    }
    public void setAssignedJob(Long assignedJob){
        this.assignedJob=assignedJob;
    }
    public String getMemberOne() {return memberOne;}
    public void setMemberOne(String memberOne) { this.memberOne = memberOne;}

    public String getMemberTwo() {
        return memberTwo;
    }

    public void setMemberTwo(String memberTwo) {
        this.memberTwo = memberTwo;
    }

    public String getMemberThree() {
        return memberThree;
    }

    public void setMemberThree(String memberThree) {
        this.memberThree = memberThree;
    }

    public String getMemberFour() {
        return memberFour;
    }

    public void setMemberFour(String memberFour) {
        this.memberFour = memberFour;
    }

    public String getMemberFive() {
        return memberFive;
    }

    public int getMemberIdOne() {
        return memberIdOne;
    }

    public void setMemberIdOne(int memberIdOne) {
        this.memberIdOne = memberIdOne;
    }

    public int getMemberIdTwo() {
        return memberIdTwo;
    }

    public void setMemberIdTwo(int memberIdTwo) {
        this.memberIdTwo = memberIdTwo;
    }

    public int getMemberIdThree() {
        return memberIdThree;
    }

    public void setMemberIdThree(int memberIdThree) {
        this.memberIdThree = memberIdThree;
    }

    public int getMemberIdFour() {
        return memberIdFour;
    }

    public void setMemberIdFour(int memberIdFour) {
        this.memberIdFour = memberIdFour;
    }

    public int getMemberIdFive() {
        return memberIdFive;
    }

    public void setMemberIdFive(int memberIdFive) {
        this.memberIdFive = memberIdFive;
    }

    public void setMemberFive(String memberFive) {
        this.memberFive = memberFive;
    }
    //        public Long getAssignedTeamId(){
//            return  assignedTeamId;
//        }
//        public void setAssignedTeamId(long assignedTeamId){
//            this.assignedTeamId=assignedTeamId;
//        }

        public boolean getStartSiteVisit(){
            return  startSiteVisit;
        }
        public void setStartSiteVisit(boolean startSiteVisit){
            this.startSiteVisit=startSiteVisit;
        }

        public Date getScheduledDate(){
            return  scheduledDate;
        }
        public void setScheduledDate(Date scheduledDate){
            this.scheduledDate=scheduledDate;
        }

        public Date getDateRange(){
            return  dateRange;
        }
        public void setDateRange(Date dateRange){
            this.dateRange=dateRange;
        }

        public String getState(){
            return  state;
        }
        public void setState(String state){
            this.state=state;
        }
        public List<Vehicle> getVehicles() {
            return vehicles;
        }
        public void setVehicles(List<Vehicle> vehicles) {
            this.vehicles = vehicles;
        }
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> Jobs) {
        this.jobs = jobs;
    }

    @Override
        public String toString(){
            return "SiteVisit{" +
                    "SiteVisitId="+ siteVisitId+'\''+
                    ", ScheduledDate"+ scheduledDate+'\''+
                    ", Vehicles"+ vehicles+'\''+
                    ", Jobs"+ jobs+'\''+
                    ", startSiteVisit "+ startSiteVisit+'\''+
                    ", DateRange "+ dateRange+'\''+
                    ", state" +state+'\''+
                    ", AssignedVehicle "+ assignedVehicle+'\''+
                    ", AssignedJob "+ assignedJob+'\''+
                    ", MemberOne "+ memberOne+'\''+
                    ", MemberTwo "+ memberTwo+'\''+
                    ", MemberThree "+ memberThree+'\''+
                    ", MemberFour "+ memberFour+'\''+
                    ", MemberFive "+ memberFive+'\''+
                    ", MemberIdOne "+ memberIdOne+'\''+
                    ", MemberIdTwo "+ memberIdTwo+'\''+
                    ", MemberIdThree "+ memberIdThree+'\''+
                    ", MemberIdFour "+ memberIdFour+'\''+
                    ", MemberIdFive "+ memberIdFive+'\''+


                    '}';
        }


}
