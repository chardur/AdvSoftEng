import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Top5Component } from './top5.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('Top5Component', () => {
  let component: Top5Component;
  let fixture: ComponentFixture<Top5Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ Top5Component ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Top5Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
