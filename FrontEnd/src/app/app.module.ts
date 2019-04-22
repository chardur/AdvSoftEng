import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { AlertModule } from 'ngx-bootstrap';
import {MatTabsModule} from '@angular/material/tabs';
import { AppComponent } from './app.component';
import {
  MatButtonModule,
  MatIconModule,
  MatBadgeModule,
  MatNativeDateModule,
  MatToolbarModule,
  MatButtonToggleModule,
  MatProgressSpinnerModule,
  MatFormFieldModule,
  MatSelectModule,
  MatListModule,
  MatTooltipModule,
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatSnackBarModule,
  MatBottomSheetModule,
  MatRadioModule
} from '@angular/material';
import { MatSidenavModule } from '@angular/material/sidenav';
import { AppRoutingModule } from './app.routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ChartsModule } from 'ng2-charts';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { NgxGalleryModule } from 'ngx-gallery';
import { MatAutocompleteModule } from '@angular/material/autocomplete';

import { AccountComponent } from './account/account.component';
import { SellingComponent } from './selling/selling.component';
import { BuyingComponent } from './buying/buying.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LayoutComponent } from './shared/layout/layout.component';
import { TableComponent } from './shared/table/table.component';
import { ChartComponent } from './shared/chart/chart.component';
import { LoginComponent } from './account/login/login.component';
import { InventoryComponent } from './inventory/inventory.component';
import { DoughnutComponent } from './shared/chart/doughnut.component';
import { Notfound404Component } from './session/notfound404/notfound404.component';
import { HomepageComponent } from './homepage/homepage.component';
import { HasClaimDirective } from './directives/has-claim.directive';
import { HttpInterceptorModule } from './services/http-request-interceptor.service';

import { ItemHistoryComponent } from './buying/item-history/item-history.component';
import { UsersComponent } from './shared/table/users/users.component';
import { SelleritemsComponent } from './shared/table/selleritems/selleritems.component';
import { BuyeritemsComponent } from './shared/table/buyeritems/buyeritems.component';
import { AdminComponent } from './account/admin/admin.component';
import { GalleryComponent } from './shared/gallery/gallery.component';
import { RegisterComponent } from './account/register/register.component';
import { BuyingService } from './buying/buying.service';
import { SellerInsightsComponent } from './inventory/seller-insights/seller-insights.component';
import { SellerInsightsService } from './inventory/seller-insights/seller-insights.service';
import { AddBuyItemComponent } from './buying/add-buy-item/add-buy-item.component';
import { HistoricalPriceComponent } from './inventory/historical-price/historical-price.component';

import { Top5Component } from './inventory/top5/top5.component';
import { OnboardComponent } from './account/onboard/onboard.component';
import { AddSellerItemComponent } from './inventory/add-seller-item/add-seller-item.component';
import { SetPriceComponent } from './inventory/set-price/set-price.component';
import { ImportCsvComponent } from './inventory/import-csv/import-csv.component';
import {BuyersearchComponent} from './shared/table/buyersearch/buyersearch.component';
import { LargeimgComponent } from './shared/table/buyersearch/largeimg/largeimg.component';
import { ComparepriceComponent } from './shared/table/buyersearch/compareprice/compareprice.component';
import { SellerguideComponent } from './guide/sellerguide/sellerguide.component';
import { BuyerguideComponent } from './guide/buyerguide/buyerguide.component';


@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    LayoutComponent,
    AccountComponent,
    SellingComponent,
    BuyingComponent,
    DashboardComponent,
    TableComponent,
    ChartComponent,
    RegisterComponent,
    LoginComponent,
    ChartComponent,
    InventoryComponent,
    DoughnutComponent,
    Notfound404Component,
    DoughnutComponent,
    HomepageComponent,
    HasClaimDirective,
    GalleryComponent,
    HasClaimDirective,
    ItemHistoryComponent,
    UsersComponent,
    SelleritemsComponent,
    BuyeritemsComponent,
    AdminComponent,
    RegisterComponent,
    SellerInsightsComponent,
    AddBuyItemComponent,
    HistoricalPriceComponent,
    Top5Component,
    OnboardComponent,
    AddSellerItemComponent,
    SetPriceComponent,
    ImportCsvComponent,
    BuyersearchComponent,
    LargeimgComponent,
    ComparepriceComponent,
    SellerguideComponent,
    BuyerguideComponent
  ],
  imports: [
    AlertModule.forRoot(),
    MatIconModule,
    MatCardModule,
    MatInputModule,
    MatMenuModule,
    MatDialogModule,
    MatNativeDateModule,
    MatBottomSheetModule,
    MatSidenavModule,
    MatToolbarModule,
    MatButtonToggleModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatSelectModule,
    MatListModule,
    MatSortModule,
    MatAutocompleteModule,
    MatTabsModule,
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule,
    MatTooltipModule,
    HttpClientModule,
    MatBadgeModule,
    NgxDatatableModule,
    AngularFontAwesomeModule,
    ChartsModule,
    HttpInterceptorModule,
    NgxGalleryModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSnackBarModule,
    MatRadioModule
  ],
  providers: [BuyingService, SellerInsightsService],
  bootstrap: [AppComponent],
  entryComponents: [ItemHistoryComponent, AddBuyItemComponent, AddSellerItemComponent]
})
export class AppModule { }
