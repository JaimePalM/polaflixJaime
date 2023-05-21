import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private endpoint = "http://localhost:8000/users";

  constructor(private http: HttpClient) { }

  public login(email: string, password: string): Observable<any> {
    return this.http.get(this.endpoint + "?email=" + email)
  }
}
