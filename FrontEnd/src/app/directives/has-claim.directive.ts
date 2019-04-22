import { Directive, TemplateRef, ViewContainerRef, Input } from '@angular/core';
import { UserAuthentication } from '../models/user-authentication';
import { Role } from '../models/role';

@Directive({
  // tslint:disable-next-line:directive-selector
  selector: '[hasClaim]'
})
export class HasClaimDirective {

  constructor(private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef) { }

  @Input() set hasClaim(claimType: any) {
    const userObject = localStorage.getItem('roles');
    if (!userObject) {
      this.viewContainer.clear();
    } else if (this.securityObjectHasClaim(claimType, JSON.parse(userObject))) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }

  securityObjectHasClaim(claim: string, roles: Role[]) {
    if (roles === null) {
      return false;
    }
    const matchingClaim = roles.find(x => x.role === claim);
    const matchingAdmin = roles.find(x => x.role === 'Admin');
    if (matchingClaim === undefined && matchingAdmin === undefined) {
      return false;
    }
    return true;
  }
}
