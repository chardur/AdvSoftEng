import { TestBed, inject } from '@angular/core/testing';

import { BuyingService } from './buying.service';
import { environment } from '../../environments/environment';

describe('BuyingService', () => {
  it('should call the correct http endpoint', () => {
    const http = jasmine.createSpyObj(['get']);
    const buyingServce = new BuyingService(http);
    const expectedEndPoint = environment.baseUrl + 'item/buyer/9/details';

    buyingServce.getBuyerItems(9);

    expect(http.get).toHaveBeenCalledWith(expectedEndPoint);
  });
});
