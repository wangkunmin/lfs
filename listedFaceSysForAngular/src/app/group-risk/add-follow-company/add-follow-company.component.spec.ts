import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFollowCompanyComponent } from './add-follow-company.component';

describe('AddFollowCompanyComponent', () => {
  let component: AddFollowCompanyComponent;
  let fixture: ComponentFixture<AddFollowCompanyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFollowCompanyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFollowCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
