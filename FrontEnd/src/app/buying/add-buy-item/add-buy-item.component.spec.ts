import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBuyItemComponent } from './add-buy-item.component';
import { of } from 'rxjs';

describe('AddBuyItemComponent', () => {
  let component: AddBuyItemComponent;
  let dialogRef;
  let restService;

  beforeEach(() => {
    dialogRef = jasmine.createSpyObj(['close']);
    restService = jasmine.createSpyObj(['addWalmartBuyerDetails']);
    restService.addWalmartBuyerDetails.and.returnValue(of());
    component = new AddBuyItemComponent(restService, dialogRef);
  });

  it('close the dialog on cancel', () => {
    component.cancel();

    expect(dialogRef.close).toHaveBeenCalledWith(false);
  });

  it('should call restservice.addWalmartBuyerDetails() on addbuyeritem', () => {
    const buyerItem = { itemId: 1, price: 9.99, userId: 100, walmartUrl: 'testurl' };
    component.buyerItem = buyerItem;

    component.addBuyerItem();

    expect(restService.addWalmartBuyerDetails).toHaveBeenCalledWith(1, buyerItem.price, 100, buyerItem.walmartUrl);
  });
});
