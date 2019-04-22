import { Component, OnInit } from '@angular/core';
import {RESTService} from '../../services/rest.service';

@Component({
  selector: 'app-top5',
  templateUrl: './top5.component.html',
  styleUrls: ['./top5.component.css']
})
export class Top5Component implements OnInit {
  top5: any = [];

  constructor(public rest: RESTService) { }

  ngOnInit() {
    this.getTop5List();
  }

  getTop5List() {
    this.rest.getTop5().subscribe((data: {}) => {
    console.log(data);
    this.top5 = data;
  });
  }
}
