import {Component, Input, OnInit, SimpleChanges, OnChanges} from '@angular/core';
import {RESTService} from '../../services/rest.service';
import * as tf from '@tensorflow/tfjs';

@Component({
  selector: 'app-historical-price',
  templateUrl: './historical-price.component.html',
  styleUrls: ['./historical-price.component.css']
})
export class HistoricalPriceComponent implements OnInit, OnChanges {

  @Input() itemId: number;
  @Input() divStatus: boolean;
  priceData: any = [];
  thisYearPrices: number[] = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
  date = new Date();
  monthArray: any = [];

  // tensorflow vars
  model: tf.Sequential;
  prediction: any;
  prices: any = [];

  chartOptions = {
    responsive: true
  };

  chartData = [
    {data: [33, 60, 26, 70], label: 'Last Year'},
    {data: [0, 0, 0, 0], label: 'This Year'},
    {data: [, , , , ], label: 'Forecast'}
  ];

  chartLabels = [];

  constructor(public rest: RESTService) {
  }

  ngOnInit() {
    this.setMonthArray();
    this.setChartLabels();

  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.itemId || changes.divStatus) {
      this.updateChartData();
    }
  }

  updateChartData() {
    this.getItemPrices();
  }

  setChartData() {
    this.chartData = [
      {data: [33, 60, 26, 70], label: 'Last Year'},
      {
        data: [this.thisYearPrices[this.monthArray[0]], this.thisYearPrices[this.monthArray[1]],
        this.thisYearPrices[this.monthArray[2]], this.thisYearPrices[this.monthArray[3]]],
        label: 'This Year'
      },
      {data: [, , , this.thisYearPrices[this.monthArray[3]], this.prediction], label: 'Forecast'}
    ];
  }

  getItemPrices() {
    this.rest.getItemPriceHistory(this.itemId).subscribe((data: {}) => {
      this.priceData = data;
      this.computeMonthlyAvg();
    });
  }

  setMonthArray() {
    const currentMonth = this.date.getMonth();

    switch (currentMonth) {

      case 0:
        this.monthArray[0] = 9;
        this.monthArray[1] = 10;
        this.monthArray[2] = 11;
        this.monthArray[3] = 0;
        this.monthArray[4] = 1;
        break;

      case 1:
        this.monthArray[0] = 10;
        this.monthArray[1] = 11;
        this.monthArray[2] = 0;
        this.monthArray[3] = 1;
        this.monthArray[4] = 2;
        break;

      case 2:
        this.monthArray[0] = 11;
        this.monthArray[1] = 0;
        this.monthArray[2] = 1;
        this.monthArray[3] = 2;
        this.monthArray[4] = 3;
        break;

      case 3:
        this.monthArray[0] = 0;
        this.monthArray[1] = 1;
        this.monthArray[2] = 2;
        this.monthArray[3] = 3;
        this.monthArray[4] = 4;
        break;

      case 11:
        this.monthArray[0] = currentMonth - 3;
        this.monthArray[1] = currentMonth - 2;
        this.monthArray[2] = currentMonth - 1;
        this.monthArray[3] = currentMonth;
        this.monthArray[4] = 0;
        break;

      default:
        this.monthArray[0] = currentMonth - 3;
        this.monthArray[1] = currentMonth - 2;
        this.monthArray[2] = currentMonth - 1;
        this.monthArray[3] = currentMonth;
        this.monthArray[4] = currentMonth + 1;
        break;

    }

  }

  setChartLabels() {
    const month = [];
    month[0] = 'January';
    month[1] = 'February';
    month[2] = 'March';
    month[3] = 'April';
    month[4] = 'May';
    month[5] = 'June';
    month[6] = 'July';
    month[7] = 'August';
    month[8] = 'September';
    month[9] = 'October';
    month[10] = 'November';
    month[11] = 'December';

    this.chartLabels[0] = month[this.monthArray[0]];
    this.chartLabels[1] = month[this.monthArray[1]];
    this.chartLabels[2] = month[this.monthArray[2]];
    this.chartLabels[3] = month[this.monthArray[3]];
    this.chartLabels[4] = month[this.monthArray[4]];

  }

  computeMonthlyAvg() {
    // denominator for each monthly average, counts the number of prices in each month
    let count0 = 0;
    let count1 = 0;
    let count2 = 0;
    let count3 = 0;
    let count4 = 0;
    let count5 = 0;
    let count6 = 0;
    let count7 = 0;
    let count8 = 0;
    let count9 = 0;
    let count10 = 0;
    let count11 = 0;
    let oldTotal = 0;

    // clear the prices array
    for (let i = 0; i < 11; i++) {
      this.thisYearPrices[i] = 0;
    }

    // clear the price data array
    this.prices = [];
    this.prediction = null;

    // parse the priceData and calculate average price for each month
    this.priceData.sort((a, b) => a.date.localeCompare(b.date));
    // console.log(this.priceData);

    for (const entry of this.priceData) {
      const dateString = entry.date;
      const dateSubString = new Date(dateString.substring(0, 10));
      // console.log(dateString.substring(0,10));

      // get data for tensorflow
      this.prices.push(entry.price);

      switch (dateSubString.getMonth()) {
        case 0:
          oldTotal = this.thisYearPrices[0] * count0;
          count0++;
          this.thisYearPrices[0] = (oldTotal + entry.price) / count0;
          break;
        case 1:
          oldTotal = this.thisYearPrices[1] * count1;
          count1++;
          this.thisYearPrices[1] = (oldTotal + entry.price) / count1;
          break;
        case 2:
          oldTotal = this.thisYearPrices[2] * count2;
          count2++;
          this.thisYearPrices[2] = (oldTotal + entry.price) / count2;
          break;
        case 3:
          oldTotal = this.thisYearPrices[3] * count3;
          count3++;
          this.thisYearPrices[3] = (oldTotal + entry.price) / count3;
          break;
        case 4:
          oldTotal = this.thisYearPrices[4] * count4;
          count4++;
          this.thisYearPrices[4] = (oldTotal + entry.price) / count4;
          break;
        case 5:
          oldTotal = this.thisYearPrices[5] * count5;
          count5++;
          this.thisYearPrices[5] = (oldTotal + entry.price) / count5;
          break;
        case 6:
          oldTotal = this.thisYearPrices[6] * count6;
          count6++;
          this.thisYearPrices[6] = (oldTotal + entry.price) / count6;
          break;
        case 7:
          oldTotal = this.thisYearPrices[7] * count7;
          count7++;
          this.thisYearPrices[7] = (oldTotal + entry.price) / count7;
          break;
        case 8:
          oldTotal = this.thisYearPrices[8] * count8;
          count8++;
          this.thisYearPrices[8] = (oldTotal + entry.price) / count8;
          break;
        case 9:
          oldTotal = this.thisYearPrices[9] * count9;
          count9++;
          this.thisYearPrices[9] = (oldTotal + entry.price) / count9;
          break;
        case 10:
          oldTotal = this.thisYearPrices[10] * count10;
          count10++;
          this.thisYearPrices[10] = (oldTotal + entry.price) / count10;
          break;
        case 11:
          oldTotal = this.thisYearPrices[11] * count11;
          count11++;
          this.thisYearPrices[11] = (oldTotal + entry.price) / count11;
          break;
      }
    }
    this.setChartData();
    this.trainNewModel().then(() => {
      // set the prediction in the chart
      this.setChartData();
      console.log(this.prediction);
    });
  }

  async trainNewModel(): Promise<any> {

    // shape the data for lstm
    const sampleSize = 1;
    const samplesX = [];
    const samplesY = [];
    const start = 0; // must be multiple of sample size
    const predictForward = 28; // must be multiple of sampleSize
    let j = start + predictForward;
    for (let i = start; i < this.prices.length - predictForward; i += sampleSize) {
      let chunk = this.prices.slice(i, i + sampleSize);
      samplesX.push(chunk);

      chunk = this.prices.slice(j, j + sampleSize);
      samplesY.push(chunk);
      j += sampleSize;
    }

    // setup the model
    this.model = tf.sequential();
    const learningRate = 0.05;
    const optimizerVar = tf.train.adam(learningRate);
    const tensorSamplesX = tf.tensor(samplesX, [samplesX.length, sampleSize, 1]);
    const tensorSamplesY = tf.tensor(samplesY, [samplesY.length, sampleSize, 1]);

    // layer 1
    this.model.add(tf.layers.lstm({
      units: 50,
      returnSequences: true,
      inputShape: [tensorSamplesX.shape[1], 1],
      dropout: 0.2
    }));

    // layer 2
    this.model.add(tf.layers.lstm({
      units: 50,
      returnSequences: true,
      dropout: 0.2
    }));

    // layer 3
    this.model.add(tf.layers.lstm({
      units: 50,
      returnSequences: true,
      dropout: 0.2
    }));

    // output layer
    this.model.add(tf.layers.dense({units: 1}));

    // compile and fit
    this.model.compile({loss: 'meanSquaredError', optimizer: optimizerVar});
    await this.model.fit(tensorSamplesX, tensorSamplesY, {epochs: 30, batchSize: 10});
    console.log('Model trained!');

    // predict
    const output = this.model.predict(tensorSamplesX) as any;
    this.prediction = Array.from(output.dataSync())[0];

    // stop memory leaks
    this.model.dispose();
    tf.disposeVariables();
  }
}
