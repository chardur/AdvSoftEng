import { Component, OnInit, ViewChild } from '@angular/core';
import { BuyerItem } from '../models/buyer-item';
import { BuyingService } from './buying.service';
import { MatTableDataSource, MatDialog, MatPaginator, MatSort, MatSnackBar, MatSnackBarConfig } from '@angular/material';
import { ItemHistoryComponent } from './item-history/item-history.component';
import { RESTService } from '../services/rest.service';
import { AddBuyItemComponent } from './add-buy-item/add-buy-item.component';
import {BuyerSearchItem} from '../models/Searched-Items';
import * as introJs from 'intro.js/intro.js';

@Component({
  selector: 'app-buying',
  templateUrl: './buying.component.html',
  styleUrls: ['./buying.component.css']
})
export class BuyingComponent implements OnInit {
  buyerItems: BuyerItem[];
  buyerSearchItems: BuyerSearchItem[];
  displayedColumns: string[] = ['item.name', 'price', 'salePrice', 'Actions'];
  dataSource: MatTableDataSource<any>;

  walmartURL: string;
  walmartBuyerDetailURL: string;
  walmartBuyerDetailPrice: number;
  walmartBuyerDetailQuantity: number;

  intro = introJs();

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private buyingService: BuyingService,
    public rest: RESTService,
    public dialog: MatDialog,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getBuyerItems();
    if (RegExp('multipage', 'gi').test(window.location.search)) {
      this.startIntro();
    }
  }

  private getBuyerItems() {
    const userId = +localStorage.getItem('userId');
    this.buyingService.getSearchItems(userId)
      .subscribe(buyerSearchItems => {
        this.buyerSearchItems = buyerSearchItems;
        this.dataSource = new MatTableDataSource(this.buyerItems);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    });
  }

  openAddWalmartItemDialog() {
    const dialog = this.dialog.open(AddBuyItemComponent, {width: '500px', height: '300px'});

    dialog.afterClosed()
    .subscribe(result => {
      if (result) {
        location.reload();
        this.openSnackBar('New Item Added Successfully');
      }
    });
  }

  openSnackBar(message: string) {
    const config = new MatSnackBarConfig();
    config.duration = 1500;
    this.snackBar.open(message, '', config);
  }


  addWalmartItem(url) {
    if (url != null) {
      this.rest.addWalmartURL(url).subscribe((result) => {
        location.reload();
      }, (err) => {
        console.log(err);
      });
    }
  }

  addWalmartBuyerDetails(quantity, price, userid, url) {
    if (url != null) {
      this.rest.addWalmartBuyerDetails(quantity, price, userid, url).subscribe((result) => {
        location.reload();
      }, (err) => {
        console.log(err);
      });
    }
  }

  startIntro() {
    const options = {
      steps: [
        {
          element: document.querySelector('#gallery'),
          intro: 'You can use the item gallery to quickly view and purchase items in your watch list.',
          disableInteraction: true,
          position: 'right'
        },
        {
          element: document.querySelector('#search'),
          // tslint:disable-next-line:max-line-length
          intro: 'You can search for products and get recommendations using this tool. Click \'Get More Info\' to view the item on the merchant site.',
          disableInteraction: true
        },
        {
          element: document.querySelector('#addItem'),
          // tslint:disable-next-line:max-line-length
          intro: 'Click here to add an item to your watchlist. When you add an item you can set a price target. We\'ll let you know when the target is reached.',
          disableInteraction: true
        },
        {
          element: document.querySelector('#watchList'),
          // tslint:disable-next-line:max-line-length
          intro: 'This is your watch list. You can view your requested price, current price, and historical prices for each item in your list. You can also quickly buy the item, or remove it from your list.',
          disableInteraction: true,
          position: 'right',
        },
        {
          element: document.querySelector('#watchlist'),
          intro: 'That\'s it for now, click done to get started.',
          disableInteraction: true,
          position: 'bottom',
        }
      ]
    };
    this.intro.setOptions(options);
    this.intro.start();
  }

}
