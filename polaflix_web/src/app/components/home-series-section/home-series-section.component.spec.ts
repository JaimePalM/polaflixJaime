import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeSeriesSectionComponent } from './home-series-section.component';

describe('HomeSeriesSectionComponent', () => {
  let component: HomeSeriesSectionComponent;
  let fixture: ComponentFixture<HomeSeriesSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeSeriesSectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeSeriesSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
