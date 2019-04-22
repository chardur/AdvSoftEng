import {Component, Input, OnInit, ViewChild} from '@angular/core';
import { BuyingService } from '../../../buying/buying.service';
import {MatTableDataSource, MatDialog, MatPaginator, MatSort, MatBottomSheet, PageEvent} from '@angular/material';
import {BuyerItem} from '../../../models/buyer-item';
import {BuyerSearchItem} from '../../../models/Searched-Items';
import {LargeimgComponent} from './largeimg/largeimg.component';
import {ComparepriceComponent} from './compareprice/compareprice.component';


@Component({
  selector: 'app-table-buyersearch',
  templateUrl: './buyersearch.component.html',
  styleUrls: ['./buyersearch.component.css']
})
export class BuyersearchComponent implements OnInit {

  pageSizeSelection: number = parseInt(localStorage.getItem('buyerSearchPageSizeSelection'), 10);
  buyerSearchItems: BuyerSearchItem[];
  displayedColumns: string[] = ['name', 'thumbnail', 'price', 'salePrice', 'Actions'];
  dataSource: MatTableDataSource<any>;
  searchTerm: 'Enter Your Search...';
  searchedItems = Array('TVs', 'Books', 'TShirts', 'Dresses', 'Video Games');
  searchedItem: string;
  searchStarted = false;
  largeImg: string;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @Input() searchItem: string;
  constructor(public buyingService: BuyingService,
              public dialog: MatDialog, private bottomSheet: MatBottomSheet) {
  }

  ngOnInit() {
    this.searchedItem = this.searchedItems[Math.floor(Math.random() * this.searchedItems.length)];
    this.getBuyerSearchItems(this.searchedItem);

  }

  public getBuyerSearchItems(searchterm) {
    this.buyingService.getSearchItems(searchterm)
      .subscribe(buyerSearchItems => {
        this.buyerSearchItems = buyerSearchItems;
        this.dataSource = new MatTableDataSource(this.buyerSearchItems);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.buyingService.mySearchItems = this.buyerSearchItems;
      });
  }

  openBottomSheetImage(itemName, largeimg): void {
    this.buyingService.itemName = itemName;
    this.buyingService.largeImg = largeimg;
    this.bottomSheet.open(LargeimgComponent);
  }

  openBottomSheetSearch(itemName): void {
    this.buyingService.itemName = itemName;
    this.bottomSheet.open(ComparepriceComponent);
  }

  handlePage($event: PageEvent) {
    localStorage.setItem('buyerSearchPageSizeSelection', this.paginator.pageSize.toString());
    this.pageSizeSelection = parseInt(localStorage.getItem('buyerSearchPageSizeSelection'), 10);
  }
}
