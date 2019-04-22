import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Notfound404Component } from './notfound404.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

describe('Notfound404Component', () => {
  let component: Notfound404Component;
  let fixture: ComponentFixture<Notfound404Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Notfound404Component ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Notfound404Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
