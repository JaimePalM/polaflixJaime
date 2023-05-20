import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SerieService } from 'src/app/services/serie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-serie',
  templateUrl: './serie.component.html',
  styleUrls: ['./serie.component.css']
})
export class SerieComponent implements OnInit {

  serie: any = {};
  views: any = {};
  currentSeason: number = 1;
  descriptionView: boolean[] = [];

  constructor(private serieService: SerieService, private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const serieId = this.route.snapshot.paramMap.get('serieId');

    this.serieService.getSerie(Number(serieId)).subscribe(serie => {
      this.serie = serie;
      console.log(serie);
    })

    this.userService.getViews(1, Number(serieId)).subscribe(views => {
      this.views = views; 
      console.log(views);
    })

    this.userService.getLastChapterViewed(1, Number(serieId)).subscribe(response => {
      this.currentSeason = response.season.number;
    });

    this.descriptionView = [];

  }

  sortedChapters(chapters: any[]): any[] {
    return chapters.sort((a, b) => a.number - b.number);
  }

  changeSeason(seasonNumber: number): void {
    this.currentSeason = seasonNumber;
  }

  showDescription(chapter: any): void {
    this.descriptionView[chapter.number - 1] = !this.descriptionView[chapter.number - 1];
  }

  viewChapter(serie: any, season: any, chapter: any): void {
    this.userService.viewChapter(1, serie.id, season.number, chapter.number).subscribe(response => {
      this.views = response;
    });
  }

}
