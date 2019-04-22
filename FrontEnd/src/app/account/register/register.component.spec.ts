import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterComponent } from './register.component';
import { User } from 'src/app/models/user';
import { environment } from 'src/environments/environment';
import { of } from 'rxjs';

describe('RegisterComponent', () => {
  let http;
  let snackBar;
  let registerComponent: RegisterComponent;

  beforeEach(() => {
    http = jasmine.createSpyObj(['post']);
    snackBar = jasmine.createSpyObj(['open']);
    registerComponent = new RegisterComponent(http, snackBar);
  });

  it('should call register endpoint with correct user', () => {
    const user = { firstName: 'Test', lastName: 'User', email: 'test@email.com', password: 'password', username: 'testuser' };
    http.post.and.returnValue(of());
    const apiLocation = environment.baseUrl + 'user';

    registerComponent.register();

    expect(http.post).toHaveBeenCalled();
  });
});
