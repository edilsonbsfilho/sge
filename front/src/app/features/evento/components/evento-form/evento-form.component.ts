import { Component, OnInit, ViewEncapsulation, ChangeDetectorRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventoService } from '../../../../core/services/evento.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Evento } from './../../../../shared/models/evento.model';

@Component({
  selector: 'app-evento-form',
  standalone: false,
  templateUrl: './evento-form.component.html',
  styleUrls: ['./evento-form.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class EventoFormComponent implements OnInit {
  eventoForm: FormGroup;
  isEditMode = false;
  eventoId: number | null = null;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private eventoService: EventoService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef,
  ) {  
    this.eventoForm = this.fb.group({
      titulo: ['', [Validators.required, Validators.maxLength(100)]],
      descricao: ['', [Validators.required, Validators.maxLength(1000)]],
      dataHoraEvento: [null, [Validators.required]],
      horaEvento: ['', [Validators.required, Validators.pattern(/^([01]\d|2[0-3]):([0-5]\d)$/)]],
      local: ['', [Validators.required, Validators.maxLength(200)]]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.eventoId = +params['id'];
        this.loadEvento();
      } else {
        const today = new Date();
        today.setHours(0, 0, 0, 0); 
        this.eventoForm.get('dataHoraEvento')?.setValue(today);

        const now = new Date();
        const currentHours = now.getHours().toString().padStart(2, '0');
        const currentMinutes = now.getMinutes().toString().padStart(2, '0');
        this.eventoForm.get('horaEvento')?.setValue(`${currentHours}:${currentMinutes}`);
      }
    });
  }

  loadEvento(): void {
    if (this.eventoId) {
      this.isLoading = true;
      this.cdr.detectChanges();

      this.eventoService.getEventoById(this.eventoId).subscribe({
        next: (evento) => {
          console.log('Evento carregado do backend:', evento);
          const loadedDate = new Date(evento.dataHoraEvento); 
          let dataHoraEventoDate = new Date(loadedDate.getFullYear(), loadedDate.getMonth(), loadedDate.getDate());
          const hours = loadedDate.getHours().toString().padStart(2, '0');
          const minutes = loadedDate.getMinutes().toString().padStart(2, '0');
          let horaEventoString = `${hours}:${minutes}`;

          this.eventoForm.patchValue({
            titulo: evento.titulo,
            descricao: evento.descricao,
            dataHoraEvento: dataHoraEventoDate, 
            horaEvento: horaEventoString,
            local: evento.local
          });
          this.isLoading = false;
        },
        error: () => {
          this.isLoading = false;
        },
        complete: () => {
          this.cdr.detectChanges();
        }
      });
    }
  }

  onSubmit(): void {
    console.log(this.eventoForm.value.dataHoraEvento);
    if (this.eventoForm.valid) {
      this.isLoading = true;

      const dataHoraEventoValue = this.eventoForm.value.dataHoraEvento;
      const horaEventoValue = this.eventoForm.value.horaEvento;
      
      const dataHoraEventoISO = this.obterDataISO(dataHoraEventoValue, horaEventoValue);

      const evento:Evento = {
        titulo: this.eventoForm.value.titulo,
        descricao: this.eventoForm.value.descricao,
        dataHoraEvento: dataHoraEventoISO,
        local: this.eventoForm.value.local,
      };
      
      const request = this.isEditMode && this.eventoId
        ? this.eventoService.updateEvento({id: this.eventoId, ...evento})
        : this.eventoService.createEvento(evento);

      request.subscribe({
        next: () => {
          this.isLoading = false;
          this.router.navigate(['/events']);
        },
        error: () => {
          this.isLoading = false;
        }
      });
    }
  }

  private obterDataISO(dataHoraEventoValue:Date, horaEventoValue:string): string {

    const [hoursStr, minutesStr] = horaEventoValue.split(':');
    const hours = parseInt(hoursStr, 10);
    const minutes = parseInt(minutesStr, 10);

    const combinedLocalDate = new Date(
        dataHoraEventoValue.getFullYear(),
        dataHoraEventoValue.getMonth(),
        dataHoraEventoValue.getDate(),
        hours,
        minutes,
        0, 
        0  
    );

    const utcDate = new Date(Date.UTC(
          combinedLocalDate.getFullYear(),
          combinedLocalDate.getMonth(),
          combinedLocalDate.getDate(),
          combinedLocalDate.getHours(),
          combinedLocalDate.getMinutes(),
          combinedLocalDate.getSeconds(),
          combinedLocalDate.getMilliseconds()
        ));
        
    return utcDate.toISOString();
  }
}