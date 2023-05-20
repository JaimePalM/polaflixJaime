import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SerieService {

  private endpoint = "http://localhost:8000/series";

  constructor(private http: HttpClient) { }

  public getSeries(): Observable<any> {
    return this.http.get(this.endpoint)
  }

  public getSerie(serieId: number): Observable<any> {
    return this.http.get(this.endpoint + "/" + serieId)
  }

  public getSeriesByInitial(initial: string): Observable<any> {
    return this.http.get(this.endpoint + "?initial=" + initial)
  }

}
