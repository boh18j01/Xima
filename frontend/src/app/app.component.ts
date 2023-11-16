import { Component } from '@angular/core';
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-prototype';
  constructor(private keyClockSer: KeycloakService) {
  }
  onLogout() {
    this.keyClockSer.logout().then(() => this.keyClockSer.clearToken());
  }
}

