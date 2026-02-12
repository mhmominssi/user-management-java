import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { UserData } from '../service/user-data';
import { Router, RouterLink } from "@angular/router";
import { User } from '../users/users';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  totalUser: number = 0;
  maleCount: number = 0;
  femaleCount: number = 0;
  cityCount: number = 0;
  recentUsers: User[] = [];
  users: User[] = [];


  constructor(private userService: UserData, private ref: ChangeDetectorRef, private router: Router) { }

  ngOnInit(): void {
    this.loadDashboardData();
  }
  loadDashboardData() {
    this.userService.getAllUser().subscribe({
      next: (response) => {
        // debugger;
        console.log("Status Code:", response.status);
        console.log("Response Body:", response.body);

        if (response.status === 200) {
          
          this.users= response.body ? response.body : [];
          console.log("Success ✅");
          this.totalUser = this.users.length;
          // ===== Dashboard Counts =====
          this.maleCount = this.users.filter(u => u.gender === 'Male').length;
          this.femaleCount = this.users.filter(u => u.gender === 'Female').length;

          const uniqueCities = new Set(this.users.map(u => u.city));
          this.cityCount = uniqueCities.size;

          // ===== Recently Added Users =====
          this.recentUsers = [...this.users]
            .sort((a, b) => b.id - a.id)   // newest first (assuming id is auto increment)
            .slice(0, 5);                  // take latest 5 users
          this.ref.detectChanges();

          // this.router.navigate(['users'])
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

    // this.userService.getAllUser().subscribe(users => {
    //   this.totalUser=users.length;
    //   // ===== Dashboard Counts =====
    //   this.maleCount = users.filter(u => u.gender === 'Male').length;
    //   this.femaleCount = users.filter(u => u.gender === 'Female').length;

    //   const uniqueCities = new Set(users.map(u => u.city));
    //   this.cityCount = uniqueCities.size;

    //   // ===== Recently Added Users =====
    //   this.recentUsers = [...users]
    //     .sort((a, b) => b.id - a.id)   // newest first (assuming id is auto increment)
    //     .slice(0, 5);                  // take latest 5 users
    //     this.ref.detectChanges();
    // },{
    //   next: (response) => {
    //     console.log("Status Code:", response.status);
    //     console.log("Response Body:", response.body);

    //     if (response.status === 200) {
    //       console.log("Success ✅");
    //       this.router.navigate(['users'])
    //     }
    //   },
    //   error: (error) => {
    //     console.log("Error Status:", error.status);

    //     if (error.status === 500) {
    //       console.log("Server Error ❌");
    //     }
    //     if (error.status === 400) {
    //       console.log("Bad Request ❌");
    //     }
    //   }
    // });
  }


}
