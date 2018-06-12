import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskContentComponent } from './risk-content.component';

describe('RiskContentComponent', () => {
  let component: RiskContentComponent;
  let fixture: ComponentFixture<RiskContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiskContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiskContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
