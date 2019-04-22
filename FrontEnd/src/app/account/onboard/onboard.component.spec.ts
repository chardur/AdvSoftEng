import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OnboardComponent } from './onboard.component';
import { AccountComponent} from "../account.component";
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";

describe('OnboardComponent', () => {
  let component: OnboardComponent;
  let fixture: ComponentFixture<OnboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OnboardComponent, AccountComponent],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OnboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
