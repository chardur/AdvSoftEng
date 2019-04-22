import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { SellerItem } from 'src/app/models/seller-item';
import { AddSellerItemService } from './add-seller-item.service';

@Component({
  selector: 'app-add-seller-item',
  templateUrl: './add-seller-item.component.html',
  styleUrls: ['./add-seller-item.component.css']
})
export class AddSellerItemComponent implements OnInit {
  public sellerItem = new SellerItem();
  public itemUrl = '';
  constructor(private dialogRef: MatDialogRef<AddSellerItemComponent>,
    private service: AddSellerItemService) { }

  ngOnInit() {
    this.sellerItem.userId = +localStorage.getItem('userId');
  }

  cancel() {
    this.dialogRef.close(false);
  }

  addSellerItem() {
    this.service.GetWalmartItem(this.itemUrl)
    .subscribe(walmartItem => {
      this.sellerItem.itemId = walmartItem.linkedItemId;
      this.service.AddSellerItem(this.sellerItem)
      .subscribe(() => {
        this.dialogRef.close(true);
        this.reload();
      });
    });
    // this.dialogRef.close(false);
  }

  reload() {
    location.reload();
  }
}
