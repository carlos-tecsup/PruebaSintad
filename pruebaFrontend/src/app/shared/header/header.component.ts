import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  user!: User;

  constructor(private userService: UserService) {
    this.userService.user.subscribe(x => this.user = x);
   }

  ngOnInit(): void {
  }
}
