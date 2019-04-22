import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ImportCsvComponent } from './import-csv.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ImportCsvComponent', () => {
  let component: ImportCsvComponent;
  let fixture: ComponentFixture<ImportCsvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ ImportCsvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImportCsvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
