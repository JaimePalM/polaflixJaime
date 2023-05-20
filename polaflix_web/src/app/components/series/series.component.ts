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

  constructor(private serieService: SerieService, private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.serieService.getSeries().subscribe(series => {
      this.series = series;
      this.sortSeries();
      this.userService.getPendingSeries(1).subscribe(pendingSeries => {
        this.pendingSeries = pendingSeries;
        //this.series = this.series.filter(s => !this.pendingSeries.some(ps => ps.id == s.id));
      });
    });
  }

  changeInitial(initial: string): void {
    this.selectedInitial = initial;
    this.serieService.getSeriesByInitial(initial).subscribe(series => {
      this.series = series;
      this.userService.getPendingSeries(1).subscribe(pendingSeries => {
        this.pendingSeries = pendingSeries;
        //this.series = this.series.filter(s => !this.pendingSeries.some(ps => ps.id == s.id));
      })
    })
  }

  clearFilter(): void {
    this.selectedInitial = '';
    this.serieService.getSeries().subscribe(response => {
      this.series = response;
      this.sortSeries();
      this.userService.getPendingSeries(1).subscribe(response => {
        this.pendingSeries = response;
        //this.series = this.series.filter(s => !this.pendingSeries.some(ps => ps.id == s.id));
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
  

/*   search(searchText: string) {
    console.log(searchText);
    this.serieService.getSeriesByInitial(searchText.charAt(0)).subscribe(response => {
      this.series = response;
      console.log(this.series);
      this.userService.getPendingSeries(1).subscribe(response => {
        this.pendingSeries = response;
        //this.series = this.series.filter(s => !this.pendingSeries.some(ps => ps.id == s.id));
      });
    });
  } */

  addPending(serie: any) {
    this.userService.addPendingSerie(1, serie.id).subscribe(response => {
      this.pendingSeries = response;
      //this.series = this.series.filter(s => !this.pendingSeries.some(ps => ps.id == s.id));
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