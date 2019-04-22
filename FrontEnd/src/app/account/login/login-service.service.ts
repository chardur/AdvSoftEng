import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from 'src/app/models/user';
import { UserRole } from 'src/app/models/user-role';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient) { }

  getUserInfo(username: string) {
    const apiLocation = environment.baseUrl + 'user/username/' + username;

    return this.http.get<User>(apiLocation);
  }

  getRoles(userId: number) {
    const apiLocation = environment.baseUrl + 'userrole/getallrolesforuserid/' + userId;

    return this.http.get<UserRole[]>(apiLocation);
  }
}
