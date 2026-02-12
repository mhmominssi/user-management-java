import { Routes } from '@angular/router';
import { Dashboard } from './dashboard/dashboard';
import { Users } from './users/users';
import { AddUser } from './add-user/add-user';

export const routes: Routes = [
    {path:'dashboard',component:Dashboard},
    {path:'users',component:Users},
    {path:'users/create',component:AddUser}
];
