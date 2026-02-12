import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, model, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserData } from '../service/user-data';
import { RouterLink } from "@angular/router";


export class User {
  constructor(
    public id: any,
    public firstName: string,
    public lastName: string,
    public gender: string,
    public city: string,
    public dob: Date
  ) { }

}
@Component({
  selector: 'app-users',
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './users.html',
  styleUrl: './users.css',
})
export class Users implements OnInit {
  users: User[] = [];
  filteredUsers: User[] = [];

  searchText: string = '';
  selectedGender: string = '';
  selectedDate: string = '';

  selectedUser: User = new User(1, '', '', '', '', new Date());
  constructor(private userService: UserData, private ref: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.userService.getAllUser().subscribe({
      next: (response) => {
        // debugger;
        console.log("Status Code:", response.status);
        console.log("Response Body:", response.body);

        if (response.status === 200) {

          this.users = response.body ? response.body : [];
          console.log("Success ✅");
          this.filteredUsers = this.users; // initially same
          this.ref.detectChanges();
        }
      },
      error: (error) => {
        console.log("Error Status:", error.status);

        if (error.status === 500) {
          console.log("Server Error ❌");
        }
        if (error.status === 400) {
          console.log("Bad Request ❌");
        }
      }
    })

  }

  applyFilters() {
    this.filteredUsers = this.users.filter(user => {

      const matchesSearch =
        this.searchText === '' ||
        user.firstName.toLowerCase().includes(this.searchText.toLowerCase()) ||
        user.lastName.toLowerCase().includes(this.searchText.toLowerCase()) ||
        user.city.toLowerCase().includes(this.searchText.toLowerCase());

      const matchesGender =
        this.selectedGender === '' ||
        user.gender === this.selectedGender;

      const matchesDate =
        this.selectedDate === '' ||
        user.dob.toISOString().split('T')[0] === this.selectedDate;
      return matchesSearch && matchesGender && matchesDate;
    });
  }
  resetFilters() {
    this.searchText = '';
    this.selectedGender = '';
    this.selectedDate = '';
    this.filteredUsers = this.users;
  }


  openEditModal(user: User) {
    this.selectedUser = { ...user };
    console.log("Selected Users" + this.selectedUser.id); // copy object

  }
  updateUser() {
    const index = this.users.findIndex(u => u.id == this.selectedUser.id);

    if (index !== -1) {
      this.users[index] = { ...this.selectedUser };
    }

    console.log("Updated User:", this.selectedUser);
  }

  deleteUser(id: number) {
    // debugger;
    this.userService.deleteUser(id).subscribe(() => {
      this.users = this.users.filter(user => user.id != id);
      console.log(`Delete of User with ID : ${id} Successful`);
      // this.loadData();


    })
  }
}
