import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ItemHistory } from '../../models/item-history';
import { environment } from '../../../environments/environment';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Chart } from 'chart.js';
import { BuyerItem } from 'src/app/models/buyer-item';

@Component({
  selector: 'app-item-history',
  templateUrl: './item-history.component.html',
  styleUrls: ['./item-history.component.css']
})
export class ItemHistoryComponent implements OnInit {
  itemHistories: ItemHistory[];
  baseUrl = environment.baseUrl;
  chartData = [];
  chartLabels = [];
  chartOptions = {
    responsive: true
  };
  chart = [];

  constructor(private http: HttpClient,
  @Inject(MAT_DIALOG_DATA) public data: BuyerItem) { }

  ngOnInit() {
    this.getItemHistories();
  }

  getItemHistories() {
    const apiLocation = this.baseUrl + 'itemhistory/' + this.data.itemId;
    this.http.get<ItemHistory[]>(apiLocation)
    .subscribe(histories => {
      this.sortHistoriesByDate(histories);
      this.populateChartArrays();
    });
  }

  sortHistoriesByDate(histories: ItemHistory[]) {
    function compare(a, b) {
      if (a.date < b.date) {
        return -1;
      }
      if (a.date > b.date) {
        return 1;
      }
      return 0;
    }
    this.itemHistories = histories.sort();
  }

  populateChartArrays() {
    this.chartData = [this.itemHistories.length];
    this.chartLabels = [this.itemHistories.length];
    for (let i = 0; i < this.itemHistories.length; i++) {
      this.chartData[i] = this.itemHistories[i].price;
      const date = new Date(this.itemHistories[i].date);
      const dateString = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
      this.chartLabels[i] = dateString;
    }

    this.chart = new Chart('canvas', {
      type: 'line',
      data: {
        labels: this.chartLabels,
        datasets: [
          {
            data: this.chartData,
            borderColor: '#3cba9f',
            fill: false
          }
        ]
      },
      options: {
        legend: {
          display: false
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              callback: function(value, index, values) {
                const twoPlacedFloat = parseFloat(value).toFixed(2);
                // tslint:disable-next-line:radix
                if (parseFloat(twoPlacedFloat) >= 1000) {
                  return '$' + twoPlacedFloat.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                } else {
                  return '$' + twoPlacedFloat;
                }
              }
            }
          }],
        }
      }
    });
  }
}
