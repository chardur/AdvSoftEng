import { RESTService } from '../services/rest.service';
import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {isNumeric} from 'rxjs/internal-compatibility';
import {TableComponent} from '../shared/table/table.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  @ViewChild(TableComponent) list: TableComponent;

  @Input() addProductData = { id: 0, name: '', quantity: 0 };

  constructor(public rest: RESTService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {}


  addProduct() {
    if (this.addProductData.name.length !== 0 && isNumeric(this.addProductData.quantity)) {
      this.rest.addExampleItem(this.addProductData).subscribe((result) => {
        location.reload();
      }, (err) => {
        console.log(err);
      });
    }
  }
}
