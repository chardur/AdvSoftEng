import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material';
import {LoginComponent} from './login/login.component';
import { UserAuthentication } from '../models/user-authentication';

export interface DialogData {
  animal: 'panda' | 'unicorn' | 'lion';
}


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  model: any = {};
  loading = false;

  animal: string;
  name: string;
  firstName: string;
  lastName: string;
  username: string;
  password: string;

  constructor() {}

  ngOnInit(): void {
  }

}
