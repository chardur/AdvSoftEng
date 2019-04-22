import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerguideComponent } from './sellerguide.component';

describe('SellerguideComponent', () => {
  let component: SellerguideComponent;
  let fixture: ComponentFixture<SellerguideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SellerguideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerguideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
