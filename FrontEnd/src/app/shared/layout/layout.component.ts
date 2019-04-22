import {ChangeDetectorRef, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {AccountComponent} from '../../account/account.component';
import {MatDialog} from '@angular/material';
import {LoginComponent} from '../../account/login/login.component';
import {MediaMatcher} from '@angular/cdk/layout';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {BuyeritemsComponent} from '../table/buyeritems/buyeritems.component';
import {RESTService} from '../../services/rest.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit, OnDestroy {
  @ViewChild('AccountComponent') account: AccountComponent;
  mobileQuery: MediaQueryList;
  emailMessages = 13;
  alertMessages = 1;
  private readonly _mobileQueryListener: () => void;
  searchValue = 'Clear me';
  myControl = new FormControl();
  products: any = [];
  temp: any = [];
  options: any = [];
  filteredOptions: Observable<string[]>;
  name: string;
  isAlert = true;
  constructor(public rest: RESTService, public dialog: MatDialog, changeDetectorRef: ChangeDetectorRef, media: MediaMatcher) {
    this.mobileQuery = media.matchMedia('(max-width: 750px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);

  }
  ngOnInit(): void {
    this.getProducts();
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
  }
  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  getProducts() {
    this.products = [];
    this.rest.getExampleItems().subscribe((data: {}) => {
      console.log(data);
      this.products = data;
      this.temp = [...this.products];
      this.options = this.temp.map(a => a.name);
    });

  }
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }
  toggleAlert() {
    if (this.isAlert === true) {
      this.alertMessages--;
    } else {
      this.alertMessages++;
    }
    this.isAlert = !this.isAlert;
  }
  openLogin() {
    this.dialog.open(LoginComponent, {
      data: {

      }
    });
  }
}


