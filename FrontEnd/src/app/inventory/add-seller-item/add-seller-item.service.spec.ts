import { TestBed, inject } from '@angular/core/testing';

import { AddSellerItemService } from './add-seller-item.service';
import { HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { SellerItem } from 'src/app/models/seller-item';

describe('AddSellerItemService', () => {
  let service: AddSellerItemService;
  let http;
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  beforeEach(() => {
    http = jasmine.createSpyObj(['post']);
    service = new AddSellerItemService(http);
  });

  // it('should call the endpoint correctly for GetWalmartItem', () => {
  //   const walmartUrl = 'testUrl';

  //   service.GetWalmartItem(walmartUrl);

  //   expect(http.post).toHaveBeenCalled();
  // });
});
