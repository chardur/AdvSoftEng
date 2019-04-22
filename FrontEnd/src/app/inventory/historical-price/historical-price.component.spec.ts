import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoricalPriceComponent } from './historical-price.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('HistoricalPriceComponent', () => {
  let component: HistoricalPriceComponent;
  let fixture: ComponentFixture<HistoricalPriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ChartsModule, HttpClientTestingModule],
      declarations: [ HistoricalPriceComponent ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoricalPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
