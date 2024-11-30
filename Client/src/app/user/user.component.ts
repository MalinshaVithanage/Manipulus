import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from './user';
import { UserService } from './user.service';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit, OnDestroy {

  addUserForm: FormGroup;
  searchForm: FormGroup;
  userSubscription: Subscription;
  users: User[] = [];
  isAddUserModalOpen = false;
  isEditUserModalOpen = false;
  selectedUser: any;
  key: string;

  constructor(private formBuilder: FormBuilder, private userService: UserService) {
    this.searchForm = this.formBuilder.group({
      key: ['']
    });
  }

  ngOnInit() {
    this.getUserList();
    this.initializeUserForm();
  }

  ngOnDestroy(): void {
    this.userSubscription?.unsubscribe();
  }

  getUserList() {
    this.userSubscription = this.userService.getUserList().subscribe(res => {
      this.users = res;
    });
  }

  initializeUserForm() {
    this.addUserForm = this.formBuilder.group({
      firstName: this.formBuilder.control('', Validators.required),
      lastName: this.formBuilder.control('', Validators.required),
      userName: this.formBuilder.control('', Validators.required),
      address: this.formBuilder.control('', Validators.required),
      mobileNumber: this.formBuilder.control(''),
      email: this.formBuilder.control(''),
      password: this.formBuilder.control(''),
      status: this.formBuilder.control(''),
      designation: this.formBuilder.control(''),
      group: this.formBuilder.control(''),
      userRole: this.formBuilder.control(''),

    });
  }

  onUserAdd() {
    console.log(this.addUserForm.value);
    this.userService.addUser(this.addUserForm.value).subscribe(res => {
      console.log(res);
    });
  }

  onAddUserSubmit() {
    if (this.addUserForm.invalid) {
      return;
    }
    const newUser = this.addUserForm.value;
    this.users.push(newUser);
    this.closeAddUserModal();
  }

  onEditUserSubmit() {
    if (this.addUserForm.invalid) {
      return;
    }
    const updatedUser = this.addUserForm.value;
    // Update the selected user in the list
    const index = this.users.findIndex(user => user === this.selectedUser);
    if (index !== -1) {
      this.users[index] = updatedUser;
    }
    this.closeEditUserModal();
  }

  openAddUserModal() {
    this.isAddUserModalOpen = true;
  }

  closeAddUserModal() {
    this.isAddUserModalOpen = false;
    this.addUserForm.reset();
  }
  openEditUserModal(user: any) {
    this.isEditUserModalOpen = true;
    this.selectedUser = user;
    this.addUserForm.patchValue({
      firstName: user.firstName,
      lastName: user.lastName,
      userName: user.userName,
      address: user.address,
      mobileNumber: user.mobileNumber,
      email: user.email,
      password: user.password,
      status: user.status,
      designation: user.designation,
      group: user.group,
      userRole: user.userRole,
    });
  }

  closeEditUserModal() {
    this.isEditUserModalOpen = false;
    this.addUserForm.reset();
    this.selectedUser = null;
  }

  searchUser(value: string) {
    const searchKey = this.searchForm.value.key;
  }
}
