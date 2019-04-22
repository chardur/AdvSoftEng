import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { SellerInsightDatapoint } from 'src/app/models/seller-insight-datapoint';
import { DetailedSellerItem } from 'src/app/models/detailed-seller-item';
import { SellerItem } from 'src/app/models/seller-item';

@Injectable({
  providedIn: 'root'
})
export class SellerInsightsService {

  constructor(private http: HttpClient) { }

  getDetailedSellerItems(sellerId: number) {
    const apiLocation = environment.baseUrl + 'item/seller';
    return this.http.get<SellerItem[]>(apiLocation);
  }

  getSellerInsightData(itemId: number) {
    const apiLocation = environment.baseUrl + 'sellerinsight/' + itemId;
    return this.http.get<SellerInsightDatapoint[]>(apiLocation);
  }

  getSellerProfitInsightData(itemId: number, sellerCost: number) {
    const apiLocation = environment.baseUrl + 'sellerinsight/profit/' + itemId + '/' + sellerCost;
    return this.http.get<SellerInsightDatapoint[]>(apiLocation);
  }
}
