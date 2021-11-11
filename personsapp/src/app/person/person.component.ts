import { Component, OnInit } from '@angular/core';
import {PersonService} from "../person.service";

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})

export class PersonComponent implements OnInit {

  newPerson: any;

  persons: any[];

  buttonString = "";

  assets: boolean = false;

  assetHolder: any;

  newAsset: any;

  removeAsset: any;

  constructor(private personService: PersonService) {
    this.persons = [];
  }

  ngOnInit(): void {
    this.getPersons();
    this.addPerson();
    this.buttonString = "Add person";
    this.assets = false;
  }

  getPersons(): void {
    this.persons = [];
    this.personService.getPersonList().then((result: any) => {
      if(result != null) {
        this.persons = result;
        console.log(this.persons);
      }
    });
  }

  addPerson(): void {
    this.newPerson = {
      name: null,
      dob: null,
      hobby: null,
      id: null,
      assets: []
    };
  }

  addAsset(): void {
    this.newAsset = {
      id: null,
      name: null,
      amount: null
    }
  }

  deleteAsset(id: number): void {
    this.personService.deleteAsset(id).then(() => {
      this.ngOnInit();
      console.log('Successfully deleted asset');
    });
  }

  createPerson(): void {
    this.personService.addPerson(this.newPerson).then(() => {
      console.log('Successfully added a person to the list');
      this.ngOnInit();
    });
  }

  deletePerson(id: number): void {
    this.personService.deletePerson(id).then(() => {
      console.log('Successfully deleted a person from the list');
      this.ngOnInit();
    });
  }

  editPerson(id: number, name: string, dob: number, hobby: string, assets: any): void {
    this.newPerson = {
      name: name,
      dob: dob,
      hobby: hobby,
      id: id,
      assets: assets
    };
    this.buttonString = "Edit person";
  }

  showAssets(person: any): void {
    this.assets = true;
    this.assetHolder = person;
    this.addAsset();
  }

  hideAssets(): void {
    this.assets = false;
  }

  createAsset(): void {
    this.assetHolder.assets.push(this.newAsset);
    this.personService.addPerson(this.assetHolder).then(() => {
      console.log('Successfully added a person to the list');
    });
    this.addAsset();
  }
}
