import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelleritemsComponent } from './selleritems.component';
import { NgxDatatableModule} from "@swimlane/ngx-datatable";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('SelleritemsComponent', () => {
  let component: SelleritemsComponent;
  let fixture: ComponentFixture<SelleritemsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [NgxDatatableModule, HttpClientTestingModule],
      declarations: [ SelleritemsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelleritemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
