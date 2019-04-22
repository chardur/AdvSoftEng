import {Component, OnInit, ViewChild} from '@angular/core';
import { BuyingService } from '../../../../buying/buying.service';
import {MatBottomSheet, MatDialog, MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {BuyerSearchItem} from '../../../../models/Searched-Items';

@Component({
  selector: 'app-compareprice',
  templateUrl: './compareprice.component.html',
  styleUrls: ['./compareprice.component.css']
})
export class ComparepriceComponent implements OnInit {
  products = this.buyingService.mySearchItems;
  bestbuyTable: any = [];
  walmartTable: any = [];
  temp: any = [];

  columns = [
    { prop: 'name' },
    { name: 'salePrice' },
    { name: 'thumbnailImage' }
  ];
  itemName: string;

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(public buyingService: BuyingService,
              public dialog: MatDialog, private bottomSheet: MatBottomSheet) { }

  ngOnInit() {
    this.itemName = this.buyingService.itemName;
    console.log(this.buyingService.mySearchItems);
    this.seperateTableData();
  }

  private seperateTableData() {
    let b = 0;
    let w = 0;
    this.walmartTable = [];
    this.bestbuyTable = [];
    for (let i = 0; i < this.products.length; i++) {
      if (this.products[i].itemId === 0) {
        this.bestbuyTable[b] = this.products[i];
        this.bestbuyTable[b].name = this.bestbuyTable[b].name.substring(0, 35) + '...';
        if (this.bestbuyTable[b].thumbnailImage.substring(0, 3) !== '<im') {
          this.bestbuyTable[b].thumbnailImage = '<img src = "' + this.bestbuyTable[b].thumbnailImage + '" >';
        }
        b++;
      } else {
        this.walmartTable[w] = this.products[i];
        this.walmartTable[w].name = this.walmartTable[w].name.substring(0, 35) + '...';
        if (this.walmartTable[w].thumbnailImage.substring(0, 3) !== '<im') {
          this.walmartTable[w].thumbnailImage = '<img src = "' + this.products[i].thumbnailImage + '" >';
        }
        w++;
      }
      console.log(this.products[i]); // use i instead of 0
    }
  }

  public updateFilter(event) {
    const val = event.target.value.toLowerCase();

    // filter our data
    const tmp = this.temp.filter(function(d) {
      return d.name.toLowerCase().indexOf(val) !== -1 || !val;
    });

    // update the rows
    this.products = tmp;
    // Whenever the filter changes, always go back to the first page
    // this.table.offset = 0;
  }
}
