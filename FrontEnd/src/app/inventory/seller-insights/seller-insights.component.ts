import { Component, OnInit, Input, ViewChild, ElementRef, AfterViewInit, OnChanges, SimpleChanges } from '@angular/core';
import { Chart } from 'chart.js';
import { SellerInsightsService } from './seller-insights.service';
import { SellerInsightDatapoint } from 'src/app/models/seller-insight-datapoint';
import { DetailedSellerItem } from 'src/app/models/detailed-seller-item';
import { SellerItem } from 'src/app/models/seller-item';

@Component({
  selector: 'app-seller-insights',
  templateUrl: './seller-insights.component.html',
  styleUrls: ['./seller-insights.component.css']
})
export class SellerInsightsComponent implements OnInit, AfterViewInit, OnChanges {
  chart: Chart = [];
  sellerInsights: SellerInsightDatapoint[];
  chartLabels = [];
  chartData = [];
  sellerItems: SellerItem[];
  profitForecast = false;
  chartHasData = true;

  @Input() itemId: number;
  @ViewChild('canvas') canvas: ElementRef;

  constructor(private service: SellerInsightsService) { }

  ngOnInit() {
    const userId = +localStorage.getItem('userId');
    this.service.getDetailedSellerItems(userId)
    .subscribe(items => {
      this.sellerItems = items;
      this.getChartData();
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.itemId) {
      this.getChartData();
    }
  }

  getChartData() {
    const item = this.getSellerItem(this.itemId);
    if (item !== undefined && item.sellerCost) {
      this.profitForecast = true;
      this.getProfitForecast(item.sellerCost);
    } else {
      this.profitForecast = false;
      this.getSellerInsightData();
    }
  }

  ngAfterViewInit(): void {
    this.createChart();
    this.getSellerInsightData();
  }

  getProfitForecast(sellerCost: number) {
    this.service.getSellerProfitInsightData(this.itemId, sellerCost)
    .subscribe(datapoints => {
      if (datapoints.length > 0) {
        this.publishChartData(datapoints);
      } else {
        this.getSellerInsightData();
      }
    });
  }

  private publishChartData(datapoints: SellerInsightDatapoint[]) {
    this.chartHasData = datapoints.length > 0;
    this.sellerInsights = this.sortDataPointsByPrice(datapoints);
    this.chartData = [];
    this.chartLabels = [];
    this.sellerInsights.forEach(element => {
      this.chartLabels.push(element.demandPrice.toString());
      this.chartData.push(element.revenue.toString());
    });
    this.updateChart();
  }

  getSellerInsightData() {
    this.service.getSellerInsightData(this.itemId)
      .subscribe(datapoints => {
        this.publishChartData(datapoints);
      });
  }

  compareSellerInsights(a: SellerInsightDatapoint, b: SellerInsightDatapoint) {
    if (a.demandPrice < b.demandPrice) {
      return -1;
    }
    if (a.demandPrice > b.demandPrice) {
      return 1;
    }
    return 0;
  }

  sortDataPointsByPrice(datapoints: SellerInsightDatapoint[]) {
    return datapoints.sort(this.compareSellerInsights);
  }

  getSellerItem(itemId: number) {
    if (this.sellerItems === undefined) {
      return;
    }
    return this.sellerItems.find(x => x.itemId === itemId);
  }

  updateChart() {
    const axisLabel = this.profitForecast ? 'Profit' : 'Revenue';
    this.chart.data = {
      labels: this.chartLabels,
      datasets: [
        {
          data: this.chartData,
          borderColor: '#3cba9f',
          fill: false,
          label: axisLabel
        }
      ]
    };
    this.chart.update();
  }

  createChart() {
    this.chart = new Chart(this.canvas.nativeElement.getContext('2d'), {
      type: 'line',
      data: {
        labels: this.chartLabels,
        datasets: [
          {
            data: this.chartData,
            borderColor: '#3cba9f',
            fill: false,
            label: 'Revenue'
          }
        ]
      },
      options: {
        hover: {
          mode: 'index',
          intersect: false
        },
        tooltips: {
          callbacks: {
            label: function (tooltipItem, data) {
              return '$' + Number(tooltipItem.yLabel).toFixed(2).replace(/./g, function (c, i, a) {
                return i > 0 && c !== '.' && (a.length - i) % 3 === 0 ? ',' + c : c;
              });
            }
          }
        },
        legend: {
          display: false
        },
        scales: {
          xAxes: [{
            ticks: {
              beginAtZero: true,
              callback: function (value, index, values) {
                const twoPlacedFloat = parseFloat(value).toFixed(2);
                // tslint:disable-next-line:radix
                if (parseFloat(twoPlacedFloat) >= 1000) {
                  return '$' + twoPlacedFloat.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                } else {
                  return '$' + twoPlacedFloat;
                }
              }
            }, scaleLabel: {
              display: true,
              labelString: 'Sale Price'
            }
          }],
          yAxes: [{
            ticks: {
              beginAtZero: true,
              callback: function (value, index, values) {
                const twoPlacedFloat = parseFloat(value).toFixed(0);
                // tslint:disable-next-line:radix
                if (parseFloat(twoPlacedFloat) >= 1000) {
                  return '$' + twoPlacedFloat.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                } else {
                  return '$' + twoPlacedFloat;
                }
              }
            }, scaleLabel: {
              display: true,
              labelString: 'Revenue'
            }
          }],
        }
      }
    });
  }
}
