import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoughnutComponent } from './doughnut.component';
import { ChartsModule } from 'ng2-charts/ng2-charts';

describe('DoughnutComponent', () => {
  let component: DoughnutComponent;
  let fixture: ComponentFixture<DoughnutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ChartsModule],
      declarations: [ DoughnutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoughnutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
