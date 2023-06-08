import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-home-series-section',
  templateUrl: './home-series-section.component.html',
  styleUrls: ['./home-series-section.component.css']
})
export class HomeSeriesSectionComponent {

  @Input() title: string = '';
  @Input() series: any[] = [];
  @Input() userId: number = 1;

}
