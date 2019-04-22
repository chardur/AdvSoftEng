import { Component, OnInit } from '@angular/core';
import {RESTService} from '../../../services/rest.service';

@Component({
  selector: 'app-table-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: any = [];
  temp: any = [];

  columns = [
    { prop: 'id' }
  ];

  constructor(public rest: RESTService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.users = [];
    this.rest.getUsers().subscribe((data: {}) => {
      console.log(data);
      this.users = data;
      this.temp = [...this.users];
    });

  }


  public updateFilter(event) {
    const val = event.target.value.toLowerCase();

    // filter our data
    const tmp = this.temp.filter(function(d) {
      return d.name.toLowerCase().indexOf(val) !== -1 || !val;
    });

    // update the rows
    this.users = tmp;
    // Whenever the filter changes, always go back to the first page
    // this.table.offset = 0;
  }

}
