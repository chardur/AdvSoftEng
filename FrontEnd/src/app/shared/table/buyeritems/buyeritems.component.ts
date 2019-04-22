import { Component, OnInit, ViewChild } from '@angular/core';
import { BuyerItem } from '../../../models/buyer-item';
import { BuyingService } from '../../../buying/buying.service';
import {MatTableDataSource, MatDialog, MatPaginator, MatSort, PageEvent} from '@angular/material';
import { ItemHistoryComponent } from '../../../buying/item-history/item-history.component';


@Component({
  selector: 'app-table-buyeritems',
  templateUrl: './buyeritems.component.html',
  styleUrls: ['./buyeritems.component.css']
})
export class BuyeritemsComponent implements OnInit {
  pageSizeSelection: number = parseInt(localStorage.getItem("buyerItemsPageSizeSelection"), 10);
  buyerItems: BuyerItem[];
  displayedColumns: string[] = ['item.name', 'price', 'salePrice', 'Actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private buyingService: BuyingService,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.getBuyerItems();
  }

  private getBuyerItems() {
    const userId = +localStorage.getItem('userId');
    this.buyingService.getBuyerItems(userId)
      .subscribe(buyerItems => {
        this.buyerItems = buyerItems;
        this.dataSource = new MatTableDataSource(this.buyerItems);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      });
  }

  openPriceHistoryDialog(item: any) {
    this.dialog.open(ItemHistoryComponent, { data: item, height: '500px', width: '800px' });
  }

  removeBuyerItem(buyerItemId: number) {
    this.buyingService.deleteItem(buyerItemId)
    .subscribe(() => {
      this.getBuyerItems();
    });
  }

  handlePage($event: PageEvent) {
    localStorage.setItem("buyerItemsPageSizeSelection", this.paginator.pageSize.toString());
    this.pageSizeSelection = parseInt(localStorage.getItem("buyerItemsPageSizeSelection"), 10);
  }

}
