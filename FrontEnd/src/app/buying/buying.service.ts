import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { BuyerItem } from '../models/buyer-item';
import {BuyerSearchItem} from '../models/Searched-Items';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuyingService {
  baseUrl = environment.baseUrl;
  largeImg: string;
  itemName: string;
  mySearchItems: BuyerSearchItem[];

  constructor(private http: HttpClient) { }

  public getBuyerItems(buyerId: number) {
    const apiLocation = this.baseUrl + 'item/buyer/' + buyerId + '/details';
    return this.http.get<BuyerItem[]>(apiLocation);
  }

  public getSearchItems(searched) {
    const apiLocation = this.baseUrl + 'search/' + searched;
    return this.http.get<BuyerSearchItem[]>(apiLocation);
  }

  public deleteItem(buyerItemId: number) {
    const apiLocation = environment.baseUrl + 'item/buyer/' + buyerItemId;
    return this.http.delete(apiLocation);
  }
}
