import { Evento } from './../../../../shared/models/evento.model';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventoService } from '../../../../core/services/evento.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-evento-detail',
  standalone: false,
  templateUrl: './evento-detail.component.html',
  styleUrls: ['./evento-detail.component.scss']
})
export class EventoDetailComponent implements OnInit {
  evento: Evento | null = null;
  isLoading = false;
  notFound = false;
  errorMessage: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private eventoService: EventoService,
    private cdr: ChangeDetectorRef,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.params['id'];
    this.isLoading = true;
    this.notFound = false;
    this.errorMessage = null;
    this.cdr.detectChanges();

    this.eventoService.getEventoById(id).subscribe({
      next: (evento) => {
        this.evento = evento;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.isLoading = false;
        this.cdr.detectChanges();
        console.log('Erro ao carregar o evento:', err);

        if (err.status === 404) {
          this.notFound = true;
          this.errorMessage = 'O evento que você procura não foi encontrado.';

          this.snackBar.open(this.errorMessage, 'Fechar', { duration: 3000 }); 
          
          setTimeout(() => {
            this.router.navigate(['/events']);
          }, 3000); 
        } else {
          this.errorMessage = 'Ocorreu um erro ao carregar o evento. Tente novamente mais tarde.';
          this.snackBar.open(this.errorMessage, 'Fechar', { duration: 5000 });
          this.router.navigate(['/events']); 
        }
        this.cdr.detectChanges();
      }
    });
  }
}