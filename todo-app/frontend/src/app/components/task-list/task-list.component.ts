import { Component, OnInit } from '@angular/core';
import { Task, TaskService } from '../../services/task.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-task-list',
  imports: [NgFor],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent implements OnInit{

  tasks : Task[] = [];


  constructor(private taskService : TaskService){}


  ngOnInit(): void {
      this.getTasks();
  }


  getTasks(){
    this.taskService.getTasks().subscribe(tasks => this.tasks=tasks);
  }

  addTask(title:string , description:string) : void {
    const newTask : Task = {title,description,completed:false};
    this.taskService.createTask(newTask).subscribe(task => this.tasks.push(task));
  }

  updateTask(task:Task) : void{
    this.taskService.updateTask(task.id!,task).subscribe();
    /* RQ: subscribe() sans argument signifie : 
       "Je veux exécuter la requête, mais je m'intéresse pas à la réponse" selon la logique de mon app
       car, en fait, la mise à jour est effectuée côté serveur
       et que l'interface utilisateur n'a pas besoin de se mettre à jour en fct de la réponse

       */
  }

  deleteTask(id:number):void{
    this.taskService.deleteTask(id).subscribe(()=>this.tasks=this.tasks.filter(task=>task.id!==id));
    /*
      RQ : subscribe() avec argument
      Car, on a besoin de mettre à jour l'interface utilisateur après la suppression.
      Pour cela, on doit réagir à la réponse du serveur.

      .subscribe(()=>{...}) signifie : "Une fois que la requête est terminée, exécute ce code"
    */
  }

}
