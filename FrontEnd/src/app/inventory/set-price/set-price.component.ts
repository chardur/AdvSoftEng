import {Component, Input, OnInit, SimpleChanges, OnChanges} from '@angular/core';
import {RESTService} from '../../services/rest.service';

@Component({
  selector: 'app-set-price',
  templateUrl: './set-price.component.html',
  styleUrls: ['./set-price.component.css']
})
export class SetPriceComponent implements OnInit, OnChanges {

  @Input() itemId: number;
  // TODO use a real userID once authentication is setup
  @Input() setPriceTargetData = {itemId: this.itemId, price: 1, userId: +localStorage.getItem('userId'), sellerCost: 1};
  products: any = [];
  isSellerItem: boolean;

  constructor(public rest: RESTService) { }

  ngOnInit() {
    this.getItemData();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.itemId) {
      this.getItemData();
    }
  }

  setPriceTarget() {
    if (this.setPriceTargetData.price != null && this.setPriceTargetData.sellerCost != null) {
      this.setPriceTargetData.itemId = this.itemId;
      this.rest.updateSellerItem(this.itemId, this.setPriceTargetData).subscribe((result) => {
        // location.reload();
        this.getItemData();
      }, (err) => {
        console.log(err);
      });
    }
  }

  getItemData() {
    this.products = [];
    this.setPriceTargetData.price = null;
    this.setPriceTargetData.sellerCost = null;
    this.isSellerItem = false;
    this.rest.getSellerItems().subscribe((data: {}) => {
      this.products = data;
      // console.log(data);
      for (const product of this.products) {
        if (product.itemId === this.itemId) {
          this.setPriceTargetData.price = product.price;
          this.setPriceTargetData.sellerCost = product.sellerCost;
          this.isSellerItem = true;
        }
      }
    });
  }
}
