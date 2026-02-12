import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../users/users';
import { API_URL } from '../app.constant';

@Injectable({
  providedIn: 'root',
})
export class UserData {
  constructor(private http: HttpClient) { }

  apiurl = "http://localhost:8080/user"

  getAllUser() {
    return this.http.get<User[]>(this.apiurl, { observe: 'response' });
  }

  deleteUser(id: number) {
    return this.http.delete(`${API_URL}/user/${id}`, { responseType: 'text' })
  }

  addUser(user: User) {
    return this.http.post(
      `${API_URL}/user`,
      user,
      { observe: 'response', responseType: 'text' }
    );
  }

}
