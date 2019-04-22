import { SellerInsightsService } from './seller-insights.service';
import { environment } from 'src/environments/environment';

describe('SellerInsightsService', () => {
  let service: SellerInsightsService;
  let http;

  beforeEach(() => {
    http = jasmine.createSpyObj(['get']);
    service = new SellerInsightsService(http);
  });

  it('should call the correct url for getDetailedSellerItems', () => {
    const expectedUrl = environment.baseUrl + 'item/seller';

    service.getDetailedSellerItems(1);

    expect(http.get).toHaveBeenCalledWith(expectedUrl);
  });

  it('should call the correct url for getSellerInsightData', () => {
    const itemId = 45;
    const expectedUrl = environment.baseUrl + 'sellerinsight/' + itemId;

    service.getSellerInsightData(itemId);

    expect(http.get).toHaveBeenCalledWith(expectedUrl);
  });

  // it('should call the correct url for getSellerProfitInsightData', () => {
  //   const itemId = 45;
  //   const sellerCost = 9.99;
  //   const expectedUrl = environment.baseUrl + 'sellerinsight/profit/' + itemId + '/' + sellerCost;

  //   service.getSellerProfitInsightData(itemId, sellerCost);

  //   expect(http.get).toHaveBeenCalledWith(expectedUrl);
  // });
});
