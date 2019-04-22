import {Component, Input, OnInit} from '@angular/core';
import {MatBottomSheetRef} from '@angular/material';
import { BuyingService } from '../../../../buying/buying.service';

@Component({
  selector: 'app-largeimg',
  templateUrl: './largeimg.component.html',
  styleUrls: ['./largeimg.component.css']
})
export class LargeimgComponent implements OnInit {
  largeImg: string;
  itemName: string;
  constructor(public buyingService: BuyingService, private bottomSheetRef: MatBottomSheetRef<LargeimgComponent>) {
  }

  ngOnInit() {
    this.largeImg = this.buyingService.largeImg;
    this.itemName = this.buyingService.itemName;
  }

  openLink(event: MouseEvent): void {
    this.bottomSheetRef.dismiss();
    event.preventDefault();
  }

}

