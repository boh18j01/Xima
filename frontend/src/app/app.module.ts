import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {ProductListComponent} from "./products/product-list/product-list.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {ProductCategoryMenuComponent} from "./products/product-category-menu/product-category-menu.component";
import {CartStatusComponent} from "./cart/cart-status/cart-status.component";
import {CheckoutComponent} from "./checkout/checkout.component";
import {ProductSearchComponent} from "./products/product-search/product-search.component";
import {ProductDetailsComponent} from "./products/product-details/product-details.component";
import {CartDetailsComponent} from "./cart/cart-details/cart-details.component";
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'prototype-realm',
        clientId: 'prototype-app'
      },
      initOptions: {
        onLoad: 'login-required',
        checkLoginIframe: true
      }
    });
}

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    PageNotFoundComponent,
    ProductCategoryMenuComponent,
    ProductSearchComponent,
    ProductDetailsComponent,
    CartStatusComponent,
    CartDetailsComponent,
    CheckoutComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    KeycloakAngularModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
