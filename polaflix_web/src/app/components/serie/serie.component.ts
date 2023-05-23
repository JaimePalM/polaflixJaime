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
  userId: number = 1;
  serieId: number = 1;

  constructor(private serieService: SerieService, private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    
    this.route.params.subscribe(params => {
      this.userId = params['userId'];
      this.serieId = params['serieId'];
    });

    this.serieService.getSerie(this.serieId).subscribe(serie => {
      this.serie = serie;
      console.log(serie);
    })

    this.userService.getViews(this.userId, this.serieId).subscribe(views => {
      this.views = views; 
      console.log(views);
    })

    this.userService.getLastChapterViewed(this.userId, this.serieId).subscribe(response => {
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
    this.userService.viewChapter(this.userId, serie.id, season.number, chapter.number).subscribe(response => {
      this.views = response;
    });
  }

}
