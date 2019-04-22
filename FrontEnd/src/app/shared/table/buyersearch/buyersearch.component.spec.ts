import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { BuyersearchComponent } from './buyersearch.component';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import { AngularFontAwesomeModule} from "angular-font-awesome";
import {MatTableModule, MatTooltipModule, MatDialogModule, MatBottomSheetModule} from "@angular/material";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";

describe('BuyersearchComponent', () => {
  let component: BuyersearchComponent;
  let fixture: ComponentFixture<BuyersearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [AngularFontAwesomeModule, MatTableModule, MatTooltipModule,
        HttpClientTestingModule, MatDialogModule, MatBottomSheetModule, RouterTestingModule],
      declarations: [ BuyersearchComponent ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuyersearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
