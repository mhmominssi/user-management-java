import { Component } from '@angular/core';
import { User } from '../users/users';
import { FormsModule } from '@angular/forms';
import { UserData } from '../service/user-data';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-user',
  imports: [FormsModule],
  templateUrl: './add-user.html',
  styleUrl: './add-user.css',
})
export class AddUser {
  user: User = new User(null, '', '', '', '', new Date());

  constructor(private userService: UserData,private router:Router) { }

  addUser(user: User) {
    this.userService.addUser(user).subscribe({
      next: (response) => {
        console.log("Status Code:", response.status);
        console.log("Response Body:", response.body);

        if (response.status === 201) {
          console.log("Success ✅");
          this.router.navigate(['users'])
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
    });
  }

}
