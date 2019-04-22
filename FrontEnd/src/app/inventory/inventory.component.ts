import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TableComponent } from '../shared/table/table.component';
import { isNumeric } from 'rxjs/internal-compatibility';
import { RESTService } from '../services/rest.service';
import { MatDialog } from '@angular/material';
import { AddSellerItemComponent } from './add-seller-item/add-seller-item.component';
import * as introJs from 'intro.js/intro.js';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { SellerItem } from '../models/seller-item';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {

  @ViewChild(TableComponent) list: TableComponent;
  @Input() addProductData = { id: 0, name: '', quantity: 0 };
  // item selector
  selectedItem: number;
  products: any = [];
  temp: any = [];
  divStatus1 = false;
  divStatus2 = false;
  divStatus3 = false;
  divStatus4 = false;
  divStatus5 = false;
  divStatus6 = false;
  intro = introJs();

  constructor(public rest: RESTService,
    private route: ActivatedRoute,
    private router: Router,
    public dialog: MatDialog,
    private http: HttpClient) {
  }

  ngOnInit() {
    this.getProducts();
    if (RegExp('multipage', 'gi').test(window.location.search)) {
      this.startIntro();
    }
  }

  public openAddItemDialog() {
    this.dialog.open(AddSellerItemComponent, { width: '500px' });
  }

  // item selection tool
  getProducts() {
    this.products = [];
    this.getSellerItems()
      .subscribe((data: {}) => {
        // console.log(data);
        this.products = data;
        this.temp = [...this.products];
        //console.log("This is products");
        //console.log(this.products);
        if (this.products.length !== 0) {
          if (localStorage.getItem('inventoryPageSelectedItem') == null) {
            localStorage.setItem('inventoryPageSelectedItem', this.products[0].id.toString());
          }
          this.selectedItem = parseInt(localStorage.getItem('inventoryPageSelectedItem'), 10);
        }
      });
  }

  getSellerItems() {
    const userId = +localStorage.getItem('userId');
    const apiLocation = environment.baseUrl + 'item/seller/' + userId + '/details';

    return this.http.get<SellerItem[]>(apiLocation);
  }

  saveSelectedValue(value: any) {
    localStorage.setItem('inventoryPageSelectedItem', value.toString());
    this.selectedItem = parseInt(localStorage.getItem('inventoryPageSelectedItem'), 10);
  }

  startIntro() {
    const options = {
      steps: [
        {
          element: document.querySelector('#itemSelector'),
          intro: 'You can use the item selector to refresh all of the tools on this page with a specific item.',
          disableInteraction: true
        },
        {
          element: document.querySelector('#addItem'),
          intro: 'You can add an item to your inventory using this button',
          disableInteraction: true
        },
        {
          element: document.querySelectorAll('.row')[1],
          intro: 'You can use the tools below to get Profit/Revenue forecast, Historical prices/forecast, set price targets, and more!',
          disableInteraction: true
        },
        {
          element: document.querySelectorAll('.row')[1],
          intro: 'If you click on a tool, it will expand for a better view.',
          disableInteraction: true,
          position: 'right',
        },
        {
          element: document.querySelectorAll('.row')[1],
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
