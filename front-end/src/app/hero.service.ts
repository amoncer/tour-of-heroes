import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Hero } from './hero';
import { HEROES } from './mock-heroes';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  private heroesUrl = "http://localhost:8080/heroes";
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private messageService: MessageService,
              private http: HttpClient) { }

  getHeroes(): Observable<Hero[]>{
    return this.http.get<Hero[]>(this.heroesUrl)
    .pipe(
      catchError(this.handleError<Hero[]>('getHeroes', []))
    );
  }

  getHero(id: number): Observable<Hero> {
    return this.http.get<Hero>(`${this.heroesUrl}/${id}`)
    .pipe(
      catchError(this.handleError<Hero>(`getHero id=${id}`))
    );
  }

  updateHero(hero: Hero): Observable<any> {
    return this.http.put(`${this.heroesUrl}/${hero.id}`, hero, this.httpOptions)
    .pipe(
      catchError(this.handleError<Hero>(`updateHero id=${hero.id}`))
    );
  }

  addHero(hero: Hero): Observable<Hero>{
    return this.http.post<Hero>(`${this.heroesUrl}`, hero, this.httpOptions)
    .pipe(
      catchError(this.handleError<Hero>(`addHero`))
    );
  }

  deleteHero(hero: Hero): Observable<any> {
    return this.http.delete(`${this.heroesUrl}/${hero.id}`, this.httpOptions)
    .pipe(
      catchError(this.handleError<Hero>(`deleteHero`))
    );
  }

  searchHeroes(term: string): Observable<Hero[]> {
    if(!term.trim()){
      return of([]);
    }

    return this.http.get<Hero[]>(`${this.heroesUrl}/search?name=${term}`)
    .pipe(
      catchError(this.handleError<Hero[]>(`searchHeroes`, []))
    );
  }

  log(message: string): void {
    this.messageService.add(message);
  }

  private handleError<T>(operation = 'operation', result?: T){
    return (error: any): Observable<T> => {

      console.log(error);

      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    }
  }
}
