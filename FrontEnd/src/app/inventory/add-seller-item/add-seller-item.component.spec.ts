import { AddSellerItemComponent } from './add-seller-item.component';
import { WalmartItem } from 'src/app/models/walmart-item';
import { of } from 'rxjs';
import { SellerItem } from 'src/app/models/seller-item';

describe('AddSellerItemComponent', () => {
  let component: AddSellerItemComponent;
  let dialogRef;
  let service;
  const walmartItem = {itemId: 50, name: 'TestItem', linkedItemId:  7};


  beforeEach(() => {
    dialogRef = jasmine.createSpyObj(['close']);
    service = jasmine.createSpyObj(['GetWalmartItem', 'AddSellerItem']);
    service.GetWalmartItem.and.returnValue(of(walmartItem));
    service.AddSellerItem.and.returnValue(of(SellerItem));

    component = new AddSellerItemComponent(dialogRef, service);
  });

  it('should set user id on ngOnInit', () => {
    component.ngOnInit();

    expect(component.sellerItem.userId).toBe(2);
  });

  it('should close the dialogref on cancel', () => {
    component.cancel();

    expect(dialogRef.close).toHaveBeenCalledWith(false);
  });

  it('should call two endpoints and close the dialogref if and item is added', () => {
    const itemUrl = 'testurl';
    component.itemUrl = itemUrl;
    const sellerItem = new SellerItem();
    sellerItem.itemId = walmartItem.linkedItemId;
    spyOn(component, "reload").and.callFake(function(){});
    component.addSellerItem();

    expect(service.GetWalmartItem).toHaveBeenCalledWith(itemUrl);
    expect(service.AddSellerItem).toHaveBeenCalledWith(sellerItem);
    expect(dialogRef.close).toHaveBeenCalledWith(true);
  });
});
