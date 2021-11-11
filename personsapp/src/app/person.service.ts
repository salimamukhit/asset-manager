import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  person: any;

  constructor(private http: HttpClient) { }

  public getPersonList(): Promise<any> {
    return new Promise(resolve => {
      this.http.get('http://localhost:9000/persons/list', )
        .subscribe(response => {
            resolve(response);
            return response;
          }, error => {
            console.log(error.status);
          });
    });
  }

  public addPerson(person: any): Promise<any> {
    return new Promise(resolve => {
      this.http.post('http://localhost:9000/persons/add', {person: person} )
        .subscribe(response => {
            resolve(response);
            return response;
          }, error => {
          console.log(error);
        });
    });
  }

  public deletePerson(id: number): Promise<any> {
    return new Promise(resolve => {
      this.http.delete('http://localhost:9000/persons/delete/' + id)
        .subscribe(response => {
            resolve(response);
            return response;
          }, error => {
          console.log(error.status);
        });
    });
  }

  public deleteAsset(id: number): Promise<any> {
    return new Promise(resolve => {
      this.http.delete('http://localhost:9000/assets/delete/' + id)
        .subscribe(response => {
          resolve(response);
          return response;
        }, error => {
          console.log(error.status);
        });
    });
  }
}
