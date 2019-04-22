import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import {BuyerItem} from '../models/buyer-item';

const endpoint = 'http://api.shmoozed.com/';
const itempath = 'example/item';
const buyerpath = 'item/buyer';
const alerts = '/alert/';
const sellerpath = 'item/seller';
const userpath = 'user';
const walmartURL = 'walmart/url';
const walmartURLDetails = 'walmart/urlbuyerdetails?quantity=';
const itemPriceHistory = 'itemhistory/';
const top5 = 'item/top';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})

export class RESTService {

  constructor(private http: HttpClient) {
  }

  private extractData(res: Response) {
    const body = res;
    return body || {};
  }

  //////////////////
  // Buyer Rest    //
  //////////////////
  getBuyerItems(): Observable<any> {
    return this.http.get(endpoint + buyerpath).pipe(
      map(this.extractData));
  }

  getBuyerItem(id): Observable<any> {
    return this.http.get(endpoint + buyerpath + id).pipe(
      map(this.extractData));
  }

  addBuyerItems(product): Observable<any> {
    console.log(product);
    return this.http.post<any>(endpoint + buyerpath, JSON.stringify(product), httpOptions).pipe(
      tap(_ => console.log(`added item w/ id=${product.id}`)),
      catchError(this.handleError<any>('addItem'))
    );
  }

  updateBuyerItems(id, product): Observable<any> {
    return this.http.put(endpoint + buyerpath + '/' + id, JSON.stringify(product), httpOptions).pipe(
      tap(_ => console.log(`updated item id=${id}`)),
      catchError(this.handleError<any>('updateItem'))
    );
  }

  deleteBuyerItems(id): Observable<any> {
    return this.http.delete<any>(endpoint + buyerpath + '/' + id, httpOptions).pipe(
      tap(_ => console.log(`deleted product id=${id}`)),
      catchError(this.handleError<any>('deleteItem'))
    );
  }

  ///////////////////////
  // End Buyer        //
  /////////////////////

  ///////////////////
  // walmart REST   //
  //////////////////
  addWalmartURL(url): Observable<any> {
    console.log(url);
    return this.http.post<any>(endpoint + walmartURL, JSON.stringify(url), httpOptions).pipe(
      tap(_ => console.log(`added item w/ url=${url}`)),
      catchError(this.handleError<any>('addItem'))
    );
  }

  addWalmartBuyerDetails(quantity, price, userid, url): Observable<any> {
    console.log(url);
    // tslint:disable-next-line:max-line-length
    return this.http.post<any>(endpoint + walmartURLDetails + quantity + '&price=' + price + '&userId=' + userid, JSON.stringify(url), httpOptions).pipe(
      tap(_ => console.log(`added item w/ url=${url}`)),
      catchError(this.handleError<any>('addItem'))
    );
  }
  //////////////////
  // End walmart  //
  //////////////////

  //////////////////
  // Seller REST   //
  //////////////////
  getSellerItems(): Observable<any> {
    return this.http.get(endpoint + sellerpath).pipe(
      map(this.extractData));
  }

  getSellerItem(id): Observable<any> {
    return this.http.get(endpoint + sellerpath + id).pipe(
      map(this.extractData));
  }

  addSellerItem(product): Observable<any> {
    console.log(product);
    return this.http.post<any>(endpoint + sellerpath, JSON.stringify(product), httpOptions).pipe(
      tap(_ => console.log(`added item w/ id=${product.id}`)),
      catchError(this.handleError<any>('addItem'))
    );
  }

  updateSellerItem(id, product): Observable<any> {
    return this.http.post(endpoint + sellerpath + '/update/' + id, JSON.stringify(product), httpOptions).pipe(
      tap(_ => console.log(`updated product id=${id}`)),
      catchError(this.handleError<any>('updateItem'))
    );
  }

  deleteSellerItem(id): Observable<any> {
    return this.http.delete<any>(endpoint + userpath + '/' + id, httpOptions).pipe(
      tap(_ => console.log(`deleted product id=${id}`)),
      catchError(this.handleError<any>('deleteItem'))
    );
  }


  getItemPriceHistory(id): Observable<any> {
    return this.http.get(endpoint + itemPriceHistory + id).pipe(
      map(this.extractData));
  }

  getTop5(): Observable<any> {
    return this.http.get(endpoint + top5).pipe(

      map(this.extractData));
  }

  ///////////////////////
  // END Seller       //
  //////////////////////

  ///////////////////////
  // Buyer Alerts     //
  //////////////////////
  getBuyerAlerts(id): Observable<any> {
    return this.http.get(endpoint + buyerpath + alerts + id).pipe(
      map(this.extractData));
  }
  ///////////////////////
  // END Buyer Alerts  //
  //////////////////////

  //////////////////
  // User REST    //
  //////////////////
  getUsers(): Observable<any> {
    return this.http.get(endpoint + userpath).pipe(
      map(this.extractData));
  }

  getUser(id): Observable<any> {
    return this.http.get(endpoint + userpath + id).pipe(
      map(this.extractData));
  }

  addUser(product): Observable<any> {
    console.log(product);
    return this.http.post<any>(endpoint + userpath, JSON.stringify(product), httpOptions).pipe(
      tap(_ => console.log(`added item w/ id=${product.id}`)),
      catchError(this.handleError<any>('addItem'))
    );
  }

  updateUser(id, product): Observable<any> {
    return this.http.put(endpoint + userpath + '/' + id, JSON.stringify(product), httpOptions).pipe(
      tap(_ => console.log(`updated product id=${id}`)),
      catchError(this.handleError<any>('updateItem'))
    );
  }

  deleteUser(id): Observable<any> {
    return this.http.delete<any>(endpoint + userpath + '/' + id, httpOptions).pipe(
      tap(_ => console.log(`deleted product id=${id}`)),
      catchError(this.handleError<any>('deleteItem'))
    );
  }

  ///////////////////////
  // END User         //
  //////////////////////
  getExampleItems(): Observable<any> {
    return this.http.get(endpoint + itempath).pipe(
      map(this.extractData));
  }

  getExampleItem(id): Observable<any> {
    return this.http.get(endpoint + itempath + id).pipe(
      map(this.extractData));
  }

  addExampleItem(product): Observable<any> {
    console.log(product);
    return this.http.post<any>(endpoint + itempath, JSON.stringify(product), httpOptions).pipe(
      tap(_ => console.log(`added item w/ id=${product.id}`)),
      catchError(this.handleError<any>('addItem'))
    );
  }

  updateExampleItem(id, product): Observable<any> {
    return this.http.put(endpoint + itempath + '/' + id, JSON.stringify(product), httpOptions).pipe(
      tap(_ => console.log(`updated product id=${id}`)),
      catchError(this.handleError<any>('updateItem'))
    );
  }

  deleteExampleItem(id): Observable<any> {
    return this.http.delete<any>(endpoint + itempath + '/' + id, httpOptions).pipe(
      tap(_ => console.log(`deleted product id=${id}`)),
      catchError(this.handleError<any>('deleteItem'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}


