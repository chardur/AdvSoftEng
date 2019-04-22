import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyerguideComponent } from './buyerguide.component';

describe('BuyerguideComponent', () => {
  let component: BuyerguideComponent;
  let fixture: ComponentFixture<BuyerguideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuyerguideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuyerguideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
