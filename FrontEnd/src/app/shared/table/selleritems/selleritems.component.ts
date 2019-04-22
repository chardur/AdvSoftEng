import { Component, OnInit } from '@angular/core';
import {RESTService} from '../../../services/rest.service';

@Component({
  selector: 'app-selleritems',
  templateUrl: './selleritems.component.html',
  styleUrls: ['./selleritems.component.css']
})
export class SelleritemsComponent implements OnInit {

  selleritems: any = [];
  temp: any = [];

  columns = [
    { prop: 'id' }
  ];

  constructor(public rest: RESTService) { }

  ngOnInit() {
    this.getItems();
  }


  getItems() {
    this.selleritems = [];
    this.rest.getSellerItems().subscribe((data: {}) => {
      console.log(data);
      this.selleritems = data;
      this.temp = [...this.selleritems];
    });

  }

  public updateFilter(event) {
    const val = event.target.value.toLowerCase();

    // filter our data
    const tmp = this.temp.filter(function(d) {
      return d.name.toLowerCase().indexOf(val) !== -1 || !val;
    });

    // update the rows
    this.selleritems = tmp;
    // Whenever the filter changes, always go back to the first page
    // this.table.offset = 0;
  }
}
