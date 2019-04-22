import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyeritemsComponent } from './buyeritems.component';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import { MatTableModule} from "@angular/material";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatDialogModule} from "@angular/material";

describe('BuyeritemsComponent', () => {
  let component: BuyeritemsComponent;
  let fixture: ComponentFixture<BuyeritemsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MatTableModule, HttpClientTestingModule, MatDialogModule],
      declarations: [ BuyeritemsComponent ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuyeritemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
