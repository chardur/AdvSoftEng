import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { BuyingComponent } from './buying/buying.component';
import { SellingComponent } from './selling/selling.component';
import { AccountComponent } from './account/account.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { LayoutComponent } from './shared/layout/layout.component';
import { LoginComponent } from './account/login/login.component';
import { InventoryComponent } from './inventory/inventory.component';
import { Notfound404Component } from './session/notfound404/notfound404.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AdminComponent } from './account/admin/admin.component';
import { ItemHistoryComponent } from './buying/item-history/item-history.component';
import { Register } from 'ts-node';
import { RegisterComponent } from './account/register/register.component';
import { OnboardComponent } from './account/onboard/onboard.component';
import { LargeimgComponent } from './shared/table/buyersearch/largeimg/largeimg.component';
import { ComparepriceComponent } from './shared/table/buyersearch/compareprice/compareprice.component';
import { SellerguideComponent } from "./guide/sellerguide/sellerguide.component";
import { BuyerguideComponent } from "./guide/buyerguide/buyerguide.component";


const routes: Routes = [{
  path: '',
  redirectTo: 'homepage',
  pathMatch: 'full'
}, {
  path: '',
  component: LayoutComponent,
  children: [{
    path: 'dashboard',
    component: DashboardComponent,
    data: { title: 'Dashboard' }
  },
  {
    path: 'account',
    component: AccountComponent,
    data: { title: 'Account' }
  }, {
    path: 'buying',
    component: BuyingComponent,
    data: { title: 'Buying' }
  }, {
    path: 'selling',
    component: SellingComponent,
    data: { title: 'Selling' }
  }, {
    path: 'inventory',
    component: InventoryComponent,
    data: { title: 'Inventory' }
  }, {
    path: 'admin',
    component: AdminComponent,
    data: { title: 'Admin' }
  }, {
    path: 'buying',
    component: BuyingComponent,
    data: { title: 'Buying' }
  }, {
    path: 'itemHistory',
    component: ItemHistoryComponent,
    data: { title: 'Item History' }
  }, {
    path: 'selling',
    component: SellingComponent,
    data: { title: 'Selling' }
  }, {
    path: 'inventory',
    component: InventoryComponent,
    data: { title: 'Inventory' }
  }, {
    path: 'sellerguide',
    component: SellerguideComponent,
    data: { title: 'Seller Guide' }
  }, {
    path: 'buyerguide',
    component: BuyerguideComponent,
    data: { title: 'Buyer Guide' }
  }],
},
{
  path: 'largeimg',
  component: LargeimgComponent,
  data: { title: 'Large Image' }
},
{
  path: 'dashboard',
  component: LayoutComponent,
  data: { title: 'Dashboard' }
},
{
  path: 'compareprice',
  component: ComparepriceComponent,
  data: { title: 'Large Image' }
},
{
  path: 'register',
  component: RegisterComponent,
  data: { title: 'Register' }
},
{
  path: 'account/login',
  component: LoginComponent,
  data: { title: 'Login' }
}, {
  path: 'onboard',
  component: OnboardComponent,
  data: { title: 'Onboarding' }
},
{
  path: 'homepage',
  component: HomepageComponent,
  data: { title: 'Shmoozed' }
}, {
  path: '**',
  component: Notfound404Component,
  data: { title: 'Not Found 404' }
}];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    FormsModule,
    BrowserModule,
    HttpClientModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
