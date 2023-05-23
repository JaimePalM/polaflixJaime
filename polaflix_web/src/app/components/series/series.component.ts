import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SerieService } from 'src/app/services/serie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-series',
  templateUrl: './series.component.html',
  styleUrls: ['./series.component.css']
})
export class SeriesComponent {

  alphabet: string[] = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('');
  series: any[] = [];
  pendingSeries: any[] = [];
  selectedInitial: string = '';
  searchText: string = '';
  descriptionView: boolean[] = [];
  userId: number = 1;

  constructor(private serieService: SerieService, private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.userId = params['userId'];
    });

    this.serieService.getSeries().subscribe(series => {
      this.series = series;
      this.sortSeries();
      this.userService.getPendingSeries(this.userId).subscribe(pendingSeries => {
        this.pendingSeries = pendingSeries;
      });
    });
  }

  changeInitial(initial: string): void {
    this.selectedInitial = initial;
    this.serieService.getSeriesByInitial(initial).subscribe(series => {
      this.series = series;
      this.userService.getPendingSeries(this.userId).subscribe(pendingSeries => {
        this.pendingSeries = pendingSeries;
      })
    })
  }

  clearFilter(): void {
    this.selectedInitial = '';
    this.serieService.getSeries().subscribe(response => {
      this.series = response;
      this.sortSeries();
      this.userService.getPendingSeries(this.userId).subscribe(response => {
        this.pendingSeries = response;
      });
    });
  }

  search(searchText: string) {
    console.log(searchText);
    this.serieService.getSeriesByInitial(searchText.charAt(0)).subscribe(response => {
      this.series = response; 
      this.sortSeries(); 
        this.series.forEach(serie => {
        serie.exactMatch = serie.title.toLowerCase() === this.searchText.toLowerCase();
      });
  
    });
  }

  addPending(serie: any) {
    this.userService.addPendingSerie(this.userId, serie.id).subscribe(response => {
      this.pendingSeries = response;
    });
  }

  showDescription(serie: any): void {
    this.descriptionView[serie.id] = !this.descriptionView[serie.id];
  }

  sortSeries(): void {
    if (Array.isArray(this.series)) {
      this.series = this.series.sort((a, b) => {
        if (a.title < b.title) {
          return -1;
        }
        if (a.title > b.title) {
          return 1;
        }
        return 0;
      });
    }
  }

  isSeriePending(serie: any): boolean {
    return this.pendingSeries.some((ps: { id: any; }) => ps.id === serie.id);
  }

}