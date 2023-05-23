import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private endpoint = "http://localhost:8000/users";

  constructor(private http: HttpClient) { }

  public getUserByEmail(email: string): Observable<any> {
    return this.http.get(this.endpoint + "?email=" + email)
  }

  public getUserById(userId: number): Observable<any> {
    return this.http.get(this.endpoint + "/" + userId)
  }

  public getLastChapterViewed(userId: number, serieId: number): Observable<any> {
    return this.http.get(this.endpoint + "/" + userId + "/last-chapter-viewed/" + serieId)
  }

  public getStartedSeries(userId: number): Observable<any> {
    return this.http.get(this.endpoint + "/" + userId + "/started-series")
  }

  public getPendingSeries(userId: number): Observable<any> {
    return this.http.get(this.endpoint + "/" + userId + "/pending-series")
  }

  public getFinishedSeries(userId: number): Observable<any> {
    return this.http.get(this.endpoint + "/" + userId + "/finished-series")
  }

  public addPendingSerie(userId: number, serieId: number): Observable<any> {
    return this.http.put(this.endpoint + "/" + userId + "/pending-series/" + serieId, {})
  }

  public getViews(userId: number, serieId: number): Observable<any> {
    console.log(this.endpoint + "/" + userId + "/views/serie/" + serieId);
    return this.http.get(this.endpoint + "/" + userId + "/views/serie/" + serieId)
  }

  public viewChapter(userId: number, serieId: number, seasonNumber: number, chapterNumber: number): Observable<any> {
    return this.http.put(this.endpoint + "/" + userId + "/views/serie/" + serieId + "/season/" + seasonNumber + "/chapter/" + chapterNumber, {})
  }

  public getBills(userId: number): Observable<any> {
    return this.http.get(this.endpoint + "/" + userId + "/bills")
  }

}
