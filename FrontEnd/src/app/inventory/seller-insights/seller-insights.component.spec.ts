import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerInsightsComponent } from './seller-insights.component';
import { SellerItem } from 'src/app/models/seller-item';
import { of } from 'rxjs';
import { assert } from '@tensorflow/tfjs-core/dist/util';

/*describe('SellerInsightsComponent', () => {
  let component: SellerInsightsComponent;
  let service;

  beforeEach(() => {
    service = jasmine.createSpyObj(['getDetailedSellerItems']);
    service.getDetailedSellerItems.and.returnValue(of(SellerItem));

    component = new SellerInsightsComponent(service);
  });

  it('should call getDetailedSellerItems onInit', () => {
    component.ngOnInit();

    expect(service.getDetailedSellerItems).toHaveBeenCalled();
  });
});*/
