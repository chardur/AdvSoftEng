import { BuyingService } from './buying.service';
import { MatDialog, MatSnackBar, MatSnackBarConfig } from '@angular/material';
import { BuyingComponent } from './buying.component';
import { BuyerItem } from '../models/buyer-item';
import { of } from 'rxjs';
import { BuyerSearchItem } from '../models/Searched-Items';

describe('BuyingComponent', () => {
    let mockBuyingService: any;
    let mockMatDialog: MatDialog;
    let buyingComponent: BuyingComponent;
    let mockSnackBar: MatSnackBar;

    beforeEach(() => {
        mockBuyingService = jasmine.createSpyObj(['getBuyerItems', 'getSearchItems']);
        mockMatDialog = jasmine.createSpyObj(['open']);
        mockSnackBar = jasmine.createSpyObj(['open']);
        const restService = jasmine.createSpyObj(['addWalmartURL']);
        buyingComponent = new BuyingComponent(mockBuyingService, restService, mockMatDialog, mockSnackBar);
    });


    it('should call the BuyingService.getSearchItems() upon init', () => {
        mockBuyingService.getBuyerItems.and.returnValue(of(BuyerItem));
        mockBuyingService.getSearchItems.and.returnValue(of(BuyerSearchItem));
        localStorage.setItem('userId', '2');

        buyingComponent.ngOnInit();

        expect(mockBuyingService.getSearchItems).toHaveBeenCalledWith(2);
    });

    it('should open the snackbar with the correct message and duration', () => {
        const config = new MatSnackBarConfig();
        config.duration = 1500;
        const message = 'test message';

        buyingComponent.openSnackBar(message);

        expect(mockSnackBar.open).toHaveBeenCalledWith(message, '', config);
    });
});
