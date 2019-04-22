import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { WalmartItem } from 'src/app/models/walmart-item';
import { SellerItem } from 'src/app/models/seller-item';

@Injectable({
  providedIn: 'root'
})
export class AddSellerItemService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  public GetWalmartItem(itemUrl: string) {
    const apiLocation = 'walmart/url';
    const url = this.baseUrl + apiLocation;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post<WalmartItem>(url, itemUrl, { headers });
  }

  public AddSellerItem(sellerItem: SellerItem) {
    const apiLocation = 'item/seller';
    const url = this.baseUrl + apiLocation;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(url, sellerItem, { headers });
  }
}
