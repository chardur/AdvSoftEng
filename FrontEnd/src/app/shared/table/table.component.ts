import {Component, OnInit, ViewChild} from '@angular/core';
import {RESTService} from '../../services/rest.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})

export class TableComponent implements OnInit {

  products: any = [];
  temp: any = [];

  columns = [
    { prop: 'id' },
    { name: 'name' },
    { name: 'quantity' }
  ];

  constructor(public rest: RESTService) { }

  ngOnInit() {
    this.getProducts();
  }

  getProducts() {
    this.products = [];
    this.rest.getExampleItems().subscribe((data: {}) => {
      console.log(data);
      this.products = data;
      this.temp = [...this.products];
    });

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



