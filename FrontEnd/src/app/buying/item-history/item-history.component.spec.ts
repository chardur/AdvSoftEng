// import { async, ComponentFixture, TestBed } from '@angular/core/testing';

// import { ItemHistoryComponent } from './item-history.component';
// import { HttpClient } from '@angular/common/http';
// import { MatDialog, MatDialogRef } from '@angular/material';
// import { BuyerItem } from '../../models/buyer-item';
// import { environment } from '../../../environments/environment';
// import { ItemHistory } from '../../models/item-history';
// import { of } from 'rxjs';

// describe('ItemHistoryComponent', () => {
//   let mockHttpClient: any;
//   let itemHistoryComponent: ItemHistoryComponent;
//   const data = new BuyerItem();

//   beforeEach(() => {
//     mockHttpClient = jasmine.createSpyObj(['get']);
//     data.itemId = 7;
//     itemHistoryComponent = new ItemHistoryComponent(mockHttpClient, data);
//   });

//   it('should call the correct endpoint on intialization', () => {
//     const expectedEndpoint = environment.baseUrl + 'itemhistory/' + data.itemId;
//     mockHttpClient.get.and.returnValue(of(ItemHistory));

//     itemHistoryComponent.ngOnInit();

//     expect(mockHttpClient.get).toHaveBeenCalledWith(expectedEndpoint);
//   });
// });
